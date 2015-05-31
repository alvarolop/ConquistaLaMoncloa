package es.upm.dit.isst.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;
import es.upm.dit.isst.resource.model.Resource;
import es.upm.dit.isst.user.dao.UserDAO;
import es.upm.dit.isst.user.dao.UserDAOImpl;
import es.upm.dit.isst.user.model.AppUser;

public class CreateUserServlet extends HttpServlet {

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
		System.out.println(isCandidato);
		if (isCandidato) {
			String titulo = req.getParameter("title");
			String img_url = req.getParameter("img_url");
			resp.sendRedirect("/createPrograma?titulo=" + titulo + "&img_url="
					+ img_url);
		} else {
			resp.sendRedirect("/main");
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		UserDAO dao = UserDAOImpl.getInstance();

		List<AppUser> users = new ArrayList<AppUser>();

		UserService userService = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = userService.getCurrentUser();
		PrintWriter out = resp.getWriter();
		// System.out.println("Creo un usuario nuevo ? "+!dao.appUserExists(user.getUserId()));
		if (!dao.appUserExists(user.getUserId())) {

			String googleId = user.getUserId();
			String email = user.getEmail();
			int level = 0;
			System.out.println("Creating new AppUser " + googleId);

			req.getSession().setAttribute("users",
					new ArrayList<AppUser>(users));
			alertHTML(out, "Bienvenido " + user.getNickname() + "!!");
			// resp.sendRedirect("/createUser.jsp");
			RequestDispatcher view = req.getRequestDispatcher("createUser.jsp");
			view.forward(req, resp);

		}
		out.println("<script>location='/main';</script>");

		// resp.sendRedirect("/main");

	}

	private void alertHTML(PrintWriter out, String message) throws IOException {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('" + message + "');");
		out.println("</script>");

	}
}
