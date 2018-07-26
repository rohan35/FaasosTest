package com.sony.faasostest.Model;

/**
 * Created by Dell on 25-07-2018.
 */


public class Company
{
    public String name ;
    public String catchPhrase ;
    public String bs;
    public Company()
    {
        //Empty Constructor
    }

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }
}