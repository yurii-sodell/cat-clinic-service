package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.domain.Gender;
import be.kdg.pro3.catclinic.exceptions.CatNotFoundException;
import be.kdg.pro3.catclinic.service.CatService;
import be.kdg.pro3.catclinic.presentation.web.CatEditForm;
import be.kdg.pro3.catclinic.presentation.web.CatSearchRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cats")
@Slf4j
public class CatController{
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
            @Valid  @ModelAttribute("catSearchRequest") CatSearchRequest request,
            BindingResult bindingResult,
            Model model
    ){
        log.info("Requested cats with searchParameters");
        if(bindingResult.hasErrors()){
            log.error("some request fields were corrupted");
            return "cats";
        }
        log.info("\n Params: " + request);

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
    public String editCat(
            @PathVariable UUID catId,
            Model model
    ){
        log.info("Get editing form page for ID: + {} \n", catId);
        Cat cat;
        try{
            cat = catService.getCatById(catId);
        }catch (CatNotFoundException e) {
            log.error("Editing page: " + e.getMessage());
            return "redirect:/error";
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
    public String editCat(@PathVariable UUID catId,
                          @Valid @ModelAttribute("catEditForm") CatEditForm catEditForm,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        log.info("The edit form was posted for id: " + catId.toString());
        model.addAttribute("catBeingEdited", catId);

        if (bindingResult.hasErrors()) {
            log.error("Cat editing form was corrupted");
            return "cat-editing-page";
        }

        try{
            catService.updateCat(catId, catEditForm);
            redirectAttributes.addFlashAttribute("successMessage", "Cat updated.");
            log.info("Cat successfully updated");
            return "redirect:/cats/edit/" + catId;
        }catch (CatNotFoundException | IllegalArgumentException e) {
            log.error("Error occurred while editing cat" + e.getMessage());
            return "redirect:/error";
        }
    }
}