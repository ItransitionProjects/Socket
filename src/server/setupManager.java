package server;

import java.util.Hashtable;
import server.User.Roles;
import tools.SerializeManager;

public class setupManager {

	public static void main(String[] args) {

		Users.addUser(new User(Roles.ADMINISTRATOR, "admin", "admin", "Valentin"));
		Users.addUser(new User(Roles.USER, "user1", "user1", "Kirill"));
		Users.addUser(new User(Roles.GUEST, "guest", "guest", "Anton"));
		Users.addUser(new User(Roles.USER, "user2", "user2", "Kirill2"));
		new SerializeManager<Hashtable<String, User>>().save(Users.getHt(), Users.getXmlFileName());

		Cataloger.add(new PersonalData("Трусевич", "Валентин", "Сергеевич", "+375298573010", "vtrusevich@gmail.com",
				"OOO", "2", "Менеджер", "Мужской"));
		Cataloger.add(new PersonalData("Редковский", "Андрей", "Адреевич", "+375294325674", "redk@gmail.com", "aaa",
				"2", "Дворник", "Мужской"));
	}
}
