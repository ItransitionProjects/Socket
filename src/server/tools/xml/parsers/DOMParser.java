package server.tools.xml.parsers;

import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import server.PersonalData;
import server.tools.xml.LoadData;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * The Class DOMParser.
 */
public class DOMParser extends LoadData {

	protected PersonalData load(ZipInputStream zin) {
		PersonalData person = new PersonalData();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(zin);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("server.PersonalData");
			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					person.setSurname(eElement.getElementsByTagName("surname").item(0).getTextContent());
					person.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					person.setFathername(eElement.getElementsByTagName("fathername").item(0).getTextContent());
					person.setPhone(eElement.getElementsByTagName("phone").item(0).getTextContent());
					person.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
					person.setEmployee(eElement.getElementsByTagName("employee").item(0).getTextContent());
					person.setJobExperience(eElement.getElementsByTagName("jobExperience").item(0).getTextContent());
					person.setJobPosition(eElement.getElementsByTagName("jobPosition").item(0).getTextContent());
					person.setSex(eElement.getElementsByTagName("sex").item(0).getTextContent());
				}
				System.out.println(person.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}
}
