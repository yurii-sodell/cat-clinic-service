package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Cat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
