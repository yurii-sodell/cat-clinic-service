package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;
import be.kdg.pro3.catclinic.domain.MedicationType;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
public class DefaultMedicationService implements MedicationService{
    private final MedicationRepository medicationRepository;

    public DefaultMedicationService(MedicationRepository medicationRepository){
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<Medication> getAllMedications(){
        return medicationRepository.getMedications();
    }

    @Override
    public List<Medication> getMedicationFilteredByPrescriptionNeed(boolean isPrescriptionNeeded){
        return medicationRepository.getMedicationFilteredByPrescriptionNeed(isPrescriptionNeeded);
    }

    @Override
    public List<Medication> getMedicationFilteredByPrice(BigDecimal minPrice, BigDecimal maxPrice){
        return medicationRepository.getMedicationFilteredByPrice(minPrice, maxPrice);
    }

    @Override
    public void registerNewMedication(String title, BigDecimal price, int weeksAfterBirthToStartMedication, LocalDate marketReleaseDate, MedicationType medicationType, boolean isPrescriptionNeeded, boolean canBeGiftedOnCatsBirthday, String photo){
        Medication medication = new Medication(title, price, MedicationId.random(), weeksAfterBirthToStartMedication, marketReleaseDate, medicationType, isPrescriptionNeeded, canBeGiftedOnCatsBirthday, photo);
        medicationRepository.saveMedication(medication);
    }

}
