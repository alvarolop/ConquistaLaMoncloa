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
import com.google.appengine.labs.repackaged.com.google.common.io.Resources;

import es.upm.dit.isst.reserve.dao.ReserveDAO;
import es.upm.dit.isst.reserve.dao.ReserveDAOImpl;
import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;
import es.upm.dit.isst.resource.model.Resource;
import es.upm.dit.isst.reserve.model.Reserve;
import es.upm.dit.isst.resource.model.Resource;

public class ListPropuestas extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		// modificariaa esto para que liste las reservas de cada recurso, asi
		// puedo mostar el nombre del recurso mas facilmente.
		// /////////////////GESTION USER////////////////////////////////////
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String url = userService.createLoginURL("/createUser");
		String urlLinktext = "Login";
		List<Reserve> reserves = null;
		boolean userAdmin = false;
		if (user != null) {
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
			if (userService.isUserLoggedIn()) {
				userAdmin = userService.isUserAdmin();
			}
			req.getSession().setAttribute("userAdmin", userAdmin);
			// ////////////////Gestion Recursos y reservas//////////////////////
			// ResourceDAO resourcedao = ResourceDAOImpl.getInstance();

			ReserveDAO reservedao = ReserveDAOImpl.getInstance();
			ResourceDAO resourcedao = ResourceDAOImpl.getInstance();

			reserves = new ArrayList<Reserve>();
			reserves = reservedao.getPropuestas();
			List<Resource> resources = null;
			// Si no eres admin, solo ves las tuyas
			if (!userAdmin && (user != null)) {// LEVEL en vez de admin

			}
			resources = resourcedao.getResources();

			// System.out.println(resources);
			// System.out.println(reserves);

			// ///////////////Gestion de req y resp////////////////////////////

			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("resources",
					new ArrayList<Resource>(resources));
			req.getSession().setAttribute("propuestas",
					new ArrayList<Reserve>(reserves));
			req.getSession().setAttribute("url", url);
			req.getSession().setAttribute("urlLinktext", urlLinktext);

			RequestDispatcher view = req
					.getRequestDispatcher("listPropuestas.jsp");
			view.forward(req, resp);
		} else {
			resp.sendRedirect("/main");
		}
	}

	private void alertHTML(PrintWriter out, String message) throws IOException {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('" + message + "');");
		out.println("</script>");
	}

}