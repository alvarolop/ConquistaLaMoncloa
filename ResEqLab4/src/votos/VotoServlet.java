package votos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votos.dao.VotoDAO;
import votos.dao.VotoDAOImpl;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;
import es.upm.dit.isst.resource.model.Resource;
import es.upm.dit.isst.user.dao.UserDAO;
import es.upm.dit.isst.user.dao.UserDAOImpl;
import es.upm.dit.isst.user.model.AppUser;

public class VotoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Adding atributes for GoogleUser ");
		String availableString = req.getParameter("available");
		boolean isCandidato = true;

		int a = Integer.parseInt(availableString);
		if (a == 0)
			isCandidato = false;

		UserService userService = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = userService.getCurrentUser();

		String googleId = user.getUserId();
		String email = user.getEmail();
		UserDAO userdao = UserDAOImpl.getInstance();
		userdao.add(googleId, email, isCandidato);

		if (isCandidato) {
			resp.sendRedirect("/createPrograma");
		} else {
			resp.sendRedirect("/main");
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL("/createUser");
		String urlLinktext = "Login";
		List<Resource> resources = new ArrayList<Resource>();

		if (user != null) {
			// if (true){
			System.out.println(user);
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
		}
		VotoDAO votodao = VotoDAOImpl.getInstance();
		String propuesta_id = req.getParameter("propuesta_id");
		String user_id = user.getUserId();
		votodao.add(user_id, propuesta_id);
		resp.sendRedirect("/listPropuestas");

	}
}
