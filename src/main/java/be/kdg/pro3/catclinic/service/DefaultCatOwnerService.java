package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import be.kdg.pro3.catclinic.exceptions.OwnerNotFoundException;
import be.kdg.pro3.catclinic.repository.CatOwnerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultCatOwnerService implements CatOwnerService{
    private final CatOwnerRepository catOwnerRepository;

    public DefaultCatOwnerService(CatOwnerRepository catOwnerRepository){
        this.catOwnerRepository = catOwnerRepository;
    }

    @Override
    public List<CatOwner> getAllOwners(){
        return catOwnerRepository.getCatOwners();
    }

    @Override
    public List<CatOwner> getCatOwnerByCatsName(String name){
        return catOwnerRepository.getCatOwnerByCatsName(name);
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
        catOwnerRepository.saveOwner(CatOwner.registerCatOwner(amountOfKids,
                familyName,
                livingSpaceSquareMeters,
                name,
                ownerPersonsPhone,
                photoOfOwnersIdCard,
                preferredCommunicationLanguage,
                smokesAtHome));
    }

    public CatOwner getCatOwnerById(UUID id) throws OwnerNotFoundException{
        Optional<CatOwner> found = catOwnerRepository.getCatOwnerById(id);
        if(found.isPresent()) return found.get();
        throw new OwnerNotFoundException("Cannot find an owner with id" + id.toString());
    }
}
