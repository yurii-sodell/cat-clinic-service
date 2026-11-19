package be.kdg.pro3.catclinic.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Medication{
    @Getter private String title;
    @Getter private BigDecimal price;
    @Getter private MedicationId productId;
    @Getter private int weeksAfterBirthToStartMedication;
    @Getter private LocalDate marketReleaseDate;
    @Getter private MedicationType medicationType;
    @Getter private boolean isPrescriptionNeeded;
    @Getter private boolean canBeGiftedOnCatsBirthday;
    @Getter private String photo;
    private List<CatId> catsThatMedicationWasAssignedTo;

    public Medication(String title, BigDecimal price, MedicationId productId,
                      int weeksAfterBirthToStartMedication, LocalDate marketReleaseDate,
                      MedicationType medicationType, boolean isPrescriptionNeeded,
                      boolean canBeGiftedOnCatsBirthday, String photo){
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

    public void prescribeTo(CatId catId){
        catsThatMedicationWasAssignedTo.add(catId);
    }

    public List<CatId> getCatsThatMedicationWasAssignedTo(){
        return Collections.unmodifiableList(catsThatMedicationWasAssignedTo);
    }

    @Override
    public String toString(){
        DateTimeFormatter releaseFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return String.format(
                "%n%-20s | %-10s | %-8.2f | %-10s | %-6d | %-12s | %-13s | %-20s",
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

    @Override
    public boolean equals(Object o){
        if(o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(productId);
    }
}
