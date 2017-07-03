package edu.napier.geo.diary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import edu.napier.geo.common.Location;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ICalendarFileHandler {

	private String icsString="";
	private DiaryFacade df;
	private static final int ICS = 0;
	//	private static final int IFB = 1;

	private static final String NL = "\r\n";
	private final String calBegin =   "BEGIN:VCALENDAR\r\n";
	private final String version =    "VERSION:2.0\r\n";
	private final String prodid =     "PRODID:NapierGeoTools_DiaryAPI\r\n";
	private final String eventBegin = "BEGIN:VEVENT\r\n";
	private final String eventEnd =   "END:VEVENT\r\n";
	private final String calEnd =     "END:VCALENDAR\r\n";

	public ICalendarFileHandler(DiaryFacade diaryFacade) {
		df=diaryFacade;
	}

	/** Creates an .ics File containing the specified events following the iCalendar specification
	 * @param events ArrayList of CalendarEntries to export
	 * @param filename user-defined filename (does not require the file extension)
	 * @return String containing the content of the written .ics file
	 */
	public String createICSFile(ArrayList<CalendarEntry> events, String filename){
		icsString += calBegin;
		icsString += version;
		icsString += prodid;
		for (CalendarEntry calendarEntry : events) {
			icsString += eventBegin;
			icsString += "UID:"+calendarEntry.getUid()+NL;
			if (calendarEntry.getLocation()!= null){
				String locationDescription = calendarEntry.getLocation().getDescription();
				if (locationDescription != null && locationDescription!="")
					icsString += "LOCATION:"+ locationDescription+NL;
			}
			String summary = calendarEntry.getSummary();
			if (summary != null && summary!="")
				icsString += "SUMMARY:"+summary+NL;
			String description = calendarEntry.getDescription();
			if (description != null && description!="")
				icsString += "DESCRIPTION:"+description+NL;
			icsString += "DTSTART:";
			icsString += this.convertDateTimeToIcalString(calendarEntry.getStart())+NL;
			icsString += "DTEND:";
			icsString += this.convertDateTimeToIcalString(calendarEntry.getEnd())+NL;
			icsString += "DTSTAMP:";
			icsString += this.convertDateTimeToIcalString(LocalDateTime.now())+NL;
			int numberOfResources = calendarEntry.getAllocatedResources().size();
			if (numberOfResources>0){
				icsString += "RESOURCES:";
				for (int i = 0; i<numberOfResources; i++) {
					icsString += calendarEntry.getAllocatedResources().get(i).getName();
					if (i!=numberOfResources-1)
						icsString +=",";
				}
				icsString += NL;
			}
			icsString += eventEnd;
		}
		icsString += calEnd;
		writeToFile(icsString, filename);
		return icsString;
	}

	/** Used by createICSFile() to write the file with the specified name
	 * @param content String that should be formatted to match the iCalendar specification
	 * @param filename user-defined filename
	 */
	private void writeToFile(String content, String filename) {

		filename+=".ics";
		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** Used by createICSFile() to build iCalendar formatted Strings from LocalDateTimes
	 * @param timestamp instant in time to convert
	 * @return String containing the instant in time in iCalendar format
	 */
	private String convertDateTimeToIcalString(LocalDateTime timestamp) {

		String result="";
		result += timestamp.getYear();

		int help = timestamp.getMonthValue();
		if (help<10)
			result+= "0";
		result+=help;

		help = timestamp.getDayOfMonth();
		if (help<10)
			result+= "0";
		result+=help + "T";

		help = timestamp.getHour();
		if (help<10)
			result +="0";
		result+=help;

		help = timestamp.getMinute();
		if (help<10)
			result +="0";
		result+=help;

		help = timestamp.getSecond();
		if (help<10)
			result +="0";
		result+=help+"Z";

		return result;

	}

	/** imports an iCalendar file and returns an ArrayList containing CalendarEntries
	 * that have been generated from events stored in the iCalendar file
	 * @param filename file to read
	 * @return  ArrayList containing CalendarEntries generated from events read from the file
	 * @throws FileNotFoundException if the specified file cannot be found
	 */
	public ArrayList<CalendarEntry> readIcalFile(String filename) throws FileNotFoundException{
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		ArrayList<String> scannedLines = new ArrayList<String>();
		ArrayList<CalendarEntry> readEvents = new ArrayList<CalendarEntry>();
		String uid= null, description= null, summary= null;
		Location location = null;
		ArrayList<Resource> resources = new ArrayList<Resource>();
		LocalDateTime start = null, end = null;

		while(scanner.hasNext()) {
			scannedLines.add(scanner.nextLine());
		}
		scanner.close();

		int i=0;
		boolean eventparsing = false;
		while (i<scannedLines.size()){
			if (scannedLines.get(i).startsWith("BEGIN:VEVENT")){
				eventparsing = true;
				i++;
			}
			while (eventparsing == true){
				if (scannedLines.get(i).startsWith("UID:")){
					uid = scannedLines.get(i).substring(4);
				}
				else if (scannedLines.get(i).startsWith("DESCRIPTION:")){
					description =scannedLines.get(i).substring(12);
				}
				else if (scannedLines.get(i).startsWith("SUMMARY:")){
					summary =scannedLines.get(i).substring(8);
				}
				else if (scannedLines.get(i).startsWith("LOCATION:")){
					String locationString = scannedLines.get(i).substring(9);
					location = new Location();
					location.setDescription(locationString);
				}
				else if (scannedLines.get(i).startsWith("RESOURCES:")){
					String resourceString = scannedLines.get(i).substring(10);
					String[] resourceStringArray = resourceString.split("\\s*,\\s*"); //also removing whitespace characters
					for (String string : resourceStringArray) {
						resources.add(df.createOrAccessResource(string));
					}
				}				
				else if (scannedLines.get(i).startsWith("DTSTART:")){
					start = convertIcalTime2LocalDateTime(scannedLines.get(i).substring(8));
				}
				else if (scannedLines.get(i).startsWith("DTEND:")){
					end = convertIcalTime2LocalDateTime(scannedLines.get(i).substring(6));
				}	
				else if (scannedLines.get(i).startsWith("END:VEVENT")){
					eventparsing = false;
					try {
						CalendarEntry ce = new CalendarEntry(start, end, description, summary, location, resources);
						start=null; end=null; description=null; summary=null;location=null;
						resources=new ArrayList<Resource>();
						ce.setUid(uid);
						readEvents.add(ce);
					} catch (StartEndException e) {
						e.printStackTrace();
					}
					break;
				}
				i++;
			}
			i++;
		}
		return readEvents;

	}

	/** Used by readIcalFile() to convert Strings of times in iCalendar format to LocalDateTime
	 * @param parsedTimeString String of a time in iCalendar format
	 * @return LocalDateTime object of the read time
	 */
	private LocalDateTime convertIcalTime2LocalDateTime(String parsedTimeString){
		int year, month, day, hour, minute, second;
		year = Integer.parseInt(parsedTimeString.substring(0, 4));
		month = Integer.parseInt(parsedTimeString.substring(4, 6));
		day  = Integer.parseInt(parsedTimeString.substring(6, 8));
		hour = Integer.parseInt(parsedTimeString.substring(9, 11));
		minute = Integer.parseInt(parsedTimeString.substring(11, 13));
		second = Integer.parseInt(parsedTimeString.substring(13, 15));
		return LocalDateTime.of(year, month, day, hour, minute, second);
	}


}
