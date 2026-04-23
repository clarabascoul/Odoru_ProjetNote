package org.example.odoruprojetnote.metier;

import org.example.odoruprojetnote.dao.MembresRepository;
import org.example.odoruprojetnote.entities.Membres;
import org.springframework.stereotype.Service;

@Service
public class GestionMembres {
    private final MembresRepository membresRepository;

    public GestionMembres(MembresRepository membresRepository) {
        this.membresRepository = membresRepository;
    }

    public Membres createMembre(Membres membres) {
        return membresRepository.save(membres);
    }

    public Membres updateLevel(Long id, int level) {
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("Level invalid");
        }
        Membres membres = membresRepository.findById(id).orElseThrow();
        membres.setNiveau(level);
        return membresRepository.save(membres);
    }

}
