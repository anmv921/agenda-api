package com.github.anmv921.agendaapi.model.api.rest;
import com.github.anmv921.agendaapi.model.entity.Contacto;
import com.github.anmv921.agendaapi.model.repository.ContactoRepository;

import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contactos")
@RequiredArgsConstructor
@CrossOrigin("*")
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
    public void favorite( @PathVariable Integer id ) {
        Optional<Contacto> optionalContacto = contactoRepository.findById(id);
        optionalContacto.ifPresent( contacto -> {
                    boolean favorito = contacto.getFavorito() == Boolean.TRUE;
                    contacto.setFavorito(!favorito);
                    contactoRepository.save(contacto);
                });
    } // End patch - favorite

    @PutMapping("{id}/foto")
    public byte[] addPhoto( @PathVariable Integer id,
                            @RequestParam("foto") Part arquivo ) {
        Optional<Contacto> contato = contactoRepository.findById(id);
        return contato.map( c -> {
           try {
               InputStream is = arquivo.getInputStream();
               byte[] bytes = new byte[(int)arquivo.getSize()];
               IOUtils.readFully(is, bytes);
               c.setFoto(bytes);
               contactoRepository.save(c);
               is.close();
               return bytes;
           } catch(IOException e) {
                return null;
           }
        }).orElse(null);
    } // End addPhoto

} // End ContactoController
