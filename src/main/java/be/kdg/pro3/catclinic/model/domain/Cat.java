package be.kdg.pro3.catclinic.model.domain;

import be.kdg.pro3.catclinic.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Cat{
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String catId;
    private double weightInKilos;
    private int visits;
    private boolean isHospitalized;
    private String photo;
    private String belongingFamilyId;
    private List<Medication> medicationsThatWasAssignedToCat;

    public Cat(String name, LocalDate dateOfBirth, Gender gender, String catId,
               double weightInKilos, int visits, boolean isHospitalized,
               String photo, String belongingFamilyId) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.catId = catId;
        this.weightInKilos = weightInKilos;
        this.visits = visits;
        this.isHospitalized = isHospitalized;
        this.photo = photo;
        this.belongingFamilyId = belongingFamilyId;
        this.medicationsThatWasAssignedToCat = new ArrayList<>();
    }


    public String getBelongingFamilyId(){
        return belongingFamilyId;
    }

    public String getCatId(){
        return catId;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }

    public Gender getGender(){
        return gender;
    }

    public boolean isHospitalized(){
        return isHospitalized;
    }

    public String getName(){
        return name;
    }

    public String getPhoto(){
        return photo;
    }

    public double getWeightInKilos(){
        return weightInKilos;
    }

    public int getVisits(){
        return visits;
    }

    public List<Medication> getMedicationsThatWasAssignedToCat(){
        return medicationsThatWasAssignedToCat;
    }

    public void addMedicationsThatWasAssignedToCat(Medication medication){
        this.medicationsThatWasAssignedToCat.add(medication);
        medication.getCatsThatMedicationWasAssignedTo().add(this);
    }

    @Override
    public String toString(){
        DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format("\nCat: %s" +
                "\nid: %s" +
                "\nGender: %s" +
                "\nDate of birth: %s",
                this.name,
                this.catId,
                this.gender.toString(),
                birthdayFormat.format(this.dateOfBirth));
    }
}
