package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.CatId;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;
import be.kdg.pro3.catclinic.presentation.web.PrescriptionEntity;

import java.util.List;

public interface PrescriptionService{
    PrescriptionEntity getAllPrescriptionsFor(CatId catId);
    void assignMedicationFor(CatId catId, MedicationId medicationId);
}
