package com.divinisoft.project.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class DateUtility {
	private static final String TIMEZONE = "IST";
	private static final String DATEFORMAT = "yyyy-MM-dd";
	private static List<Date> HOLIDAYS;

	public static String formatDateToString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat insdf = new SimpleDateFormat(DATEFORMAT);
		insdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE));

		return insdf.format(date);
	}

	public static Date formatStringToDate(String dateString) {
		if (dateString == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE));

		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Unable to parse date: " + dateString);
		}

		return date;
	}

	public static Date getStartDateOfCurrentYear() {
		String dateString = Year.now().getValue() + "-01-01";
		return formatStringToDate(dateString);
	}

	public static String getStartDateStringOfCurrentYear() {
		return Year.now().getValue() + "-01-01";
	}

	public static boolean isWeekendDay(String date) {
		Calendar c = Calendar.getInstance();
		c.setTime(formatStringToDate(date));
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	public static boolean isHoliday(String date) {
		return DateUtility.getHolidays().contains(DateUtility.formatStringToDate(date));
	}

	public static void setHolidays(List<String> holidays) {
		List<Date> holidayDates = new ArrayList<>();
		for (String holiday : holidays) {
			Date holidayDate = DateUtility.formatStringToDate(holiday);
			holidayDates.add(holidayDate);
		}
		DateUtility.HOLIDAYS = holidayDates;
	}

	public static List<Date> getHolidays() {
		if (DateUtility.HOLIDAYS == null || DateUtility.HOLIDAYS.size() == 0) {
			List<String> holidays = DateUtility.get2020HolidayList();
			DateUtility.setHolidays(holidays);
		}
		return DateUtility.HOLIDAYS;
	}

	public static List<String> get2020HolidayList() {
		List<String> holidays = new ArrayList<>();
		holidays.add("2020-01-01");
		holidays.add("2020-01-15");
		holidays.add("2020-01-26");
		holidays.add("2020-05-01");
		holidays.add("2020-08-15");
		holidays.add("2020-08-21");
		holidays.add("2020-10-02");
		holidays.add("2020-10-23");
		holidays.add("2020-10-26");
		holidays.add("2020-11-01");
		holidays.add("2020-11-13");
		holidays.add("2020-12-25");

		return holidays;
	}
}
