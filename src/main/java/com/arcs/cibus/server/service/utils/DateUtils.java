package com.arcs.cibus.server.service.utils;

import com.arcs.cibus.server.domain.enums.DashboardPeriod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static Date defineInitialDate(DashboardPeriod period) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
            calendar.setTime(new Date());

            int DAY = calendar.get(Calendar.DAY_OF_MONTH);
            int INITIAL_DAY_WEEK = DAY - calendar.get(Calendar.DAY_OF_WEEK);
            int MOUNTH_NUMBER = calendar.get(Calendar.MONDAY) + 1;
            int YEAR = calendar.get(Calendar.YEAR);

            String MOUNTH = MOUNTH_NUMBER < 10 ? "0" + MOUNTH_NUMBER : MOUNTH_NUMBER + "";
            String dateInitial = "";

            switch (period) {
                case DAY:
                    dateInitial = YEAR + "-" + MOUNTH + "-" + DAY + " 00:00:00";
                    break;
                case WEEK:
                    dateInitial = YEAR + "-" + MOUNTH + "-" + INITIAL_DAY_WEEK + " 00:00:00";
                    break;
                case MOUNTH:
                    dateInitial = YEAR + "-" + MOUNTH + "-" + "01" + " 00:00:00";
                    break;
            }

            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateInitial);
        }
        catch (Exception e){
            return new Date();
        }
    }

    public static Date defineFinalDate(DashboardPeriod period) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
            calendar.setTime(new Date());

            int DAY = calendar.get(Calendar.DAY_OF_MONTH);
            int FINAL_DAY_WEEK = DAY + (7 - calendar.get(Calendar.DAY_OF_WEEK));
            int FINAL_DAY_MOUNTH =  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int MOUNTH_NUMBER = calendar.get(Calendar.MONDAY) + 1;
            int YEAR = calendar.get(Calendar.YEAR);

            String MOUNTH = MOUNTH_NUMBER < 10 ? "0" + MOUNTH_NUMBER : MOUNTH_NUMBER + "";
            String dateFinal = "";

            switch (period) {
                case DAY:
                    dateFinal = YEAR + "-" + MOUNTH + "-" + DAY + " 23:59:59";
                    break;
                case WEEK:
                    dateFinal = YEAR + "-" + MOUNTH + "-" + FINAL_DAY_WEEK + " 23:59:59";
                    break;
                case MOUNTH:
                    dateFinal = YEAR + "-" + MOUNTH + "-" + FINAL_DAY_MOUNTH + " 23:59:59";
                    break;
            }

            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateFinal);
        }
        catch (Exception e){
            return new Date();
        }
    }
}
