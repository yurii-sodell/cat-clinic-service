package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicationService{
    List<Medication> getAllMedications();

    List<Medication> getMedicationFilteredByPrescriptionNeed(boolean isPrescriptionNeeded);
    List<Medication> getMedicationFilteredByPrice(double minPrice, double maxPrice);
    void registerNewMedication(String title, double price,
                               int weeksAfterBirthToStartMedication, LocalDate marketReleaseDate,
                               MedicationType medicationType, boolean isPrescriptionNeeded,
                               boolean canBeGiftedOnCatsBirthday, String photo);

}
