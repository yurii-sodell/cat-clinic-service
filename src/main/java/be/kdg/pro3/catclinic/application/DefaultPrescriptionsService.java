package be.kdg.pro3.catclinic.application;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;
import be.kdg.pro3.catclinic.application.dto.PrescriptionResponseEntity;
import be.kdg.pro3.catclinic.presistence.CatRepository;
import be.kdg.pro3.catclinic.presistence.MedicationRepository;
import be.kdg.pro3.catclinic.application.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.application.exceptions.MedicationNotFoundException;
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
    public PrescriptionResponseEntity getAllPrescriptionsFor(CatId catId){
        Cat cat = catRepository.getCatById(catId)
                .orElseThrow(CatNotFoundException::new);

        PrescriptionResponseEntity prescriptionEntity = new PrescriptionResponseEntity();

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

    @Override
    public List<Medication> getMedicationThatCanBePrescribedFor(CatId catId){
        Cat cat = catRepository.getCatById(catId)
                .orElseThrow(CatNotFoundException::new);

        return medicationRepository.getMedicationsByAgeAllowance(cat.getAgeInWeeks());
    }
}
