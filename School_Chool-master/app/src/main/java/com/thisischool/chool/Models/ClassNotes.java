package com.thisischool.chool.Models;

public class ClassNotes {
    private String title, content, id, name;

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public ClassNotes() {
    }

    public ClassNotes(String title, String content, String id, String name) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.name = name;
    }
}
