package com.example.nianchen.normaluniversitytourgroup.BaseClass;

/**
 * Created by jiahang Lee on 2016/10/17.
 */

public class today {
    private String city;
    private String temperature;
    private String week;
    private String weather;
    private String dressing_advice;
    private String wind;

    @Override
    public String toString() {
        return "today{" +
                "city='" + city + '\'' +"\n"+
                ", week='" + week + '\'' +"\n"+
                ", temperature='" + temperature + '\'' +"\n"+
                ", weather='" + weather + '\'' +"\n"+
                ", dressing_advice='" + dressing_advice + '\'' +"\n"+
                ", wind='" + wind + '\'' +"\n"+
                '}';
    }

    public today() {
        this.temperature = temperature;
        this.city = city;
        this.week = week;
        this.weather = weather;
        this.dressing_advice = dressing_advice;
        this.wind = wind;
    }

    public today(String city) {
        this.city = city;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDressing_advice() {
        return dressing_advice;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }
}
