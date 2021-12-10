package com.example.shareadrop;

public class stockmodel {

    String id, date, bestbefore, ounce, bags;
    public stockmodel(){}

    public stockmodel(String id, String date , String bestbefore, String ounce, String bags) {
        this.id = id;
        this.date = date;
        this.bestbefore = bestbefore;
        this.ounce = ounce;
        this.bags = bags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBestbefore() {
        return bestbefore;
    }

    public void setBestbefore(String bestbefore) {
        this.bestbefore = bestbefore;
    }

    public String getOunce() {
        return ounce;
    }

    public void setOunce(String ounce) {
        this.ounce = ounce;
    }

    public String getBags() {
        return bags;
    }

    public void setBags(String bags) {
        this.bags = bags;
    }
}
