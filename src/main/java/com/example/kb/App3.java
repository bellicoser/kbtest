package com.example.kb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App3 {
	public static void main(String[] args) throws ParseException {
		String s1 = "Sun 14:00-16:00\nMon 01:00-21:00\nFri 11:00-14:00\nTue 09:00-10:00\nSat 08:00-09:00\nWed 14:00-19:00\nWed 10:00-12:00";
		String s2 = "Sun 01:00\nMon 00:00\nFri 11:00\nTue 09:00\nSat 08:00\nWed 14:00";
		String s3 = "Sunday \nMonday \nFriday \nTueday \nSatday \nWedday ";
		DateFormat df1 = new SimpleDateFormat("EEE HH:mm-HH:mm");
		DateFormat df2 = new SimpleDateFormat("EEE HH:mm");
		DateFormat df3 = new SimpleDateFormat("EEE");

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

		List<Date> ldate = new ArrayList<Date>();
		DateFormat df = new SimpleDateFormat("EEE HH:mm");
		for (String as : dates) {
			String s1from = as.substring(0, 3).concat(" ").concat(as.substring(4, 9));
			String s1to = as.substring(0, 3).concat(" ").concat(as.substring(10));
			System.out.println(s1from + " " + s1to);
			ldate.add(df.parse(s1from));
			ldate.add(df.parse(s1to));
		}
		System.out.println("size = " + ldate.size());
		calcDiffMin(ldate);
	}

	public static long calcDiffMin(List<Date> dates) throws ParseException {
		long dif = 0;
		long result = 0;
		DateFormat df = new SimpleDateFormat("EEE");
		DateFormat df1 = new SimpleDateFormat("EEE HH:mm");
		int count = 1;
		Date from;
		Date to;
		for (int i = 1; i < dates.size(); i++) {
			System.out.println("count : " + count);
			System.out.println("i : " + i);
			if (i == dates.size()-1) {
				from = dates.get(i);
				to = dates.get(0);
			} else {
				from = dates.get(i);
				to = dates.get(i + 1);
			}
			if (df.format(from).equals("Sun")) {
				System.out.println("Sun ");
				to = df1.parse("Mon 00:00");
				System.out.println(from + " " + to);
				dif = from.getTime() - to.getTime();
			} else {
				System.out.println(from + " " + to);
				dif = from.getTime() - to.getTime();
			}
			dif = dif / (60 * 60 * 1000) % 24;
			dif = Math.abs(dif);
			System.out.println("dif : " + dif);
			result = result > dif ? result : dif;
			i++;
			count++;
		}
		System.out.println("result : " + result);
		return result;
	}

}
