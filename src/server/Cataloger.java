package server;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import server.tools.xml.parsers.SAXParser;
import server.tools.xml.parsers.StAXParser;
import server.tools.xml.parsers.UsedParses;
import server.tools.ServerMain;
import server.tools.xml.LoadData;
import server.tools.xml.parsers.DOMParser;
import server.tools.xml.parsers.JDOMParser;
import tools.Request.Requests;
import tools.Response;
import tools.SerializeManager;
import tools.Validator;

/**
 * The Class Cataloger. Used as a DAO layer between xml storage of an archive
 * and the program.
 */
public abstract class Cataloger {

	/** The used parser. */
	private static LoadData usedParser = useSAXParser();

	/**
	 * This Hashtable used to search by name and surname quickly. Key =
	 * name+surname, Value = address of the xml doc.
	 */
	public static Hashtable<String, String> htNameSurname = new Hashtable<String, String>();

	/** This Hashtable used to search by Email quickly. Key =
	 * email, Value = address of the xml doc. 
	 */
	public static Hashtable<String, String> htEmail = new Hashtable<String, String>();

	/** This Hashtable used to search by phone quickly. Key =
	 * phone, Value = address of the xml doc. 
	 */
	public static Hashtable<String, String> htPhone = new Hashtable<String, String>();

	/**
	 * Adds a new person to the cataloger
	 *
	 * @param person
	 *            the person
	 * @return the response 
	 */
	public static Response add(PersonalData person) {
		htNameSurname.put(new String("" + person.getName() + person.getSurname()), person.getXmlFileName());
		htEmail.put(person.getEmail(), person.getXmlFileName());
		htPhone.put(person.getPhone(), person.getXmlFileName());
		if (validate(person))
			new SerializeManager<PersonalData>().save(person, person.getXmlFileName());

		ServerMain.loggerServer
				.info(new String("Client " + person.getSurname() + " " + person.getName() + " wass succesfully added"));
		saveData();
		return new Response(Requests.ADD, null, null, true);
	}

	/**
	 * Validates the document before savin
	 *
	 * @param person
	 *            the person
	 * @return true, if successful
	 */
	private static boolean validate(PersonalData person) {
		String s = new String();
		s = new SerializeManager<PersonalData>().serialize(person);
		if (!Validator.validate(s)) {
			ServerMain.loggerServer.error(
					new String("Validation of a document " + person.getName() + person.getSurname() + " failed"));
			return false;
		}
		ServerMain.loggerServer
				.info(new String("Document " + person.getName() + person.getSurname() + " validated successfully"));
		return true;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public static Response getAll() {
		Enumeration<String> files = htEmail.elements();
		Vector<PersonalData> cataloger = new Vector<PersonalData>();
		while (files.hasMoreElements()) {
			cataloger.addElement(parse(files.nextElement()));
		}
		ServerMain.loggerServer.info(new String("Request to get all data is served"));

		return new Response(Requests.SHOW_ALL, cataloger, null, true);
	}

	/**
	 * Parses the.
	 *
	 * @param nextElement
	 *            the next element
	 * @return the personal data
	 */
	private static PersonalData parse(String nextElement) {
		PersonalData person = new PersonalData();
		person = usedParser.loadPersonalData(nextElement);
		return person;
	}

	
	@UsedParses("SAXParser")
	public static LoadData useSAXParser() {
		ServerMain.loggerServer.info(new String("Parser changed to SAX"));
		usedParser = new SAXParser();
		return usedParser;
	}

	
	@UsedParses("StAXParser")
	public static LoadData useStAXParser() {
		ServerMain.loggerServer.info(new String("Parser changed to StAX"));
		usedParser = new StAXParser();
		return usedParser;
	}


	@UsedParses("DOMParser")
	public static LoadData useDOMParser() {
		ServerMain.loggerServer.info(new String("Parser changed to DOM"));
		usedParser = new DOMParser();
		return usedParser;
	}


	@UsedParses("JDOMParser")
	public static LoadData useJDOMParser() {
		ServerMain.loggerServer.info(new String("Parser changed to JDOM"));
		usedParser = new JDOMParser();
		return usedParser;
	}

	/**
	 * Find by phone.
	 *
	 * @param phone
	 *            the phone
	 * @return the personal data
	 */
	public static PersonalData findByPhone(String phone) {
		PersonalData person = new PersonalData();
		String fileName = null;
		fileName = htPhone.get(phone);
		if (fileName == null) {
			return null;
		}
		if (new SerializeManager<PersonalData>().load(person, fileName) != null) {
			ServerMain.loggerServer.info(new String("Request to find by phone is served"));
			return person;
		}
		ServerMain.loggerServer.error(new String("Error in reading file"));
		return null;
	}

	/**
	 * Find by name and surname.
	 *
	 * @param searchStr
	 *            the search str
	 * @return the personal data
	 */
	public static PersonalData findByNameAndSurname(String searchStr) {
		PersonalData person = new PersonalData();
		String fileName = null;
		fileName = htNameSurname.get(searchStr);
		if (fileName == null) {
			return null;
		}
		if (new SerializeManager<PersonalData>().load(person, fileName) != null) {
			ServerMain.loggerServer.info(new String("Request to find by name and surname is served"));
			return person;
		}
		ServerMain.loggerServer.info(new String("Error in reading file"));
		return null;
	}

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return the personal data
	 */
	public static PersonalData findByEmail(String email) {
		PersonalData person = new PersonalData();
		String fileName = null;
		fileName = htEmail.get(email);
		if (fileName == null) {
			return null;
		}
		if (new SerializeManager<PersonalData>().load(person, htEmail.get(email)) != null) {
			return person;
		}
		ServerMain.loggerServer.info(new String("Error in reading file"));
		return null;
	}

	/**
	 * Edits the.
	 *
	 * @param personalDataOld
	 *            the personal data old
	 * @param personalDataNew
	 *            the personal data new
	 * @return the response
	 */
	public static Response edit(PersonalData personalDataOld, PersonalData personalDataNew) {
		delete(personalDataOld);
		add(personalDataNew);
		return new Response(Requests.EDIT, null, null, true);
	}

	/**
	 * Delete.
	 *
	 * @param personalData
	 *            the personal data
	 * @return the response
	 */
	public static Response delete(PersonalData personalData) {

		htEmail.remove(personalData.getEmail());
		htNameSurname.remove(new String("" + personalData.getName() + personalData.getSurname()));
		htPhone.remove(personalData.getPhone());

		ServerMain.loggerServer.info(new String(
				"Client " + personalData.getSurname() + " " + personalData.getName() + " wass succesfully deleted"));
		saveData();
		return new Response(Requests.DELETE, null, null, true);
	}

	/**
	 * Save data.
	 */
	private static void saveData() {
		new SerializeManager<Hashtable<String, String>>().save(htEmail, "Email");
		new SerializeManager<Hashtable<String, String>>().save(htNameSurname, "NameSurname");
		new SerializeManager<Hashtable<String, String>>().save(htPhone, "Phone");

	}

	/**
	 * Load data.
	 */
	public static void loadData() {
		htEmail = new SerializeManager<Hashtable<String, String>>().load(htEmail, "Email");
		htNameSurname = new SerializeManager<Hashtable<String, String>>().load(htNameSurname, "NameSurname");
		htPhone = new SerializeManager<Hashtable<String, String>>().load(htPhone, "Phone");
	}

	/**
	 * Find.
	 *
	 * @param searchStr
	 *            the search str
	 * @return the response
	 */
	public static Response find(String searchStr) {
		Vector<PersonalData> persons = new Vector<PersonalData>();

		PersonalData email = findByEmail(searchStr);
		if (email != null)
			persons.add(email);

		PersonalData nameAndSurname = findByNameAndSurname(searchStr);
		if (nameAndSurname != null)
			persons.add(nameAndSurname);

		PersonalData phone = findByPhone(searchStr);
		if (phone != null)
			persons.add(phone);

		return new Response(Requests.FIND, persons, null, true);
	}

}
