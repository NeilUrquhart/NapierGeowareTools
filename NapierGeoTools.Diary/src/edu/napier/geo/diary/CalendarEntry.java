package edu.napier.geo.diary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import edu.napier.geo.common.Location;

public class CalendarEntry implements Serializable {

	private static final long serialVersionUID = -4638363557017293395L;
	private LocalDateTime start;
	private LocalDateTime end;
	private String description = null;
	private String summary = null;
	private Location location = null;
	private String uid = null;
	private ArrayList<Resource> allocatedResources = new ArrayList<Resource>();

	//only required parameters
	/** Constructor creating a new CalendarEntry with only the required parameters
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
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	protected CalendarEntry (int yearStart, int monthStart, int dayStart, int hourStart, int minuteStart,
			int yearEnd, int monthEnd, int dayEnd, int hourEnd, int minuteEnd) throws StartEndException{
		start = LocalDateTime.of(yearStart, monthStart, dayStart, hourStart, minuteStart);
		end = LocalDateTime.of(yearEnd, monthEnd, dayEnd, hourEnd, minuteEnd);
		if (start.isAfter(end))
			throw new StartEndException();
		this.uid = UUID.randomUUID().toString();
		uid = uid+"napier";
	}

	//all parameters
	/** Constructor creating a new CalendarEntry with all parameters
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
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	protected CalendarEntry (int yearStart, int monthStart, int dayStart, int hourStart, int minuteStart,
			int yearEnd, int monthEnd, int dayEnd, int hourEnd, int minuteEnd,
			String description, String summary, Location location, ArrayList<Resource> resources) throws StartEndException{

		start = LocalDateTime.of(yearStart, monthStart, dayStart, hourStart, minuteStart);
		end = LocalDateTime.of(yearEnd, monthEnd, dayEnd, hourEnd, minuteEnd);
		if (start.isAfter(end))
			throw new StartEndException();

		this.description = description;
		this.summary = summary;
		this.location = location;
		this.uid = UUID.randomUUID().toString();
		uid = uid+"napier";
		allocatedResources = resources;
	}

	//Constructor using LocalDateTimes and all parameters
	/** Constructor creating a new CalendarEntry with all parameters
	 * @param start start instant of time
	 * @param end end instant of time
	 * @param description description/title of the calendar entry
	 * @param summary summary of the calendar entry
	 * @param location location associated with the event of the calendar entry
	 * @param ressources ressources associated with the event of the calendar entry
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	protected CalendarEntry (LocalDateTime start, LocalDateTime end, String description, String summary, Location location, ArrayList<Resource> ressources) throws StartEndException{
		this(start.getYear(),start.getMonthValue(), start.getDayOfMonth(),start.getHour(), start.getMinute(),
				end.getYear(),end.getMonthValue(), end.getDayOfMonth(),end.getHour(), end.getMinute(),
				description, summary, location, ressources);
	}

	//Constructor using LocalDateTimes and only required parameters
	/** Constructor creating a new CalendarEntry with only the required parameters
	 * @param start start instant of time
	 * @param end end instant of time
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	protected CalendarEntry (LocalDateTime start, LocalDateTime end) throws StartEndException{
		this(start.getYear(),start.getMonthValue(), start.getDayOfMonth(),start.getHour(), start.getMinute(),
				end.getYear(),end.getMonthValue(), end.getDayOfMonth(),end.getHour(), end.getMinute());
	}

	public String toString(){
		String startMinuteString, endMinuteString, startHourString, endHourString;
		startMinuteString = ""+start.getMinute();
		endMinuteString = ""+end.getMinute();
		startHourString =""+start.getHour();
		endHourString=""+end.getHour();

		if (start.getMinute()==0)
			startMinuteString="00";
		if (start.getHour()==0)
			startHourString="00";
		if (end.getMinute()==0)
			endMinuteString="00";
		if (end.getHour()==0)
			endHourString="00";

		String locationstring=null;
		String ressourcestring=null;
		if (location != null)
			locationstring = location.getDescription();
		if (locationstring==null)
			locationstring = "no location set";
		if (allocatedResources != null)
			ressourcestring = allocatedResources.toString();
		if (ressourcestring==null)
			ressourcestring = "no ressources allocated";
		if (description==null)
			description = "no description";
		if (summary==null)
			summary = "no summary";

		return "Event start:        "+start.getDayOfMonth()+"."+start.getMonth()+" "+start.getYear()+" "+startHourString+":"+startMinuteString+"\n"
		+"Event end:          "+end.getDayOfMonth()+"."+end.getMonth()+" "+end.getYear()+" "+endHourString+":"+endMinuteString+"\n"
		+"Event description:  "+description+"\n"
		+"Event summary:      "+summary+"\n"
		+"UniqueID:           "+uid+"\n"
		+"Location:           "+locationstring+"\n"
		+"Resources:         "+ressourcestring+"\n"
		;

	}

	/** getter for the scheduled start instant of time
	 * @return start instant of time
	 */
	public LocalDateTime getStart() {
		return start;
	}

	/** setter for the scheduled start instant of time
	 * @param start scheduled start instant of time
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public void setStart(LocalDateTime start) throws StartEndException {
		if (start.isAfter(end))
			throw new StartEndException();
		else this.start = start;
	}

	/** getter for the scheduled end instant of time
	 * @return end instant of time
	 */
	public LocalDateTime getEnd() {
		return end;
	}

	/** setter for the scheduled end instant of time
	 * @param end scheduled end instant of time
	 * @throws StartEndException if the event end is earlier than the event start
	 */
	public void setEnd(LocalDateTime end) throws StartEndException {
		if (start.isAfter(end))
			throw new StartEndException();
		else this.end = end;
	}

	/** getter for the event description
	 * @return event description
	 */
	public String getDescription() {
		return description;
	}

	/** setter for the event description
	 * @param description event description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** getter for the event summary
	 * @return event summary
	 */
	public String getSummary() {
		return summary;
	}

	/** setter for the event summary
	 * @param summary event summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/** getter for the Location
	 * @return Location of the event
	 */
	public Location getLocation() {
		return location;
	}

	/** setter for the Location
	 * @param location Location of the event
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/** getter for the event's UID
	 * @return the event's UID
	 */
	public String getUid() {
		return uid;
	}

	/** setter for the event's UID - should only be used when external events are imported
	 * @param uid the UID to set
	 */
	protected void setUid(String uid){
		this.uid = uid;
	}

	/** getter for the allocated Resources
	 * @return ArrayList of allocated Resources
	 */
	public ArrayList<Resource> getAllocatedResources() {
		return allocatedResources;
	}

	/** setter for the allocated Resources
	 * @param allocatedResources the UID to set
	 */
	protected void setAllocatedResources(ArrayList<Resource> allocatedResources) {
		this.allocatedResources = allocatedResources;
	}

	//should only be accessed from the Facade class to ensure correct listing of Events and Ressources!
	/** adds Resources to the CalendarEntry
	 * @param res ArrayList of Resources to add
	 */
	protected void addResources(ArrayList<Resource> res){
		allocatedResources.addAll(res);
	}
	
	//should only be accessed from the Facade class to ensure correct listing of Events and Ressources!
	/** adds a Resource to the CalendarEntry
	 * @param res Resource to add
	 */
	protected void addResource(Resource res){
		allocatedResources.add(res);
	}

	//should only be accessed from the Facade class to ensure correct listing of Events and Ressources!
	/** removes Resources from the CalendarEntry
	 * @param res ArrayList of Resources to remove
	 */
	protected void removeResources(ArrayList<Resource> res){
		allocatedResources.removeAll(res);
	}
	
	//should only be accessed from the Facade class to ensure correct listing of Events and Ressources!
	/** remove a Resource from the CalendarEntry
	 * @param res Resource to remove
	 */
	protected void removeResource(Resource res){
		allocatedResources.remove(res);
	}

}
