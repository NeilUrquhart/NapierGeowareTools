package edu.napier.geo.diary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.napier.geo.common.Location;

public class DiaryFacade{

	private ArrayList<CalendarEntry> allEntries = new ArrayList<CalendarEntry>();
	private ArrayList<Resource> allResources = new ArrayList<Resource>();
	private ICalendarFileHandler icalHandler = new ICalendarFileHandler(this);
	private String savefile = "DiaryAPI";

	/**
	 *  tries to read a saved file from an earlier execution - if this fails, it creates a new one
	 */
	public DiaryFacade(){
		this("DiaryAPI");
	}

	/** tries to read a saved file from an earlier execution - if this fails, it creates a new one
	 * @param savefilename name for the save file to read from (if it exists) and write to
	 */
	public DiaryFacade(String savefilename){
		this.savefile=savefilename;
		ObjectInputStream ois;

		try {						
			ois = new ObjectInputStream(new FileInputStream(savefile+".ser")); //read an existing file
			allEntries = (ArrayList<CalendarEntry>) ois.readObject();
			allResources = (ArrayList<Resource>) ois.readObject();
			System.out.println("File has been read successfully");
		} catch (Exception ex) {
			System.out.println("File not found - creating a new file");
			saveDataPersistently();
		}
	}

	/** returns an ArrayList containing all CalendarEntries
	 * @return ArrayList containing all CalendarEntries
	 */
	public ArrayList<CalendarEntry> getAllEntries() {
		return allEntries;
	}

	/** returns an ArrayList containing all CalendarEntries
	 * @return ArrayList containing all Resources
	 */
	public ArrayList<Resource> getAllResources() {
		return allResources;
	}

	/**
	 * saves object data to the file, is called whenever something is created, deleted, or changed
	 */
	private void saveDataPersistently() {
		try {
			FileOutputStream out = new FileOutputStream(savefile+".ser");
			ObjectOutputStream oout = new ObjectOutputStream(out);
			oout.writeObject(allEntries);
			oout.writeObject(allResources);
			oout.close();
			System.out.println("Data saved!");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	//all parameters using YMDHM
	/** creates and returns a new CalendarEntry with the associated parameters (including all optional ones).
	 * It is automatically added to the facade classes ArrayList,
	 * and Resources are automatically associated and cross-referenced
	 * @param yearStart year of the start instant in time
	 * @param monthStart month of the start instant in time
	 * @param dayStart day of the start instant in time
	 * @param hourStart hour of the start instant in time
	 * @param minuteStart minute of the start instant in time
	 * @param yearEnd year of the end instant in time
	 * @param monthEnd month of the end instant in time
	 * @param dayEnd day of the end instant in time
	 * @param hourEnd hour of the end instant in time
	 * @param minuteEnd minute of the end instant in time
	 * @param description description/title of the calendar entry
	 * @param summary summary of the calendar entry
	 * @param location location associated with the event of the calendar entry
	 * @param resources resources associated with the event of the calendar entry
	 * @return CalendarEntry object with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public CalendarEntry createCalendarEntry(int yearStart, int monthStart, int dayStart, int hourStart, int minuteStart,
			int yearEnd, int monthEnd, int dayEnd, int hourEnd, int minuteEnd,
			String description, String summary, Location location, ArrayList<Resource> resources) throws StartEndException{
		CalendarEntry ce = new CalendarEntry(yearStart, monthStart, dayStart, hourStart, minuteStart, yearEnd, monthEnd, dayEnd, hourEnd, minuteEnd, description, summary, location, resources);
		allEntries.add(ce);
		if (resources!=null){
			for (Resource resource : resources) {
				resource.addParticipation(ce);
			}
		}
		saveDataPersistently();
		return ce;
	}

	//only required parameters using YMDHM
	/** creates and returns a new CalendarEntry with only the required parameters.
	 * It is automatically added to the facade classes ArrayList,
	 * and Resources are automatically associated and cross-referenced
	 * @param yearStart year of the start instant in time
	 * @param monthStart month of the start instant in time
	 * @param dayStart day of the start instant in time
	 * @param hourStart hour of the start instant in time
	 * @param minuteStart minute of the start instant in time
	 * @param yearEnd end of the start instant in time
	 * @param monthEnd month of the start instant in time
	 * @param dayEnd day of the start instant in time
	 * @param hourEnd hour of the start instant in time
	 * @param minuteEnd minute of the start instant in time
	 * @return CalendarEntry object with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public CalendarEntry createCalendarEntry(int yearStart, int monthStart, int dayStart, int hourStart, int minuteStart,
			int yearEnd, int monthEnd, int dayEnd, int hourEnd, int minuteEnd) throws StartEndException{
		CalendarEntry ce = new CalendarEntry(yearStart, monthStart, dayStart, hourStart, minuteStart, yearEnd, monthEnd, dayEnd, hourEnd, minuteEnd, null, null, null, null);
		allEntries.add(ce);
		saveDataPersistently();
		return ce;
	}

	//all parameters using LocalDateTime
	/** creates and returns a new CalendarEntry with the associated parameters (including all optional ones).
	 * It is automatically added to the facade classes ArrayList,
	 * and Resources are automatically associated and cross-referenced
	 * @param start start instant of time
	 * @param end end instant of time
	 * @param description description/title of the calendar entry
	 * @param summary summary of the calendar entry
	 * @param location location associated with the event of the calendar entry
	 * @param ressources resources associated with the event of the calendar entry
	 * @return CalendarEntry object with the specified parameters
	 * @throws StartEndException  if the event end is earlier than the event start
	 */
	public CalendarEntry createCalendarEntry(LocalDateTime start, LocalDateTime end, String description, String summary, Location location, ArrayList<Resource> ressources) throws StartEndException{
		return createCalendarEntry(start.getYear(),start.getMonthValue(), start.getDayOfMonth(),start.getHour(), start.getMinute(),
				end.getYear(),end.getMonthValue(), end.getDayOfMonth(),end.getHour(), end.getMinute(),
				description, summary, location, ressources);
	}

	//only required parameters using LocalDateTime
	/** creates and returns a new CalendarEntry with only the required parameters.
	 * It is automatically added to the facade classes ArrayList,
	 * and Resources are automatically associated and cross-referenced
	 * @param start start instant of time
	 * @param end end instant of time
	 * @return CalendarEntry object with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public CalendarEntry createCalendarEntry(LocalDateTime start, LocalDateTime end) throws StartEndException{
		return createCalendarEntry(start.getYear(),start.getMonthValue(), start.getDayOfMonth(),start.getHour(), start.getMinute(),
				end.getYear(),end.getMonthValue(), end.getDayOfMonth(),end.getHour(), end.getMinute());
	}

	//Whole day Event using YMD
	/** creates and returns a Whole-Day event.
	 * It is automatically added to the facade classes ArrayList,
	 * and Resources are automatically associated and cross-referenced
	 * @param year year of the whole-day event
	 * @param month month of the whole-day event
	 * @param day day of the whole-day event
	 * @param description description/title of the calendar entry
	 * @param summary summary of the calendar entry
	 * @param location location associated with the event
	 * @param ressources resources associated with the event
	 * @return CalendarEntry object with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public CalendarEntry createWholeDayEvent(int year, int month, int day,
			String description, String summary, Location location, ArrayList<Resource> ressources) throws StartEndException{
		return createCalendarEntry(year, month, day, 0, 0, year, month, day, 23, 59,
				description, summary, location, ressources);
	}

	//Whole day Event using LocalDate
	/** creates and returns a Whole-Day event. It is automatically added to the facade classes ArrayList,
	 * and Resources are automatically associated and cross-referenced
	 * @param day date of the whole-day event
	 * @param description description/title of the calendar entry
	 * @param summary summary of the calendar entry
	 * @param location location associated with the event
	 * @param ressources resources associated with the event
	 * @return CalendarEntry object with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public CalendarEntry createWholeDayEvent(LocalDate day, String description, String summary, Location location, ArrayList<Resource> ressources) throws StartEndException{
		return createCalendarEntry(day.getYear(),day.getMonthValue(), day.getDayOfMonth(),0,0,
				day.getYear(),day.getMonthValue(), day.getDayOfMonth(),23,59,
				description, summary, location, ressources);
	}

	//Daily Event
	/** creates and returns a specified number of events recurring on a daily basis.
	 * They are automatically added to the facade classes ArrayList,
	 * and Resources are automatically associated and cross-referenced
	 * @param iterations specifies how many repetitive events should be created
	 * @param start start instant of time for the first event
	 * @param end end instant of time for the first event
	 * @param description description/title of the calendar entries
	 * @param summary summary of the calendar entries
	 * @param location location associated with the events
	 * @param ressources resources associated with the events
	 * @return ArrayList of CalendarEntry objects with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public ArrayList<CalendarEntry> createDailyEvent(int iterations, LocalDateTime start, LocalDateTime end,
			String description, String summary, Location location, ArrayList<Resource> ressources) throws StartEndException{
		ArrayList<CalendarEntry> dailyCalendarEntries = new ArrayList<CalendarEntry>();
		for (int i=0; i<iterations; i++){
			dailyCalendarEntries.add(createCalendarEntry(start.getYear(),start.getMonthValue(), start.getDayOfMonth(),start.getHour(), start.getMinute(),
					end.getYear(),end.getMonthValue(), end.getDayOfMonth(),end.getHour(), end.getMinute(),
					description, summary, location, ressources));
			start=start.plusDays(1);
			end=end.plusDays(1);
		}
		return dailyCalendarEntries;
	}

	//Weekly Event
	/**creates and returns a specified number of events recurring on a weekly basis.
	 * They are automatically added to the facade classes ArrayList,
	 * and Resources are automatically associated and cross-referenced
	 * @param iterations specifies how many repetitive events should be created
	 * @param start start instant of time for the first event
	 * @param end end instant of time for the first event
	 * @param description description/title of the calendar entries
	 * @param summary summary of the calendar entries
	 * @param location location associated with the events
	 * @param ressources resources associated with the events
	 * @return ArrayList of CalendarEntry objects with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public ArrayList<CalendarEntry> createWeeklyEvent(int iterations, LocalDateTime start, LocalDateTime end,
			String description, String summary, Location location, ArrayList<Resource> ressources) throws StartEndException{
		ArrayList<CalendarEntry> weeklyCalendarEntries = new ArrayList<CalendarEntry>();
		for (int i=0; i<iterations; i++){
			weeklyCalendarEntries.add(createCalendarEntry(start.getYear(),start.getMonthValue(), start.getDayOfMonth(),start.getHour(), start.getMinute(),
					end.getYear(),end.getMonthValue(), end.getDayOfMonth(),end.getHour(), end.getMinute(),
					description, summary, location, ressources));
			start=start.plusDays(7);
			end=end.plusDays(7);
		}
		return weeklyCalendarEntries;
	}

	//Monthly Event
	/**creates and returns a specified number of events recurring on a monthly basis. 
	 * They are automatically added to the facade classes ArrayList, 
	 * and Resources are automatically associated and cross-referenced.
	 * @param iterations specifies how many repetitive events should be created
	 * @param start start instant of time for the first event
	 * @param end end instant of time for the first event
	 * @param description description/title of the calendar entries
	 * @param summary summary of the calendar entries
	 * @param location location associated with the events
	 * @param ressources resources associated with the events
	 * @return ArrayList of CalendarEntry objects with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public ArrayList<CalendarEntry> createMonthlyEvent(int iterations, LocalDateTime start, LocalDateTime end,
			String description, String summary, Location location, ArrayList<Resource> ressources) throws StartEndException{
		ArrayList<CalendarEntry> monthlyCalendarEntries = new ArrayList<CalendarEntry>();
		for (int i=0; i<iterations; i++){
			monthlyCalendarEntries.add(createCalendarEntry(start.getYear(),start.getMonthValue(), start.getDayOfMonth(),start.getHour(), start.getMinute(),
					end.getYear(),end.getMonthValue(), end.getDayOfMonth(),end.getHour(), end.getMinute(),
					description, summary, location, ressources));
			start=start.plusMonths(1);
			end=end.plusMonths(1);
		}
		return monthlyCalendarEntries;
	}

	//Yearly Event
	/**creates and returns a specified number of events recurring on a yearly basis. 
	 * They are automatically added to the facade classes ArrayList, 
	 * and Resources are automatically associated and cross-referenced
	 * @param iterations specifies how many repetitive events should be created
	 * @param start start instant of time for the first event
	 * @param end end instant of time for the first event
	 * @param description description/title of the calendar entries
	 * @param summary summary of the calendar entries
	 * @param location location associated with the events
	 * @param ressources resources associated with the events
	 * @return ArrayList of CalendarEntry objects with the specified parameters
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public ArrayList<CalendarEntry> createYearlyEvent(int iterations, LocalDateTime start, LocalDateTime end,
			String description, String summary,  Location location, ArrayList<Resource> ressources) throws StartEndException{
		ArrayList<CalendarEntry> yearlyCalendarEntries = new ArrayList<CalendarEntry>();
		for (int i=0; i<iterations; i++){
			yearlyCalendarEntries.add(createCalendarEntry(start.getYear(),start.getMonthValue(), start.getDayOfMonth(),start.getHour(), start.getMinute(),
					end.getYear(),end.getMonthValue(), end.getDayOfMonth(),end.getHour(), end.getMinute(),
					description, summary, location, ressources));
			start=start.plusYears(1);
			end=end.plusYears(1);
		}
		return yearlyCalendarEntries;
	}

	/** creates a Location object and sets the description. This object can be used in creation of a CalendarEntry.
	 * @param description a name or description for the location
	 * @return a Location object with the specified description and default latitude/longitude (0/0)
	 */
	public Location createTextOnlyLocation(String description){
		Location l = new Location();
		l.setDescription(description);
		return l;
	}


	/** removes an CalendarEntry from the central ArrayList. Associated Ressources are updated by removing the references.
	 * @param ce the CalendarEntry object to remove
	 */
	public void deleteEvent(CalendarEntry ce){
		allEntries.remove(ce);
		for (Resource resource : ce.getAllocatedResources()) {
			resource.removeParticipation(ce);
		}
		saveDataPersistently();
	}

	/**
	 * clears all data stored in the facade class
	 */
	public void deleteAllEvents(){
		allEntries.clear();
		for (Resource resource : allResources) {
			resource.removeAllParticipations();
		}
		saveDataPersistently();
	}
	
	/**
	 *  deletes all events that are scheduled to end before the current system time
	 */
	public void deleteAllEventsEndingInPast(){
		int x =0;
		for (CalendarEntry calendarEntry : allEntries) {
			if (calendarEntry.getEnd().isBefore(LocalDateTime.now())){
				deleteEvent(calendarEntry);
				x++;
			}
		}
		System.out.println(x+" Events have been deleted");
	}

	/** creates a new Resource object. If a Resource with the provided description already exists, it returns that one instead.
	 * @param name a name for the resource
	 * @return a Resource object with the specified name
	 */
	public Resource createOrAccessResource(String name){		//if the same name = returns existing Ressource
		for (Resource resource : allResources) {
			if (resource.getName().equals(name))
				return resource;
		}
		Resource r = new Resource(name);
		allResources.add(r);
		saveDataPersistently();
		return r;
	}

	/** Adds the specified Resources to a CalendarEntry and updates the references accordingly
	 * @param ce the CalendarEntry that shall be allocated the resources
	 * @param res the resources that shall be allocated to the CalendarEntry
	 * @return the updated CalendarEntry object
	 */
	public CalendarEntry addResourcesToEvent(CalendarEntry ce, ArrayList<Resource> res){
		ArrayList<Resource> existingRessources = ce.getAllocatedResources();
		ArrayList<Resource> resourcesToAdd = new ArrayList<Resource>();
		for (Resource resource : res) {
			if (!existingRessources.contains(resource)){
				resourcesToAdd.add(resource);
				resource.addParticipation(ce);
			}
		}
		ce.addResources(resourcesToAdd);
		saveDataPersistently();
		return ce;
	}
	
	/** Adds the specified Resource to a CalendarEntry and updates the references accordingly
	 * @param ce the CalendarEntry that shall be allocated the resource
	 * @param res the resource that shall be allocated to the CalendarEntry
	 * @return the updated CalendarEntry object
	 */
	public CalendarEntry addResourceToEvent(CalendarEntry ce, Resource res){
		ArrayList<Resource> existingRessources = ce.getAllocatedResources();
		if (!existingRessources.contains(res)){
			ce.addResource(res);
			res.addParticipation(ce);
		}
		saveDataPersistently();
		return ce;
	}

	/** removes the specified Resources from a CalendarEntry and updates the references accordingly
	 * @param ce the CalendarEntry that shall get resource(s) removed
	 * @param res  the resources that shall be removed from the CalendarEntry
	 * @return the updated CalendarEntry object
	 */
	public CalendarEntry removeResourcesFromEvent(CalendarEntry ce, ArrayList<Resource> res){
		for (Resource resource : res) {
			resource.removeParticipation(ce);
		}
		ce.removeResources(res);
		saveDataPersistently();
		return ce;
	}

	/** removes the specified Resource from a CalendarEntry and updates the references accordingly
	 * @param ce the CalendarEntry that shall get resource removed
	 * @param res  the resource that shall be removed from the CalendarEntry
	 * @return the updated CalendarEntry object
	 */
	public CalendarEntry removeResourceFromEvent (CalendarEntry ce, Resource res){
		res.removeParticipation(ce);
		ce.removeResource(res);
		saveDataPersistently();
		return ce;
	}

	/** returns an existing event with a known UID. If it does not exist, it returns null.
	 * @param uid UID to search for
	 * @return the event with the specified UID. If it does not exist, it returns null.
	 */
	public CalendarEntry getEventByUID(String uid){
		for (CalendarEntry calendarEntry : allEntries) {
			if (calendarEntry.getUid().equals(uid)) {
				return calendarEntry;
			}
		}
		return null;
	}

	/** returns an ArrayList of existing events with a known description. If none exist, it returns null.
	 * @param description description to search for
	 * @return an ArryList of events with a matching description. If none exist, it returns null.
	 */
	public ArrayList<CalendarEntry> getEventsByDescription(String description){
		ArrayList<CalendarEntry> ce = new ArrayList<CalendarEntry>();
		for (CalendarEntry calendarEntry : allEntries) {
			if (calendarEntry.getDescription().equals(description)) {
				ce.add(calendarEntry);
			}
		}
		if (!ce.isEmpty())
			return ce;
		else return null;
	}

	/** returns an ArrayList of existing events with a known Location object.
	 * it does NOT compare location descriptions only, as different locations might have the same
	 * description (e.g. "University), but different geographical coordinates. 
	 * If no matching events exist, it returns null.
	 * @param location Location to search for
	 * @return ArrayList of matching CalendarEntry objects
	 */
	public ArrayList<CalendarEntry> getEventsByLocation(Location location){
		ArrayList<CalendarEntry> ce = new ArrayList<CalendarEntry>();
		for (CalendarEntry calendarEntry : allEntries) {
			if (calendarEntry.getLocation().equals(location)) {
				ce.add(calendarEntry);
			}
		}
		if (!ce.isEmpty())
			return ce;
		else return null;
	}

	/** returns an ArrayList of existing events with a known start time. If none exist, it returns null.
	 * @param start search parameter start instant of time
	 * @return ArrayList of matching CalendarEntry objects
	 */
	public ArrayList<CalendarEntry> getEventsByStartTime(LocalDateTime start){
		ArrayList<CalendarEntry> ce = new ArrayList<CalendarEntry>();
		for (CalendarEntry calendarEntry : allEntries) {
			if (calendarEntry.getStart().equals(start)) {
				ce.add(calendarEntry);
			}
		}
		if (!ce.isEmpty())
			return ce;
		else return null;
	}

	/** returns an ArrayList of existing events with a known end time. If none exist, it returns null.
	 * @param end search parameter end instant of time
	 * @return ArrayList of matching CalendarEntry objects
	 */
	public ArrayList<CalendarEntry> getEventsByEndTime(LocalDateTime end){
		ArrayList<CalendarEntry> ce = new ArrayList<CalendarEntry>();
		for (CalendarEntry calendarEntry : allEntries) {
			if (calendarEntry.getEnd().equals(end)) {
				ce.add(calendarEntry);
			}
		}
		if (!ce.isEmpty())
			return ce;
		else return null;
	}

	/** returns an ArrayList of CalendarEntries that start and end between
	 *  a specified timespan defined by "from" and "to". If none exist, it returns null.
	 * @param from search parameter start instant of time
	 * @param to end search parameter end instant of time
	 * @return ArrayList of CalendarEntry objects scheduled to start and end within the timespan
	 */
	public ArrayList<CalendarEntry> getEventsCompletelyWithinTimespan(LocalDateTime from, LocalDateTime to){
		ArrayList<CalendarEntry> ce = new ArrayList<CalendarEntry>();
		for (CalendarEntry calendarEntry : allEntries) {
			if ((calendarEntry.getStart().isAfter(from) || calendarEntry.getStart().isEqual(from)) && (calendarEntry.getEnd().isBefore(to) || calendarEntry.getEnd().isEqual(to)))
				ce.add(calendarEntry);
		}
		if (!ce.isEmpty())
			return ce;
		else return null;
	}

	/** returns an ArrayList of CalendarEntries that intersect at least partially 
	 * with a specified timespan which is defined by "from" and "to". 
	 * If none exist, it returns null.
	 * @param from search parameter start instant of time
	 * @param to end search parameter end instant of time
	 * @return ArrayList of CalendarEntry objects intersecting the specified timespan
	 */
	public ArrayList<CalendarEntry> getEventsPartiallyWithinTimespan(LocalDateTime from, LocalDateTime to){
		ArrayList<CalendarEntry> ce = new ArrayList<CalendarEntry>();
		for (CalendarEntry calendarEntry : allEntries) {
			if (((calendarEntry.getEnd().isAfter(from)) && (calendarEntry.getEnd().isBefore(to))) || ((calendarEntry.getStart().isBefore(to)) && calendarEntry.getStart().isAfter(from)))
				ce.add(calendarEntry);
		}
		if (!ce.isEmpty())
			return ce;
		else return null;
	}

	/** checks a given Ressource for allocated events that intersect. 
	 * Returns an ArrayList of all confliting CalendarEntries associated with the Ressource.
	 * If no conflicting events exist, it returns an empty ArrayList.
	 * @param r Ressource to check for conflicting allocated events
	 * @return ArrayList of CalendarEntry objects associated to the ressource that overlap 
	 */
	public ArrayList<CalendarEntry> checkResourceForConflictingEvents(Resource r){
		return r.getConflictingEvents();
	}

	/** Returns a boolean indicating whether the Ressource is not yet used between 
	 * a specified timespan, defined by "start" and "end".
	 * @param r Ressource to test
	 * @param start start instant of timespan to test
	 * @param end end instant of timespan to test
	 * @return true if no CalendarEntries are scheduled to use the ressource for the specified timespan, else false
	 */
	public boolean resourceIsAvailiableBetween(Resource r, LocalDateTime start, LocalDateTime end){
		return r.isAvailiableBetween(start, end);
	}

	/** Returns an ArrayList of CalendarEntries that are allocated to the given Ressource. 
	 * If none exist, it returns an empty ArrayList.
	 * @param r Ressource to use
	 * @return ArrayList of CalendarEntries allocated to the Ressource
	 */
	public ArrayList<CalendarEntry> getEventsAllocatedToResource(Resource r){
		return r.getParticipating();
	}

	/** Returns the time between the end of firstEvent and the start of secondEvent as a Duration object.
	 * Does not check whether the events are overlapping.
	 * @param firstEvent earlier event
	 * @param secondEvent later event
	 * @return Duration between the end of the first event and the start of the second event
	 */
	public Duration getFreeTimeBetween(CalendarEntry firstEvent, CalendarEntry secondEvent){
		return Duration.between(firstEvent.getEnd(), secondEvent.getStart());
	}


	/** prints all CalendarEntries to the console
	 * 
	 */
	public void printAllCalendarEntries(){
		int i = 1;
		for (CalendarEntry calendarEntry : allEntries) {
			System.out.println("Event "+i);
			System.out.println(calendarEntry);
			i++;
		}
	}

	/** Exports the given ArrayList of CalendarEntries to an iCal format file (.ics). 
	 * @param events ArrayList of CalendarEntry objects to export
	 * @param filename user-defined filename (does not require the file extension)
	 */
	public void exportToICAL(ArrayList<CalendarEntry> events, String filename){
		icalHandler.createICSFile(events, filename);
	}

	/** Imports an iCal format file and creates a CalendarEntry for each included event. 
	 * Existing events with the same UID get updated, the old event will be overwritten
	 * - when this happens, an information will be printed to the console.
	 * @param filename file to import. Must be in the same directory.
	 */
	public ArrayList<CalendarEntry> importEventsFromIcalFile(String filename){ 				
		ArrayList<CalendarEntry> readEvents = new ArrayList<CalendarEntry>();
		ArrayList<CalendarEntry> overwrittenEvents = new ArrayList<CalendarEntry>();
		try {
			readEvents=icalHandler.readIcalFile(filename);
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
			e.printStackTrace();
			return null;
		}
		for (CalendarEntry calendarEntry : readEvents) {
			String uid = calendarEntry.getUid();
			for (CalendarEntry existingEvent : allEntries) {
				if (uid.equals(existingEvent.getUid())){				//will overwrite existing events with same UID
					overwrittenEvents.add(existingEvent);
					System.out.println("overwriting existing event");
				}
			}
		}
		for (CalendarEntry calendarEntry : overwrittenEvents) {
			this.deleteEvent(calendarEntry);
		}

		allEntries.addAll(readEvents);
		saveDataPersistently();
		return readEvents;
	}

}