package be.kdg.pro3.catclinic;

import be.kdg.pro3.catclinic.model.domain.Cat;
import be.kdg.pro3.catclinic.model.domain.CatOwner;
import be.kdg.pro3.catclinic.model.domain.PreferredCommunicationLanguage;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CatClinicApp {
    private static final PrintStream io = System.out;
    private static final Scanner sc = new Scanner(System.in);
    private static final String MAIN_MENU = "\n   What are we working with?\n1 - Cats\n2 - Owners\n3 - Medications\n4 - Exit";
    private static final String CAT_MENU = "\n   Cats\n1 - Show all cats\n2 - Filter by age and weight\n3 - Filter by prescripted medications\n4 - Back";
    private static final String MEDICATION_MENU = "\n   Medications\n1 - Show all medications\n2 - is prescription needed\n3 - Price\n4 - Back";
    private static final String OWNER_MENU = "\n   Owners\n1 - Show all owners\n2 - Find owner by name\n3 - Language\n4 - Find by cats\n5 - Back";
    private static final String UNKNOWN_CHOICE_MESSAGE = "I don't get you";
    private static final String NO_RESULTS_MESSAGE = "None satisfied the provided filters";

    public static void main(String[] args) {
        DataFactory.seed();

        boolean keepRunning = true;
        while (keepRunning) {
            io.println(MAIN_MENU);
            int choice;
            try {
                choice = getIntInput(-1);
            } catch (InputMismatchException e) {
                io.println(UNKNOWN_CHOICE_MESSAGE);
                continue;
            }
            switch (choice) {
                case 1 -> workWithCats();
                case 2 -> workWithOwners();
                case 3 -> workWithMedication();
                case 4 -> keepRunning = false;
                default -> io.println(UNKNOWN_CHOICE_MESSAGE);
            }
        }
    }

    private static void workWithCats() {
        boolean keepRunning = true;
        while (keepRunning) {
            io.println(CAT_MENU);
            int choice;
            try {
                choice = getIntInput();
            } catch (InputMismatchException e) {
                io.println(e.getMessage());
                continue;
            }

            switch (choice) {
                case 1 -> showAllCats();
                case 2 -> filterCatsByAgeAndWeight();
                case 3 -> filterCatsByMedicationName();
                case 4 -> keepRunning = false;
                default -> io.println(UNKNOWN_CHOICE_MESSAGE);
            }
        }
    }

    private static void showAllCats() {
        DataFactory.cats.forEach(io::println);
    }

    private static void filterCatsByAgeAndWeight() {
        int ageMin, ageMax, weightMin, weightMax;
        try {
            io.println("Provide filter (Leave empty to ignore the criteria)");
            io.println("Enter minimum and max age.");
            io.print("Min: ");
            ageMin = getIntInput(0);
            io.print("Max: ");
            ageMax = getIntInput(Integer.MAX_VALUE);
            io.println("Enter minimum and max weight");
            io.print("Min: ");
            weightMin = getIntInput(0);
            io.print("Max: ");
            weightMax = getIntInput(Integer.MAX_VALUE);
        } catch (InputMismatchException e) {
            io.println(e.getMessage());
            return;
        }

        if (ageMin < 0 || weightMin < 0 || ageMin > ageMax || weightMin > weightMax) {
            io.println("Age and weight should be a number above 0, and min can't be higher than max");
            return;
        }

        DataFactory.cats.stream()
                .filter(cat -> cat.getAge() >= ageMin && cat.getAge() <= ageMax)
                .filter(cat -> cat.getWeightInKilos() >= weightMin && cat.getWeightInKilos() <= weightMax)
                .toList().forEach(io::println);
    }

    private static void filterCatsByMedicationName() {
        io.println("Enter the medication name");
        String medicationName = sc.nextLine();

        Predicate<Cat> hasMedication = cat -> cat.getMedicationsThatWasAssignedToCat().stream()
                .anyMatch(medication -> medication.getTitle().equalsIgnoreCase(medicationName));

        DataFactory.cats.stream()
                .filter(hasMedication)
                .toList().forEach(io::println);
    }

    private static void workWithOwners() {
        boolean keepRunning = true;
        while (keepRunning) {
            io.println(OWNER_MENU);
            int choice;
            try {
                choice = getIntInput();
            } catch (InputMismatchException e) {
                io.println(e.getMessage());
                continue;
            }

            switch (choice) {
                case 1 -> showAllOwners();
                case 2 -> filterOwnersByFamilyName();
                case 3 -> filterOwnersByLanguage();
                case 4 -> filterOwnersByCatName();
                case 5 -> keepRunning = false;
                default -> io.println(UNKNOWN_CHOICE_MESSAGE);
            }
        }
    }

    private static void showAllOwners() {
        DataFactory.catOwners.forEach(io::println);
    }

    private static void filterOwnersByFamilyName() {
        io.println("Enter (part of) the family name");
        String familyNamePart = sc.nextLine();

        List<CatOwner> owners = DataFactory.catOwners.stream()
                .filter(owner -> owner.getFamilyName().toLowerCase().contains(familyNamePart.toLowerCase()))
                .toList();
        if(owners.isEmpty()) io.println(NO_RESULTS_MESSAGE);
        owners.forEach(io::println);
    }

    private static void filterOwnersByLanguage() {
        io.println("Enter preferred communication language (e.g. EN, NL, FR, DE or OTHER)");
        String language = sc.nextLine();

        PreferredCommunicationLanguage preferredLanguage;
        try {
            preferredLanguage = PreferredCommunicationLanguage.valueOf(language.toUpperCase());
        } catch (IllegalArgumentException e) {
            io.println("Unknown language");
            return;
        }

        List<CatOwner> owners = DataFactory.catOwners.stream()
                .filter(owner -> owner.getPreferredCommunicationLanguage() == preferredLanguage)
                .toList();
        if(owners.isEmpty()){
            System.out.printf(NO_RESULTS_MESSAGE);
            return;
        }
        owners.forEach(io::println);
    }

    private static void filterOwnersByCatName() {
        io.println("Enter the cat's name: ");
        String catName = sc.nextLine();

        Predicate<CatOwner> hasCat = owner -> owner.getCats().stream()
                .anyMatch(cat -> cat.getName().equalsIgnoreCase(catName));

        List<CatOwner> filtered = DataFactory.catOwners.stream()
                .filter(hasCat)
                .toList();

        if (filtered.isEmpty()) {
            io.println(NO_RESULTS_MESSAGE);
            return;
        }

        Consumer<CatOwner> printOwnerWithCats = owner -> {
            io.printf("%s\nCat list:\n", owner);
            owner.getCats().forEach(cat -> io.printf("%s | %s\n", cat.getName(), cat.getCatId()));
        };
        filtered.forEach(printOwnerWithCats);
    }

    private static void workWithMedication() {
        boolean keepRunning = true;
        while (keepRunning) {
            io.println(MEDICATION_MENU);
            int choice;
            try {
                choice = getIntInput();
            } catch (InputMismatchException e) {
                io.println(e.getMessage());
                continue;
            }

            switch (choice) {
                case 1 -> showAllMedications();
                case 2 -> filterMedicationsByPrescriptionNeeded();
                case 3 -> filterMedicationsByPrice();
                case 4 -> keepRunning = false;
                default -> io.println(UNKNOWN_CHOICE_MESSAGE);
            }
        }
    }

    private static void showAllMedications() {
        DataFactory.medications.forEach(io::println);
    }

    private static void filterMedicationsByPrescriptionNeeded() {
        io.println("Prescription needed? (yes/no)");
        boolean needsPrescription;
        String input = sc.nextLine();
        if(!((needsPrescription = input.equalsIgnoreCase("yes")) || input.equalsIgnoreCase("no"))){
            io.println("yes or no was expected");
            return;
        }

        DataFactory.medications.stream()
                .filter(medication -> medication.isPrescriptionNeeded() == needsPrescription)
                .toList().forEach(io::println);
    }

    private static void filterMedicationsByPrice() {
        double priceMin, priceMax;
        try {
            io.println("Enter minimum and max price");
            io.print("Min: ");
            priceMin = getDoubleInput(0);
            io.print("Max: ");
            priceMax = getDoubleInput(Double.MAX_VALUE);
        } catch (InputMismatchException e) {
            io.println(e.getMessage());
            return;
        }

        if (priceMin < 0 || priceMin > priceMax) {
            io.println("Price should be a number above 0, and min can't be higher than max");
            return;
        }

        io.println("Medications :");
        DataFactory.medications.stream()
                .filter(medication -> medication.getPrice() >= priceMin && medication.getPrice() <= priceMax)
                .toList().forEach(io::println);
    }


    private static int getIntInput() throws InputMismatchException {
        try {
            int value = sc.nextInt();
            sc.nextLine();
            return value;
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new InputMismatchException("Try a number instead");
        }
    }

    private static int getIntInput(int defaultValue) throws InputMismatchException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return defaultValue;

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Try a number instead");
        }
    }

    private static double getDoubleInput(double defaultValue) throws InputMismatchException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) return defaultValue;

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Try a number instead");
        }
    }
}