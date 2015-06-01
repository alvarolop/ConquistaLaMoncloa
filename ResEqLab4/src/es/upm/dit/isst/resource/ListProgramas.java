package es.upm.dit.isst.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;
import es.upm.dit.isst.resource.model.Resource;

public class ListProgramas extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		ResourceDAO dao = ResourceDAOImpl.getInstance();
		
		// /////////////////GESTION USER////////////////////////////////////

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL("/createUser");
		String urlLinktext = "Login";

		if (user != null) {
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
		}
		// ////////////////Gestion Recursos y reservas//////////////////////
		
		List<Resource> resources = new ArrayList<Resource>();
		resources = dao.getResources();
		
		// ///////////////Gestion de req y resp////////////////////////////

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("programas",
				new ArrayList<Resource>(resources));
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);

		RequestDispatcher view = req.getRequestDispatcher("listProgramas.jsp");
		view.forward(req, resp);

	}
}
