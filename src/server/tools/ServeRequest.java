package server.tools;

import server.Cataloger;
import server.Users;
import tools.Request;
import tools.Response;

/**
 * The Class ServeRequest handles with requests from client.
 */
public class ServeRequest {

	/** The request. */
	private Request request;

	/**
	 * Instantiates a new serve request.
	 *
	 * @param request
	 *            the request
	 */
	public ServeRequest(Request request) {
		super();
		this.request = request;
	}

	/**
	 * Serves request, accordingly to request calls required method in
	 * cataloger.
	 *
	 * @return the response
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public Response serveRequest() throws IllegalArgumentException {
		Response responce = null;
		switch (request.getRequest()) {
		case SHOW_ALL:
			responce = Cataloger.getAll();
			break;

		case FIND:
			responce = Cataloger.find(request.getSearchStr());
			break;

		case ADD:
			responce = Cataloger.add(request.getPersonalData());
			break;

		case EDIT:
			responce = Cataloger.edit(request.getPersonalData(), request.getPersonalDataNew());
			break;

		case DELETE:
			responce = Cataloger.delete(request.getPersonalData());
			break;
		case SHOW_USERS:
			responce = Users.getAll();
			break;
		case CHANGE_USERS:
			responce = Users.changeUsers(request.getUsers());
			break;
		default:
			throw new IllegalArgumentException();
		}
		return responce;
	}
}
