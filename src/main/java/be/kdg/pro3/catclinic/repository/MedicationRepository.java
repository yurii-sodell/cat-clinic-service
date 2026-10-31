package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MedicationRepository{
    void save(Medication medication);
    void saveMedications(List<Medication> medications);
    List<Medication> getMedications();
    List<Medication> getMedicationFilteredByPrescriptionNeed(boolean isPrescriptionNeeded);
    List<Medication> getMedicationFilteredByPrice(BigDecimal minPrice, BigDecimal maxPrice);
    Optional<Medication> getMedicationIdByName(String name);
    Optional<Medication> getMedicationIdById(MedicationId medicationId);
}
