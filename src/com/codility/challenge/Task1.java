package com.codility.challenge;

import java.util.HashSet;
import java.util.Set;

public class Task1 {

	public static void main(String[] args) {
		String s = "jane    529K 05 Feb 1971 delete-this.mov\n" + 
				"john    243K 26 Jul 1962 system.dll\n" + 
				"root    748K 25 Jan 1963 listing.h\n" + 
				"bob      666 13 Dec 2013 moonlight-sonata.wav\n" + 
				"alice   565M 11 Jun 1985 temporary.txt\n" + 
				"admin   673M 16 Sep 2007 picture.gif\n" + 
				"jane      2M 24 Nov 1994 important.xml\n" + 
				"john     240 28 Feb 2000 song.wav";
		System.out.println(solution(s));
	}
	
	public static String solution(String S) {
		String[] files = S.split("\n");
		Set<String> owners = new HashSet<>();
		String limitDate = "28 Feb 2000";
		
		for (int i = 0; i < files.length; i++) {
			String file = files[i];
			
			String strSize = file.substring(7, 12).trim();
			char[] chars = strSize.toCharArray();
			char unit = chars[chars.length - 1];
			boolean isBytes = false;
			
			if (Character.isDigit(unit)) {
				strSize = strSize.substring(0, strSize.length());
				isBytes = true;
			} else {
				strSize = strSize.substring(0, strSize.length() - 1);
			}
			
			int size = Integer.parseInt(strSize);
			
			String lastModified = file.substring(13, 24);
			String[] dates = lastModified.split(" ");
			int day = Integer.parseInt(dates[0]);
			String month = dates[1];
			int year = Integer.parseInt(dates[2]);
			String owner = file.substring(0, 5);
			
			if (lastModified.equals(limitDate) || year < 2000) {
				
				addOwner(owners, unit, isBytes, size, owner);
				
			} else if (year == 2000) {
				if (month.equals("Jan")) {
					addOwner(owners, unit, isBytes, size, owner);					
				} else if (month.equals("Feb") && day < 28) {
					addOwner(owners, unit, isBytes, size, owner);					
				}
				
			}
			
		}
		
		
		
		return String.valueOf(owners.size());
	}

	private static void addOwner(Set<String> owners, char unit, boolean isBytes, int size, String owner) {
		if (isBytes || unit == 'K') {
			owners.add(owner.trim());
		} else if (unit == 'M' && size < 2) {
			owners.add(owner.trim());
		}
	}
}
