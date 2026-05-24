package org.example.odoruprojetnote.exposition;



import org.example.odoruprojetnote.dao.dto.AuthDto;
import org.example.odoruprojetnote.dao.dto.MembresDto;

import org.example.odoruprojetnote.utilities.GestionAuth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final GestionAuth gestionAuth;

    public AuthController(GestionAuth gestionAuth) {
        this.gestionAuth = gestionAuth;
    }

    // POST /api/auth/inscription
    @PostMapping("/inscription")
    public ResponseEntity<MembresDto> inscrire(@RequestBody MembresDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gestionAuth.inscrire(dto));
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDto dto) {
        return ResponseEntity.ok(gestionAuth.login(dto));
    }
}
