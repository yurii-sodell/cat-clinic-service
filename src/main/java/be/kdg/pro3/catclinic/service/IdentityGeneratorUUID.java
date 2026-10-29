package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.MedicationId;

import java.util.UUID;

public class IdentityGeneratorUUID implements IdentityGenerator{

    @Override
    public CatId nextCatId(){
        return new CatId(UUID.randomUUID());
    }

    @Override
    public CatOwnerId nextCatOwnerId(){
        return new CatOwnerId(UUID.randomUUID());
    }

    @Override
    public MedicationId nextMedicationId(){
        return new MedicationId(UUID.randomUUID());
    }
}
