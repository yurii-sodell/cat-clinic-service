package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;
import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import be.kdg.pro3.catclinic.service.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.service.exceptions.MedicationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPrescriptionsService implements PrescriptionService{
    private MedicationRepository medicationRepository;
    private CatRepository catRepository;

    public DefaultPrescriptionsService(CatRepository catRepository, MedicationRepository medicationRepository){
        this.catRepository = catRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<Medication> getAllPrescriptionsFor(CatId catId){
        return medicationRepository.getMedicationsByCatId(catId);
    }

    @Override
    public void assignMedicationFor(CatId catId, MedicationId medicationId)
            throws CatNotFoundException, MedicationNotFoundException{
        //not a best solution, but we don't have subtable that would solve many to many, so it is what it is

        Optional<Medication> foundMedication = medicationRepository.getMedicationIdById(medicationId);
        Medication medication = foundMedication.orElseThrow(MedicationNotFoundException::new);

        Optional<Cat> foundCat = catRepository.getCatById(catId);
        Cat cat = foundCat.orElseThrow(CatNotFoundException::new);

        medication.prescribeTo(cat.getCatId());
        cat.prescribe(medication.getProductId());

        medicationRepository.save(medication);
        catRepository.save(cat);
    }
}
