package com.sony.faasostest.Model;

/**
 * Created by Dell on 25-07-2018.
 */

public class Address
{
    public String street;
    public String suite ;
    public String city ;
    public String zipcode;
    public Geo geo ;
    public Address()
    {
        //Empty Constructor
    }

    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }
}