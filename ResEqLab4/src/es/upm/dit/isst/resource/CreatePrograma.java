package es.upm.dit.isst.resource;

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

import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;
import es.upm.dit.isst.resource.model.Resource;
import es.upm.dit.isst.user.dao.UserDAO;
import es.upm.dit.isst.user.dao.UserDAOImpl;
import es.upm.dit.isst.user.model.AppUser;

public class CreatePrograma extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// //////////CREATE RESOURCE///////////////////////
		// String title = checkNull(req.getParameter("title"));
		// String Description = checkNull(req.getParameter("description"));
		//
		// ResourceDAO dao = ResourceDAOImpl.getInstance();
		// dao.add(title, Description, sessionTime);
		// PrintWriter out = resp.getWriter();
		// // alertHTML(out, "Creado el recurso " + title + "!!");
		// req.getSession().setAttribute("dialogo",
		// "Recurso Creado Correctamente!");
		// out.println("<script>location='/main';</script>");

		// resp.sendRedirect("/main");
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// ///////////////////////USER////////////////////////////////
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		// String url = userService.createLoginURL(req.getRequestURI());

		boolean userAdmin = false;
		if (userService.isUserLoggedIn()) {
			userAdmin = userService.isUserAdmin();
		}
		req.getSession().setAttribute("userAdmin", userAdmin);

		// ///////////////////////Resource////////////////////////////////
		UserDAO daoUser = UserDAOImpl.getInstance();
		String google_Id = user.getUserId();
		System.out.println(google_Id);
		AppUser usuario = daoUser.getUserId(google_Id);
		// ///
		// Crear programa
		if (usuario.isCandidato()) {
			ResourceDAO dao = ResourceDAOImpl.getInstance();
			System.out.print(usuario.getId());
			dao.add(usuario.getId());
		}
		// //

		RequestDispatcher view = req.getRequestDispatcher("creaPropuestas.jsp");
		view.forward(req, resp);
	}
}