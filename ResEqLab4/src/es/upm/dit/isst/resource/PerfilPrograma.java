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

import es.upm.dit.isst.reserve.dao.ReserveDAO;
import es.upm.dit.isst.reserve.dao.ReserveDAOImpl;
import es.upm.dit.isst.reserve.model.Reserve;
import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;
import es.upm.dit.isst.resource.model.Resource;
import es.upm.dit.isst.user.dao.UserDAO;
import es.upm.dit.isst.user.dao.UserDAOImpl;
import es.upm.dit.isst.user.model.AppUser;

public class PerfilPrograma extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		String programa_id = req.getParameter("programa_id");

		ResourceDAO dao = ResourceDAOImpl.getInstance();

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String url = userService.createLoginURL("/createUser");
		String urlLinktext = "Login";
		boolean userAdmin = false;
		if (userService.isUserLoggedIn()) {
			userAdmin = userService.isUserAdmin();
		}
		req.getSession().setAttribute("userAdmin", userAdmin);
		System.out.println(userAdmin);
		
		if (user != null) {
			// if (true){
			System.out.println(user);
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
		}
		UserDAO appUserdao = UserDAOImpl.getInstance();
		AppUser appUser = appUserdao.getUserId(user.getUserId());

		Resource programa = dao.getResource(Long.parseLong(programa_id));
		ReserveDAO reservedao = ReserveDAOImpl.getInstance();

		List<Reserve> propuestas = null;
		propuestas = new ArrayList<Reserve>();
		propuestas = reservedao.getPropuestas();
		req.getSession().setAttribute("user", user);
		req.getSession().setAttribute("appUser", appUser);

		req.getSession().setAttribute("programa", programa);
		req.getSession().setAttribute("propuestas",
				new ArrayList<Reserve>(propuestas));
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);

		RequestDispatcher view = req.getRequestDispatcher("perfilPrograma.jsp");
		view.forward(req, resp);

	}
}
