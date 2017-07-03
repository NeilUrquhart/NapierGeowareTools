The EasyKMLCreator API simplifies the creation of KML documents and provides a single entry point for 
user to provide a high usability and make the usage as simple as possible

The first version of the API limits the functionality to the creation of point placemarks, paths, 
polygons, styles and simple tours through the landscape. 

Installation:

The usage of the EasyKMLCreator API is extremely simple. Initially, the JAR file or the complete 
source code is needed as this contains all of the functionality. Both are located and available for 
download at the following location: https://github.com/jannikG95/NapierGeoTools. 

Afterwards, the JAR file or the code needs to be imported in the development environment and linked 
to the project for which it will be used by configuring the properties. 

The following is an exemplary eclipse process:
 
Select your project > Project > Properties > Libraries > Add External Jar > EasyKMLCreator.JAR > Apply

or for the source code:

1. Import the .zip archive in eclipse 

2. Select your project > Project > Properties > Projects > Add > NapierGeoTools.EasyKMLCreator > Apply

3. Furthermore JDOM, JUnit, XMLUnit and the Commons IO API must be unzipped, and afterwards linked to the 
   EasyKMLCreator project. All of them can be found in the project folder of the EasyKMLCreator.

When the API is linked to the project it can be used by creating an instance of the façade class EasyKMLCreator. 
This class provides all functions that can be ac-cessed by users. Furthermore, a comprehensive KML object structure 
is available.
