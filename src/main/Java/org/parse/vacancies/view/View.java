package org.parse.vacancies.view;


import org.parse.vacancies.ParsController;
import org.parse.vacancies.entity.Vacancy;

import java.util.List;

public interface View {
    public void update(List<Vacancy> vacancies);
    public void setController(ParsController controller);
}
