package server.tools.xml.parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipInputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import server.PersonalData;
import server.tools.ServerMain;
import server.tools.xml.LoadData;

/**
 * The Class StAXParser.
 */
public class StAXParser extends LoadData {

	protected PersonalData load(ZipInputStream zin) {
		boolean bSurname = false;
		boolean bName = false;
		boolean bFathername = false;
		boolean bPhone = false;
		boolean bEmail = false;
		boolean bSex = false;
		boolean bEmployee = false;
		boolean bJobExperience = false;
		boolean jobPosition = false;
		PersonalData person = new PersonalData();
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();

			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(zin, "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			XMLEventReader eventReader = factory.createXMLEventReader(br);

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				switch (event.getEventType()) {

				// catches the beginning of the element and sets specified
				// boolean to true, to remember it
				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					String qName = startElement.getName().getLocalPart();
					if (qName.equalsIgnoreCase("person")) {
						ServerMain.loggerServer.info("Start Element : person");
					} else if (qName.equalsIgnoreCase("personJob")) {
						ServerMain.loggerServer.info("Start Element : personJob");
					} else if (qName.equalsIgnoreCase("surname")) {
						bSurname = true;
					} else if (qName.equalsIgnoreCase("name")) {
						bName = true;
					} else if (qName.equalsIgnoreCase("fathername")) {
						bFathername = true;
					} else if (qName.equalsIgnoreCase("phone")) {
						bPhone = true;
					} else if (qName.equalsIgnoreCase("email")) {
						bEmail = true;
					} else if (qName.equalsIgnoreCase("sex")) {
						bSex = true;
					} else if (qName.equalsIgnoreCase("employee")) {
						bEmployee = true;
					} else if (qName.equalsIgnoreCase("jobExperience")) {
						bJobExperience = true;
					} else if (qName.equalsIgnoreCase("jobPosition")) {
						jobPosition = true;
					}
					break;

				// catches the element and writes it to variable
				case XMLStreamConstants.CHARACTERS:
					Characters characters = event.asCharacters();
					if (bSurname) {
						person.setSurname(characters.getData());
						bSurname = false;
					}
					if (bName) {
						person.setName(characters.getData());
						bName = false;
					}
					if (bFathername) {
						person.setFathername(characters.getData());
						bFathername = false;
					}
					if (bPhone) {
						person.setPhone(characters.getData());
						bPhone = false;
					}
					if (bEmail) {
						person.setEmail(characters.getData());
						bEmail = false;
					}
					if (bSex) {
						person.setSex(characters.getData());
						bSex = false;
					}
					if (bEmployee) {
						person.setEmployee(characters.getData());
						bEmployee = false;
					}
					if (bJobExperience) {
						person.setJobExperience(characters.getData());
						bJobExperience = false;
					}
					if (jobPosition) {
						person.setJobPosition(characters.getData());
						jobPosition = false;
					}
					break;

				// catches closing tag of an element
				case XMLStreamConstants.END_ELEMENT:
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart().equalsIgnoreCase("person")) {
						ServerMain.loggerServer.info("End Element : person");
					}
					if (endElement.getName().getLocalPart().equalsIgnoreCase("personJob")) {
						ServerMain.loggerServer.info("End Element : personJob");
					}
					break;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		System.out.println(person.toString());
		return person;
	}
}
