package org.parse.vacancies.model;


import org.parse.vacancies.entity.Vacancy;
import org.parse.vacancies.view.View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Model {
    private Provider[] providers;

    public Model(Provider ...providers) {
        /*if (view == null || providers == null || providers.length == 0) {
            throw new IllegalArgumentException();
        }*/
        this.providers = providers;
    }

    public List<Vacancy> getVacancies(String keyWords, String city){
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider provider: providers) {
            List<Vacancy> tmpVacancy = provider.getJavaVacancies(keyWords, city);
            tmpVacancy.sort(new Comparator<Vacancy>() {
                @Override
                public int compare(Vacancy o1, Vacancy o2) {
                    return o2.getDates().compareTo(o1.getDates());
                }
            });
            //System.out.println(tmpVacancy.toString());
            vacancies.addAll(tmpVacancy);
        }
        return vacancies;
    }
}
