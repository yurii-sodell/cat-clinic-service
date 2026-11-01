package be.kdg.pro3.catclinic.presentation.web;

import be.kdg.pro3.catclinic.domain.Medication;

import java.util.HashMap;
import java.util.Map;

public class PrescriptionEntity{
    private final Map<Medication, Integer> medicationAndAmountPrescripted;

    public PrescriptionEntity(){
        this.medicationAndAmountPrescripted = new HashMap<>();
    }

    public void add(Medication medication) {
        medicationAndAmountPrescripted.merge(medication, 1, Integer::sum);
    }

    public Map<Medication, Integer> getMedicationAndAmountPrescripted(){
        return medicationAndAmountPrescripted;
    }
}
