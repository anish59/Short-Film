package in.helpingdevelop.shortfilm.helper;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatterUtils {

    public static final int DAY = 1;
    public static final int WEEK = 2;
    public static final int MONTH = 3;

    public static final SimpleDateFormat hourMinuteFormat = new SimpleDateFormat("hh:mm", Locale.US); // 01:55

    public static final SimpleDateFormat fullHourMinuteFormat = new SimpleDateFormat("HH:mm", Locale.US); // 13:55

    public static final SimpleDateFormat hourMinuteSecFormat = new SimpleDateFormat("HH:mm:ss", Locale.US); // 13:55:22

    public static final SimpleDateFormat ampmFormat = new SimpleDateFormat("a", Locale.US); // PM

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US); // 06-03-2018

    public static final SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US); // 06-03-2018 13:55

    public static final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US); // 2018-03-06

    public static final SimpleDateFormat dmyhmaFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.US); // 06 Mar 2018, 01:55 PM

    public static final SimpleDateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US); // 2018-03-06 01:55:22

    public static final SimpleDateFormat sdf_day = new SimpleDateFormat("EEE dd-MMM-yyyy", Locale.US); // Tue 06-Mar-2018
    public static final SimpleDateFormat dd_MMMM_YYYY = new SimpleDateFormat("dd MMMM yyyy", Locale.US); // 01 June,2018

    public static final SimpleDateFormat dayDate = new SimpleDateFormat("EEE dd", Locale.US); // Tue 06

    public static final SimpleDateFormat sdf_month = new SimpleDateFormat("MMMM yyyy", Locale.US); // March 2018

    public static final SimpleDateFormat zoneFormat = new SimpleDateFormat("Z", Locale.US); // +0530

    public static final SimpleDateFormat zoneShortFormat = new SimpleDateFormat("z", Locale.US); // GMT+05:30

    public static final SimpleDateFormat zoneLongFormat = new SimpleDateFormat("zzzz", Locale.US); // India Standard Time

    public static final SimpleDateFormat MMMFormat = new SimpleDateFormat("MMM", Locale.US); // Mar

    public static final SimpleDateFormat MMFormat = new SimpleDateFormat("MM", Locale.US); // 03

    public static final SimpleDateFormat ddFormat = new SimpleDateFormat("dd", Locale.US); // 06

    public static final SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US); // Tuesday

    public static final SimpleDateFormat weekFormat = new SimpleDateFormat("MMM dd", Locale.US); // Mar 06

    public static final SimpleDateFormat ddMMMyyyyFormat = new SimpleDateFormat("dd MMM, yyyy hh:mm a", Locale.US); // 06 Mar, 2018 01:55 PM
    public static final SimpleDateFormat MMMddyyyyFormat = new SimpleDateFormat(" MMM dd yyyy, hh:mm a", Locale.US); //  Mar 06 2018, 01:55 PM

    public static final SimpleDateFormat bookingDateFormat = new SimpleDateFormat("EEEE dd MMM, yyyy", Locale.US); // Tuesday 06 Mar, 2018
    public static final SimpleDateFormat bookingDateFormat2 = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.US); // Tuesday 06 Mar, 2018

    public static final SimpleDateFormat bookingTimeFormat = new SimpleDateFormat("hh:mm a", Locale.US); // 01:55 PM
    public static final SimpleDateFormat bookingTimeFormat2 = new SimpleDateFormat("hh:mm:ss a", Locale.US); // 01:55:00 PM

    public static final SimpleDateFormat dateFormatBooking = new SimpleDateFormat("MM/dd/yyyy", Locale.US); // 03/06/2018

    public static final SimpleDateFormat bookingReqFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US); // 06-3-2018
    public static final SimpleDateFormat MMMyyyy = new SimpleDateFormat("MMM:yyyy", Locale.US); // 05:1994
    public static final SimpleDateFormat MMyyyy = new SimpleDateFormat("MM:yyyy", Locale.US); // 05:1994
    public static final SimpleDateFormat MMM = new SimpleDateFormat("MMM", Locale.US); // 05:1994
    public static final SimpleDateFormat MM = new SimpleDateFormat("MM", Locale.US); // 05:1994
    public static final SimpleDateFormat EEEMMMddyyyy = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.US); // MON, Nov 18,2018


    public static String parseDate(String inputDate, SimpleDateFormat input, SimpleDateFormat output) {
        Date date = null;
        String str = null;
        try {
            date = input.parse(inputDate);
            str = output.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String parseDate(Date date, String outputFormat) {


        SimpleDateFormat spf = new SimpleDateFormat(outputFormat, Locale.US);
        return spf.format(date);
    }

    public static String parseDate(Calendar calendar, String outputFormat) {

        SimpleDateFormat spf = new SimpleDateFormat(outputFormat, Locale.US);
        return spf.format(calendar.getTime());
    }

    public static String parseDate(Calendar calendar, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String setCalendarDateTitle(Date date, boolean isWeek) {
        SimpleDateFormat spf;
        String outputFormat;

        if (!isWeek) {
            outputFormat = "EEEE MMMM dd";
            if (DateUtils.isToday(date.getTime())) {
                spf = new SimpleDateFormat(outputFormat, Locale.US);
                return "Today - " + spf.format(date);
            } else {
                spf = new SimpleDateFormat(outputFormat, Locale.US);
                return spf.format(date);
            }
        } else {
            outputFormat = "MMMM dd";

            spf = new SimpleDateFormat(outputFormat, Locale.US);
            String selectedMonthDate = spf.format(date);

            Calendar endWeekDate = toCalendar(date);
            endWeekDate.add(Calendar.DAY_OF_MONTH, 6);

            return selectedMonthDate + " - " + parseDate(endWeekDate.getTime(), "dd");
        }
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Date stringToDate(String dateString, String inputFormat) {

//        String dateString = "03/26/2012 11:49:00 AM";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(inputFormat);
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(convertedDate);

        return convertedDate;
    }

    public static Calendar stringToCalendar(String dateTime, SimpleDateFormat dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        try {
            cal.setTime(dateFormat.parse(dateTime));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }


    public static Date setTimeToCurrentDate(Date dateForTime) {
        Calendar calForTime = toCalendar(dateForTime);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.set(Calendar.HOUR_OF_DAY, calForTime.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calForTime.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calForTime.get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calForTime.get(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    public static String milisecondToString(long milisecondTime, SimpleDateFormat outputFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milisecondTime);
        return DateFormatterUtils.parseDate(calendar, outputFormat);
    }

    public static Date milisecondToDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static boolean isToday(String inputTime, SimpleDateFormat inputFormat) {
        return DateUtils.isToday(stringToCalendar(inputTime, inputFormat).getTimeInMillis());
    }
}
