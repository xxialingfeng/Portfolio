package edu.northeastern.group_project_team12;

import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bullhead.equalizer.EqualizerFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link recordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SharedPreferences globalLoginData;
    private ImageButton deleteButton;
    private ImageButton pauseButton;
    private ImageButton playButton;
    private ImageButton startButton;
    private ImageView recordingImage;
    private MediaRecorder recorder;
    private MediaPlayer player;
    private String filename;

    private SimpleDateFormat formatter;
    private Date now;
    private Chronometer timer;

    private FrameLayout eqFrame;
    private int sessionId;
    private EqualizerFragment equalizerFragment;
    File file;
    Boolean isPlay = false;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public recordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab03, container, false);
        return view;
    }


    public static recordFragment newInstance(String param1, String param2) {
        recordFragment fragment = new recordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        globalLoginData = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        deleteButton = view.findViewById(R.id.deleteButton);
        pauseButton = view.findViewById(R.id.pauseButton);
        startButton = view.findViewById(R.id.startButton);
        playButton = view.findViewById(R.id.playButton);
        recordingImage = view.findViewById(R.id.recordingImage);
        timer = view.findViewById(R.id.timer);
        //set equalizer layout
        eqFrame = view.findViewById(R.id.equalizerContainer);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording(v);
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecording();
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay) {
                    stopPlay();
                } else {
                    startPlay();
                    openEqualizer();

                }
            }
        });
    }

    public void openEqualizer(){
        Intent intent = new Intent(getActivity(), Equalizer.class);
        intent.putExtra("data_key", sessionId);
        startActivity(intent);
    }



    private void startPlay() {
        if (!isPlay) {
            isPlay = true;
            timer.start();
            player = new MediaPlayer();
            try {
                player.setDataSource(getRecordingFilePath());
                player.prepare();
                player.start();
            } catch (Exception e) {
                Log.e("playing audio record", e.getMessage());
            }
            sessionId = player.getAudioSessionId();
        }
    }

    private void stopPlay() {
        if (isPlay) {
            isPlay = false;
            timer.stop();
            timer.setBase(SystemClock.elapsedRealtime());
            player.stop();
            player.release();
            player = null;
        }
    }

    private void stopRecording() {
        timer.stop();
        timer.setBase(SystemClock.elapsedRealtime());
        recordingImage.setImageDrawable(getResources().getDrawable(R.drawable.record_inactive));
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;

        uploadAudio();
    }

    private void deleteRecording() {
        timer.stop();
        timer.setBase(SystemClock.elapsedRealtime());
        recordingImage.setImageDrawable(getResources().getDrawable(R.drawable.record_inactive));
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;
    }

    public void startRecording(View v) {
        if (checkPermission()) {
            try {
                timer.start();
                recordingImage.setImageDrawable(getResources().getDrawable(R.drawable.record_active));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    recorder = new MediaRecorder(v.getContext());
                } else {
                    recorder = new MediaRecorder();
                }

                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                recorder.setOutputFile(getRecordingFilePath());
                recorder.prepare();
                recorder.start();

            } catch (Exception e) {
                Log.e("start recording", e.getMessage());
            }
        }
    }

    // check recorder permission
    private boolean checkPermission() {
        int permission = ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.RECORD_AUDIO);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.RECORD_AUDIO}, 0);
        return false;
    }

    // upload audio to firebase
    private void uploadAudio() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String username = globalLoginData.getString("username", "unknownUser");
            if (username.isEmpty()) {
                return;
            }

            try {
                Uri audioUri = Uri.fromFile(new File(file.getPath()));
                StorageReference storageReference = storage.getReference().child("username/" + filename);
                UploadTask uploadTask = storageReference.putFile(audioUri);

                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.d("upload audio", "File uploaded successfully" + " " + file.getPath() + filename);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("upload audio", e.getMessage() + " " + file.getPath() + filename);
                    }
                });
            } catch (Exception e) {
                Log.e("upload audio", e.getMessage() + " " + file.getPath() + filename);

            }
        }
    }

        // get file path to store the recording
        private String getRecordingFilePath () {
            formatter = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss", Locale.getDefault());
            now = new Date();
            filename = String.format("New_Recording_%s.3gp", formatter.format(now));

            ContextWrapper contextWrapper = new ContextWrapper(getContext());
            File recording = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            file = new File(recording, filename);

            Log.d("recording path", file.getPath());

            return file.getPath();
        }
    }
