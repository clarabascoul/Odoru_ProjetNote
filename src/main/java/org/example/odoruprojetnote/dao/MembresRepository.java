package org.example.odoruprojetnote.dao;

import org.example.odoruprojetnote.entities.Membres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MembresRepository extends JpaRepository<Membres, Long> {

    List<Membres> findAll();

    Optional<Membres> findByNomUser(String nomUser);
    
    Optional<Membres> findByEmail(String email);

    List<Membres> findAllByNiveau(int niveau);

    boolean existsByNomUser(String n);

    boolean existsByMel(String m);
}
