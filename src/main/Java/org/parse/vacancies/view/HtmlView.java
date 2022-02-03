package org.parse.vacancies.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parse.vacancies.ParsController;
import org.parse.vacancies.entity.Vacancy;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View{
    private ParsController controller;
    private final String filePath = "./4.JavaCollections/src/"
            + this.getClass().getPackage().getName().replaceAll("[.]","/")
            + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        updateFile(getUpdatedFileContent(vacancies));
    }

    @Override
    public void setController(ParsController controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.setKeyWordsAndParse("","Kaliningrad");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies){
        try {
            Document parseDoc = getDocument();
            Elements elements = parseDoc.getElementsByClass("template");
            Elements copyElements = elements.clone().removeAttr("style").removeClass("template");
            Element template = copyElements.get(1);

            for (Element element: parseDoc.getElementsByClass("vacancy")) {
                if(!element.hasClass("template")){
                    element.remove();
                }
            }

            Elements firstElement = parseDoc.getElementsByClass("first-row");
            Elements copyFirstElements = firstElement.clone().removeAttr("style").removeClass("template");
            Element templateFirst = copyFirstElements.get(0);

            String siteName = "";
            for (Vacancy vacancy: vacancies) {
                if(!vacancy.getSiteName().equals(siteName)){
                    siteName = vacancy.getSiteName();
                    templateFirst.select("b").get(0).text(siteName); //.getElementsByClass("site").get(0).text(siteName);
                    firstElement.before(templateFirst.outerHtml());
                }
                Element tmp = template.clone();
                tmp.getElementsByClass("city").get(0).text(vacancy.getCity());
                tmp.getElementsByClass("companyName").get(0).text(vacancy.getCompanyName());
                tmp.getElementsByClass("salary").get(0).text(vacancy.getSalary());
                tmp.getElementsByAttribute("href").get(0).text(vacancy.getTitle()).attr("href", vacancy.getUrl());
                tmp.getElementsByClass("date").get(0).text(vacancy.getDate());
                //tmp.getElementsByClass("date").get(0).text(vacancy.getDate());
                elements.get(0).before(tmp.outerHtml());
            }

            return parseDoc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";
    }

    private void updateFile(String content){
        try(FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument()throws IOException{
        return Jsoup.parse(new File(filePath),"UTF-8");
    }
}
