package be.kdg.pro3.catclinic.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cat{
    @Getter private String name;
    @Getter private LocalDate dateOfBirth;
    @Getter private Gender gender;
    @Getter private CatId catId;
    @Getter private double weightInKilos;
    @Getter private int visits;
    @Getter private boolean isHospitalized;
    @Getter private String photo;
    @Getter private CatOwnerId BelongingOwnerId;
    private List<MedicationId> medicationsThatWasAssignedToCat;

    public Cat(String name, LocalDate dateOfBirth, Gender gender, CatId catId,
               double weightInKilos, int visits, boolean isHospitalized,
               String photo, CatOwnerId belongingOwnerId){
        if(catId == null) throw new IllegalArgumentException("Cat id cannot be null");
        if(belongingOwnerId == null) throw new IllegalArgumentException("Cat owners ids cannot be null");
        if(visits < 0) throw new IllegalArgumentException("Visits cannot be less then null");
        this.catId = catId;
        this.visits = visits;
        this.isHospitalized = isHospitalized;
        this.BelongingOwnerId = belongingOwnerId;

        setName(name);
        setDateOfBirth(dateOfBirth);
        setGender(gender);
        setWeightInKilos(weightInKilos);
        setPhoto(photo);
        this.medicationsThatWasAssignedToCat = new ArrayList<>();
    }



    public void setDateOfBirth(LocalDate dateOfBirth){
        if(dateOfBirth.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date of birth must be in the past");
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
        if(photo == null) photo = "";
        this.photo = photo;
    }

    public void setWeightInKilos(double weightInKilos) throws IllegalArgumentException{
        if(weightInKilos < 0) throw new IllegalArgumentException("Weight cannot be less then 0");
        this.weightInKilos = weightInKilos;
    }

    public void prescribe(MedicationId medicationId ){
        this.medicationsThatWasAssignedToCat.add(medicationId);
    }

    public int getAge(){
        return LocalDate.now().getYear() - this.dateOfBirth.getYear();
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
    public String toString(){
        DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "%n%-20s | %-7s | %-12s | %-8.2f | %-6d | %-13s | %-20s | %-15s",
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
