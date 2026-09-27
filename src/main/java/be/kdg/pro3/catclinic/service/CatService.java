package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.repository.CatOwnerRepository;
import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService{
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository){
        this.catRepository = catRepository;
    }

    public List<Cat> getAllCats(){
        return catRepository.getCats();
    }
}
