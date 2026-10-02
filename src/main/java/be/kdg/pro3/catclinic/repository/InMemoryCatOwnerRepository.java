package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

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

    @Override
    public List<CatOwner> getCatOwnerByCatsName(String name){
        Predicate<CatOwner> hasCat = owner -> owner.getCats().stream()
                .anyMatch(cat -> cat.getName().equalsIgnoreCase(name));

        return catOwners.stream()
                .filter(hasCat)
                .toList();
    }

    @Override
    public List<CatOwner> getCatOwnerByItsName(String name){
        return catOwners.stream()
                .filter(owner -> owner.getFamilyName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public List<CatOwner> getCatOwnerByItsLanguage(PreferredCommunicationLanguage language){
        return catOwners.stream()
                .filter(owner -> owner.getPreferredCommunicationLanguage() == language)
                .toList();
    }




}
