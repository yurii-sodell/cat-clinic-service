package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

@Repository
public class InMemoryCatOwnerRepository implements CatOwnerRepository{
    private static HashSet<CatOwner> catOwners = new HashSet<>();

    @Override
    public void saveOwner(CatOwner catOwner){
        catOwners.remove(catOwner);
        catOwners.add(catOwner);
    }

    @Override
    public void saveOwners(List<CatOwner> newOwners){
        catOwners.addAll(newOwners);
    }

    @Override
    public List<CatOwner> getCatOwners(){
        return List.copyOf(catOwners);
    }

    @Override
    public List<CatOwner> getCatOwnerByCatsName(String name){
        Predicate<CatOwner> hasCat = owner -> owner.getCats().stream()
                .anyMatch(cat -> cat.getName().equalsIgnoreCase(name));

        return catOwners.stream()
                .filter(hasCat)
                .sorted(Comparator.comparing(CatOwner::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public List<CatOwner> getCatOwnerByItsName(String name){
        return catOwners.stream()
                .filter(owner -> owner.getFamilyName().toLowerCase().contains(name.toLowerCase()))
                .sorted(Comparator.comparing(CatOwner::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public List<CatOwner> getCatOwnerByItsLanguage(PreferredCommunicationLanguage language){
        return catOwners.stream()
                .filter(owner -> owner.getPreferredCommunicationLanguage() == language)
                .sorted(Comparator.comparing(CatOwner::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public List<CatOwner> getOwnersByRegistrationDate(LocalDate registrationDate){
        return catOwners.stream()
                .filter(owner -> owner.getRegistrationDate().equals(registrationDate))
                .sorted(Comparator.comparing(CatOwner::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public Optional<CatOwner> getCatOwnerById(UUID id){
        return catOwners.stream()
                .filter(owner -> owner.getOwnerId().equals(id))
                .findFirst();
    }

}
