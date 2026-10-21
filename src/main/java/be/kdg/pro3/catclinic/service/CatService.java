package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.web.CatEditForm;

import java.util.List;
import java.util.UUID;

public interface CatService{
    List<Cat> getAllCats();
    List<Cat> getCatsFilteredByAgeAndByWeight(int minAge, int maxAge, int minWeight, int maxWeight);
    List<Cat> getCatsFilteredByMedicationName(String medicationName);
    List<Cat> getCatBySearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight);
    Cat getCatById(UUID uuid);
    void updateCat(UUID uuid, CatEditForm catEditForm);
}
