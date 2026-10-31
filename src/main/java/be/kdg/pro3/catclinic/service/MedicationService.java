package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;
import be.kdg.pro3.catclinic.domain.MedicationType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicationService{
    List<Medication> getAllMedications();
    Optional<Medication> getMedicationById(MedicationId medicationId);
    List<Medication> getMedicationFilteredByPrescriptionNeed(boolean isPrescriptionNeeded);
    List<Medication> getMedicationFilteredByPrice(BigDecimal minPrice, BigDecimal maxPrice);
    void registerNewMedication(String title, BigDecimal price,
                               int weeksAfterBirthToStartMedication, LocalDate marketReleaseDate,
                               MedicationType medicationType, boolean isPrescriptionNeeded,
                               boolean canBeGiftedOnCatsBirthday, String photo);

}
