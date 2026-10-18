package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.Gender;
import be.kdg.pro3.catclinic.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.service.CatService;
import be.kdg.pro3.catclinic.web.CatEditForm;
import be.kdg.pro3.catclinic.web.CatSearchRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cats")
public class CatController{
    private static Logger log = LoggerFactory.getLogger(CatController.class);
    private CatService catService;

    public CatController(CatService catService){
        this.catService = catService;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException e, Model model){
        log.error(e.getMessage());
        model.addAttribute("cats", List.of());
        return "cats";
    }

    @ModelAttribute("genders")
    public Gender[] getGenders(){
        return Gender.values();
    }

    @GetMapping
    public String getCats(
            @ModelAttribute("catSearchRequest") CatSearchRequest request,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()){
            log.error("some request fields were corrupted");
            return "cats";
        }

        log.info("Requested cats with searchParameters");

        log.info("Params: " + request);
        List<Cat> cats = catService.getCatBySearchRequest(
                request.getCatName(),
                request.getMinAge(),
                request.getMaxAge(),
                request.getMinWeight(),
                request.getMaxWeight()
        );

        log.info("\nFound cats: \n");
        for(Cat cat : cats){
            log.info("\n" + cat);
        }

        model.addAttribute("cats", cats);

        return "cats";
    }

    @GetMapping("/edit/{catId}")
    public String getCatEditingPage(
            @PathVariable UUID catId,
            Model model
    ){
        log.info("Get editing form page for ID: + {} \n", catId);
        Cat cat;
        try{
            cat = catService.getCatById(catId);
        }catch (CatNotFoundException e){
            log.error("Editing page: " + e.getMessage());
            return "/error";
        }

        model.addAttribute("catEditForm",
                new CatEditForm(cat.getName(),
                        cat.getDateOfBirth(),
                        cat.getGender(),
                        cat.getWeightInKilos(),
                        cat.getPhoto())
        );
        model.addAttribute("catBeingEdited", catId);
        log.info("Form to update cat was created");
        return "cat-editing-page";
    }

    @PostMapping("/edit/{catId}")
    public String getCatEditingPage(
            @PathVariable UUID catId,
            @Valid @ModelAttribute("catEditForm") CatEditForm catEditForm,
            BindingResult bindingResult
    ){
        log.info("Cat edit request entered");
        if(bindingResult.hasErrors()){
            log.error("Cat editing form were corrupted");
            return "cat-editing-page";
        }

        try{
            catService.updateCat(catId, catEditForm);
        }catch (CatNotFoundException | IllegalArgumentException e){
            log.error("Error occurred while editing cat" + e.getMessage());
            return "/error";
        }

        log.info("Editing was done");
        return "cat-editing-page";
    }
}