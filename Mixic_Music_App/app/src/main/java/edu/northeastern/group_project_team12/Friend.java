package edu.northeastern.group_project_team12;

public class Friend {
    private String name;
    private String imageUrl;

    public Friend(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}