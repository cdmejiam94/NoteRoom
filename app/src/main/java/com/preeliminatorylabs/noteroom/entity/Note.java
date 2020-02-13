package com.preeliminatorylabs.noteroom.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String descriptor;

    private int priority;

    //For not repeat the id
    public Note(String title, String descriptor, int priority) {
        this.title = title;
        this.descriptor = descriptor;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptor() {
        return descriptor;
    }
    public int getPriority() {
        return priority;
    }
}
