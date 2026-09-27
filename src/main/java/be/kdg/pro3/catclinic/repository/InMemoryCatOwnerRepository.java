package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.CatOwner;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryCatOwnerRepository implements CatOwnerRepository{
    private static List<CatOwner> catOwners = new ArrayList<>();

    @Override
    public void saveOwner(CatOwner catOwner){
        catOwners.add(catOwner);
    }

    @Override
    public void saveOwners(List<CatOwner> newOwners){
        catOwners.addAll(newOwners);
    }

    @Override
    public List<CatOwner> getCatOwners(){
        return Collections.unmodifiableList(catOwners);
    }

}
