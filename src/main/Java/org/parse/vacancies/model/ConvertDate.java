package org.parse.vacancies.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertDate {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM", Locale.getDefault());
    private SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    public Date convertStringToDate(String stringDate){
        Date date = null;
        /*I know this is bad methods, some day refactor this code with new methods (Calendar)
        This code need for check date because input date without years*/
        Date currentDate = new Date();
        int year = (currentDate.getYear());
        try {
            date = dateFormat.parse(stringDate);
            date.setYear(year);
            if(date.after(currentDate)){date.setYear(year-1);}
            //Calendar calendar = new GregorianCalendar();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String convertDateToString(Date date){
        return newDateFormat.format(date);
    }
}
