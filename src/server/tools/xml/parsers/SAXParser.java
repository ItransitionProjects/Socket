package server.tools.xml.parsers;

import java.util.zip.ZipInputStream;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import server.PersonalData;
import server.tools.ServerMain;
import server.tools.xml.LoadData;

/**
 * The Class SAXParser.
 */
public class SAXParser extends LoadData {

	protected PersonalData load(ZipInputStream zin) {
		Handler handler = null;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
			handler = new Handler();
			saxParser.parse(zin, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handler.getPerson();
	}
}

class Handler extends DefaultHandler {

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

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
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
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("person")) {
			ServerMain.loggerServer.info("End Element : person");
		}
		if (qName.equalsIgnoreCase("personJob")) {
			ServerMain.loggerServer.info("End Element : personJob");
			ServerMain.loggerServer.info(person.toString());
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bSurname) {
			person.setSurname(new String(ch, start, length));
			bSurname = false;
		}
		if (bName) {
			person.setName(new String(ch, start, length));
			bName = false;
		}
		if (bFathername) {
			person.setFathername(new String(ch, start, length));
			bFathername = false;
		}
		if (bPhone) {
			person.setPhone(new String(ch, start, length));
			bPhone = false;
		}
		if (bEmail) {
			person.setEmail(new String(ch, start, length));
			bEmail = false;
		}
		if (bSex) {
			person.setSex(new String(ch, start, length));
			bSex = false;
		}
		if (bEmployee) {
			person.setEmployee(new String(ch, start, length));
			bEmployee = false;
		}
		if (bJobExperience) {
			person.setJobExperience(new String(ch, start, length));
			bJobExperience = false;
		}
		if (jobPosition) {
			person.setJobPosition(new String(ch, start, length));
			jobPosition = false;
		}
	}

	public PersonalData getPerson() {
		return person;
	}
}
