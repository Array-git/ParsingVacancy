package org.parse.vacancies;

import org.parse.vacancies.entity.Vacancy;
import org.parse.vacancies.model.HHStrategy;
import org.parse.vacancies.model.HabrCareerStrategy;
import org.parse.vacancies.model.Model;
import org.parse.vacancies.model.Provider;

import java.util.ArrayList;
import java.util.List;

public class Aggregator {
    public List<Vacancy> getVacancies(String keyWords, String city, Provider[] stringProviders) {
        Model model = new Model(stringProviders);
        ParsController controller = new ParsController(model);
        return controller.setKeyWordsAndParse(keyWords, city);
    }
    
    public Provider[] toProvider(String[] strategies){
        List<Provider> providers = new ArrayList<>();
        if(strategies.length==0){return null;}
        for (int i = 0; i < strategies.length; i++) {
            if(strategies[i].equals("hh.ru")){providers.add(new Provider(new HHStrategy())); continue;}
            if(strategies[i].equals("career.habr.com")){providers.add(new Provider(new HabrCareerStrategy()));}
        }
        return providers.toArray(new Provider[providers.size()]);
    }
}
