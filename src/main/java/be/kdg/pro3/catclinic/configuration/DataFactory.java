package be.kdg.pro3.catclinic.configuration;

import be.kdg.pro3.catclinic.domain.*;
import be.kdg.pro3.catclinic.repository.CatOwnerRepository;
import be.kdg.pro3.catclinic.repository.CatRepository;
import be.kdg.pro3.catclinic.repository.MedicationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
@ConditionalOnProperty(name = "db.autofill", havingValue = "true")
public class DataFactory{
    private final CatOwnerRepository catOwnerRepository;
    private final MedicationRepository medicationRepository;
    private final CatRepository catRepository;

    public DataFactory(CatOwnerRepository catOwnerRepository, MedicationRepository medicationRepository, CatRepository catRepository){
        this.catRepository = catRepository;
        this.medicationRepository = medicationRepository;
        this.catOwnerRepository = catOwnerRepository;
    }

    private static void prescribe(Cat cat, Medication medication){
        cat.addMedicationsThatWasAssignedToCat(medication);
        medication.addCatsThatMedicationWasAssignedTo(cat);
    }

    @PostConstruct
    public void seed() {

        CatOwnerId ownerId1 = CatOwnerId.random();
        CatOwnerId ownerId2 = CatOwnerId.random();
        CatOwnerId ownerId3 = CatOwnerId.random();
        CatOwnerId ownerId4 = CatOwnerId.random();

        CatOwner catOwner1 = new CatOwner(2, "Johnson", 85.5, "Emma", ownerId1,
                "+32 470 123 456", "id_johnson.jpg",
                PreferredCommunicationLanguage.EN, LocalDate.of(2019, 5, 12), false, new ArrayList<>());

        CatOwner catOwner2 = new CatOwner(0, "Peeters", 62.0, "Lucas", ownerId2,
                "+32 486 234 567", "id_peeters.jpg",
                PreferredCommunicationLanguage.DE, LocalDate.of(2020, 8, 3), true, new ArrayList<>());

        CatOwner catOwner3 = new CatOwner(1, "Martinez", 74.3, "Sofia", ownerId3,
                "+32 493 345 678", "id_martinez.jpg",
                PreferredCommunicationLanguage.NL, LocalDate.of(2021, 2, 27), false, new ArrayList<>());

        CatOwner catOwner4 = new CatOwner(3, "De Smet", 110.8, "Noah", ownerId4,
                "+32 478 456 789", "id_desmet.jpg",
                PreferredCommunicationLanguage.FR, LocalDate.of(2018, 11, 9), false, new ArrayList<>());

        // catOwner1
        Cat cat1 = new Cat("Whiskers", LocalDate.of(2020, 3, 14), Gender.FEMALE, CatId.random(),
                8.5, 3, false, "whiskers.jpg", ownerId1);
        Cat cat2 = new Cat("Simba", LocalDate.of(2019, 7, 22), Gender.MALE, CatId.random(),
                6.2, 7, true, "simba.jpg", ownerId1);
        Cat cat3 = new Cat("Tom", LocalDate.of(2021, 1, 5), Gender.FEMALE, CatId.random(),
                9.1, 1, false, "Tom.jpg", ownerId1);

        // catOwner2
        Cat cat4 = new Cat("AsteroidDestroyer", LocalDate.of(2018, 11, 30), Gender.MALE, CatId.random(),
                5.7, 12, false, "AsteroidDestroyer.jpg", ownerId2);
        Cat cat5 = new Cat("MrPaw", LocalDate.of(2022, 5, 18), Gender.MALE, CatId.random(),
                7.8, 2, true, "bella.jpg", ownerId2);

        // catOwner3
        Cat cat6 = new Cat("Luna", LocalDate.of(2025, 9, 2), Gender.FEMALE, CatId.random(),
                4.9, 4, false, "luna.jpg", ownerId3);
        Cat cat7 = new Cat("Oliver", LocalDate.of(2017, 2, 11), Gender.MALE, CatId.random(),
                7.3, 9, true, "oliver.jpg", ownerId3);
        // cat11 -> catOwner3, то же имя, что у cat10
        Cat cat11 = new Cat("Shadow", LocalDate.of(2019, 4, 8), Gender.FEMALE, CatId.random(),
                8.9, 11, true, "shadow.jpg", ownerId3);

        // catOwner4
        Cat cat8 = new Cat("Milo", LocalDate.of(2023, 6, 27), Gender.MALE, CatId.random(),
                3.4, 0, false, "milo.jpg", ownerId4);
        Cat cat9 = new Cat("Nala", LocalDate.of(2020, 12, 19), Gender.FEMALE, CatId.random(),
                6.8, 5, false, "nala.jpg", ownerId4);
        Cat cat10 = new Cat("Shadow", LocalDate.of(2016, 4, 8), Gender.MALE, CatId.random(),
                8.9, 11, true, "shadow.jpg", ownerId4);


        catOwner1.getCats().addAll(List.of(cat1.getCatId(), cat2.getCatId(), cat3.getCatId()));
        catOwner2.getCats().addAll(List.of(cat4.getCatId(), cat5.getCatId()));
        catOwner3.getCats().addAll(List.of(cat6.getCatId(), cat7.getCatId(), cat11.getCatId()));
        catOwner4.getCats().addAll(List.of(cat8.getCatId(), cat9.getCatId(), cat10.getCatId()));

        catOwnerRepository.saveOwners(List.of(catOwner1, catOwner2, catOwner3, catOwner4));

        Medication Amoxicillin =
                new Medication("Amoxicillin", BigDecimal.valueOf(2.50), MedicationId.random(),
                        4, LocalDate.of(2015, 6, 1), MedicationType.PILLS,
                        true, false, "amoxicillin.jpg");

        Medication FrontlinePlus = new Medication("Frontline Plus", BigDecimal.valueOf(24.99),MedicationId.random() ,
                8, LocalDate.of(2010, 3, 15), MedicationType.OINTMENT,
                false, false, "frontline.jpg");

        Medication Metacam = new Medication("Metacam", BigDecimal.valueOf(18.75), MedicationId.random(),
                6, LocalDate.of(2018, 9, 10), MedicationType.SYRUP,
                true, false, "metacam.jpg");

        Medication Revolution = new Medication("Revolution", BigDecimal.valueOf(32.00), MedicationId.random(),
                6, LocalDate.of(2012, 1, 20), MedicationType.INJECTION,
                true, false, "revolution.jpg");

        Medication VitaminCatTreats = new Medication("Vitamin Cat Treats", BigDecimal.valueOf(9.99), MedicationId.random(),
                0, LocalDate.of(2019, 4, 5), MedicationType.POWDER,
                false, true, "vitamintreats.jpg");

        Medication Panacur = new Medication("Panacur", BigDecimal.valueOf(15.30), MedicationId.random(),
                5, LocalDate.of(2016, 8, 12), MedicationType.PILLS,
                true, false, "panacur.jpg");

        Medication ConvenIa = new Medication("Convenia", BigDecimal.valueOf(45.00), MedicationId.random(),
                3, LocalDate.of(2013, 11, 2), MedicationType.INJECTION,
                true, false, "convenia.jpg");

        Medication HillsZD = new Medication("Hill's z/d Diet", BigDecimal.valueOf(21.40), MedicationId.random(),
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

}