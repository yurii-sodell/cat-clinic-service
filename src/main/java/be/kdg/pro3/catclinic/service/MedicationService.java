package be.kdg.pro3.catclinic.service;

import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService{
    private MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository){
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> getAllMedications(){
        return medicationRepository.getMedications();
    }
}
