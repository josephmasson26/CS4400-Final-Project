package com.example.traveljournal;

public class SearchData {
    String city;
    String country;

    double rating;

    SearchData(String city, String country, double rating) {
        this.city = city;
        this.country = country;
        this.rating = rating;
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
}
