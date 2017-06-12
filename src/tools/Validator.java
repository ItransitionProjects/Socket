package tools;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import server.tools.ServerMain;

/**
 * The Class Validator. Validates xml files before saving.
 */
public class Validator {

	/**
	 * Instantiates a new validator.
	 */
	public Validator() {
		super();
	}

	/**
	 * Validate.
	 *
	 * @param strToValidate the object serialized to xml String to validate
	 * @return true, if successful
	 */
	public static boolean validate(String strToValidate) {

		// 1. ����� � �������� ���������� ������� ��� ����� XML Schema
		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

		// 2. ���������� �����
		// ����� ����������� � ������ ���� java.io.File, �� �� ����� ������
		// ������������
		// ������ java.net.URL � javax.xml.transform.Source
		File schemaLocation = new File("PersonalData.xsd");

		Schema schema = null;
		try {
			schema = factory.newSchema(schemaLocation);
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		}

		// 3. �������� ���������� ��� �����
		javax.xml.validation.Validator validator = schema.newValidator();

		// 4. ������ ������������ ���������
		SAXSource xmlSource = new SAXSource(new InputSource(new StringReader(strToValidate)));

		// 5. ��������� ���������
		try {
			try {
				validator.validate(xmlSource);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			ServerMain.loggerServer.info(" is valid.");
		} catch (SAXException ex) {
			ServerMain.loggerServer.error(new String(" is not valid because " + ex.getMessage()));
			return false;
		}
		return true;
	}

}