package edu.napier.geo.diary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Resource implements Serializable {

	private static final long serialVersionUID = 4883692450600389560L;
	private String name;
	private ArrayList<CalendarEntry> participating = new ArrayList<CalendarEntry>();

	/** Constructor
	 * @param name name of the ressource
	 */
	public Resource(String name){
		this.name = name;
	}

	/** Returns a boolean indicating whether the Ressource is not yet used between 
	 * a specified timespan, defined by "start" and "end".
	 * @param start start instant of timespan to test
	 * @param end end instant of timespan to test
	 * @return true if no CalendarEntries are scheduled to use the ressource for the specified timespan, else false
	 */
	public boolean isAvailiableBetween(LocalDateTime start, LocalDateTime end){
		for (CalendarEntry calendarEntry : participating) {
			if (((calendarEntry.getEnd().isAfter(start)) && (calendarEntry.getEnd().isBefore(end))) || ((calendarEntry.getStart().isAfter(start)) && calendarEntry.getStart().isBefore(end)))
				return false;
		}
		return true;
	}

	/** checks the Ressource for allocated events that intersect. 
	 * Returns an ArrayList of all confliting CalendarEntries associated with the Ressource.
	 * @return ArrayList of CalendarEntry objects associated to the ressource that overlap 
	 */
	public ArrayList<CalendarEntry> getConflictingEvents(){
		if (participating.size()==0)
			return new ArrayList<CalendarEntry>();
		ArrayList<CalendarEntry> helper = new ArrayList<CalendarEntry>();
		ArrayList<CalendarEntry> conflicting = new ArrayList<CalendarEntry>();
		helper = (ArrayList<CalendarEntry>) participating.clone();
		CalendarEntry toTest;
		for (int i=0; i<participating.size()-1;i++) {
			toTest = helper.get(0);
			helper.remove(0);
			for (int j=0; j<helper.size();j++){
				if (((toTest.getEnd().isAfter(helper.get(j).getStart())) && (toTest.getEnd().isBefore(helper.get(j).getEnd()))) || ((toTest.getStart().isBefore(helper.get(j).getEnd())) && toTest.getStart().isAfter(helper.get(j).getStart()))){
					if (!conflicting.contains(toTest))
						conflicting.add(toTest);
					if (!conflicting.contains(helper.get(j)))
						conflicting.add(helper.get(j));
				}
			}
		}
		return conflicting;
	}

	/** getter for the name of the ressource
	 * @return name of the ressource
	 */
	public String getName(){
		return name;
	}

	/** setter for the Ressource name
	 * @param name Ressource name
	 */
	protected void setName(String name) {
		this.name = name;
	}	

	/** associates a CalendarEntry to this Ressource
	 * @param ce CalendarEntry to associate
	 */
	protected void addParticipation(CalendarEntry ce){
		participating.add(ce);
	}

	/** removes the association of a CalendarEntry for this Ressource
	 * @param ce CalendarEntry to remove the association with
	 */
	protected void removeParticipation (CalendarEntry ce){
		participating.remove(ce);
	}

	/** removes all associated CalendarEntries for this Ressource
	 * 
	 */
	protected void removeAllParticipations (){
		participating.clear();
	}

	public String toString(){
		return name;   
	}

	/** getter for CalendarEntry objects associated with the Ressource
	 * @return ArrayList of CalendarEntry objects associated with the Ressource
	 */
	public ArrayList<CalendarEntry> getParticipating() {
		return participating;
	}



}
