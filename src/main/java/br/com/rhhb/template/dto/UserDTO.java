package br.com.rhhb.template.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserDTO {

    private Long id;
    @NotBlank(message = "O campo nome deve ser informado")
    private String name;
}