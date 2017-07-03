package edu.napier.geo.easykml.KML_Object.timePrimitive;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Time {
	
	private String formattedString;

	/**
	 * Time defines a formatted dateTime string according to the XML schema time
	 */
	public Time() {
		// TODO Auto-generated constructor stub
	}
	
	public String setYear(int year) {
		formattedString = Integer.toString(year);
		
		return formattedString;
	}

	public String setYearMonth(int year, int month) {
		formattedString = Integer.toString(year) + "-" + Integer.toString(month);

		return formattedString;
	}

	public String setDate(int year, int month, int day) {
		formattedString = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

		return formattedString;
	}

	public String setDateTime(int year, int month, int day, int hour, int minute, int second, int timeZone) {
		String utcTimeZone = "Z";
		if (timeZone != 0 && (timeZone <= 14 || timeZone >= -12)) {
			DecimalFormat df = new DecimalFormat("00");

			if (timeZone < 0)
				utcTimeZone = "-" + df.format(timeZone * (-1)) + ":00";

			else
				utcTimeZone = "+" + df.format(timeZone) + ":00";
		}

		formattedString = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + "T"
				+ Integer.toString(hour) + ":" + Integer.toString(minute) + ":" + Integer.toString(second) + utcTimeZone;

		return formattedString;
	}
	
	public String getFormattedString(){
		if(formattedString == "" || formattedString == null){
			// return current year
			return Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		}
		return formattedString;
	}

}
