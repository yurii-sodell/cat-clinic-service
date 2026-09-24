package be.kdg.pro3.catclinic;

import be.kdg.pro3.catclinic.model.domain.Cat;
import be.kdg.pro3.catclinic.model.domain.Medication;
import be.kdg.pro3.catclinic.model.domain.CatOwner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataFactory{
    public static List<Cat> catsThatMedicationPrescriptedTo = new ArrayList<>();
    public static List<Medication> prescriptionsThatWasPrescriptedToCat = new ArrayList<>();

    public static void seed(){
        CatOwner catOwner1 = new CatOwner();
        CatOwner catOwner2 = new CatOwner();
        CatOwner catOwner3 = new CatOwner();
        CatOwner catOwner4 = new CatOwner();

        Cat cat1 = new Cat("Whiskers", LocalDate.of(2020, 3, 14), Gender.FEMALE, "CAT-001",
                8.5, 3, false, "whiskers.jpg", "FAM-101");
        Cat cat2 = new Cat("Simba", LocalDate.of(2019, 7, 22), Gender.MALE, "CAT-002",
                6.2, 7, true, "simba.jpg", "FAM-101");

        Cat cat3 = new Cat("Tom", LocalDate.of(2021, 1, 5), Gender.FEMALE, "CAT-003",
                9.1, 1, false, "Tom.jpg", "FAM-102");

        Cat cat4 = new Cat("AsteroidDestroyer", LocalDate.of(2018, 11, 30), Gender.MALE, "CAT-004",
                5.7, 12, false, "AsteroidDestroyer.jpg", "FAM-103");

        Cat cat5 = new Cat("MrPaw", LocalDate.of(2022, 5, 18), Gender.MALE, "CAT-005",
                7.8, 2, true, "bella.jpg", "FAM-104");

        catOwner1.getCats().addAll(List.of(cat1, cat2, cat3));
        catOwner2.getCats().addAll(List.of(cat4, cat5));

        Medication Amoxicillin =
                new Medication("Amoxicillin", 12.50, "MED-001",
                        4, LocalDate.of(2015, 6, 1), MedicationType.PILLS,
                        true, false, "amoxicillin.jpg");

        Medication FrontlinePlus =         new Medication("Frontline Plus", 24.99, "MED-002",
                8, LocalDate.of(2010, 3, 15), MedicationType.OINTMENT,
                false, false, "frontline.jpg");

        Medication Metacam =         new Medication("Metacam", 18.75, "MED-003",
                6, LocalDate.of(2018, 9, 10), MedicationType.SYRUP,
                true, false, "metacam.jpg");

        Medication Revolution =         new Medication("Revolution", 32.00, "MED-004",
                6, LocalDate.of(2012, 1, 20), MedicationType.INJECTION,
                true, false, "revolution.jpg");

        Medication VitaminCatTreats =        new Medication("Vitamin Cat Treats", 9.99, "MED-005",
                0, LocalDate.of(2019, 4, 5), MedicationType.POWDER,
                false, true, "vitamintreats.jpg");

        prescriptionsThatWasPrescriptedToCat.addAll(List.of(Amoxicillin, FrontlinePlus, Metacam, Revolution, VitaminCatTreats));

        prescribe(cat1, VitaminCatTreats);
        prescribe(cat2, VitaminCatTreats);
        prescribe(cat3, Metacam);

        catsThatMedicationPrescriptedTo.forEach(cat -> System.out.println(cat));
    }

    private static void prescribe(Cat cat, Medication medication){
        cat.addMedicationsThatWasAssignedToCat(medication);
        medication.addCatsThatMedicationWasAssignedTo(cat);
    }
}


