package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.*;
import be.kdg.pro3.catclinic.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.exceptions.OwnerNotFoundException;
import be.kdg.pro3.catclinic.repository.CatOwnerRepository;
import be.kdg.pro3.catclinic.repository.CatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DefaultCatOwnerService implements CatOwnerService{
    private final CatOwnerRepository catOwnerRepository;
    private final CatRepository catRepository;

    public DefaultCatOwnerService(CatOwnerRepository catOwnerRepository, CatRepository catRepository){
        this.catOwnerRepository = catOwnerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public List<CatOwner> getAllOwners(){
        return catOwnerRepository.getCatOwners();
    }

    @Override
    public List<CatOwner> getCatOwnerByCatsName(String name) throws CatNotFoundException{
        List<Cat> foundCats = catRepository.getCatByName(name);
        if(foundCats.isEmpty()) throw new CatNotFoundException();
        return foundCats.stream()
                .map(Cat::getBelongingOwnerId)
                .map(catOwnerRepository::getCatOwnerById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }



    @Override
    public List<CatOwner> getCatOwnerByItsName(String name){
        return catOwnerRepository.getCatOwnerByItsName(name);
    }

    @Override
    public List<CatOwner> getCatOwnerByItsLanguage(PreferredCommunicationLanguage language){
        return catOwnerRepository.getCatOwnerByItsLanguage(language);
    }

    @Override
    public List<CatOwner> getOwnersByRegistrationDate(LocalDate registrationDate){
        return catOwnerRepository.getOwnersByRegistrationDate(registrationDate);
    }

    @Override
    public void registerNewOwner(String name, String familyName,
                                 double livingSpaceSquareMeters, int amountOfKids,
                                 String ownerPersonsPhone, String photoOfOwnersIdCard,
                                 PreferredCommunicationLanguage preferredCommunicationLanguage,
                                 boolean smokesAtHome){
        catOwnerRepository.saveOwner(
                new CatOwner(
                        amountOfKids,
                        familyName,
                        livingSpaceSquareMeters,
                        name,
                        CatOwnerId.random(),
                        ownerPersonsPhone,
                        photoOfOwnersIdCard,
                        preferredCommunicationLanguage,
                        LocalDate.now(),
                        smokesAtHome,
                        new ArrayList<>()
                ));

    }

    @Override
    public CatOwner getCatOwnerById(CatOwnerId id)  throws OwnerNotFoundException{
        Optional<CatOwner> found = catOwnerRepository.getCatOwnerById(id);
        if(found.isPresent()) return found.get();
        throw new OwnerNotFoundException();
    }
}
