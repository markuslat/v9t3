package com.example.v9t3;

public class SmartpostObject {

    private String place_id;
    private String name;
    private String city;
    private String address;
    private String country;
    private String postalcode;
    private String availability;

    public SmartpostObject(String id, String n, String ci, String ad, String co, String p, String av) {
        this.place_id = id;
        this.name = n;
        this.city = ci;
        this.address = ad;
        this.country = co;
        this.postalcode = p;
        this.availability = av;
    }
    @Override
    public String toString() {
        return this.getPlace_id() + this.getName() +
                this.getCity() + this.getAddress() +
                this.getPostalcode() + this.getCountry() +
                this.getAvailability();
    }

    public String getPlace_id(){
        return place_id;
    }
    public String getName(){
        return name;
    }
    public String getCity(){
        return city;
    }
    public String getAddress(){
        return address;
    }
    public String getCountry(){
        return country;
    }
    public String getPostalcode(){
        return postalcode;
    }
    public String getAvailability(){
        return availability;
    }
}
