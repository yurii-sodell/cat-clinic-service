package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;
import be.kdg.pro3.catclinic.presentation.web.PrescriptionEntity;
import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import be.kdg.pro3.catclinic.service.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.service.exceptions.MedicationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultPrescriptionsService implements PrescriptionService{
    private MedicationRepository medicationRepository;
    private CatRepository catRepository;

    public DefaultPrescriptionsService(CatRepository catRepository, MedicationRepository medicationRepository){
        this.catRepository = catRepository;
        this.medicationRepository = medicationRepository;
    }

    //still not the best solution, could be better with subtable
    @Override
    public PrescriptionEntity getAllPrescriptionsFor(CatId catId){
        Cat cat = catRepository.getCatById(catId)
                .orElseThrow(CatNotFoundException::new);

        PrescriptionEntity prescriptionEntity = new PrescriptionEntity();

        cat.getMedicationsThatWasAssignedToCat().stream()
                .map(medicationRepository::getMedicationIdById)
                .map(optional -> optional.orElseThrow(MedicationNotFoundException::new))
                .forEach(prescriptionEntity::add);
        return prescriptionEntity;
    }

    @Override
    public void assignMedicationFor(CatId catId, MedicationId medicationId)
            throws CatNotFoundException, MedicationNotFoundException{
        //not a best solution, but we don't have subtable that would solve many to many, so it is what it is

        Medication medication = medicationRepository.getMedicationIdById(medicationId)
                .orElseThrow(MedicationNotFoundException::new);
        Cat cat = catRepository.getCatById(catId)
                .orElseThrow(CatNotFoundException::new);

        medication.prescribeTo(cat.getCatId());
        cat.prescribe(medication.getProductId());

        medicationRepository.save(medication);
        catRepository.save(cat);
    }
}
