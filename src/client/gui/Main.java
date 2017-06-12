package client.gui;

import java.util.Vector;

import client.tools.ChangeParserClient;
import client.tools.ClientCommunication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.PersonalData;
import server.User;
import server.User.Roles;
import tools.Request;
import tools.Request.Requests;
import tools.Response;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * The Class Main. It displays gui part to the client.
 */
public class Main extends Application {

	/**
	 * The communication between client and server. this object is used to
	 * handle with data transferring from client to server and vice versa
	 */
	private static ClientCommunication communication;

	/** The main scene of the gui. */
	private static Scene scene;

	/** The user login. */
	static String userLogin;

	/** The user password. */
	static String userPassword;

	/** The Array of persons to be displayed in the table */
	private ObservableList<PersonalData> data;

	/** The Array of persons, which exist in catalog */
	private Vector<PersonalData> persons = null;

	/** The table. */
	private TableView<PersonalData> table;

	/** The text surname. */
	private TextField textSurname;

	/** The text name. */
	private TextField textName;

	/** The text fathername. */
	private TextField textFathername;

	/** The text phone. */
	private TextField textPhone;

	/** The text email. */
	private TextField textEmail;

	/** The text employee. */
	private TextField textEmployee;

	/** The text job experience. */
	private TextField textJobExperience;

	/** The text job position. */
	private TextField textJobPosition;

	/** The combo sex. */
	private ComboBox<String> comboSex;

	/** The selected person. */
	private PersonalData selectedPerson = null;

	/** The is to change. */
	private boolean isToChange = false;

	@Override
	public void start(Stage primaryStage) {
		try {
			// authorization
			Stage myDialog = new MyDialog();
			myDialog.sizeToScene();
			myDialog.showAndWait();

			// connecting to the server
			communication = new ClientCommunication(userLogin, userPassword);
			if (!communication.setConnection()) {
				showMessageBox("Произошла ошибка подключения к серверу. \nСервер не доступен. \nПрограмма завершена.");
				Platform.exit();
				System.exit(0);
			}

			BorderPane root = new BorderPane();
			scene = new Scene(root, 900, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Архив");
			root.setPadding(new Insets(20, 20, 20, 20));

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					communication.setNewRequest(new Request(Requests.EXIT, null, null, null));
					Platform.exit();
					System.exit(0);
				}
			});

			fillFrame(root);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method. Launches the application.
	 * 
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method creates the frame.
	 *
	 * @param root
	 *            BorderPane on which all components will be displayed
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void fillFrame(BorderPane root) {

		Button buttonShow = new Button("Показать все дела");
		Button buttonAdd = new Button("Добавить дело");
		Button buttonChange = new Button("Изменить дело");
		Button buttonDelete = new Button("Удалить дело");

		ObservableList<PersonalData> tableData = FXCollections.observableArrayList();
		table = new TableView<PersonalData>(tableData);

		GridPane paneAdd = new GridPane();
		Button buttonOk = new Button("ОК");
		Font font = new Font(16);

		HBox paneSearch = new HBox(50);

		// table
		TableColumn<PersonalData, String> columnName = new TableColumn<PersonalData, String>("Имя");
		columnName.setPrefWidth((scene.getWidth() - 42) / 9);
		columnName.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("name"));

		TableColumn<PersonalData, String> columnSurname = new TableColumn<PersonalData, String>("Фамилия");
		columnSurname.setPrefWidth((scene.getWidth() - 42) / 9);
		columnSurname.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("surname"));

		TableColumn<PersonalData, String> columnFathername = new TableColumn<PersonalData, String>("Отчество");
		columnFathername.setPrefWidth((scene.getWidth() - 42) / 9);
		columnFathername.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("fathername"));

		TableColumn<PersonalData, String> columnEmail = new TableColumn<PersonalData, String>("Электронная почта");
		columnEmail.setPrefWidth((scene.getWidth() - 42) / 9);
		columnEmail.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("email"));

		TableColumn<PersonalData, String> columnPhone = new TableColumn<PersonalData, String>("Телефон");
		columnPhone.setPrefWidth((scene.getWidth() - 42) / 9);
		columnPhone.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("phone"));

		TableColumn<PersonalData, String> columnEmployee = new TableColumn<PersonalData, String>("Место работы");
		columnEmployee.setPrefWidth((scene.getWidth() - 42) / 9);
		columnEmployee.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("employee"));

		TableColumn<PersonalData, String> columnJobPosition = new TableColumn<PersonalData, String>("Должность");
		columnJobPosition.setPrefWidth((scene.getWidth() - 42) / 9);
		columnJobPosition.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("jobPosition"));

		TableColumn<PersonalData, String> columnJobExperience = new TableColumn<PersonalData, String>("Опыт работы");
		columnJobExperience.setPrefWidth((scene.getWidth() - 42) / 9);
		columnJobExperience.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("JobExperience"));

		TableColumn<PersonalData, String> columnSex = new TableColumn<PersonalData, String>("Пол");
		columnSex.setPrefWidth((scene.getWidth() - 42) / 9);
		columnSex.setCellValueFactory(new PropertyValueFactory<PersonalData, String>("sex"));

		table.getColumns().addAll(columnName, columnSurname, columnFathername, columnEmail, columnPhone, columnEmployee,
				columnJobPosition, columnJobExperience, columnSex);

		fillTable();

		table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				selectedPerson = table.getSelectionModel().getSelectedItem();
				if (!communication.isGuest()) {
					buttonChange.setDisable(false);
					buttonDelete.setDisable(false);
				}
			}
		});

		// Add & change pane
		textSurname = new TextField();
		textSurname.setPrefSize(300, 30);
		textName = new TextField();
		textName.setPrefSize(300, 30);
		textFathername = new TextField();
		textFathername.setPrefSize(300, 30);
		textPhone = new TextField();
		textPhone.setPrefSize(300, 30);
		textEmail = new TextField();
		textEmail.setPrefSize(300, 30);
		textEmployee = new TextField();
		textEmployee.setPrefSize(300, 30);
		textJobExperience = new TextField();
		textJobExperience.setPrefSize(300, 30);
		textJobExperience.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					textJobExperience.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		textJobPosition = new TextField();
		textJobPosition.setPrefSize(300, 30);
		ObservableList<String> sexOptions = FXCollections.observableArrayList("Мужской", "Женский");
		comboSex = new ComboBox<String>(sexOptions);
		comboSex.setPrefSize(300, 30);

		Label labelSurname = new Label("Фамилия:");
		labelSurname.setFont(font);
		Label labelName = new Label("Имя:");
		labelName.setFont(font);
		Label labelFathername = new Label("Отчество:");
		labelFathername.setFont(font);
		Label labelPhone = new Label("Телефон:");
		labelPhone.setFont(font);
		Label labelEmail = new Label("Электронная почта:");
		labelEmail.setFont(font);
		Label labelEmployee = new Label("Место работы:");
		labelEmployee.setFont(font);
		Label labelJobExperience = new Label("Опыт работы:");
		labelJobExperience.setFont(font);
		Label labelJobPosition = new Label("Должность:");
		labelJobPosition.setFont(font);
		Label labelSex = new Label("Пол:");
		labelSex.setFont(font);

		buttonOk.setPrefSize(120, 40);
		buttonOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (!verifyData()) {
					showMessageBox("Все поля должны быть заполнены");
					return;
				}
				paneAdd.setVisible(false);
				root.setCenter(table);
				table.setVisible(true);

				PersonalData personalData = new PersonalData(textSurname.getText(), textName.getText(),
						textFathername.getText(), textPhone.getText(), textEmail.getText(), textEmployee.getText(),
						textJobExperience.getText(), textJobPosition.getText(), comboSex.getValue());

				Request request = null;
				if (isToChange) {
					request = new Request(Requests.EDIT, null, selectedPerson, personalData);
				} else {
					request = new Request();
					request.setRequest(Request.Requests.ADD);
					request.setPersonalData(personalData);
				}

				Response responce = communication.setNewRequest(request);
				if (responce == null) {
					Platform.exit();
					System.exit(0);
				}
				if (!responce.isSuccess()) {
					showMessageBox("Не удалось добавить нового пользователя");
				}
				fillTable();
				clearTextFields();
				buttonAdd.setDisable(false);
				buttonShow.setDisable(true);
				paneSearch.setVisible(true);

			}

			private boolean verifyData() {
				if (textName.getText().length() == 0)
					return false;
				else if (textSurname.getText().length() == 0)
					return false;
				else if (textFathername.getText().length() == 0)
					return false;
				else if (textPhone.getText().length() == 0)
					return false;
				else if (textEmail.getText().length() == 0)
					return false;
				else if (textEmployee.getText().length() == 0)
					return false;
				else if (textJobExperience.getText().length() == 0)
					return false;
				else if (textJobPosition.getText().length() == 0)
					return false;
				else if (comboSex.getValue().length() == 0)
					return false;

				return true;
			}
		});

		paneAdd.setVgap(10);
		paneAdd.setHgap(80);
		paneAdd.setPadding(new Insets(20));
		paneAdd.setAlignment(Pos.CENTER);

		paneAdd.add(textSurname, 1, 0);
		paneAdd.add(textName, 1, 1);
		paneAdd.add(textFathername, 1, 2);
		paneAdd.add(textPhone, 1, 3);
		paneAdd.add(textEmail, 1, 4);
		paneAdd.add(textEmployee, 1, 5);
		paneAdd.add(textJobExperience, 1, 6);
		paneAdd.add(textJobPosition, 1, 7);
		paneAdd.add(comboSex, 1, 8);

		paneAdd.add(labelSurname, 0, 0);
		paneAdd.add(labelName, 0, 1);
		paneAdd.add(labelFathername, 0, 2);
		paneAdd.add(labelPhone, 0, 3);
		paneAdd.add(labelEmail, 0, 4);
		paneAdd.add(labelEmployee, 0, 5);
		paneAdd.add(labelJobExperience, 0, 6);
		paneAdd.add(labelJobPosition, 0, 7);
		paneAdd.add(labelSex, 0, 8);

		paneAdd.add(buttonOk, 2, 9);

		// searching pane
		TextField textSearch = new TextField();
		textSearch.setPrefSize(500, 40);
		Button buttonSearch = new Button("Найти");
		buttonSearch.setPrefSize(120, 40);

		buttonSearch.setDefaultButton(true);

		buttonSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				Response responce = communication
						.setNewRequest(new Request(Requests.FIND, textSearch.getText(), null, null));
				if (responce == null) {
					Platform.exit();
					System.exit(0);
				}
				persons = responce.getPersons();
				data = FXCollections.observableArrayList(persons);
				table.setItems(data);
			}
		});
		paneSearch.getChildren().addAll(textSearch, buttonSearch);
		paneSearch.setAlignment(Pos.CENTER);
		paneSearch.setPadding(new Insets(0, 0, 20, 0));

		// buttons
		buttonShow.setPrefSize(120, 40);
		buttonShow.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (!communication.isGuest()) {
					buttonChange.setDisable(true);
					buttonDelete.setDisable(true);
				}
				buttonShow.setDisable(true);
				buttonAdd.setDisable(false);

				textSearch.clear();

				buttonSearch.setDefaultButton(true);

				paneAdd.setVisible(false);
				root.setCenter(table);
				paneSearch.setVisible(true);
				fillTable();
				clearTextFields();
				table.setVisible(true);
			}
		});

		buttonAdd.setPrefSize(120, 40);
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (!communication.isGuest()) {
					buttonChange.setDisable(true);
					buttonDelete.setDisable(true);
				}
				buttonShow.setDisable(false);
				buttonAdd.setDisable(true);

				buttonSearch.setDefaultButton(false);
				buttonOk.setDefaultButton(true);

				table.setVisible(false);
				root.setCenter(paneAdd);
				paneSearch.setVisible(false);
				paneAdd.setVisible(true);
				isToChange = false;
			}

		});

		buttonChange.setPrefSize(120, 40);
		buttonChange.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				buttonAdd.setDisable(false);
				buttonShow.setDisable(false);
				buttonChange.setDisable(true);
				buttonDelete.setDisable(true);
				table.setVisible(false);
				root.setCenter(paneAdd);
				paneSearch.setVisible(false);
				paneAdd.setVisible(true);
				isToChange = true;

				buttonSearch.setDefaultButton(false);
				buttonOk.setDefaultButton(true);

				textEmail.setText(selectedPerson.getEmail());
				textEmployee.setText(selectedPerson.getEmployee());
				textFathername.setText(selectedPerson.getFathername());
				textJobExperience.setText(selectedPerson.getJobExperience());
				textJobPosition.setText(selectedPerson.getJobPosition());
				textName.setText(selectedPerson.getName());
				textPhone.setText(selectedPerson.getPhone());
				textSurname.setText(selectedPerson.getSurname());
			}
		});

		buttonDelete.setPrefSize(120, 40);
		buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				buttonChange.setDisable(true);
				buttonDelete.setDisable(true);
				buttonShow.setDisable(true);
				buttonAdd.setDisable(false);
				Response responce = communication
						.setNewRequest(new Request(Requests.DELETE, null, selectedPerson, null));
				if (responce == null) {
					Platform.exit();
					System.exit(0);
				}
				persons = responce.getPersons();
				fillTable();

			}
		});

		buttonShow.setDisable(true);
		buttonChange.setDisable(true);
		buttonDelete.setDisable(true);

		HBox paneButtons = new HBox(50, buttonShow, buttonAdd, buttonChange, buttonDelete);
		paneButtons.setAlignment(Pos.CENTER);
		paneButtons.setPadding(new Insets(20));

		// adding all to borderPane
		root.setCenter(table);
		root.setBottom(paneButtons);
		root.setTop(paneSearch);

		// for admin
		if (communication.isAdmin()) {
			// pane for admin
			GridPane paneAdmin = new GridPane();
			Button buttonAdminOk = new Button("OK");

			Button buttonAdmin = new Button("Администрирование");
			buttonAdmin.setPrefSize(120, 40);
			buttonAdmin.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent actionEvent) {
					root.setCenter(paneAdmin);
					paneSearch.setVisible(false);
					paneAdmin.setVisible(true);
					paneAdmin.setAlignment(Pos.CENTER);

					buttonSearch.setDefaultButton(false);
					buttonOk.setDefaultButton(false);
					buttonAdminOk.setDefaultButton(true);
				}
			});

			paneButtons.getChildren().add(buttonAdmin);

			paneAdmin.setVgap(10);
			paneAdmin.setHgap(80);
			paneAdmin.setPadding(new Insets(20));
			paneAdmin.setAlignment(Pos.CENTER);
			Response responce = communication.setNewRequest(new Request(Requests.SHOW_USERS, null, null, null));
			if (responce == null) {
				Platform.exit();
				System.exit(0);
			}
			if (responce.isSuccess()) {
				Label login = new Label("Логин");
				login.setFont(font);
				paneAdmin.add(login, 1, 0);
				Label name = new Label("Имя");
				name.setFont(font);
				paneAdmin.add(name, 2, 0);
				Label rights = new Label("Уровень прав");
				rights.setFont(font);
				paneAdmin.add(rights, 3, 0);

				ObservableList<String> options = FXCollections.observableArrayList("Администратор", "Пользователь",
						"Гость");
				Vector<ComboBox<String>> combos = new Vector<ComboBox<String>>();
				Vector<User> users = responce.getUsers();
				for (int i = 0; i < users.size(); i++) {
					Label lab1 = new Label(users.get(i).getLogin());
					lab1.setFont(font);
					Label lab2 = new Label(users.get(i).getName());
					lab2.setFont(font);
					paneAdmin.add(lab1, 1, i + 1);
					paneAdmin.add(lab2, 2, i + 1);

					ComboBox<String> comboBox = new ComboBox<String>(options);
					comboBox.setPrefSize(140, 40);
					combos.add(comboBox);
					switch (users.get(i).getRole()) {
					case GUEST:
						comboBox.setValue("Гость");
						break;
					case USER:
						comboBox.setValue("Пользователь");
						break;
					case ADMINISTRATOR:
						comboBox.setValue("Администратор");
						break;
					}
					paneAdmin.add(comboBox, 3, i + 1);

				}

				// combobox to change parser
				ObservableList<String> parserOptions = FXCollections.observableArrayList("SAXParser", "StAXParser",
						"DOMParser", "JDOMParser");
				ComboBox<String> comboParser = new ComboBox<String>(parserOptions);
				comboParser.setPrefSize(140, 40);
				comboParser.setValue("SAXParser");
				comboParser.valueProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> ov, String oldChoise, String newChoise) {
						if (oldChoise.equals(newChoise)) {
							return;
						}
						new ChangeParserClient().changeParser(newChoise);
					}
				});
				paneAdmin.add(new Label("Вид парсера"), 4, 1);
				paneAdmin.add(comboParser, 4, 2);

				buttonAdminOk.setPrefSize(120, 40);
				paneAdmin.add(buttonAdminOk, 4, users.size() + 1);
				buttonAdminOk.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent actionEvent) {
						for (int i = 0; i < responce.getUsers().size(); i++) {
							if (combos.get(i).getValue().equals("Гость")) {
								users.get(i).setRole(Roles.GUEST);
							} else if (combos.get(i).getValue().equals("Пользователь")) {
								users.get(i).setRole(Roles.USER);
							} else if (combos.get(i).getValue().equals("Администратор")) {
								users.get(i).setRole(Roles.ADMINISTRATOR);
							}
						}
						Request request = new Request();
						request.setRequest(Requests.CHANGE_USERS);
						request.setUsers(users);
						Response responce = communication.setNewRequest(request);
						if (responce == null) {
							Platform.exit();
							System.exit(0);
						}
						if (!responce.isSuccess()) {
							showMessageBox("Не удалось изменить данные пользователей");
						}
					}
				});

			}

		}
	}

	/**
	 * This method is used to fill the table with the data.
	 */
	private void fillTable() {
		Response responce = communication.setNewRequest(new Request(Requests.SHOW_ALL, null, null, null));
		if (responce == null) {
			Platform.exit();
			System.exit(0);
		}
		persons = responce.getPersons();
		data = FXCollections.observableArrayList(persons);
		table.setItems(data);

	}

	/**
	 * Clear text fields in addPane after working with it.
	 */
	private void clearTextFields() {
		textEmail.clear();
		textEmployee.clear();
		textFathername.clear();
		textJobExperience.clear();
		textJobPosition.clear();
		textName.clear();
		textPhone.clear();
		textSurname.clear();
	}

	/**
	 * Shows informational message box with the text from pamameter string.
	 *
	 * @param string
	 *            the string to display in MB
	 */
	public static void showMessageBox(String string) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Уведомление");
		alert.setHeaderText(null);
		alert.setContentText(string);
		// alert.initOwner((Stage) scene.getWindow());
		alert.showAndWait();
	}

}

/**
 * The Class MyDialog. It displays login dialog.
 */
class MyDialog extends Stage {

	public MyDialog() {
		super();

		resizableProperty().setValue(Boolean.FALSE);
		setTitle("Авторизация");
		Group root = new Group();
		Scene scene = new Scene(root, 220, 110);
		setScene(scene);

		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5));
		gridpane.setHgap(5);
		gridpane.setVgap(5);

		Label labelLogin = new Label("Логин:");
		gridpane.add(labelLogin, 0, 1);

		Label labelPassword = new Label("Пароль:");
		gridpane.add(labelPassword, 0, 2);
		final TextField loginFld = new TextField();
		gridpane.add(loginFld, 1, 1);

		final PasswordField passwordFld = new PasswordField();
		gridpane.add(passwordFld, 1, 2);

		Button ok = new Button("OK");
		ok.setDefaultButton(true);
		ok.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				close();
				Main.userLogin = loginFld.getText();
				Main.userPassword = loginFld.getText();

			}
		});

		this.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				Platform.exit();
				System.exit(0);
			}
		});

		gridpane.add(ok, 1, 3);
		GridPane.setHalignment(ok, HPos.RIGHT);
		root.getChildren().add(gridpane);
	}
}
