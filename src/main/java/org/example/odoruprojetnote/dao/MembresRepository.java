package org.example.odoruprojetnote.dao;

import org.example.odoruprojetnote.entities.Membres;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MembresRepository extends CrudRepository<Membres, Long> {
    Optional<Membres> findByNomUser(String nomUser);
}
