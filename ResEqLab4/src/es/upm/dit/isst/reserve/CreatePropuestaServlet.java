package es.upm.dit.isst.reserve;

import java.io.IOException;
import java.io.PrintWriter;
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

import es.upm.dit.isst.reserve.dao.ReserveDAO;
import es.upm.dit.isst.reserve.dao.ReserveDAOImpl;
import es.upm.dit.isst.reserve.model.Reserve;
import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;
import es.upm.dit.isst.resource.model.Resource;

public class CreatePropuestaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// ////////CREATE RESOURCE///////////////////////
		String title = checkNull(req.getParameter("title"));
		String Description = checkNull(req.getParameter("description"));
		String programa_id = checkNull(req.getParameter("programa_id"));

		ReserveDAO propuestasdao = ReserveDAOImpl.getInstance();
		long propuesta_id = propuestasdao.add(title, Description);
		System.out.println("createpropuestaservlet" + programa_id);

		ResourceDAO programasdao = ResourceDAOImpl.getInstance();
		programasdao.addPropuesta(propuesta_id, programa_id);
		req.getSession().setAttribute("dialogo",
				"Propuesta Creada Correctamente!");
		resp.sendRedirect("/main");
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		ResourceDAO dao = ResourceDAOImpl.getInstance();

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
		resources = dao.getResources();
		String programa_id = req.getParameter("programa_id");

		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("resources",
				new ArrayList<Resource>(resources));
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		req.getSession().setAttribute("programa_id", programa_id);

		RequestDispatcher view = req.getRequestDispatcher("creaPropuestas.jsp");
		view.forward(req, resp);
	}
}