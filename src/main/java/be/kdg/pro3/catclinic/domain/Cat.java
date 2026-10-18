package be.kdg.pro3.catclinic.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Cat{
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
    private UUID catId;
    private Double weightInKilos;
    private int visits;
    private boolean isHospitalized;
    private String photo;
    private String BelongingOwnerId;
    private List<Medication> medicationsThatWasAssignedToCat;

    public Cat(String name, LocalDate dateOfBirth, Gender gender, UUID catId,
               Double weightInKilos, int visits, boolean isHospitalized,
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

    public Double getWeightInKilos(){
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

    public void setDateOfBirth(LocalDate dateOfBirth){
        if(dateOfBirth.isAfter(LocalDate.now())) throw new IllegalArgumentException("Date of birth must be in the past");
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(Gender gender){
        if(gender == null) throw new IllegalArgumentException("Gender is not provided");
        this.gender = gender;
    }

    public void setName(String name){
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name cannot be empty");
        this.name = name;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public void setWeightInKilos(Double weightInKilos) throws IllegalArgumentException{
        if(weightInKilos < 0) throw new IllegalArgumentException("Weight cannot be less then 0");
        this.weightInKilos = weightInKilos;
    }

    @Override
    public boolean equals(Object o){
        if(o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(catId, cat.catId);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(catId);
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
