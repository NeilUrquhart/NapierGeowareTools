import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;






//import edu.napier.geo.publicTransport.General.Location;
import edu.napier.geo.common.Location;
import edu.napier.geo.publicTransport.InformationStorage.JourneyInformation;
import edu.napier.geo.publicTransport.ResponseTfL.Leg;
import edu.napier.geo.publicTransport.ResponseTfL.ResponseTfL;
import edu.napier.geo.publicTransport.ResponseTfL.StopPoint;
import edu.napier.geo.publicTransport.ResponseTfL.StopPointInPath;
import edu.napier.geo.publicTransport.ResponseTfL.TflJourney;
import edu.napier.geo.publicTransport.fascade.PublicTransport;
import edu.napier.geo.publicTransport.fascade.RequestAndGetJsonFromServer;


public class RunTest {
	
	private static PublicTransport publicTransport;

	
	public static void main(String args[]) throws Exception{
		RunTest test = new RunTest();
		publicTransport= new PublicTransport(true);
		test.test();
		test.testGetMethods();
		test.testSteps();
		
	}
	
	private void test() throws Exception{
		ArrayList<String> preferencesTestList = new ArrayList<String>();
		preferencesTestList.add("fromName=DepartureLocationTestName");

		// try {
		
		Location locationWithoutFailure1 = new Location(51.51100069912, -0.12300137615, publicTransport.getSource());
		Location locationWithoutFailure2 = new Location(51.49839044663, -0.1301682292, publicTransport.getSource());
		Location locationWithoutFailure3 = new Location(51.599577, -0.059587, publicTransport.getSource());
//		Location locationWithFailure = new Location();

		publicTransport.createResponseJavaObject(locationWithoutFailure1, locationWithoutFailure2, preferencesTestList);
		
		
//		String responseString;
//		try {
//			responseString = RequestAndGetJsonFromServer.getJSON(locationWithoutFailure3/*oldLocation*/, locationWithoutFailure2,
//					preferencesTestList);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			responseString = null;
//		}

		
//		responseJavaObject = getResponseObjectFromJSONString(responseString);

		ResponseTfL responseJavaObject= publicTransport.getResponseJavaObject();
		System.out.println("object x=:" + responseJavaObject.x());
		System.out.println("number of journeys=" + responseJavaObject.getTflJourneys().length);
		

		System.out.println(
				"with obj. !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("averageTime="
				+ publicTransport.getInformationStorage().getAverageTimeMSForJourney(locationWithoutFailure1, locationWithoutFailure2));
		System.out.println(
				"obj.dep point lat=" + responseJavaObject.getTflJourneys()[0].getLegs()[0].getDeparturePoint().getLat());
		System.out.println(
				"obj.dep point lon=" + responseJavaObject.getTflJourneys()[0].getLegs()[0].getDeparturePoint().getLon());
		System.out.println("locationWithoutFail1 lat=" + locationWithoutFailure1.getLat());
		System.out.println("locationWithoutFail1 lon=" + locationWithoutFailure1.getLon());
		System.out.println(
				"obj.arr point lat=" + responseJavaObject.getTflJourneys()[responseJavaObject.getTflJourneys().length - 1]
						.getLegs()[responseJavaObject.getTflJourneys()[responseJavaObject.getTflJourneys().length - 1]
								.getLegs().length - 1].getArrivalPoint().getLat());
		System.out.println(
				"obj.arr point lon=" + responseJavaObject.getTflJourneys()[responseJavaObject.getTflJourneys().length - 1]
						.getLegs()[responseJavaObject.getTflJourneys()[responseJavaObject.getTflJourneys().length - 1]
								.getLegs().length - 1].getArrivalPoint().getLon());
		System.out.println("locationWithoutFail2 lat=" + locationWithoutFailure2.getLat());
		System.out.println("locationWithoutFail2 lon=" + locationWithoutFailure2.getLon());

		for (TflJourney tflJourney : responseJavaObject.getTflJourneys()) {
			System.out.println("New Journey");
			for (Leg leg : tflJourney.getLegs()) {
				System.out.println("new Leg:");
				System.out
						.println("dep.=" + leg.getDeparturePoint().getLat() + " | " + leg.getDeparturePoint().getLon());
				System.out.println("arr.=" + leg.getArrivalPoint().getLat() + " | " + leg.getArrivalPoint().getLon());
			}
		}

		publicTransport.printAllJourneyInformation();

		System.out
				.println("informationStorage.getStoredJourneys.size=" + publicTransport.getInformationStorage().getStoredJourneyInformation().size());

		System.out.println("avg time from old location:"+publicTransport.getInformationStorage().getAverageTimeMSForJourney(locationWithoutFailure3, locationWithoutFailure2));

		publicTransport.printAllJourneyInformation();


		// // Test: 2 Locations
		//
		// String responseString2 =
		// PublicTransportRequest.getJSON(locationWithoutFailure1,
		// locationWithoutFailure2 , testList);
		//
		// if (responseString2 != null) { Response obj =
		// gson.fromJson(responseString2, Response.class);
		//
		// System.out.println(obj);
		//
		// System.out.println(obj.x()); System.out.println(responseString2);
		// }else{
		// System.out.println("responseString=null, no JSON code responded!"); }
		//
		//
		//
		// //Test: one location has no lon and lat:
		//
		// String responseString3 =
		// PublicTransportRequest.getJSON(locationWithoutFailure1,
		// locationWithFailure, testList);
		//
		// if (responseString3 != null) { Response obj =
		// gson.fromJson(responseString3, Response.class);
		//
		// System.out.println(obj);
		//
		// System.out.println(obj.x()); System.out.println(responseString3);
		// }else{
		// System.out.println("responseString=null, no JSON code responded!"); }

	}
	
	public void testGetMethods(){
		System.out.println("duration first journey:"+publicTransport.getDurationMinutesOfFirstJourney());
		System.out.println("dep point first journey:"+publicTransport.getDeparturePointOfFirstJourney().toString());
		System.out.println("arr point first journey:"+publicTransport.getArrivalPointOfFirstJourney().toString());
		System.out.println("number legs first journey:"+publicTransport.getNumberOfLegsOfFirstJourney());
		System.out.println("first journey"+publicTransport.getFirstJourney().toString());
		System.out.println("get all journeys:");
		for(TflJourney journey: publicTransport.getAllJourneys()){
			System.out.println("get all journeys function. journey:"+journey+" = "+journey.toString());
		}
		System.out.println("methods for each journey. number of journeys="+publicTransport.getResponseJavaObject().getTflJourneys().length);
		for(int x=0; x<publicTransport.getResponseJavaObject().getTflJourneys().length;x++ ){
			System.out.println("journey number x="+x);			
			System.out.println("duration:"+publicTransport.getDurationMinutsOfJourney(x));
			System.out.println("dep point"+publicTransport.getDeparturePointOfJourney(x));
			System.out.println("arr point"+publicTransport.getArrivalPointOfJourney(x));
			System.out.println("number legs:"+publicTransport.getNumberOfLegsOfJourney(x));
			System.out.println("journey:"+publicTransport.getJourney(x));
		}
		for(TflJourney journey : publicTransport.getResponseJavaObject().getTflJourneys()){
			System.out.println("Start with journey "+journey);
			System.out.println("duration:"+publicTransport.getDurationOfJourney(journey));
			System.out.println("dep point"+publicTransport.getDeparturePointOfJourney(journey));
			System.out.println("arr point"+publicTransport.getArrivalPointOfJourney(journey));
			System.out.println("number legs:"+publicTransport.getNumberOfLegsOfJourney(journey));
		}
		
		
		
	}
	public void testSteps(){
		System.out.println("test"+publicTransport.getResponseJavaObject().getTflJourneys()[0].getLegs()[0].getPath().getLineString());
		String string= publicTransport.getResponseJavaObject().getTflJourneys()[0].getLegs()[0].getPath().getLineString();
		string=string.replace("[", "");
		string=string.replace("]", "");
		string=string.replace(" ", "");
		System.out.println(string);
//		for(StopPointInPath loc : publicTransport.getResponseJavaObject().getTflJourneys()[0].getLegs()[0].getPath().getStopPoints()){
//			System.out.println(loc.toString());
//		}
		int x=0;
		for (JourneyInformation journeyInformation : publicTransport.getInformationStorage().getStoredJourneyInformation()){
			System.out.println("new journeyInfo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			for (Location loc : journeyInformation.getRouteLocations()){
				System.out.println(x+"  "+loc.toString());
				x++;
			}
		}
	}

}
