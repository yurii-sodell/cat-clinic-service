package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Cat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@Repository
public class InMemoryCatRepository implements CatRepository{
    private static List<Cat> cats = new ArrayList<>();

    @Override
    public void saveCat(Cat cat){
        cats.add(cat);
    }

    @Override
    public void saveCats(List<Cat> newCats){
        cats.addAll(newCats);
    }

    @Override
    public List<Cat> getCats(){
        return Collections.unmodifiableList(cats);
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
    public List<Cat> getCatsFilteredByMedicationName(String name){
                Predicate<Cat> hasMedication = cat -> cat.getMedicationsThatWasAssignedToCat().stream()
                .anyMatch(medication -> medication.getTitle().equalsIgnoreCase(name));

        return cats.stream()
                .filter(hasMedication)
                .sorted(Comparator.comparing(Cat::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public List<Cat> getCatsBySearchRequest(String catName, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight){
        return cats.stream()
                .filter(cat -> minAge == null || cat.getAge() >= minAge)
                .filter(cat -> maxAge == null || cat.getAge() <= maxAge)
                .filter(cat -> minWeight == null || cat.getWeightInKilos() >= minWeight)
                .filter(cat -> maxWeight == null || cat.getWeightInKilos() <= maxWeight)
                .filter(cat -> catName == null || catName.isEmpty() || cat.getName().equalsIgnoreCase(catName))
                .sorted(Comparator.comparing(Cat::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

}
