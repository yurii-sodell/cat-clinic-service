package be.kdg.pro3.catclinic.configuration.converter;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.MedicationId;
import org.springframework.core.convert.converter.Converter;

public class MedicationIdFromStringConverter implements Converter<String, MedicationId>{

    @Override
    public MedicationId convert(String source){
        if(source == null || source.isBlank()) return null;
        return MedicationId.parse(source);
    }
}
