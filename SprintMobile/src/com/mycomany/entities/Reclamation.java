/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Reclamation {
    //nemchio taw nchofo entity fi symfony
    
    private int id;
   
    
    private String type,description,reponse;
    private Date daterec;
    private String etat;

    public Reclamation() {
    }

    
    
    
    public Reclamation(int id,  String type, String description,String reponse, Date daterec, String etat) {
        this.id = id;
        
        this.type = type;
        this.description = description;
        this.daterec = daterec;
        this.reponse = reponse ;
        this.etat = etat;
    }

    public Reclamation(String type, String description, Date daterec,String reponse , String etat) {
        this.type = type;
        this.description = description ;
        this.daterec = daterec;
        this.etat = etat;
        this.reponse = reponse ;
    }

    public Reclamation(String toString, String toString0, String format, int i) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDaterec() {
        return daterec;
    }

    public void setDaterec(Date daterec) {
        this.daterec = daterec;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
      public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    
    
    
    
    
    }
