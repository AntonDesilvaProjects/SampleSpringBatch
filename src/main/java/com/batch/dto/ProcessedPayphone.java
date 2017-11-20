package com.batch.dto;

import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class ProcessedPayphone {

    private int id;
    private String company;
    private double latitude;
    private double longitude;
    private boolean isActive;
    private String address;
    private String googleMapsURL;

    public ProcessedPayphone(){}

    public ProcessedPayphone(int id, String company, double latitude, double longitude, boolean isActive, String address, String googleMapsURL) {
        this.id = id;
        this.company = company;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isActive = isActive;
        this.address = address;
        this.googleMapsURL = googleMapsURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGoogleMapsURL(String googleMapsURL) {
        this.googleMapsURL = googleMapsURL;
    }

    public String getGoogleMapsURL() {
        return googleMapsURL;
    }
    @Override
    public String toString() {
        return "ProcessedPayphone{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", isActive=" + isActive +
                ", address='" + address + '\'' +
                ", googleMapsURL='" + googleMapsURL + '\'' +
                '}';
    }
}
