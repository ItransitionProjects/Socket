package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * The Class SerializeManager. Used to serialize data to xml and deserialize
 * from xml to tranfer it between client and server.
 *
 * @param <T>
 *            the generic type
 */
public class SerializeManager<T> {

	/**
	 * Instantiates a new serialize manager.
	 */
	public SerializeManager() {
		super();
	}

	/**
	 * Deserializes data from serialized String to the type T object. 
	 *
	 * @param serializedT
	 *            the serialized T
	 * @param t
	 *            the t
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T deserialize(String serializedT, T t) {
		try {
			XStream xs = new XStream(new DomDriver());
			t = (T) xs.fromXML(serializedT);
		} catch (XStreamException e) {
			e.printStackTrace();
			return null;
		}
		return t;
	}

	/**
	 * Serializes data from the type T object to serialized String.
	 *
	 * @param t
	 *            the t
	 * @return the string
	 */
	public String serialize(T t) {
		String serializedT = new String();
		try {
			XStream xs = new XStream(new DomDriver());
			serializedT = xs.toXML(t);

		} catch (XStreamException e) {
			e.printStackTrace();
			return null;
		}
		return serializedT;
	}

	/**
	 * Saves data to archive
	 *
	 * @param t
	 *            the t
	 * @param fileName
	 *            the file name
	 * @return true, if successful
	 */
	public boolean save(T t, String fileName) {

		XStream xs = new XStream(new StaxDriver());
		try {

			FileOutputStream fs = new FileOutputStream(new String("Catalog\\" + fileName + ".zip"));
			ZipOutputStream zout = new ZipOutputStream(fs);

			ZipEntry ze = new ZipEntry(new String(fileName + ".xml"));
			try {
				zout.putNextEntry(ze);
				xs.toXML(t, zout);
				zout.closeEntry();
				zout.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/**
	 * Loads from archive
	 *
	 * @param t
	 *            the t
	 * @param fileName
	 *            the file name
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	public T load(T t, String fileName) {
		try {
			XStream xs = new XStream(new StaxDriver());
			FileInputStream fis = new FileInputStream(new String("Catalog\\" + fileName + ".zip"));
			ZipInputStream zin = new ZipInputStream(fis);
			ZipEntry entry;
			try {
				zin.closeEntry();
				while ((entry = zin.getNextEntry()) != null) {
					if (entry.getName().equals((new String(fileName + ".xml")))) {
						t = (T) xs.fromXML(zin, t);
						break;
					}
				}
				zin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return t;
	}
}
