package be.kdg.pro3.catclinic.controller;

import be.kdg.pro3.catclinic.domain.*;
import be.kdg.pro3.catclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.function.Consumer;

@Component
public class Presenter implements CommandLineRunner{
    private final ConsoleView view;
    private final CatOwnerService catOwnerService;
    private final CatService catService;
    private final MedicationService medicationService;

    public Presenter(ConsoleView view, CatOwnerService catOwnerService, CatService catService, MedicationService medicationService){
        this.view = view;
        this.catOwnerService = catOwnerService;
        this.catService = catService;
        this.medicationService = medicationService;
    }

    @Override
    public void run(String... args) {
        show();
    }

    public void show(){
        boolean keepRunning = true;
        while (keepRunning) {
            view.showMainMenu();
            String choice = view.inputString();
            switch (choice) {
                case "1" -> workWithCats();
                case "2" -> workWithOwners();
                case "3" -> workWithMedication();
                case "4" -> keepRunning = false;
                default -> view.showUnknownChoiceMessage();
            }
        }
        view.show("\nSee you next time!\n\n");
    }


    private void workWithCats() {
        boolean keepRunning = true;
        while (keepRunning) {
            view.showCatMenu();
            String choice = view.inputString();
            switch (choice) {
                case "1" -> showAllCats();
                case "2" -> filterCatsByAgeAndWeight();
                case "3" -> filterCatsByMedicationName();
                case "4" -> keepRunning = false;
                default -> view.showUnknownChoiceMessage();
            }
        }
    }

    private void showAllCats() {
       catService.getAllCats().forEach(view::show);
    }

    private void filterCatsByAgeAndWeight() {
        int ageMin, ageMax, weightMin, weightMax;
        try {
            view.show("Provide filter (Leave empty to ignored the criteria)\n");
            view.show("Enter minimum and max age.\n");
            view.show("Min: ");
            ageMin = view.getIntInput(0);
            view.show("Max: ");
            ageMax = view.getIntInput(Integer.MAX_VALUE);
            view.show("Enter minimum and max weight\n");
            view.show("Min: ");
            weightMin = view.getIntInput(0);
            view.show("Max: ");
            weightMax = view.getIntInput(Integer.MAX_VALUE);
        } catch (InputMismatchException e) {
            view.showUnknownChoiceMessage();
            return;
        }

        if (ageMin < 0 || weightMin < 0 || ageMin > ageMax || weightMin > weightMax) {
            view.show("Age and weight should be a number above 0, and min can't be higher than max\n");
            return;
        }

        List<Cat> cats = catService.getCatsFilteredByAgeAndByWeight(ageMin, ageMax, weightMin, weightMax);
        if(cats.isEmpty()){
            view.showNoResultsMessage();
            return;
        }
        cats.forEach(view::show);
    }

    private void filterCatsByMedicationName() {
        view.show("Enter the medication name: ");
        String medicationName = view.inputString();
        catService.getCatsFilteredByMedicationName(medicationName).forEach(view::show);
    }

    private void workWithOwners() {
        boolean keepRunning = true;
        while (keepRunning) {
            view.showOwnerMenu();
            String choice = view.inputString();
            switch (choice) {
                case "1" -> showAllOwners();
                case "2" -> filterOwnersByFamilyName();
                case "3" -> filterOwnersByLanguage();
                case "4" -> filterOwnersByCatName();
                case "5" -> filterOwnersByRegistrationDate();
                case "6" -> keepRunning = false;
                default -> view.showUnknownChoiceMessage();
            }
        }
    }

    private void showAllOwners() {
        catOwnerService.getAllOwners().forEach(view::show);
    }

    private void filterOwnersByFamilyName() {
        view.show("Enter (part of) the family name: ");
        String familyNamePart = view.inputString();

        List<CatOwner> owners = catOwnerService.getCatOwnerByItsName(familyNamePart);

        if (owners.isEmpty()) {
            view.showNoResultsMessage();
            return;
        }
        owners.forEach(view::show);
    }

    private void filterOwnersByLanguage() {
        view.show("Enter preferred communication language (e.g. EN, NL, FR, DE or OTHER): ");
        String language = view.inputString().toUpperCase().trim();

        PreferredCommunicationLanguage preferredLanguage;
        try {
            preferredLanguage = PreferredCommunicationLanguage.valueOf(language);
        } catch (IllegalArgumentException e) {
            view.show("Unknown language filter\n");
            return;
        }

        List<CatOwner> owners = catOwnerService.getCatOwnerByItsLanguage(preferredLanguage);

        if (owners.isEmpty()) {
            view.showNoResultsMessage();
            return;
        }
        owners.forEach(view::show);
    }

    private void filterOwnersByCatName() {
        view.show("Enter the cat's name: ");
        String catName =view.inputString();
        List<CatOwner> filtered = catOwnerService.getCatOwnerByCatsName(catName);
        if (filtered.isEmpty()){
            view.showNoResultsMessage();
            return;
        }

        Consumer<CatOwner> printOwnerWithCats = owner -> {
            view.show(owner);
            view.show("Cat list:\n");
            owner.getCats().forEach(cat -> view.show(cat.getName() + " | Photo: " + cat.getPhoto() + "\n"));
        };
        filtered.forEach(printOwnerWithCats);
    }

    private void filterOwnersByRegistrationDate(){
        view.show("Enter registration date (yyyy-mm-dd): ");
        String date = view.inputString();
        DateTimeFormatter format =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate registrationDate;
        try{
            registrationDate = LocalDate.parse(date,format);
        }catch (DateTimeParseException e){
            view.show("Wrong date format provided\n");
            return;
        }
        List<CatOwner> owners = catOwnerService.getOwnersByRegistrationDate(registrationDate);
        if(owners.isEmpty()){
            view.showNoResultsMessage();
            return;
        }
        owners.forEach(view::show);
    }

    private void workWithMedication() {
        boolean keepRunning = true;
        while (keepRunning) {
            view.showMedicationMenu();
            String choice = view.inputString();
            switch (choice) {
                case "1" -> showAllMedications();
                case "2" -> filterMedicationsByPrescriptionNeeded();
                case "3" -> filterMedicationsByPrice();
                case "4" -> registerNewMedication();
                case "5" -> keepRunning = false;
                default -> view.showUnknownChoiceMessage();
            }
        }
    }

    private void registerNewMedication(){
        view.show("Enter new medication name: ");
        String title = view.inputString();

        view.show("Enter price: ");
        double price;
        try{
            price = view.getDoubleInput(0);
        }catch (InputMismatchException e) {
            view.show(e.getMessage());
            return;
        }

        if(price <= 0){
            view.show("Price must be above 0");
            return;
        }

        view.show("Enter number of weeks after birth to start medication: ");
        int weeksAfterBirthToStartMedication;
        try{
            weeksAfterBirthToStartMedication = view.getIntInput();
        }catch (InputMismatchException e) {
            view.show(e.getMessage());
            return;
        }

        if(weeksAfterBirthToStartMedication < 0){
            view.show("Weeks cannot be less then 0");
            return;
        }

        view.show("Enter market release date (yyyy-mm-dd): ");
        String date = view.inputString();
        DateTimeFormatter format =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate marketReleaseDate;
        try{
            marketReleaseDate = LocalDate.parse(date,format);
        }catch (DateTimeParseException e){
            view.show("Wrong date format provided\n");
            return;
        }

        view.show("Choose medication type:");

        MedicationType[] types = MedicationType.values();
        for(int i = 0; i<types.length; i++){
            view.show("\n" + (i + 1) + " - " + types[i].name().toLowerCase());
        }
        view.show("\n");

        int typeChoice;
        do {
            typeChoice = view.getIntInput();
        } while (typeChoice < 1 || typeChoice > types.length);
        MedicationType medicationType = types[typeChoice - 1];

        view.show("Is prescription needed? (yes/no): ");
        String inputPrescription = view.inputString();
        boolean needsPrescription;
        if (!((needsPrescription = inputPrescription.equalsIgnoreCase("yes")) || inputPrescription.equalsIgnoreCase("no"))) {
            view.show("yes or no was expected\n");
            return;
        }


        view.show("Can it be gifted on cat's birthday? (yes/no): ");
        String inputGiftable = view.inputString();
        boolean canBeGiftedOnCatsBirthday;
        if (!((canBeGiftedOnCatsBirthday = inputGiftable.equalsIgnoreCase("yes")) || inputGiftable.equalsIgnoreCase("no"))) {
            view.show("yes or no was expected\n");
            return;
        }
        view.show("Enter photo path or URL (can be ignore): ");
        String photo = view.inputString();

        medicationService.registerNewMedication(title, price, weeksAfterBirthToStartMedication,
                marketReleaseDate, medicationType, needsPrescription,
                canBeGiftedOnCatsBirthday, photo);
        view.show("Medication registered successfully!");
    }

    private void showAllMedications() {
        medicationService.getAllMedications().forEach(view::show);
    }

    private void filterMedicationsByPrescriptionNeeded() {
        view.show("Prescription needed? (yes/no) : ");
        boolean needsPrescription;
        String input = view.inputString();
        if (!((needsPrescription = input.equalsIgnoreCase("yes")) || input.equalsIgnoreCase("no"))) {
            view.show("yes or no was expected\n");
            return;
        }
        medicationService.getMedicationFilteredByPrescriptionNeed(needsPrescription).forEach(view::show);
    }

    private void filterMedicationsByPrice() {
        double priceMin, priceMax;
        try {
            view.show("Enter minimum and max price\n");
            view.show("Min: ");
            priceMin = view.getDoubleInput(0);
            view.show("Max: ");
            priceMax = view.getDoubleInput(Double.MAX_VALUE);
        } catch (InputMismatchException e) {
            view.show(e.getMessage());
            return;
        }

        if (priceMin < 0 || priceMin > priceMax) {
            view.show("Price should be a number above 0, and min can't be higher than max\n");
            return;
        }

        view.show("Medications :\n");
        List<Medication> found = medicationService.getMedicationFilteredByPrice(priceMin, priceMax);
        if(found.isEmpty()){
            view.showNoResultsMessage();
            return;
        }

        found.forEach(view::show);
    }
}