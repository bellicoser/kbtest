package com.example.kb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 *
 */
public class App3 {
	public static void main(String[] args) throws ParseException {
		String s1 = "Sun 14:00-16:00\nMon 01:00-21:00\nFri 21:01-14:00\nTue 21:00-22:00\nSat 08:00-09:00\nWed 14:00-19:00\nWed 01:00-02:00";
		String s2 = "Mon 01:00-23:00\nTue 01:00-23:00\nWed 01:00-23:00\nThu 01:00-23:00\nFri 01:00-23:00\nSat 01:00-23:00\nSun 01:00-21:00";

		String[] task = s1.split("\n");
		List<String> dates = Arrays.asList(task);
		System.out.println("-=before Sorted=-");
		for (String d1 : dates) {
			System.out.println(d1);
		}

		Collections.sort(dates, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				int result = 0;
				try {
					DateFormat format = new SimpleDateFormat("EEE");
					Date d1 = format.parse(s1);
					Date d2 = format.parse(s2);
					if (d1.equals(d2)) {
						int c = s1.substring(s1.indexOf(" ") + 1).compareTo(s2.substring(s2.indexOf(" ") + 1));
						result = c;
					} else {
						Calendar cal1 = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();
						cal1.setTime(d1);
						cal2.setTime(d2);
						result = cal1.get(Calendar.DAY_OF_WEEK) - cal2.get(Calendar.DAY_OF_WEEK);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return result;
			}
		});
		System.out.println("-=after Sorted=-");
		System.out.println(dates);
		System.out.println("size = " + dates.size());
		calcDiffMin(dates);
	}

	public static long calcDiffMin(List<String> dates) throws ParseException {
		int result = 0;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		System.out.println(">>>>>>>>>>>>>>>>");

		DateFormat format = new SimpleDateFormat("EEE");
		DateFormat df = new SimpleDateFormat("EEE HH:mm");
		String from = "";
		String to = "";
		for (int i = 0; i < dates.size(); i++) {
			//System.out.println("i " + i);
			Date d1 = format.parse(dates.get(i));
			Date d2 = null;
			String day1 = format.format(d1);
			String day2 = "";
			from = dates.get(i).substring(dates.get(i).indexOf("-") + 1);
			if (i < dates.size()-1) {
				d2 = format.parse(dates.get(i+1));
				day2 = format.format(d2);
				to = dates.get(i + 1).substring(dates.get(i + 1).indexOf(" ") + 1, dates.get(i + 1).indexOf("-"));
				if (day1.equals("Sun")) {
					System.out.println("> Sun Case limit is 00:00");
					to = "00:00";
				}
			} else {
				d2 = format.parse(dates.get(0));
				day2 = format.format(d2);
				to = dates.get(0).substring(dates.get(0).indexOf(" ") + 1, dates.get(0).indexOf("-"));
			}
			from = day1.concat(" ").concat(from);
			to = day2.concat(" ").concat(to);
			System.out.println("> from " + from +" to "+to);
			cal1.setTime(df.parse(from));
			cal2.setTime(df.parse(to));
			//result = cal1.get(Calendar.HOUR_OF_DAY) - cal2.get(Calendar.HOUR_OF_DAY);
//			System.out.println("DAY_OF_WEEK "+cal1.get(Calendar.DAY_OF_WEEK)+" HOUR_OF_DAY "+cal1.get(Calendar.HOUR_OF_DAY));
//			System.out.println("DAY_OF_WEEK "+cal2.get(Calendar.DAY_OF_WEEK)+" HOUR_OF_DAY "+cal2.get(Calendar.HOUR_OF_DAY));
//			int DAY_OF_WEEK = cal2.get(Calendar.DAY_OF_WEEK)-cal1.get(Calendar.DAY_OF_WEEK);
//			int HOUR_OF_DAY = cal2.get(Calendar.HOUR_OF_DAY)-cal1.get(Calendar.HOUR_OF_DAY);
//			System.out.println("Diff  "+DAY_OF_WEEK+" Diff "+HOUR_OF_DAY);
		
			long days = ChronoUnit.DAYS.between(cal1.toInstant(), cal2.toInstant());
			long hours = ChronoUnit.HOURS.between(cal1.toInstant(), cal2.toInstant());
			long mins = ChronoUnit.MINUTES.between(cal1.toInstant(), cal2.toInstant());
			System.out.println("days "+days);
			System.out.println("hours "+hours);
			System.out.println("mins "+mins);
			if (mins < 0) {
				System.out.println("java bugs day ");
				System.out.println("DAY_OF_WEEK "+cal1.get(Calendar.DAY_OF_WEEK)+" HOUR_OF_DAY "+cal1.get(Calendar.HOUR_OF_DAY)+" MINUTE "+cal1.get(Calendar.MINUTE));
				System.out.println("DAY_OF_WEEK "+cal2.get(Calendar.DAY_OF_WEEK)+" HOUR_OF_DAY "+cal2.get(Calendar.HOUR_OF_DAY)+" MINUTE "+cal2.get(Calendar.MINUTE));
				int DAY_OF_WEEK = cal2.get(Calendar.DAY_OF_WEEK)-cal1.get(Calendar.DAY_OF_WEEK);
				int HOUR_OF_DAY = cal2.get(Calendar.HOUR_OF_DAY)-cal1.get(Calendar.HOUR_OF_DAY);
				int MINUTE = cal2.get(Calendar.MINUTE)-cal1.get(Calendar.MINUTE);
				System.out.println("DiffDAY_OF_WEEK  "+DAY_OF_WEEK+" DiffHOUR_OF_DAY "+HOUR_OF_DAY+" DiffMINUTE "+MINUTE);
				mins = (DAY_OF_WEEK-1)*24*60 + HOUR_OF_DAY*60 + MINUTE;
				System.out.println("new mins "+mins);
			}
//			long seconds = (cal2.getTimeInMillis()-cal1.getTimeInMillis()) /1000;
//			int hours = (int) (seconds / 3600);
//			System.out.println("hours "+hours);
		}
		return result;
	}

}
