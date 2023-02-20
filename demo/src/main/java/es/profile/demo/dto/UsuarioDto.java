package es.profile.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UsuarioDto implements Serializable {

    private String id;

    private String username;


    private Date birthdate;

    private Integer tlf;
}
