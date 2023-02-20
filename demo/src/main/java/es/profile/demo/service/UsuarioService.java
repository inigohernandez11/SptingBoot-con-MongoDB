package es.profile.demo.service;

import es.profile.demo.dto.UsuarioCreateDto;
import es.profile.demo.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDto> findAll();
    UsuarioDto insert(UsuarioCreateDto usuarioRepository);
    UsuarioDto getById(String id);
    UsuarioDto deleteById(String id);
}
