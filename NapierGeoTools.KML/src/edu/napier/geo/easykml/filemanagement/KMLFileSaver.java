package edu.napier.geo.easykml.filemanagement;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class KMLFileSaver {

	public void saveFile(Document document, String destination) {

		try {
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());

			FileWriter result;
			if (destination != "") {
				result = new FileWriter(destination + ".kml");
			} else {
				result = new FileWriter("./EasyKMLCreator.kml");
			}

			xmlOutput.output(document, result);

			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}

	}

}
