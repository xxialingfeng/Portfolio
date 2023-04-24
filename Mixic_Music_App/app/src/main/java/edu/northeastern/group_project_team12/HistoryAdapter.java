package edu.northeastern.group_project_team12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<String> mHistoryList;

    public HistoryAdapter(List<String> historyList) {
        mHistoryList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.historyTextView.setText(mHistoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return mHistoryList.size();
    }

    // Delete a record and update the adapter
    public void deleteRecord(int position) {
        mHistoryList.remove(position);
        notifyItemRemoved(position);
        // TODO: Implement the logic to delete the record from the cloud
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView historyTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historyTextView = itemView.findViewById(R.id.history_text_view);
        }
    }
}
