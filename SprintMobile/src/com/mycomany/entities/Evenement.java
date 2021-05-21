/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author louay_yahyaoui
 */
public class Evenement {
    
    private int id;
    private String lieu;
    private String heure;
    private Date date;
    private int participants;
    private String image;
    private String nom;

    public Evenement() {
    }

    public Evenement(int id, String lieu, String heure, Date date, int participants, String image, String nom) {
        this.id = id;
        this.lieu = lieu;
        this.heure = heure;
        this.date = date;
        this.participants = participants;
        this.image = image;
        this.nom = nom;
    }

    public Evenement(String lieu, String heure, Date date, int participants, String image, String nom) {
        this.lieu = lieu;
        this.heure = heure;
        this.date = date;
        this.participants = participants;
        this.image = image;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", lieu=" + lieu + ", heure=" + heure + ", date=" + date + ", participants=" + participants + ", image=" + image + ", nom=" + nom + '}';
    }
    
    
    
    
    
}
