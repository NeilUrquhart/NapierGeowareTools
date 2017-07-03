package edu.napier.geo.easykml.filemanagement;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;

import edu.napier.geo.easykml.KML_Object.KML_object;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class KMLFileWriter {

	private Document document;
	private Element KMLNamespaceDefinition;
	private Element rootElement;

	public KMLFileWriter(Document document) {
		this.document = document;
		defineBasicDocumentStructure();
	}

	public void appendObject(KML_object kml_object) {
		processLinkedOutputTree(kml_object);
	}

	// --------------------------------------------------------------------------------------------------------------
	// private method section
	// --------------------------------------------------------------------------------------------------------------

	private void defineBasicDocumentStructure() {
		KMLNamespaceDefinition = new Element("kml", Namespace.getNamespace("http://www.opengis.net/kml/2.2"));
		document.setRootElement(KMLNamespaceDefinition);

		rootElement = new Element("Document", KMLNamespaceDefinition.getNamespace());
		KMLNamespaceDefinition.addContent(rootElement);

	}

	private void addGoogleExtansionsPack() {
		List<Namespace> additional_Namespaces = KMLNamespaceDefinition.getAdditionalNamespaces();

		if (additional_Namespaces.size() == 0)
			KMLNamespaceDefinition
					.addNamespaceDeclaration(Namespace.getNamespace("gx", "http://www.google.com/kml/ext/2.2"));

		//element.setNamespace((Namespace) KMLNamespaceDefinition.getAdditionalNamespaces().get(0));
	}

	private void processLinkedOutputTree(KML_object kml_object) {
		TreeNode<KMLNotation> treeRoot = kml_object.getLinkedOutput();


		ArrayList<Element> ParentElementofLevel = new ArrayList<>();

		for (TreeNode<KMLNotation> node : treeRoot) {

			if (node.isRoot()) {
				ParentElementofLevel.add(node.getLevel(), createJDOMElement(node, rootElement));
			} else {

				if (!node.isLeaf()) {
					ParentElementofLevel.add(node.getLevel(), createJDOMElement(node, ParentElementofLevel.get(node.getLevel() - 1)));
				} else {
					if (node.data.getName() == "id" && node.data.getText() != "")
						setElementID(ParentElementofLevel.get(node.getLevel()-1), "id", node.data.getText());
					else if (node.data.getName() == "targetId" && node.data.getText() != "") {
						setElementID(ParentElementofLevel.get(node.getLevel()), "targetId", node.data.getText());
					} else {				
						createJDOMElement(node, ParentElementofLevel.get(node.getLevel() - 1));
					}
				}
			}
		}

	}
	
	private Element createJDOMElement(TreeNode<KMLNotation> node, Element parent) {
		if(node.data.isgExtenstion()) addGoogleExtansionsPack();
		Element childElement = new Element(node.data.getName(), !node.data.isgExtenstion() ? KMLNamespaceDefinition.getNamespace()
				: KMLNamespaceDefinition.getAdditionalNamespaces().get(0));
		if(node.data.getText()!=null)
			childElement.setText(node.data.getText());
		
		parent.addContent(childElement);
		
		return childElement;
	}

	private void setElementID(Element element, String attr_Name, String attr_Text) {
		if (attr_Text != null) {
			element.setAttribute(attr_Name, attr_Text);
		}
	}


}
