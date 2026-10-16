package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.service.CatService;
import be.kdg.pro3.catclinic.web.CatSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}