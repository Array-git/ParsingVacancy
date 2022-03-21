package org.parse.vacancies.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConvertDate {
    private SimpleDateFormat dateFormatHabr = new SimpleDateFormat("d MMMM", Locale.getDefault());
    private SimpleDateFormat dateFormatHH = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    public Date convertStringToDateForHabr(String stringDate) {
        Date date = null;
        /*I know this is bad methods, some day refactor this code with new methods (Calendar)
        This code need for check date because input date without years*/
        Date currentDate = new Date();
        int year = (currentDate.getYear());
        try {
            date = dateFormatHabr.parse(stringDate);
            /*if(!stringDate.equals("")){date = dateFormat.parse(stringDate);}
            else { date = currentDate;}*/

            date.setYear(year);
            if (date.after(currentDate)) {
                date.setYear(year - 1);
            }
            //Calendar calendar = new GregorianCalendar();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date convertStringToDateForHH(String stringDate) {
        Date date = null;
        try {
            if(!stringDate.equals("")){date = dateFormatHH.parse(stringDate);}
            else { date = dateFormatHH.parse("01.01.1970");}
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String convertDateToString(Date date) {
        return newDateFormat.format(date);
    }
}
