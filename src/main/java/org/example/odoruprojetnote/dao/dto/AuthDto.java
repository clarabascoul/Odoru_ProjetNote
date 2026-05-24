package org.example.odoruprojetnote.dao.dto;



public class AuthDto {

    private String nomUser;
    private String mdp;

    public String getNomUser() { return nomUser; }
    public String getMdp() { return mdp; }
    public void setNomUser(String nomUser) { this.nomUser = nomUser; }
    public void setMdp(String mdp) { this.mdp = mdp; }
}