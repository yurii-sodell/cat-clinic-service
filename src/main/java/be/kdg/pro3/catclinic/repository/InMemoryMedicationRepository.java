package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.MedicationId;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class InMemoryMedicationRepository implements MedicationRepository{
    private static HashSet<Medication> medications = new HashSet<>();

    @Override
    public void saveMedication(Medication medication){
        medications.remove(medication);
        medications.add(medication);
    }

    @Override
    public void saveMedications(List<Medication> newMedications){
        medications.addAll(newMedications);
    }

    @Override
    public List<Medication> getMedications(){
        return List.copyOf(medications);
    }

    @Override
    public List<Medication> getMedicationFilteredByPrescriptionNeed(boolean isPrescriptionNeeded){
        return medications.stream()
                .filter(medication -> medication.isPrescriptionNeeded() == isPrescriptionNeeded)
                .toList();
    }

    @Override
    public List<Medication> getMedicationFilteredByPrice(BigDecimal minPrice, BigDecimal maxPrice){
        return medications.stream()
                .filter(medication -> medication.getPrice().compareTo(minPrice) >= 0
                        && medication.getPrice().compareTo(maxPrice) <= 0)
                .sorted(Comparator.comparing(Medication::getPrice))
                .toList();
    }

    @Override
    public Optional<Medication> getMedicationIdByName(String name){
        return medications.stream().filter(medication ->
                medication.getTitle().equalsIgnoreCase(name))
                .findAny();
    }

}
