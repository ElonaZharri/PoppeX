package com.example.android.poppex;

import android.os.Parcel;
import android.os.Parcelable;

public class AskQuestion implements Parcelable {

    private String userId;
    private String questionTitle;
    private String questionDescription;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AskQuestion() {
    }

    protected AskQuestion(Parcel in) {
        this.userId = in.readString();
        this.questionTitle = in.readString();
        this.questionDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.questionTitle);
        dest.writeString(this.questionDescription);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AskQuestion> CREATOR = new Creator<AskQuestion>() {
        @Override
        public AskQuestion createFromParcel(Parcel in) {
            return new AskQuestion(in);
        }

        @Override
        public AskQuestion[] newArray(int size) {
            return new AskQuestion[size];
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AskQuestion question = (AskQuestion) obj;

        if (userId != null ? !userId.equals(question.userId) : question.userId != null)
            return false;
        if (questionTitle != null ? !questionTitle.equals(question.questionTitle) : question.questionTitle != null)
            return false;
        return questionDescription != null ? questionDescription.equals(question.questionDescription) : question.questionDescription == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
