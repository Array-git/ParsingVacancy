package org.parse.vacancies.entity;

import org.parse.vacancies.model.Lvl;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigToSearch {
    //@Size(min = 1, max=25, message = "name must be 1-25 symbols")
    @NotEmpty(message = "Введите cлова для поиска")
    private String keyWords;
    private String city;
    private boolean remote = false;
    private Lvl lvl;
    private Map<Lvl, String> lvlList;
    @NotEmpty(message = "Выберите площадку")
    private String[] strategies;
    private Map<String, String> strategiesList;


    public ConfigToSearch() {
        strategiesList = new HashMap<>();
        strategiesList.put("hh.ru", "Head Hunter");
        strategiesList.put("career.habr.com", "Хабр Карьера");
        lvlList = new LinkedHashMap<>();
        lvlList.put(Lvl.NONE,"");
        lvlList.put(Lvl.Trainee,"Trainee");
        lvlList.put(Lvl.Junior,"Junior");
        lvlList.put(Lvl.Middle,"Middle");
        lvlList.put(Lvl.Senior,"Senior");
        lvlList.put(Lvl.Lead,"Lead");
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

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public Lvl getLvl() {
        return lvl;
    }

    public void setLvl(Lvl lvl) {
        this.lvl = lvl;
    }

    public Map<Lvl, String> getLvlList() {
        return lvlList;
    }

    public void setLvlList(Map<Lvl, String> lvlList) {
        this.lvlList = lvlList;
    }

    /*
    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }
    public Map<String, String> getLvlList() {
        return lvlList;
    }

    public void setLvlList(Map<String, String> lvlList) {
        this.lvlList = lvlList;
    }*/

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
