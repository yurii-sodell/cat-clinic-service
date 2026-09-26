package be.kdg.pro3.catclinic.model.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Medication{
    private String title;
    private double price;
    private String productId;
    private int weeksAfterBirthToStartMedication;
    private LocalDate marketReleaseDate;
    private MedicationType medicationType;
    private boolean isPrescriptionNeeded;
    private boolean canBeGiftedOnCatsBirthday;
    private String photo;
    private List<Cat> catsThatMedicationWasAssignedTo;

    public Medication(String title, double price, String productId,
                      int weeksAfterBirthToStartMedication, LocalDate marketReleaseDate,
                      MedicationType medicationType, boolean isPrescriptionNeeded,
                      boolean canBeGiftedOnCatsBirthday, String photo) {
        this.title = title;
        this.price = price;
        this.productId = productId;
        this.weeksAfterBirthToStartMedication = weeksAfterBirthToStartMedication;
        this.marketReleaseDate = marketReleaseDate;
        this.medicationType = medicationType;
        this.isPrescriptionNeeded = isPrescriptionNeeded;
        this.canBeGiftedOnCatsBirthday = canBeGiftedOnCatsBirthday;
        this.photo = photo;
        this.catsThatMedicationWasAssignedTo = new ArrayList<>();
    }

    public boolean isCanBeGiftedOnCatsBirthday(){
        return canBeGiftedOnCatsBirthday;
    }

    public boolean isPrescriptionNeeded(){
        return isPrescriptionNeeded;
    }

    public LocalDate getMarketReleaseDate(){
        return marketReleaseDate;
    }

    public MedicationType getMedicationType(){
        return medicationType;
    }

    public String getPhoto(){
        return photo;
    }

    public double getPrice(){
        return price;
    }

    public String getProductId(){
        return productId;
    }

    public String getTitle(){
        return title;
    }

    public int getWeeksAfterBirthToStartMedication(){
        return weeksAfterBirthToStartMedication;
    }

    public void assignCatToMedication(Cat cat){
        catsThatMedicationWasAssignedTo.add(cat);
    }

    public List<Cat> getCatsThatMedicationWasAssignedTo(){
        return catsThatMedicationWasAssignedTo;
    }

    public void addCatsThatMedicationWasAssignedTo(Cat cat){
        this.catsThatMedicationWasAssignedTo.add(cat);
    }
    @Override
    public String toString() {
        DateTimeFormatter releaseFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "\n%-20s| %-10s| %-8.2f| %-10s| %-6d| %-12s| %-13s| %-20s",
                this.title,
                this.productId,
                this.price,
                this.medicationType.toString(),
                this.weeksAfterBirthToStartMedication,
                releaseFormat.format(this.marketReleaseDate),
                this.isPrescriptionNeeded ? "Prescription" : "No prescription",
                this.canBeGiftedOnCatsBirthday ? "Giftable" : "Not giftable"
        );
    }
}
