package com.github.anmv921.agendaapi.model.api.rest;
import com.github.anmv921.agendaapi.model.entity.Contacto;
import com.github.anmv921.agendaapi.model.repository.ContactoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contactos")
@RequiredArgsConstructor
public class ContactoController {

    // Injeção de dependência - precisa de keyword final e um
    // construtor - criado por @RequiredArgsConstructor do lombok
    private final ContactoRepository contactoRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contacto save(
            @RequestBody Contacto in_contacto) {
        return contactoRepository.save(in_contacto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deletar( @PathVariable Integer id ) {

        contactoRepository.deleteById(id);
    }

    @GetMapping
    public List<Contacto> list() {

        return contactoRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Contacto> obterContactoPorId ( @PathVariable Integer id ) {
        return contactoRepository.findById(id);
    }

    // Actualização parcial; put é usado em actualizações totais
    @PatchMapping("{id}/favorito")
    public void favorite( @PathVariable Integer id,
                          @RequestBody Boolean favorito ) {
        Optional<Contacto> optionalContacto = contactoRepository.findById(id);
        optionalContacto.ifPresent( contacto -> {
                    contacto.setFavorito(favorito);
                    contactoRepository.save(contacto);
                });
    } // End patch - favorite

} // End ContactoController
