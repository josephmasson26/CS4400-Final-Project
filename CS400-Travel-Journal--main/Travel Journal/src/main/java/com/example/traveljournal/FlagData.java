package com.example.traveljournal;

public class FlagData {
    private String viewedUser;

    private String city;

    private String note;

    private String reasons;

    private String flagger;

    FlagData(String viewedUser, String city, String note, String reasons, String flagger) {
        this.viewedUser = viewedUser;
        this.city = city;
        this.note = note;
        this.reasons = reasons;
        this.flagger = flagger;
    }

    public String getViewedUser() {
        return viewedUser;
    }

    public String getCity() {
        return city;
    }

    public String getNote() {
        return note;
    }

    public String getReasons() {
        return reasons;
    }

    public String getFlagger() {
        return flagger;
    }
}
