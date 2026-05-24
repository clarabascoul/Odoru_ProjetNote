package org.example.odoruprojetnote.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Table(name = "membres")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private String adresse;

    private String ville;
    private String pays;

    private int niveau;

    @Enumerated(EnumType.STRING)
    private Roles role;


}
