package org.example.odoruprojetnote.metier;

import org.example.odoruprojetnote.dao.MembresRepository;
import org.example.odoruprojetnote.dao.dto.MembresDto;
import org.example.odoruprojetnote.entities.Membres;
import org.example.odoruprojetnote.entities.Roles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionMembres {
    private final MembresRepository membresRepository;

    public GestionMembres(MembresRepository membresRepository) {
        this.membresRepository = membresRepository;
    }

    public List<MembresDto> findAll() {
        return membresRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }


    public MembresDto findById(Long id) {
        Membres m = getOrThrow(id);
        return toDto(m);
    }

    /*public Membres createMembre(Membres membres) {
        return membresRepository.save(membres);
    }*/

   /* public Membres updateLevel(Long id, int level) {
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("Level invalid");
        }
        Membres membres = membresRepository.findById(id).orElseThrow();
        membres.setNiveau(level);
        return membresRepository.save(membres);
    }*/



    // Créer un nouveau membre
    public MembresDto creerMembre(MembresDto dto) {
        if (membresRepository.existsByNomUser(dto.getNomUser())){
            throw new RuntimeException("nom User déjà existant: " + dto.getNomUser());
        }
        if (membresRepository.existsByMel(dto.getMel())) {
            throw new RuntimeException("Email déjà utilisé : " + dto.getMel());
        }
        // Tout nouveau membre commence avec le rôle MEMBRE
        Membres m=toEntite(dto);
        m.setRole(Roles.MEMBRE);
        return toDto(membresRepository.save(m));
    }

    // Modifier les infos d'un membre
    public MembresDto update(Long id, MembresDto mDto) {
        Membres m= getOrThrow(id);
        m.setNomF(mDto.getNomF());
        m.setPrenom(mDto.getPrenom());
        m.setMel(mDto.getMel());
        m.setAdresse(mDto.getAdresse());
        m.setVille(mDto.getVille());
        m.setPays(mDto.getPays());
        return toDto(membresRepository.save(m));
    }

    // Modifier le niveau d'expertise (réservé au secrétaire)

    public MembresDto updateNiveau(Long id, int n) {
        if (n < 1 || n > 5) {
            throw new RuntimeException("Le niveau être entre 1 et 5");
        }
        Membres m = getOrThrow(id);
        m.setNiveau(n);
        return toDto(membresRepository.save(m));
    }

    // Changer le rôle d'un membre (réservé au secrétaire)
    public MembresDto setRole(Long id, Roles r) {
        Membres m = getOrThrow(id);
        m.setRole(r);
        return toDto(membresRepository.save(m));
    }

    // Supprimer un membre
    public void delete(Long id) {
        if (!membresRepository.existsById(id)) {
            throw new RuntimeException("Membre introuvable : " + id);
        }
        membresRepository.deleteById(id);
    }


    //----------

    private MembresDto toDto(Membres m) {
        MembresDto dto = new MembresDto();
        dto.setId(m.getId());
        dto.setNomF(m.getNomF());
        dto.setPrenom(m.getPrenom());
        dto.setMel(m.getMel());
        dto.setNomUser(m.getNomUser());
        dto.setAdresse(m.getAdresse());
        dto.setVille(m.getVille());
        dto.setPays(m.getPays());
        dto.setNiveau(m.getNiveau());
        dto.setRole(m.getRole());
        // password → jamais inclus dans la réponse
        return dto;
    }

    private Membres toEntite(MembresDto dto) {
        Membres m = new Membres();
        m.setNomF(dto.getNomF());
        m.setPrenom(dto.getPrenom());
        m.setMel(dto.getMel());
        m.setNomUser(dto.getNomUser());
        m.setMdp(dto.getMdp());
        m.setAdresse(dto.getAdresse());// stocké à la création
        m.setVille(dto.getVille());
        m.setPays(dto.getPays());
        m.setNiveau(dto.getNiveau());
        return m;
    }

    private Membres getOrThrow(Long id) {
        return membresRepository.findById(id).orElseThrow(() -> new RuntimeException("Membre introuvable : " + id));
    }






}
