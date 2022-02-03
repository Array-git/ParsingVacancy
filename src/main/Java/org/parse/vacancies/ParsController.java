package org.parse.vacancies;


import org.parse.vacancies.entity.Vacancy;
import org.parse.vacancies.model.Model;

import java.util.List;

public class ParsController {
    private Model model;

    public ParsController(Model model) {
        if(model==null) throw new IllegalArgumentException();
        this.model = model;
    }

    public List<Vacancy> setKeyWordsAndParse(String keyWords, String cityName){
        return model.getVacancies(keyWords, cityName);
    }
}
