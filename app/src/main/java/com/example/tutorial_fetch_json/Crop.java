package com.example.tutorial_fetch_json;


public class Crop {
    public String plant_name;
    public String soil;
    public String temperature;
    public String rainfall;
    public String image;


    public Crop(String plant_name, String soil, String temp, String rain, String img){
        this.plant_name = plant_name;
        this.soil = soil;
        this.temperature = temp;
        this.rainfall = rain;
        this.image = img;
    }

    public String showSoilRain(){
        return "The soil type is "+ this.soil +" and the rainfall amount "+this.rainfall;
    }
}
