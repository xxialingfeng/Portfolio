package edu.northeastern.group_project_team12;

import java.util.ArrayList;

public class User {
    private String username;
    private long lastVisitedEpochSecond;
    public Integer sentCount;
    public ArrayList<String> sendHistory;

    public User() {

    }

    public User(String username, long lastVisitedEpochSecond) {
        this.username = username;
        this.lastVisitedEpochSecond = lastVisitedEpochSecond;
        this.sentCount = 0;
        this.sendHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSentCount() {
        return sentCount;
    }

    public ArrayList<String> getSendHistory() {
        return sendHistory;
    }

    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }

    public long getLastVisitedEpochSecond() {
        return lastVisitedEpochSecond;
    }

    public void setLastVisitedEpochSecond(long lastVisitedEpochSecond) {
        this.lastVisitedEpochSecond = lastVisitedEpochSecond;
    }

    public void setSendHistory(ArrayList<String> sendHistory) {
        this.sendHistory = sendHistory;
    }
}
