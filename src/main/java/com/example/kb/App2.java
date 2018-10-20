package com.example.kb;

import java.util.Arrays;
import java.util.BitSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App2 {
	public static void main( String[] args ) {
		
		new Thread (new Runnable() {

			public void run() {
				System.out.println("Thread Run");
			}
			
		}).start();
	
    String S1 = "We test coders. Give us a try?";
    String S2 = "Forget CVs..Save time . x x";
    String S3 = "OMG!! This is good for me";
    String S4 = "..........";
    System.out.println("return "+checkSpecial(S1));
    System.out.println("return "+checkSpecial(S2));
    System.out.println("return "+checkSpecial(S3));
    System.out.println("return "+checkSpecial(S4));
	}	
	
	public static int checkSpecial(String s) {
		Pattern p = Pattern.compile("[a-zA-Z\\s]+", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(s);
		int foundWord = 0;
		int maxword = 0;
		while(m.find()) {
			System.out.println("found sentense : " + m.group(0));
	
			Pattern p2 = Pattern.compile("[a-zA-X]+", Pattern.CASE_INSENSITIVE);
			Matcher m2 = p2.matcher(m.group());
			while(m2.find()) {
				System.out.println("found word : " + m2.group());
				foundWord++;
			}
			if(foundWord>=maxword)maxword = foundWord;
			foundWord = 0;
		}				
		return  maxword;
	}
	
	
}
