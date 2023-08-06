package com.example.traveljournal;

public class EntryData {
    String date;
    String note;
    Integer rating;
    String viewedUsername;

    EntryData(String date, Integer rating, String note, String viewedUsername) {
        this.date = date;
        this.note = note;
        this.rating = rating;
        this.viewedUsername = viewedUsername;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public Integer getRating() {
        return rating;
    }

    public String getViewedUsername() { return viewedUsername; }
}
