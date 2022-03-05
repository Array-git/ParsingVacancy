package org.parse.vacancies.controller;


import org.parse.vacancies.Aggregator;
import org.parse.vacancies.entity.ConfigToSearch;
import org.parse.vacancies.entity.Vacancy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;

@Controller
//@RequestMapping("/")
public class MyControllers {
    @RequestMapping("/")
    public String startView(Model model){
        model.addAttribute("configToSearch", new ConfigToSearch());
        return "start-page";
    }

    @GetMapping("showVacancies")
    public String showVacancies(@Valid @ModelAttribute("configToSearch") ConfigToSearch configToSearch, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){return "start-page";}
        Aggregator aggregator = new Aggregator();
        List<Vacancy> vacancyList = aggregator.getVacancies(configToSearch.getKeyWords()
                , configToSearch.getCity()
                , aggregator.toProvider(configToSearch.getStrategies())
                , configToSearch.getLvl()
                , configToSearch.isRemote());
        model.addAttribute("allVacancy",vacancyList);
        return "vacancies-page";
    }
}
