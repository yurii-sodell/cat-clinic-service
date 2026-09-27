package be.kdg.pro3.catclinic.repository;

import be.kdg.pro3.catclinic.domain.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataFactory{
    private final CatOwnerRepository catOwnerRepository;
    private final MedicationRepository medicationRepository;
    private final CatRepository catRepository;

    public DataFactory(CatOwnerRepository catOwnerRepository, MedicationRepository medicationRepository,CatRepository catRepository){
        this.catRepository = catRepository;
        this.medicationRepository = medicationRepository;
        this.catOwnerRepository = catOwnerRepository;
        seed();
    }

    public void seed(){
        CatOwner catOwner1 = new CatOwner(2, "Johnson", 85.5, "Emma Johnson", "OWN-101",
                "+32 470 123 456", "id_johnson.jpg",
                PreferredCommunicationLanguage.EN, LocalDate.of(2019, 5, 12), false);

        CatOwner catOwner2 = new CatOwner(0, "Peeters", 62.0, "Lucas Peeters", "OWN-102",
                "+32 486 234 567", "id_peeters.jpg",
                PreferredCommunicationLanguage.DE, LocalDate.of(2020, 8, 3), true);

        CatOwner catOwner3 = new CatOwner(1, "Martinez", 74.3, "Sofia Martinez", "OWN-103",
                "+32 493 345 678", "id_martinez.jpg",
                PreferredCommunicationLanguage.NL, LocalDate.of(2021, 2, 27), false);

        CatOwner catOwner4 = new CatOwner(3, "De Smet", 110.8, "Noah De Smet", "OWN-104",
                "+32 478 456 789", "id_desmet.jpg",
                PreferredCommunicationLanguage.FR, LocalDate.of(2018, 11, 9), false);

        catOwnerRepository.saveOwners(List.of(catOwner1, catOwner2, catOwner3, catOwner4));

// catOwner1 -> OWN-101
        Cat cat1 = new Cat("Whiskers", LocalDate.of(2020, 3, 14), Gender.FEMALE, "CAT-001",
                8.5, 3, false, "whiskers.jpg", "OWN-101");
        Cat cat2 = new Cat("Simba", LocalDate.of(2019, 7, 22), Gender.MALE, "CAT-002",
                6.2, 7, true, "simba.jpg", "OWN-101");
        Cat cat3 = new Cat("Tom", LocalDate.of(2021, 1, 5), Gender.FEMALE, "CAT-003",
                9.1, 1, false, "Tom.jpg", "OWN-101");

// catOwner2 -> OWN-102
        Cat cat4 = new Cat("AsteroidDestroyer", LocalDate.of(2018, 11, 30), Gender.MALE, "CAT-004",
                5.7, 12, false, "AsteroidDestroyer.jpg", "OWN-102");
        Cat cat5 = new Cat("MrPaw", LocalDate.of(2022, 5, 18), Gender.MALE, "CAT-005",
                7.8, 2, true, "bella.jpg", "OWN-102");

// catOwner3 -> OWN-103
        Cat cat6 = new Cat("Luna", LocalDate.of(2025, 9, 2), Gender.FEMALE, "CAT-006",
                4.9, 4, false, "luna.jpg", "OWN-103");
        Cat cat7 = new Cat("Oliver", LocalDate.of(2017, 2, 11), Gender.MALE, "CAT-007",
                7.3, 9, true, "oliver.jpg", "OWN-103");

// catOwner4 -> OWN-104
        Cat cat8 = new Cat("Milo", LocalDate.of(2023, 6, 27), Gender.MALE, "CAT-008",
                3.4, 0, false, "milo.jpg", "OWN-104");
        Cat cat9 = new Cat("Nala", LocalDate.of(2020, 12, 19), Gender.FEMALE, "CAT-009",
                6.8, 5, false, "nala.jpg", "OWN-104");
        Cat cat10 = new Cat("Shadow", LocalDate.of(2016, 4, 8), Gender.MALE, "CAT-010",
                8.9, 11, true, "shadow.jpg", "OWN-104");

// cat11 -> OWN-103, same name as cat10
        Cat cat11 = new Cat("Shadow", LocalDate.of(2019, 4, 8), Gender.FEMALE, "CAT-011",
                8.9, 11, true, "shadow.jpg", "OWN-103");

        catOwner1.getCats().addAll(List.of(cat1, cat2, cat3));
        catOwner2.getCats().addAll(List.of(cat4, cat5));
        catOwner3.getCats().addAll(List.of(cat6, cat7, cat11));
        catOwner4.getCats().addAll(List.of(cat8, cat9, cat10));

        Medication Amoxicillin =
                new Medication("Amoxicillin", 12.50, "MED-001",
                        4, LocalDate.of(2015, 6, 1), MedicationType.PILLS,
                        true, false, "amoxicillin.jpg");

        Medication FrontlinePlus = new Medication("Frontline Plus", 24.99, "MED-002",
                8, LocalDate.of(2010, 3, 15), MedicationType.OINTMENT,
                false, false, "frontline.jpg");

        Medication Metacam = new Medication("Metacam", 18.75, "MED-003",
                6, LocalDate.of(2018, 9, 10), MedicationType.SYRUP,
                true, false, "metacam.jpg");

        Medication Revolution = new Medication("Revolution", 32.00, "MED-004",
                6, LocalDate.of(2012, 1, 20), MedicationType.INJECTION,
                true, false, "revolution.jpg");

        Medication VitaminCatTreats = new Medication("Vitamin Cat Treats", 9.99, "MED-005",
                0, LocalDate.of(2019, 4, 5), MedicationType.POWDER,
                false, true, "vitamintreats.jpg");

        Medication Panacur = new Medication("Panacur", 15.30, "MED-006",
                5, LocalDate.of(2016, 8, 12), MedicationType.PILLS,
                true, false, "panacur.jpg");

        Medication ConvenIa = new Medication("Convenia", 45.00, "MED-007",
                3, LocalDate.of(2013, 11, 2), MedicationType.INJECTION,
                true, false, "convenia.jpg");

        Medication HillsZD = new Medication("Hill's z/d Diet", 21.40, "MED-008",
                0, LocalDate.of(2020, 2, 17), MedicationType.POWDER,
                false, true, "hillszd.jpg");

        medicationRepository.saveMedications(List.of(
                Amoxicillin, FrontlinePlus, Metacam, Revolution, VitaminCatTreats,
                Panacur, ConvenIa, HillsZD));

        catRepository.saveCats(List.of(
                cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11));

        prescribe(cat1, VitaminCatTreats);
        prescribe(cat2, VitaminCatTreats);
        prescribe(cat3, Metacam);
        prescribe(cat4, Panacur);
        prescribe(cat5, FrontlinePlus);
        prescribe(cat6, Amoxicillin);
        prescribe(cat7, ConvenIa);
        prescribe(cat7, Revolution);
        prescribe(cat8, HillsZD);
        prescribe(cat9, VitaminCatTreats);
        prescribe(cat10, Metacam);
        prescribe(cat10, Panacur);
    }

    private static void prescribe(Cat cat, Medication medication){
        cat.addMedicationsThatWasAssignedToCat(medication);
        medication.addCatsThatMedicationWasAssignedTo(cat);
    }

}