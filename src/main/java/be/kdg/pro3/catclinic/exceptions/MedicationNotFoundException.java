package be.kdg.pro3.catclinic.exceptions;

public class MedicationNotFoundException extends RuntimeException{
    public MedicationNotFoundException(){
        super("Medication not found!");
    }
}
