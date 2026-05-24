package org.example.odoruprojetnote.exposition;


import org.example.odoruprojetnote.dao.dto.MembresDto;
import org.example.odoruprojetnote.entities.Membres;
import org.example.odoruprojetnote.entities.Roles;
import org.example.odoruprojetnote.metier.GestionMembres;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membres")
public class MembresController {


    private final GestionMembres gm;

    public MembresController(GestionMembres gm) {
        this.gm = gm;
    }


   // @PreAuthorize("hasAnyRole('SECRETAIRE', 'PRESIDENT')")






    // GET /api/membres
    @GetMapping
    @PreAuthorize("hasAnyRole('SECRETAIRE', 'PRESIDENT')")
    public List<MembresDto> findAll() {
        return gm.findAll();
    }

    // GET /api/membres/1
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SECRETAIRE', 'PRESIDENT')")
    public MembresDto findById(@PathVariable Long id) {
        return gm.findById(id);
    }

    // POST /api/membres
    @PostMapping
    public ResponseEntity<MembresDto> creer(@RequestBody MembresDto mDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gm.creerMembre(mDto));
    }

    // PUT /api/membres/1
   /* @PutMapping("/{id}")
    public Membres update(@PathVariable Long id, @RequestBody Membres membre) {
        return gm.update(id, membre);
    }*/



    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SECRETAIRE', 'PRESIDENT')")
    public MembresDto update(@PathVariable Long id, @RequestBody MembresDto mDto) {
        return gm.update(id, mDto);
    }


    // PATCH /api/membres/1/niveau?niveau=3
    @PatchMapping("/{id}/niveau")
    @PreAuthorize("hasAnyRole('SECRETAIRE', 'PRESIDENT')")
    public MembresDto updateNiveau(@PathVariable Long id, @RequestParam int niveau) {
        return gm.updateNiveau(id, niveau);
    }

    // PATCH /api/membres/1/role?role=ENSEIGNANT
    @PatchMapping("/{id}/role")
    @PreAuthorize("hasAnyRole('SECRETAIRE', 'PRESIDENT')")
    public MembresDto setRole(@PathVariable Long id, @RequestParam Roles role) {
        return gm.setRole(id, role);
    }

    // DELETE /api/membres/1
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SECRETAIRE', 'PRESIDENT')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gm.delete(id);
        return ResponseEntity.noContent().build();
    }

}
