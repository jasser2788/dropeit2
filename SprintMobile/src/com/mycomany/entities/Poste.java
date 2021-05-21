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
public class Poste {
    private int id;
    private String titre,sujet,image,groupe;
    public Poste(){}

    public Poste(int id, String titre, String sujet, String image, String groupe) {
        this.id = id;
        this.titre = titre;
        this.sujet = sujet;
        this.image = image;
        this.groupe = groupe;
    }

    public Poste(String titre, String sujet, String image, String groupe) {
        this.titre = titre;
        this.sujet = sujet;
        this.image = image;
        this.groupe = groupe;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getSujet() {
        return sujet;
    }

    public String getImage() {
        return image;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "Poste{" + "titre=" + titre + ", sujet=" + sujet + ", image=" + image + ", groupe=" + groupe + '}';
    }
    
    
    
}
