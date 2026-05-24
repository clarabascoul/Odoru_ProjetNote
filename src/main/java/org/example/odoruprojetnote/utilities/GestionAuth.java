package org.example.odoruprojetnote.utilities;



import org.example.odoruprojetnote.dao.MembresRepository;
import org.example.odoruprojetnote.dao.dto.AuthDto;
import org.example.odoruprojetnote.dao.dto.MembresDto;
import org.example.odoruprojetnote.entities.Membres;
import org.example.odoruprojetnote.entities.Roles;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GestionAuth {

    private final MembresRepository repository;

    private final AuthenticationManager authenticationManager;

    public GestionAuth(MembresRepository repository,
                       AuthenticationManager authenticationManager) {
        this.repository = repository;

        this.authenticationManager = authenticationManager;
    }

    // Inscription
    public MembresDto inscrire(MembresDto dto) {
        if (repository.existsByNomUser(dto.getNomUser())) {
            throw new RuntimeException("Nom d'utilisateur déjà pris : " + dto.getNomUser());
        }
        if (repository.existsByMel(dto.getMel())) {
            throw new RuntimeException("Email déjà utilisé : " + dto.getMel());
        }

        Membres membre = new Membres();
        membre.setNomUser(dto.getNomUser());
        membre.setMdp(dto.getMdp());
        membre.setNomF(dto.getNomF());
        membre.setPrenom(dto.getPrenom());
        membre.setMel(dto.getMel());
        membre.setAdresse(dto.getAdresse());
        membre.setVille(dto.getVille());
        membre.setPays(dto.getPays());
        membre.setNiveau(dto.getNiveau());
        membre.setRole(Roles.MEMBRE); // rôle par défaut

        Membres sauvegarde = repository.save(membre);

        // On renvoie le membre sans le mdp
        MembresDto reponse = new MembresDto();
        reponse.setId(sauvegarde.getId());
        reponse.setNomUser(sauvegarde.getNomUser());
        reponse.setNomF(sauvegarde.getNomF());
        reponse.setPrenom(sauvegarde.getPrenom());
        reponse.setMel(sauvegarde.getMel());
        reponse.setRole(sauvegarde.getRole());
        return reponse;
    }

    // Connexion — vérifie nomUser + mdp
    public String login(AuthDto dto) {
        // Spring vérifie automatiquement nomUser + mdp chiffré
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getNomUser(), dto.getMdp())
        );
        // Si on arrive ici c'est que c'est bon
        return "Connexion réussie pour : " + dto.getNomUser();
    }
}