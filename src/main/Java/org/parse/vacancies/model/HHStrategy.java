package org.parse.vacancies.model;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parse.vacancies.entity.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36";
    private static final String referrer = "https://kaliningrad.hh.ru/";
    private static final int timeout = 5 * 1000;
    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?%s&search_field=name&search_field=description&text=%s+%s+%s&page=%d";


    public HHStrategy() {
    }

    @Override
    public List<Vacancy> getVacancies(String searchString, String cityName, Lvl lvl, boolean remove) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        ConvertDate convertDate = new ConvertDate();
        do {
            Document cacheDoc = getDocument(searchString, cityName, page, lvl, remove);
            Elements elements = cacheDoc.getElementsByClass("serp-item"); //select("div[data-qa=vacancy-serp__vacancy vacancy-serp__vacancy_standard_plus]"); //"data-qa#vacancy-serp__vacancy-title");
            if (elements.isEmpty()) break;
            for (Element element : elements) {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(element.select("a[data-qa=vacancy-serp__vacancy-title]").text());
                vacancy.setCity(element.select("div[data-qa=vacancy-serp__vacancy-address]").text());
                vacancy.setCompanyName(element.select("a[data-qa=vacancy-serp__vacancy-employer]").text());
                vacancy.setSalary(element.select("[data-qa=vacancy-serp__vacancy-compensation]").text());
                vacancy.setUrl(element.select("[data-qa=vacancy-serp__vacancy-title]").attr("abs:href"));
                //vacancy.setDate(element.getElementsByAttributeValue("class","vacancy-serp-item__publication-date vacancy-serp-item__publication-date_long").text());
                //String data = vacancy.getDate().replaceAll(String.valueOf((char) 160), " ");
                String date = element.select("[data-qa=vacancy-serp__vacancy-date]").text().replaceAll(String.valueOf((char) 160), " ");
                vacancy.setDates(convertDate.convertStringToDateForHH(date));
                if(date.equals("")){vacancy.setDate("Работодатель онлайн");}
                else {vacancy.setDate(convertDate.convertDateToString(vacancy.getDates()));}
                vacancy.setSiteName("HeadHunter hh.ru");
                vacancies.add(vacancy);
            }
            page++;
        } while (true);
        return vacancies;
    }

    protected Document getDocument(String searchString, String city, int page, Lvl lvl, boolean remote) {
        try {
            String strRemote = remote ? "schedule=remote" : "";
            String stringForSearch = String.format(URL_FORMAT, strRemote, searchString, LvlHH.valueOf(lvl.name()).getStringLVL(), city, page);
            Connection conn = Jsoup.connect(stringForSearch).timeout(timeout);
            conn.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            conn.header("Accept-Encoding", "gzip, deflate, br");
            conn.header("Accept-Language", "en-US,en;q=0.9");
            conn.header("Host", "kaliningrad.hh.ru");
            conn.header("sec-ch-ua-platform", "Windows");
//            conn.header("Sec-Fetch-Dest", "document");
//            conn.header("Upgrade-Insecure-Requests", "1");
            conn.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36");
            //return Jsoup.connect(stringForSearch).timeout(timeout).userAgent(userAgent).referrer(referrer).get();
            return conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
