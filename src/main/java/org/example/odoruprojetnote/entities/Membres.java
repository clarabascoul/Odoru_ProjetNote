package org.example.odoruprojetnote.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Membres {
    @Id
    @GeneratedValue
    private Long id;

    private String nomUser;
    private String mdp;
    private String nomF;
    private String prenom;
    private String mel;
    private String Adresse;
    private String Ville;
    private String Pays;
    private int Niveau;
    private boolean Secretaire;
    private boolean Enseignant;


    public void setNiveau(int niveau) {
        Niveau = niveau;
    }
}
