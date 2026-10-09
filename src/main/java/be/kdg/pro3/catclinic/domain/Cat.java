package be.kdg.pro3.catclinic.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cat{
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private UUID catId;
    private double weightInKilos;
    private int visits;
    private boolean isHospitalized;
    private String photo;
    private String BelongingOwnerId;
    private List<Medication> medicationsThatWasAssignedToCat;

    public Cat(String name, LocalDate dateOfBirth, Gender gender, UUID catId,
               double weightInKilos, int visits, boolean isHospitalized,
               String photo, String BelongingOwnerId) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.catId = catId;
        this.weightInKilos = weightInKilos;
        this.visits = visits;
        this.isHospitalized = isHospitalized;
        this.photo = photo;
        this.BelongingOwnerId = BelongingOwnerId;
        this.medicationsThatWasAssignedToCat = new ArrayList<>();
    }

    public String getBelongingOwnerId(){
        return BelongingOwnerId;
    }

    public UUID getCatId(){
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
    }

    public int getAge(){
        return LocalDate.now().getYear() - this.dateOfBirth.getYear();
    }

    @Override
    public String toString() {
        DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "\n%-20s | %-7s | %-12s | %-8.2f | %-6d | %-13s | %-20s | %-15s",
                this.name,
                this.gender.toString(),
                birthdayFormat.format(this.dateOfBirth),
                this.weightInKilos,
                this.visits,
                this.isHospitalized ? "Hospitalized" : "Not hospitalized",
                this.photo,
                this.BelongingOwnerId
        );
    }
}
