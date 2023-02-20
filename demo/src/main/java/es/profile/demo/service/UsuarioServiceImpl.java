package es.profile.demo.service;

import es.profile.demo.dto.UsuarioCreateDto;
import es.profile.demo.dto.UsuarioDto;
import es.profile.demo.mapper.ObjectMapper;
import es.profile.demo.repository.models.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final es.profile.demo.repository.UsuarioRepository usuarioRepository;
    private final ObjectMapper mapper;
    @Override
    public List<UsuarioDto> findAll(){
        return mapper.map(usuarioRepository.findAll(),UsuarioDto.class);
    }
    @Transactional
    @Override
    public UsuarioDto insert(UsuarioCreateDto usuario) {
        return mapper.map(usuarioRepository.save(mapper.map(usuario, UsuarioRepository.class)), UsuarioDto.class);
    }

    @Override
    public UsuarioDto getById(String id){
        return usuarioRepository.findById(id)
                .map(value -> mapper.map(value, UsuarioDto.class))
                .orElse(null);
    }
    @Transactional
    @Override
    public UsuarioDto deleteById(String id){
        Optional<UsuarioRepository> usuario = usuarioRepository.findById(id);
        usuarioRepository.deleteById(id);
        return usuario.map(value -> mapper.map(value,UsuarioDto.class))
                .orElse(null);
    }


}
