package com.sakuno.unisync;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Todo {
    private String subject;
    private String date;
    private String todo;


    public Todo() {
    }

    public Todo(String subject, String date, String todo) {
        this.subject = subject;
        this.date = date;
        this.todo = todo;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getTodo() {
        return todo;
    }

}
