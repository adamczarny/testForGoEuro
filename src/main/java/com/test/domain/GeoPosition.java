package com.test.domain;

import com.test.adam.csv.CSVisable;

import java.io.Serializable;

/**
 * Created by adam on 29.04.15.
 */
public class GeoPosition {
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
