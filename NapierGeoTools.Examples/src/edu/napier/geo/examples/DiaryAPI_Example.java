package edu.napier.geo.examples;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import edu.napier.geo.common.Location;
import edu.napier.geo.diary.CalendarEntry;
import edu.napier.geo.diary.DiaryFacade;
import edu.napier.geo.diary.Resource;


public class DiaryAPI_Example {
	public static void main(String[] args){
		DiaryFacade appointments = new DiaryFacade();
		appointments.deleteAllEvents();//Remove any previous events loaded from file
		//Locations
		
		Location merchiston = new Location(55.933326,-3.213896,0.0);
		merchiston.setDescription("Edinburgh Napier University, Merchiston Campus");
		merchiston.setSource("NeilU");
		
		Location sighthill = new Location(55.924959,-3.289067,0.0);
		merchiston.setDescription("Edinburgh Napier University, Merchiston Campus");
		merchiston.setSource("NeilU");
		
		//Resources: people
		
		Resource neilU = new Resource("Neil Urquhart, Lecturer");
		Resource csStudents = new Resource("Computing Science Students");
		Resource seStudents= new Resource("Software Engineering Students");
		
		//Resources rooms
		Resource meetingRoomS1 = new Resource("Sighthill Meeting room 1");
		Resource lectureTheatreM1 = new Resource("Merchiston Lecture Theatre");
		Resource classRoomM5 = new Resource("Merchiston Classroom 5");
		
		try{
			ArrayList<Resource> resources = new ArrayList<Resource>();
			
			resources.add(neilU);
			resources.add(csStudents);
			resources.add(lectureTheatreM1);
		    appointments.createCalendarEntry(20017,5,20,14,00,20017,5,20,16,00,"Comp. Sci 2 Lecture","Lecture",merchiston,resources);
		    
		    resources.clear();
		    resources.add(neilU);
			resources.add(csStudents);
			resources.add(classRoomM5);
			appointments.createCalendarEntry(20017,5,20,11,00,20017,5,20,12,00,"Comp. Sci 2 Tutorial","Tutorial",merchiston,resources);
			
			resources.clear();
		    resources.add(neilU);
			resources.add(seStudents);
			resources.add(classRoomM5);
			appointments.createCalendarEntry(20017,5,21,10,00,20017,5,21,12,00,"Software Eng. Practical","Practical",merchiston,resources);
			
			resources.clear();
		    resources.add(neilU);
			resources.add(meetingRoomS1);
			appointments.createCalendarEntry(20017,5,21,15,00,20017,5,21,16,00,"Business meeting","Meeting",sighthill,resources);
			
			System.out.println("Schedule:");
			for(CalendarEntry e: appointments.getAllEntries()){
				System.out.println("Event " + e);
			}

			System.out.println("Schedule for CS students:");
			for(CalendarEntry e: appointments.getEventsAllocatedToResource(csStudents)){
				System.out.println("Event " + e);
			}
			
			System.out.println("Write to iCal");
			appointments.exportToICAL(appointments.getAllEntries(), "Test.ical");

			
		}catch(Exception e){
			System.out.println("Error creating event");
		}
	}
}
