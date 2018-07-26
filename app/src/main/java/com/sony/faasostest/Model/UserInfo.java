package com.sony.faasostest.Model;

/**
 * Created by Dell on 25-07-2018.
 */

public class UserInfo
{
    public int id ;
    public String name;
    public String username;
    public String email ;
    public Address address;
    public String phone;
    public String website;
    public Company company ;
    private boolean isChecked;
    public UserInfo()
    {
        // Empty Constructor
    }

    public UserInfo(int id, String name, String username, String email,
                    Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }
    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}