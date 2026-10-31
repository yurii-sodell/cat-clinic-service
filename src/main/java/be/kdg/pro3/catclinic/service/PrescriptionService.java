package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;

import java.util.List;

public interface PrescriptionService{
    List<Medication> getAllPrescriptionsFor(CatId catId);
    void assignMedicationFor(CatId catId, MedicationId medicationId);
}
