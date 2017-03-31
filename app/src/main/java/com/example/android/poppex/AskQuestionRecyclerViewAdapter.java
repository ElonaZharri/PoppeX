package com.example.android.poppex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AskQuestionRecyclerViewAdapter extends RecyclerView.Adapter<AskQuestionRecyclerViewAdapter.ViewHolder> {

    private List<AskQuestion> askQuestions;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        TextView descriptionTextView;
        private AskQuestion askQuestion;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.question_title);
            descriptionTextView = (TextView) itemView.findViewById(R.id.question_description);
            itemView.setOnClickListener(this);
        }

        public void bind(AskQuestion askQuestion) {
            this.askQuestion = askQuestion;
            titleTextView.setText(askQuestion.getQuestionTitle());
            descriptionTextView.setText(askQuestion.getQuestionDescription());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            context.startActivity(AskQuestionActivity.newInstance(context, askQuestion));
        }
    }

    public AskQuestionRecyclerViewAdapter(List<AskQuestion> askQuestions) {
        this.askQuestions = askQuestions;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.postedquestion_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(askQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return askQuestions.size();
    }

    public void updateList(List<AskQuestion> askQuestions) {
        // Allow recyclerview animations to complete normally if we already know about data changes
        if (askQuestions.size() != this.askQuestions.size() || !this.askQuestions.containsAll(askQuestions)) {
            this.askQuestions = askQuestions;
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        askQuestions.remove(position);
        notifyItemRemoved(position);
    }

    public AskQuestion getItem(int position) {
        return askQuestions.get(position);
    }
}
