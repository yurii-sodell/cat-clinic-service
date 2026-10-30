package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Cat;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.CatOwnerId;
import be.kdg.pro3.catclinic.domain.MedicationId;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;

@Repository
public class InMemoryCatRepository implements CatRepository{
    //unique by UUID's
    private static HashSet<Cat> cats = new HashSet<>();

    @Override
    public void saveCat(Cat cat){
        cats.remove(cat);
        cats.add(cat);
    }

    @Override
    public void saveCats(List<Cat> newCats){
        cats.addAll(newCats);
    }

    @Override
    public List<Cat> getCats(){
        return List.copyOf(cats);
    }

    @Override
    public List<Cat> getCatsFilteredByAgeAndByWeight(int minAge, int maxAge, int minWeight, int maxWeight){
        return cats.stream()
                .filter(cat -> cat.getAge() >= minAge && cat.getAge() <= maxAge)
                .filter(cat -> cat.getWeightInKilos() >= minWeight && cat.getWeightInKilos() <= maxWeight)
                .sorted(Comparator.comparing(Cat::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public List<Cat> getCatsFilteredByMedicationId(MedicationId medicationId){
        return List.of();
    }

    @Override
    public List<Cat> getCatsBySearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight){
        return cats.stream()
                .filter(cat -> minAge == null || cat.getAge() >= minAge)
                .filter(cat -> maxAge == null || cat.getAge() <= maxAge)
                .filter(cat -> minWeight == null || cat.getWeightInKilos() >= minWeight)
                .filter(cat -> maxWeight == null || cat.getWeightInKilos() <= maxWeight)
                .filter(cat -> catName == null || catName.isEmpty() || cat.getName().toLowerCase().contains(catName.toLowerCase()))
                .sorted(Comparator.comparing(Cat::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public Optional<Cat> getCatById(CatId id){
        return cats.stream()
                .filter(cat -> cat.getCatId().equals(id))
                .findAny();
    }

    @Override
    public List<Cat> getCatByName(String name){
        return cats.stream()
                .filter(cat -> cat.getName().equalsIgnoreCase(name))
                .toList();
    }

    @Override
    public List<Cat> getCatByIdOfOwner(CatOwnerId id){
        return cats.stream()
                .filter(cat -> cat.getBelongingOwnerId().equals(id))
                .toList();
    }

}
