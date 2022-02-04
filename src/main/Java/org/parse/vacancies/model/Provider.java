package org.parse.vacancies.model;

import org.parse.vacancies.entity.Vacancy;

import java.util.List;

public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String keyWords, String city, Lvl lvl, boolean remove){
        return strategy.getVacancies(keyWords, city, lvl, remove);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "strategy=" + strategy.getClass().getName() +
                '}';
    }
}
