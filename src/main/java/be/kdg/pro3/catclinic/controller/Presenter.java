package be.kdg.pro3.catclinic.controller;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.CatOwner;
import be.kdg.pro3.catclinic.domain.Medication;
import be.kdg.pro3.catclinic.domain.PreferredCommunicationLanguage;
import be.kdg.pro3.catclinic.service.CatOwnerService;
import be.kdg.pro3.catclinic.service.CatService;
import be.kdg.pro3.catclinic.service.MedicationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Controller
public class Presenter implements CommandLineRunner {
    private static final Scanner sc = new Scanner(System.in);
    private final ConsoleView view;
    private final CatOwnerService catOwnerService;
    private final CatService catService;
    private final MedicationService medicationService;
    private final ConfigurableApplicationContext context;

    public Presenter(CatOwnerService catOwnerService, ConsoleView view, CatService catService, MedicationService medicationService, ConfigurableApplicationContext context){
        this.catOwnerService = catOwnerService;
        this.view = view;
        this.catService = catService;
        this.medicationService = medicationService;
        this.context = context;
    }

    @Override
    public void run(String... args) {
        boolean keepRunning = true;
        while (keepRunning) {
            show(view.getMainMenu());
            int choice;
            try {
                choice = getIntInput();
            } catch (InputMismatchException e) {
                show(view.getUnknownChoiceMessage());
                continue;
            }
            switch (choice) {
                case 1 -> workWithCats();
                case 2 -> workWithOwners();
                case 3 -> workWithMedication();
                case 4 -> keepRunning = false;
                default -> show(view.getUnknownChoiceMessage());
            }
        }
        context.close();
    }

    private void workWithCats() {
        boolean keepRunning = true;
        while (keepRunning) {
            show(view.getCatMenu());
            int choice;
            try {
                choice = getIntInput();
            } catch (InputMismatchException e) {
                show(e.getMessage());
                continue;
            }

            switch (choice) {
                case 1 -> showAllCats();
                case 2 -> filterCatsByAgeAndWeight();
                case 3 -> filterCatsByMedicationName();
                case 4 -> keepRunning = false;
                default -> show(view.getUnknownChoiceMessage());
            }
        }
    }

    private void showAllCats() {
       catService.getAllCats().forEach(Presenter::show);
    }

    private void filterCatsByAgeAndWeight() {
        int ageMin, ageMax, weightMin, weightMax;
        try {
            show("Provide filter (Leave empty to ignore the criteria)\n");
            show("Enter minimum and max age.\n");
            show("Min: ");
            ageMin = getIntInput(0);
            show("Max: ");
            ageMax = getIntInput(Integer.MAX_VALUE);
            show("Enter minimum and max weight\n");
            show("Min: ");
            weightMin = getIntInput(0);
            show("Max: ");
            weightMax = getIntInput(Integer.MAX_VALUE);
        } catch (InputMismatchException e) {
            show(e.getMessage());
            return;
        }

        if (ageMin < 0 || weightMin < 0 || ageMin > ageMax || weightMin > weightMax) {
            show("Age and weight should be a number above 0, and min can't be higher than max\n");
            return;
        }

        catService.getAllCats().stream()
                .filter(cat -> cat.getAge() >= ageMin && cat.getAge() <= ageMax)
                .filter(cat -> cat.getWeightInKilos() >= weightMin && cat.getWeightInKilos() <= weightMax)
                .toList().forEach(Presenter::show);
    }

    private void filterCatsByMedicationName() {
        show("Enter the medication name: ");
        String medicationName = sc.nextLine();

        Predicate<Cat> hasMedication = cat -> cat.getMedicationsThatWasAssignedToCat().stream()
                .anyMatch(medication -> medication.getTitle().equalsIgnoreCase(medicationName));

        catService.getAllCats().stream()
                .filter(hasMedication)
                .toList().forEach(Presenter::show);
    }

    private void workWithOwners() {
        boolean keepRunning = true;
        while (keepRunning) {
            show(view.getOwnerMenu());
            int choice;
            try {
                choice = getIntInput();
            } catch (InputMismatchException e) {
                show(e.getMessage());
                continue;
            }

            switch (choice) {
                case 1 -> showAllOwners();
                case 2 -> filterOwnersByFamilyName();
                case 3 -> filterOwnersByLanguage();
                case 4 -> filterOwnersByCatName();
                case 5 -> keepRunning = false;
                default -> show(view.getUnknownChoiceMessage());
            }
        }
    }

    private void showAllOwners() {
        catOwnerService.getAllOwners().forEach(Presenter::show);
    }

    private void filterOwnersByFamilyName() {
        show("Enter (part of) the family name: ");
        String familyNamePart = sc.nextLine();

        List<CatOwner> owners = catOwnerService.getAllOwners().stream()
                .filter(owner -> owner.getFamilyName().toLowerCase().contains(familyNamePart.toLowerCase()))
                .toList();

        if (owners.isEmpty()) {
            show(view.getNoResultsMessage());
            return;
        }
        owners.forEach(Presenter::show);
    }

    private void filterOwnersByLanguage() {
        show("Enter preferred communication language (e.g. EN, NL, FR, DE or OTHER): ");
        String language = sc.nextLine();

        PreferredCommunicationLanguage preferredLanguage;
        try {
            preferredLanguage = PreferredCommunicationLanguage.valueOf(language.toUpperCase());
        } catch (IllegalArgumentException e) {
            show("Unknown language\n");
            return;
        }

        List<CatOwner> owners = catOwnerService.getAllOwners().stream()
                .filter(owner -> owner.getPreferredCommunicationLanguage() == preferredLanguage)
                .toList();

        if (owners.isEmpty()) {
            show(view.getNoResultsMessage());
            return;
        }
        owners.forEach(Presenter::show);
    }

    private void filterOwnersByCatName() {
        show("Enter the cat's name: ");
        String catName = sc.nextLine();

        Predicate<CatOwner> hasCat = owner -> owner.getCats().stream()
                .anyMatch(cat -> cat.getName().equalsIgnoreCase(catName));

        List<CatOwner> filtered = catOwnerService.getAllOwners().stream()
                .filter(hasCat)
                .toList();

        if (filtered.isEmpty()) {
            show(view.getNoResultsMessage());
            return;
        }

        Consumer<CatOwner> printOwnerWithCats = owner -> {
            show(owner);
            show("Cat list:\n");
            owner.getCats()
                    .stream()
                    .sorted(Comparator.comparing(Cat::getCatId))
                    .forEach(cat -> show(cat.getName() + " | " + cat.getCatId() + "\n"));
        };
        filtered.forEach(printOwnerWithCats);
    }

    private void workWithMedication() {
        boolean keepRunning = true;
        while (keepRunning) {
            show(view.getMedicationMenu());
            int choice;
            try {
                choice = getIntInput();
            } catch (InputMismatchException e) {
                show(e.getMessage());
                continue;
            }

            switch (choice) {
                case 1 -> showAllMedications();
                case 2 -> filterMedicationsByPrescriptionNeeded();
                case 3 -> filterMedicationsByPrice();
                case 4 -> keepRunning = false;
                default -> show(view.getUnknownChoiceMessage());
            }
        }
    }

    private void showAllMedications() {
        medicationService.getAllMedications().forEach(Presenter::show);
    }

    private void filterMedicationsByPrescriptionNeeded() {
        show("Prescription needed? (yes/no) : ");
        boolean needsPrescription;
        String input = sc.nextLine();
        if (!((needsPrescription = input.equalsIgnoreCase("yes")) || input.equalsIgnoreCase("no"))) {
            show("yes or no was expected\n");
            return;
        }

        medicationService.getAllMedications().stream()
                .filter(medication -> medication.isPrescriptionNeeded() == needsPrescription)
                .toList().forEach(Presenter::show);
    }

    private void filterMedicationsByPrice() {
        double priceMin, priceMax;
        try {
            show("Enter minimum and max price\n");
            show("Min: ");
            priceMin = getDoubleInput(0);
            show("Max: ");
            priceMax = getDoubleInput(Double.MAX_VALUE);
        } catch (InputMismatchException e) {
            show(e.getMessage());
            return;
        }

        if (priceMin < 0 || priceMin > priceMax) {
            show("Price should be a number above 0, and min can't be higher than max\n");
            return;
        }

        show("Medications :\n");
        medicationService.getAllMedications().stream()
                .filter(medication -> medication.getPrice() >= priceMin && medication.getPrice() <= priceMax)
                .toList().forEach(Presenter::show);
    }

    private static int getIntInput() throws InputMismatchException {
        try {
            int value = sc.nextInt();
            sc.nextLine();
            return value;
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new InputMismatchException("Try a number instead\n");
        }
    }

    private static int getIntInput(int defaultValue) throws InputMismatchException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return defaultValue;

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Try a number instead\n");
        }
    }

    private static double getDoubleInput(double defaultValue) throws InputMismatchException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return defaultValue;

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Try a number instead\n");
        }
    }

    private static void show(String message) {
        if(message != null) System.out.printf(message);
    }

    private static void show(Cat cat) {
        if(cat != null) System.out.println(cat);
    }

    private static void show(CatOwner owner) {
        if(owner != null) System.out.println(owner);
    }

    private static void show(Medication medication) {
        if(medication != null) System.out.println(medication);
    }
}