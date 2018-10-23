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
		String s = "Sun 01:00-09:00\nMon 00:00-21:00\nFri 11:00-14:00\nTue 09:00-10:00\nSat 08:00-09:00\nWed 14:00-09:00";
		String s2 = "Sun 01:00\nMon 00:00\nFri 11:00\nTue 09:00\nSat 08:00\nWed 14:00";
		String s3 = "Sunday \nMonday \nFriday \nTueday \nSatday \nWedday ";
		DateFormat df = new SimpleDateFormat("EEE HH:mm-HH:mm");
		DateFormat df2 = new SimpleDateFormat("EEE HH:mm");
		DateFormat df3 = new SimpleDateFormat("EEE");
		List<Date> myList = new ArrayList<Date>();
		String[] task = s3.split("\n");
		
		System.out.println("-=before Sorted=-");
		for (String d1 : task) {
			myList.add(df3.parse(d1));
			System.out.println(d1);
		}
		Collections.sort(myList);
		
//		Collections.sort(myList,new Comparator<Date>(){
//			@Override
//			public int compare(Date d1,Date d2) {
//				int compare1 = d1.compareTo(d2);
//				//boolean compare2 = d1.getDay() > d2.getDay();
//				//int result = (compare2) ? 1 : 0;
//				System.out.println("> "+df.format(d1)+" day = "+d1.getDay()+" : "+df.format(d2)+" day = "+d2.getDay()+" ans: "+compare1);
//				return compare1;
//			}
//		});
	
		new Comparator<Date>(){
			@Override
			public int compare(Date d1,Date d2) {
				return 0;
			}
		};
		System.out.println("-=after Sorted=-");
		for (Date d1 : myList) {
			System.out.println(df.format(d1));
		}
	}

	public static String[] sorting(String[] task) {
		
		return task;
	}

	// sorting convenience utility
	public static boolean compareArr(String arr1, String arr2) {
		arr1 = arr1.substring(0, arr1.indexOf("-"));
		arr2 = arr2.substring(0, arr2.indexOf("-"));
		DateFormat df = new SimpleDateFormat("EEE HH:mm");
		Date date1 = null, date2 = null;
		try {
			date1 = df.parse(arr1);
			date2 = df.parse(arr2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// int result = (date1.before(date2)) ? 0 : 1;
		// System.out.println("----");
		System.out.println(arr1 + " -> " + arr2);
		// System.out.println(date1.before(date2));
		boolean res = date2.after(date1);
		System.out.println(res);
		return res;
	}

	public static long calcDiff(String a, String b) {
		Pattern p1 = Pattern.compile("[\\d:]+", Pattern.CASE_INSENSITIVE);
		Pattern p2 = Pattern.compile("-[\\d:]+", Pattern.CASE_INSENSITIVE);
		Matcher m1 = p1.matcher("Sun 08:00-09:00");
		Matcher m2 = p2.matcher("Sun 08:00-09:00");
		m1.find();m2.find();
		System.out.println("found sentense : " + m1.group());
		System.out.println("found sentense : " + m2.group());
		DateFormat df = new SimpleDateFormat("HH:mm");
		long dif = 0;
		try {
			Date d1 = df.parse(a);
			Date d2 = df.parse(b);
			dif = (d1.getTime() - d2.getTime()) / (60 * 1000) % 60;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dif;
	}

}
