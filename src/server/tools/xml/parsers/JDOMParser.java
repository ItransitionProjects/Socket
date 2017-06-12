package server.tools.xml.parsers;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import server.PersonalData;
import server.tools.xml.LoadData;

/**
 * The Class JDOMParser.
 */
public class JDOMParser extends LoadData {

	protected PersonalData load(ZipInputStream zin) {
		PersonalData personData = new PersonalData();
		try {
			SAXBuilder saxBuilder = new SAXBuilder();
			Document document = saxBuilder.build(zin);
			Element classElement = document.getRootElement();
			List<Element> persons = classElement.getChildren();

			Element person = persons.get(0);
			personData.setSurname(person.getChild("surname").getText());
			personData.setName(person.getChild("name").getText());
			personData.setFathername(person.getChild("fathername").getText());
			personData.setPhone(person.getChild("phone").getText());
			personData.setEmail(person.getChild("email").getText());
			personData.setSex(person.getChild("sex").getText());

			Element personJob = persons.get(1);
			personData.setJobExperience(personJob.getChild("jobExperience").getText());
			personData.setJobPosition(personJob.getChild("jobPosition").getText());
			personData.setEmployee(personJob.getChild("employee").getText());

			System.out.println(personData.toString());
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return personData;
	}
}
