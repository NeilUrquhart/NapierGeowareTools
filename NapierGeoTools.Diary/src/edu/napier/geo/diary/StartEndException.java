package edu.napier.geo.diary;


public class StartEndException extends Exception {
	
 /** should be thrown when the start time of a CalendarEntry is set to be after its end time
 * 
 */
public StartEndException()
 {
     super("Start time must not be after End time");
 }
}
