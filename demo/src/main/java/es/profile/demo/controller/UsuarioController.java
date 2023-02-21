package es.profile.demo.controller;

import es.profile.demo.dto.UsuarioCreateDto;
import es.profile.demo.dto.UsuarioDto;
import es.profile.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Devuelve una lista de usuarios con sus atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios devuelta",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UsuarioDto.class)))
                    })
    })
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getUsuarios() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Crea un usuario y lo introduce en la base de datos de MongoDB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario Creado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }) })
    @PostMapping
    public ResponseEntity<UsuarioDto> insertUsuario(@RequestBody UsuarioCreateDto usuario) {
        return new ResponseEntity<>(usuarioService.insert(usuario),HttpStatus.CREATED);
    }

    @Operation(summary = "Devuelve el usuario al que pertenece el id que se pasa por parámetro o advierte de que ese usuario no se encuentra en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuario Not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuario(@PathVariable String id)
    {
        UsuarioDto usuario = usuarioService.getById(id);
        if(Objects.isNull(usuario)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Not Found");
        }
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }

    @Operation(summary = "Elimina de la base de datos el usuario que se ha pasado por parámetro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Usuario Not found",
                    content = @Content) })
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
