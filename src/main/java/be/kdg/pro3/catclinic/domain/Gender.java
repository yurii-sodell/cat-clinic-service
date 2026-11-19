package be.kdg.pro3.catclinic.domain;

public enum Gender {
    MALE("male-lang"),
    FEMALE("female-lang");

    private final String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }
}