package es.profile.demo.controller;

import es.profile.demo.dto.UsuarioCreateDto;
import es.profile.demo.dto.UsuarioDto;
import es.profile.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getUsuarios() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> insertUsuario(@RequestBody UsuarioCreateDto usuario) {
        return new ResponseEntity<>(usuarioService.insert(usuario),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuario(@PathVariable String id)
    {
        UsuarioDto usuario = usuarioService.getById(id);
        if(Objects.isNull(usuario)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Not Found");
        }
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable String id)
    {
        UsuarioDto usuario = usuarioService.getById(id);
        if(Objects.isNull(usuario)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Not Found");
        }
        usuarioService.deleteById(id);
    }
}
