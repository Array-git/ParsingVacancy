package org.parse.vacancies.model;


import org.parse.vacancies.entity.Vacancy;

import java.util.List;

public interface Strategy {
    public List<Vacancy> getVacancies(String key, String city);
}
