******************
 ~ Introduction ~
******************

DiaryAPI may be used for creating, modifying and managing calendar events and resources.
The import and export of data from / to iCalendar files also is supported. 


******************
    ~ Usage ~
******************

Simply add the DiaryAPI jar file to your classpath.


******************
   ~ Examples ~
******************

Start by creating a DiaryFacade object, which provides all methods to use the library:

DiaryFacade df = new DiaryFacade();

A new event can be created in multiple ways. 
Both examples shown below will create a CalendarEntry representing an event scheduled to start at 30th March 2017 18:00, ending on 31st March at 4 am.

CalendarEntry ce;
try {
	ce = df.createCalendarEntry(2017, 3, 30, 18, 00, 2017, 3, 31, 4, 0)
	ce = df.createCalendarEntry(LocalDateTime.of(2017, 3, 30, 18, 0), 
                                   LocalDateTime.of(2017, 3, 31, 4, 0));
} 
catch (StartEndException e) {
	e.printStackTrace();
}

It also is possible to add optional attributes such as a summary, description, location or allocated resources to an event, or to edit the event start or end times:

ce.setDescription("Birthday Party");
ce.setSummary("Time to celebrate my birthday!");
try {
ce.setStart(LocalDateTime.of(2017, 3, 30, 19, 30)); ce.setEnd(LocalDateTime.of(2017, 3, 31, 4, 30));

}
catch (StartEndException e1) {
	e1.printStackTrace();
}
 
Location and Resources are represented by own classes and can be created as follows:

Location location1 = df.createTextOnlyLocation("My home");
Resource resource1 = df.createOrAccessResource("Sound system");

Locations can be added to a CalendarEntry just like description and summary. 
To add a Resource to a CalendarEntry, the DiaryAPI has to be used:

df.addResourceToEvent(ce,resource1);

It also is possible to add optional attributes on creation of a CalendarEntry, with multiple Resources at once:

ArrayList<Resource> allocatedResources = new ArrayList<Resource>();
allocatedResources.add(res1, res2, res3);
ce = df.createCalendarEntry(2017, 3, 31, 15, 30, 2017, 3, 31, 16, 30, "Description", "Summary", location1, allocatedResources); 

To export CalendarEntries to an iCalendar file, simply create an ArrayList containing all events you want to export and invoke the exportToICAL method, with a String for the desired file name:

df.exportToICAL(list, "Diary_API_ical_Export");

Importing CalendarEntries from an iCalendar file is just as easy and only requires the file name:

df.importEventsFromIcalFile("Diary_API_ical_Export.ics");

This method will return an ArrayList of the imported CalendarEntries, but those will also be added to DiaryFacade’s ArrayList allEntries.
