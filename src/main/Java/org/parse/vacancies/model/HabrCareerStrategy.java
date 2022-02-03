package org.parse.vacancies.model;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parse.vacancies.entity.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerStrategy implements Strategy{
    private static final String userAgent = "Chrome/97.0.4692.99";
    //private static final String referrer = "https://kaliningrad.hh.ru/";
    private static final String URL_FORMAT = "https://career.habr.com/vacancies?page=%d&q=%s+%s&qid=3&remote=true";

    public HabrCareerStrategy() {
    }

    @Override
    public List<Vacancy> getVacancies(String searchString, String cityName) {
        List<Vacancy> vacancies = new ArrayList<>();
        /*int page = 0; //для валидатора, страница устарела
        do{
            Document document = getDocument(searchString, page);
            Elements elements = document.getElementsByClass("job");
            if(elements.isEmpty()) break;
            for (Element element: elements) {
                Vacancy vacancy = new Vacancy();
                vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                vacancy.setTitle(element.getElementsByAttributeValue("class", "title").text());
                vacancy.setCity(element.getElementsByAttributeValue("class", "location").text());
                vacancy.setSalary(element.getElementsByAttributeValue("class", "salary").text());
                vacancy.setUrl("https://career.habr.com"+element.getElementsByClass("title").get(0).getElementsByTag("a").attr("href"));
                vacancy.setSiteName("career.habr.com");
                vacancies.add(vacancy);
            }
            page++;
        }
        while (true);*/
        int page =1; //Начинается с 1 (страницы 0 и 1 одна и та же)
        ConvertDate convertDate = new ConvertDate();
        do{
            Document document = getDocument(searchString, cityName, page);
            Elements elements = document.getElementsByClass("vacancy-card");
            if(elements.isEmpty()) break;
            for (Element element: elements) {
                Vacancy vacancy = new Vacancy();
                vacancy.setCompanyName(element.getElementsByAttributeValue("class", "vacancy-card__company-title").text());
                vacancy.setTitle(element.getElementsByAttributeValue("class", "vacancy-card__title").text());
                vacancy.setCity(element.getElementsByAttributeValue("class", "preserve-line").get(0).getElementsByTag("a").text());
                vacancy.setSalary(element.getElementsByAttributeValue("class", "basic-salary").text());
                vacancy.setUrl("https://career.habr.com"+element.getElementsByClass("vacancy-card__title-link").attr("href"));
                //vacancy.setDate(element.getElementsByClass("basic-date").text());
                vacancy.setDates(convertDate.convertStringToDate(element.getElementsByClass("basic-date").text()
                        .replaceAll(String.valueOf((char) 160), " ")));
                vacancy.setDate(convertDate.convertDateToString(vacancy.getDates()));
                vacancy.setSiteName("Хабр Карьера career.habr.com");
                vacancies.add(vacancy);
            }
            page++;
        }
        while (true);
        return vacancies;
    }

    protected Document getDocument(String searchString, String city, int page){
        try {
            return Jsoup.connect(String.format(URL_FORMAT,page,searchString, city)).userAgent(userAgent).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
