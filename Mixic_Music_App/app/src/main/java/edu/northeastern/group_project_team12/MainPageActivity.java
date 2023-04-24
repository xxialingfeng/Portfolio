package edu.northeastern.group_project_team12;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainPageActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mTabMusic;
    private LinearLayout mTabfrd;
    private LinearLayout mTabRecord;
    private LinearLayout mTabMe;

    private ImageButton mImgMusic;
    private ImageButton mImgFrd;
    private ImageButton mImgRecord;
    private ImageButton mImgMe;

    private android.app.Fragment mTab05;
    private android.app.Fragment mTab01 = new MusicFragment();
    private android.app.Fragment mTab02 = new frdFragment();
    private android.app.Fragment mTab03 = new recordFragment();
    private android.app.Fragment mTab04 = new meFragment();

    private TextView textView1,textView2,textView3,textView4;
    private android.app.FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_page);

        mTabMusic = findViewById(R.id.id_tab_music);
        mTabfrd = findViewById(R.id.id_tab_frd);
        mTabRecord = findViewById(R.id.id_tab_record);
        mTabMe = findViewById(R.id.id_tab_me);

        initView();
        initEvent();
        initFragment();
        selectfragment(0);
    }
    private void initFragment(){
        fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content,mTab01);
        transaction.add(R.id.id_content,mTab02);
        transaction.add(R.id.id_content,mTab03);
        transaction.add(R.id.id_content,mTab04);
        transaction.commit();
    }

    private void initEvent(){
        mTabMusic.setOnClickListener((View.OnClickListener) this);
        mTabfrd.setOnClickListener((View.OnClickListener) this);
        mTabRecord.setOnClickListener((View.OnClickListener) this);
        mTabMe.setOnClickListener((View.OnClickListener) this);
    }

    private void initView() {
        mTabMusic = (LinearLayout)findViewById(R.id.id_tab_music);
        mTabfrd = (LinearLayout)findViewById(R.id.id_tab_frd);
        mTabRecord = (LinearLayout)findViewById(R.id.id_tab_record);
        mTabMe = (LinearLayout)findViewById(R.id.id_tab_me);

        mImgMusic = (ImageButton)findViewById(R.id.id_tab_music_img);
        mImgFrd = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mImgRecord = (ImageButton)findViewById(R.id.id_tab_record_img);
        mImgMe = (ImageButton)findViewById(R.id.id_tab_me_img);
    }

    private void selectfragment(int i){
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i){
            case 0:
                Log.d("setSelect","1");
                transaction.show(mTab01);
                break;
            case 1:
                transaction.show(mTab02);
                break;
            case 2:
                transaction.show(mTab03);
                break;
            case 3:
                transaction.show(mTab04);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.id_tab_music:
                selectfragment(0);
                break;
            case R.id.id_tab_frd:
                selectfragment(1);
                break;
            case R.id.id_tab_record:
                selectfragment(2);
                break;
            case R.id.id_tab_me:
                selectfragment(3);
                break;
        }
    }
}
