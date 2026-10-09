package be.kdg.pro3.catclinic.presentation.controllers;

import be.kdg.pro3.catclinic.domain.Cat;
import be.kdg.pro3.catclinic.service.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cats")
public class CatController{
    private CatService catService;

    public CatController(CatService catService){
        this.catService = catService;
    }

    @GetMapping
    public String getCats(Model model){
        model.addAttribute("allCats",catService.getAllCats());
        return "cats";
    }
}
