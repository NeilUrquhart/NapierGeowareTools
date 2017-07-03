# Simplified GraphHopper API

To get started developing using Eclipse you need to install Git (e.g. EGit http://www.eclipse.org/egit/).

## To edit or use the Simplified GraphHopper API

Download the SimplifiedGraphHopperAPI.jar if you just want to use it in your software. If you want to edit it, import the whole project via Git: file -> import -> git -> projects from Git -> clone URL and then paste in https://github.com/jannikG95/NapierGeoTools. Now you can select the projects you want to import, for GraphHopper choose SimplifiedGraphHopper and NapierGeoTools.common.
The class UsabilityTest might give you an overview of how to use the API.

## Getting Started For Advanced Developers

This section is for users who need access to the underlying GraphHopper routing engine as well as to the Simplified GraphHopper API.

First you need to install Maven (e.g. Maven2Eclipse http://www.eclipse.org/m2e/). 

After that import the Simplified GraphHopper API via file -> import -> git -> projects from Git -> clone URL and then paste in https://github.com/jannikG95/NapierGeoTools. Now you can select the projects you want to import, for GraphHopper choose SimplifiedGraphHopper and NapierGeoTools.common.

Within the SimplifiedGraphHopper project you find a .zip file called graphhopper-master. Move this to a place on your local file system and unpack it.
Now you must import the GraphHopper routing engine. Therefore go to file -> import -> maven -> existing maven projects and browse to the unpacked graphhopper-master file. Import it. 
Finally you must edit the Build Path. To do so, right-click on the SimplifiedGraphHopper project and select Build Path -> Configure Build Path. In the Properties window remove all jars in the libraries tab. Then go to the Projects tab and add all the GraphHopper projects you imported via Maven.

Now you can start developing. The class UsabilityTest might give you an overview of how to use the API.
