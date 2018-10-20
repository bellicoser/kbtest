package com.example.kb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Hello world!
 *
 */
public class App3 {
	public static void main(String[] args) throws ParseException {
		String s = "Mon 08:00-09:00\nTue 20:00-21:00\nTue 08:00-09:00";
		String[] task = s.split("\n");
		List<Date> l = new ArrayList<Date>();
		DateFormat df = new SimpleDateFormat("EEE hh:mm-hh:mm");
		for (String d : task) {
			System.out.println(d);
			Date date = df.parse(d);
			l.add(date);
		}
		for (Date d : l) {
			System.out.println(d);
		}
		
		long diff = l.get(1).getTime() - l.get(0).getTime();
		long diffMinutes = diff / (60 * 1000) % 60; 
		
		System.out.println(diffMinutes);
				
			
	}
	
	String dFormat = "EEEE, MMMM d, yyyy";
	String tFormat = "KK:mm a";

	public String formatInterval(Date from, Date to) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat(dFormat),
	                     timeFormat = new SimpleDateFormat(tFormat);

	    StringBuilder s = new StringBuilder();
	    // Day
	    s.append(dateFormat.format(from));
	    s.append(' ');
	    // Start time
	    s.append(timeFormat.format(from));
	    s.append(" - ");
	    // End time
	    s.append(timeFormat.format(to));
	    return s.toString();
	}

	public String formatInterval(Calendar from, Calendar to) {
	    return formatInterval(from.getTime(), to.getTime());
	}
	
}
