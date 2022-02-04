package org.parse.vacancies.entity;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConfigToSearch {
    //@Size(min = 1, max=25, message = "name must be 1-25 symbols")
    @NotEmpty(message = "Введите cлова для поиска")
    private String keyWords;
    private String city;
    @NotEmpty(message = "Выберите площадку")
    private String[] strategies;
    private Map<String, String> strategiesList;


    public ConfigToSearch() {
        strategiesList = new HashMap<>();
        strategiesList.put("hh.ru", "Head Hunter");
        strategiesList.put("career.habr.com", "Хабр Карьера");
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getStrategies() {
        return strategies;
    }

    public void setStrategies(String[] strategies) {
        this.strategies = strategies;
    }

    public Map<String, String> getStrategiesList() {
        return strategiesList;
    }

    public void setStrategiesList(Map<String, String> strategiesList) {
        this.strategiesList = strategiesList;
    }

    @Override
    public String toString() {
        return "ConfigToSearch{" +
                "keyWords='" + keyWords + '\'' +
                "city='" + city +'\''+
                ", strategies=" + Arrays.toString(strategies) +
                ", strategiesList=" + strategiesList +
                '}';
    }
}
