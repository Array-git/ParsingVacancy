package org.parse.vacancies.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parse.vacancies.entity.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy{
    private static final String userAgent = "Chrome/97.0.4692.99";
    private static final String referrer = "https://kaliningrad.hh.ru/";
    //    private static final int timeout = 5 * 1000;
    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=%s+%s&page=%d";
    //private static final String URL_CACHE = "https://javarush.ru/testdata/big28data.html";


    public HHStrategy() {
    }

    @Override
    public List<Vacancy> getVacancies(String searchString, String cityName){
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        ConvertDate convertDate = new ConvertDate();
        do {
            Document cacheDoc = getDocument(searchString, cityName, page);
            Elements elements = cacheDoc.getElementsByClass("vacancy-serp-item"); //select("div[data-qa=vacancy-serp__vacancy vacancy-serp__vacancy_standard_plus]"); //"data-qa#vacancy-serp__vacancy-title");
            if(elements.isEmpty()) break;
            for (Element element: elements) {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(element.select("a[data-qa=vacancy-serp__vacancy-title]").text());
                vacancy.setCity(element.select("div[data-qa=vacancy-serp__vacancy-address]").text());
                vacancy.setCompanyName(element.select("a[data-qa=vacancy-serp__vacancy-employer]").text());
                vacancy.setSalary(element.getElementsByClass("vacancy-serp-item__sidebar").text());
                vacancy.setUrl(element.select("[data-qa=vacancy-serp__vacancy-title]").attr("abs:href"));
                //vacancy.setDate(element.getElementsByAttributeValue("class","vacancy-serp-item__publication-date vacancy-serp-item__publication-date_long").text());
                //String data = vacancy.getDate().replaceAll(String.valueOf((char) 160), " ");
                vacancy.setDates(convertDate.convertStringToDate(element.getElementsByAttributeValue("class","vacancy-serp-item__publication-date vacancy-serp-item__publication-date_long").text()
                        .replaceAll(String.valueOf((char) 160), " ")));
                vacancy.setDate(convertDate.convertDateToString(vacancy.getDates()));
                vacancy.setSiteName("HeadHunter hh.ru");
                vacancies.add(vacancy);
            }
            page++;
        } while (true);
        /*try {
            Document document = Jsoup.connect(String.format(URL_FORMAT,"Kiev",0)).userAgent(userAgent).referrer(referrer).get();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return vacancies;
    }

    protected Document getDocument(String searchString, String city, int page){
        try {
            return Jsoup.connect(String.format(URL_FORMAT,searchString, city,page)).userAgent(userAgent).referrer(referrer).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
