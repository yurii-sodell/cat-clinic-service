package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.MedicationId;

public interface IdentityGenerator{
    CatId nextCatId();
    CatOwnerId nextCatOwnerId();
    MedicationId nextMedicationId();
}
