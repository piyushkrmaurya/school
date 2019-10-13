package com.herokuapp.schoolmvc.model;

import java.sql.Date;

public class Notification {
    private Long noteId;
    private CoursePage coursePage;
    private String text;
    private Date date;

    public Notification(Long noteId, CoursePage coursePage, String text, Date date) {
        this.noteId = noteId;
        this.coursePage = coursePage;
        this.text = text;
        this.date = date;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public CoursePage getCoursePage() {
        return coursePage;
    }

    public void setCoursePage(CoursePage coursePage) {
        this.coursePage = coursePage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}