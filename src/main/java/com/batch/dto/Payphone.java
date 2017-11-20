package com.batch.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Payphone {

    private int id;
    private String company;
    private double latitude;
    private double longitude;
    private String borough;
    private String building;
    private String street;
    private boolean isActive;
    private String zoning;
    private String pointStr;

    public Payphone() {
    }

    public Payphone(int id, String company, double latitude, double longitude, String borough, String building, String street, boolean isActive, String zoning) {
        this.company = company;
        this.latitude = latitude;
        this.longitude = longitude;
        this.borough = borough;
        this.building = building;
        this.street = street;
        this.isActive = isActive;
        this.zoning = zoning;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointStr() {
        return pointStr;
    }

    public void setPointStr(String pointStr) {
        this.pointStr = pointStr;
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

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
    }

    @Override
    public String toString() {
        return "Payphone{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", borough='" + borough + '\'' +
                ", building='" + building + '\'' +
                ", street='" + street + '\'' +
                ", isActive=" + isActive +
                ", zoning='" + zoning + '\'' +
                ", pointStr='" + pointStr + '\'' +
                '}';
    }
}
