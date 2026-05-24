package org.example.odoruprojetnote.dao.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.odoruprojetnote.entities.Roles;

@Getter
@Setter
public class MembresDto {
    private Long id;
    private String nomUser;
    private String mdp;// reçu à la création, jamais renvoyé
    private String nomF;
    private String prenom;
    private String mel;
    private String adresse;
    private String ville;
    private String pays;
    private int niveau;
    private Roles role;
}
