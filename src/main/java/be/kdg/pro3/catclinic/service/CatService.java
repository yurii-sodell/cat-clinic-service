package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;

import java.util.List;

public interface CatService{
    List<Cat> getAllCats();
    List<Cat> getCatsFilteredByAgeAndByWeight(int minAge, int maxAge, int minWeight, int maxWeight);
    List<Cat> getCatsFilteredByMedicationName(String medicationName);
}
