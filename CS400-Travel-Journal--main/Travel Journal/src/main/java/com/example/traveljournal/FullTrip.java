package com.example.traveljournal;

public class FullTrip {
    private String date;

    private String city;

    private String country;

    private Double rating;

    private String note;

    FullTrip(String date, String city, String country, Double rating, String note) {
        this.date = date;
        this.city = city;
        this.country = country;
        this.rating = rating;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Double getRating() {
        return rating;
    }

    public String getNote() {
        return note;
    }
}
