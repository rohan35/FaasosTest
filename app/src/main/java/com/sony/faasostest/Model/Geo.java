package com.sony.faasostest.Model;

/**
 * Created by Dell on 25-07-2018.
 */

public class Geo
{
    public String lat;
    public String lng;
    public Geo()
    {
        // Empty constructor
    }

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}