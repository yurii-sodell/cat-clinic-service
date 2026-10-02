package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Medication;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryMedicationRepository implements MedicationRepository{
    private static List<Medication> medications = new ArrayList<>();

    @Override
    public void saveMedication(Medication medication){
        medications.add(medication);
    }

    @Override
    public void saveMedications(List<Medication> newMedications){
        medications.addAll(newMedications);
    }

    @Override
    public List<Medication> getMedications(){
        return Collections.unmodifiableList(medications);
    }

    @Override
    public List<Medication> getMedicationFilteredByPrescriptionNeed(boolean isPrescriptionNeeded){
        return  medications.stream()
                .filter(medication -> medication.isPrescriptionNeeded() == isPrescriptionNeeded)
                .toList();
    }

    @Override
    public List<Medication> getMedicationFilteredByPrice(double minPrice, double maxPrice){
        return medications.stream()
                .filter(medication -> medication.getPrice() >= minPrice && medication.getPrice() <= maxPrice)
                .toList();
    }

}
