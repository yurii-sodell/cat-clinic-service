package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.MedicationId;
import be.kdg.pro3.catclinic.presentation.web.CatEditForm;

import java.util.List;
import java.util.UUID;

public interface CatService{
    List<Cat> getAllCats();
    List<Cat> getCatsFilteredByAgeAndByWeight(int minAge, int maxAge, int minWeight, int maxWeight);
    List<Cat> getCatsFilteredByMedicationName(String medicationName);
    List<Cat> getCatBySearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight);
    Cat getCatById(CatId id);
    void updateCat(CatId id, CatEditForm catEditForm);
    List<Cat> getCatsForOwner(CatOwnerId catOwnerId);
}
