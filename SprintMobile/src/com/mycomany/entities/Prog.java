/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author asus
 */
public class Prog {
     private int id;
    private String name,type,image;
    private int days;
    public Prog(){}
    
      public Prog(int id, String name, String type, String image,int days) {
        this.id = id;
        this.name= name;
        this.type = type;
        this.image = image;
        this.days = days;
    }
     public Prog(String name, String type,String image ,int days) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.days = days;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Prog{" + "id=" + id + ", name=" + name + ", type=" + type + ", image=" + image + ", days=" + days + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
}

