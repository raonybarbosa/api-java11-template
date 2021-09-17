package br.com.rhhb.template.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DTOMapper {

    public static <T> T map(Object instanceOrigin, Class<T> destinationClass){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(instanceOrigin, destinationClass);
    }
}
