QueryOsmAPI
==============

README
-------

QueryOsmAPI is a collection of Java classes allowing to parse raw OSM XML files and perform 
areal searches for tagged objects. This library is very simple to understand an collects the most
important functions in the QueryOsmFacade class. The API uses the BasicOSMParser library (GNU General Public License version 3 ) which uses the default Java SAX parser (org.xml.sax). 

Installation (For Developers)
------------------------------

In order to use QueryOsmAPI, you can download the QueryOsmAPI.jar file. Alternatively,
you can put the content of src/main/ folder in the source directory of your project.


Usage
-----

### In Another Project

Here is a simple example of how to use the API. You just need to create a new QueryOsmFacade
object, and then call the <code>parseOSMFile</code> method which takes the filepath of the OSM file as input .
The created Java objects are then stored in the Parser object.

```
	QueryOsmFacade facade = new QueryOsmFacade();

		facade.parseOSMFile("filepath");

		OSMParser parser1 = facade.getParser();   // stores created Java objects
```

The facade class contains a <code>Map<String,Element> elements</code> which stores all the created nodes and a
<code>TagList taglist</code> object that contains a <code>TreeMap<String,ArrayList<String>> listOfTags </code> with references to the associated node objects.

The queries searching for the "coffee" tag or an RegEx, after the file was parsed into the parser1 object can look like this:

```
//Query with default Radius without regex
ArrayList <Location> locationsFound = facade.findLocations("coffee", currentLocation, parser1); 						

//Query with own Radius without Regex
ArrayList <Location> locationsFound = facade.findLocationsWithRadius(2.0, "coffee", currentLocation, parser1);			

//Query with default Radius and Regex
ArrayList <Location> locationsFound = facade.findLocationsWithRegEx( "cof*e*", currentLocation, parser1 );				

//Query with own radius of 2km and regex
ArrayList <Location> locationsFound = facade.findLocationsWithRegEx(2.0, "cof*e*", currentLocation, parser1);			

//Query within Boundingbox without regex
ArrayList <Location> locationsFound = facade.findLocationsWithinBoundingBox(parser1.getMapRange(), "coffee", parser1);	

//Query within Boundingbox and regex:
ArrayList <Location> locationsFound = facade.findLocationsWithRegEx("cof*e*", parser1.getMapRange(), parser1);			

```
<code>locationsFound</code> now contains all the found locations. The radial search queries need a <code>currentLocation</code> to the define the center of the search area.

For prior analysis of the existing tags the tags can be output either to the console or the a text file.
```
	//print to console
	facade.printTagsInConsole(parser1);		
	
	//print to new text file								
	facade.printTagsToTxtFile(parser1, "filepath/nameForNewFile.txt"); 			
```




