package be.kdg.pro3.catclinic.configuration.converter;

import be.kdg.pro3.catclinic.domain.CatId;
import org.springframework.core.convert.converter.Converter;

public class CatIdFromStringConverter implements Converter<String, CatId>{

    @Override
    public CatId convert(String source){
        if(source == null || source.isBlank()) return null;
        return CatId.parse(source);
    }
}

