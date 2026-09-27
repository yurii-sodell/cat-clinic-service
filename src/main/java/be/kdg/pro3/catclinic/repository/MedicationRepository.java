package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Medication;

import java.util.List;

public interface MedicationRepository{
    void saveMedication(Medication medication);
    void saveMedications(List<Medication> medications);
    List<Medication> getMedications();
}
