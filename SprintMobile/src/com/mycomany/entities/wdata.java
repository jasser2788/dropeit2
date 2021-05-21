/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author User
 */
public class wdata {
    int id,id_client;
    float height,weight,waist,neck,hip;
    String date;

    public wdata(int id,int id_client , float weight,float height, float waist, float neck, float hip,String d) {
        this.id = id;
        this.height = height;
        
        this.weight = weight;
        this.waist = waist;
        this.neck = neck;
        this.hip = hip;
        this.id_client=id_client;
        this.date=d;
    }

    public wdata() {
    }

    public wdata(int id_client ,float height, float weight, float waist, float neck, float hip,String d) {
        this.height = height;
        this.weight = weight;
        this.waist = waist;
        this.neck = neck;
        this.hip = hip;
        this.id_client=id_client;
        this.date=d;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public float getWaist() {
        return waist;
    }

    @Override
    public String toString() {
        return "wdata{" + "id=" + id + ", height=" + height + ", weight=" + weight + ", waist=" + waist + ", neck=" + neck + ", hip=" + hip + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public void setNeck(float neck) {
        this.neck = neck;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public float getNeck() {
        return neck;
    }

    public float getHip() {
        return hip;
    }
    
    
}
