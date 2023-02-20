package es.profile.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UsuarioCreateDto implements Serializable {

    private String id;

    private String username;

    private String password;

    private Date birthdate;

    private Integer tlf;
}