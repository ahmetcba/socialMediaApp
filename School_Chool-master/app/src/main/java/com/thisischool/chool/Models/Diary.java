package com.thisischool.chool.Models;

public class Diary {
    private String title, content, id, name, imageUrl, addToFvrt, timeStamp;


    public Diary() {
    }


    public Diary(String title, String content, String id, String name, String imageUrl, String addToFvrt, String timeStamp) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.addToFvrt = addToFvrt;
        this.timeStamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddToFvrt() {
        return addToFvrt;
    }

    public void setAddToFvrt(String addToFvrt) {
        this.addToFvrt = addToFvrt;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
