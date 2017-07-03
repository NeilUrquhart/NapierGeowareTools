package edu.napier.geo.examples;
import java.util.ArrayList;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.JourneyFactory;
import edu.napier.geo.common.Location;
import edu.napier.geo.tfl.TransportForLondonFacade;


public class PublicTransport_Example {

	public static void main(String[] args) {

		
		JourneyFactory pt = new TransportForLondonFacade("You need to obtain a TfL API key for here....");
		
		Location from = new Location(51.52575,-0.178121);
		Location to = new Location(51.503761,-0.1118024);
		
		Journey[] options = pt.getJourney(from, to);
		
		for(Journey j : options)
			System.out.println(j.toString());


	}

}
