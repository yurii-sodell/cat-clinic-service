package be.kdg.pro3.catclinic.configuration.converter;

import be.kdg.pro3.catclinic.domain.CatOwnerId;
import org.springframework.core.convert.converter.Converter;

public class OwnerIdFromStringConverter implements Converter<String, CatOwnerId>{

    @Override
    public CatOwnerId convert(String source){
        if(source == null || source.isBlank()) return null;
        return CatOwnerId.parse(source);
    }
}
