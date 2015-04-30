package com.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.adam.csv.CSVisable;

import java.io.Serializable;

/**
 * Created by adam on 29.04.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity{
    private String _type;
    private String _id;
    private String name;
    private String type;
    @CSVisable
    private GeoPosition geo_position = new GeoPosition();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoPosition getGeo_position() {
        return geo_position;
    }

    public void setGeo_position(GeoPosition geo_position) {
        this.geo_position = geo_position;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }
}
