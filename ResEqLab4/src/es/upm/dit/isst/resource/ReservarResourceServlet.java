package es.upm.dit.isst.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
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
import es.upm.dit.isst.resource.dao.EMFService;
import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;
import es.upm.dit.isst.resource.model.Resource;

public class ReservarResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// /////////////USER////////////////////
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		boolean userAdmin = false;
		if (userService.isUserLoggedIn()) {
			userAdmin = userService.isUserAdmin();
		}
		req.getSession().setAttribute("userAdmin", userAdmin);

		String url = userService.createLoginURL("/createUser");
		String urlLinktext = "Login";
		List<Resource> resources = new ArrayList<Resource>();

		if (user != null) {
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";

			// /////////////////Crear Reserva y a�adirla al recurso/////

			ResourceDAO resourcedao = ResourceDAOImpl.getInstance();

			resources = resourcedao.getResources();

			req.getSession().setAttribute("user", user);
			req.getSession().setAttribute("resources",
					new ArrayList<Resource>(resources));
			req.getSession().setAttribute("url", url);
			req.getSession().setAttribute("urlLinktext", urlLinktext);

			RequestDispatcher view = req
					.getRequestDispatcher("ResourceResApplication.jsp");
			view.forward(req, resp);
		} else {
			resp.sendRedirect(url);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		String user = userService.getCurrentUser().getNickname();
		long reserveid = 0;

		if (user != null) {// ///LEVEL/////
			ResourceDAO daoresource = ResourceDAOImpl.getInstance();
			ReserveDAO daoreserve = ReserveDAOImpl.getInstance();

			String resourceId = req.getParameter("id");
			String startdate = req.getParameter("date");
			String starthour = req.getParameter("mishoras");
			String title = req.getParameter("title");

			Resource resource = daoresource.getResource(Long
					.parseLong(resourceId));
			int sessionTime = Integer.parseInt(req.getParameter("sessionTime"));

			String endhour = starthour;

			// Cambiamos de string al formato de Calendar
			String enddate = startdate;
			System.out.println(enddate);
			int Syear = Integer.parseInt(startdate.split("-")[0]);
			int Smonth = Integer.parseInt(startdate.split("-")[1]);
			int Sday = Integer.parseInt(startdate.split("-")[2]);
			int Shour = Integer.parseInt(starthour.split(":")[0]);
			int Smin = Integer.parseInt(starthour.split(":")[1]);

			int Eyear = Integer.parseInt(enddate.split("-")[0]);
			int Emonth = Integer.parseInt(enddate.split("-")[1]);
			int Eday = Integer.parseInt(enddate.split("-")[2]);
			int Ehour = Integer.parseInt(endhour.split(":")[0]) + sessionTime;
			int Emin = Integer.parseInt(endhour.split(":")[1]);

			// System.out.println("Start hour: " + Shour + " End hour: " + Ehour
			// + " SessionTime: " + sessionTime);

			Calendar start = new GregorianCalendar(Syear, Smonth, Sday, Shour,
					Smin);
			Calendar end = new GregorianCalendar(Eyear, Emonth, Eday, Ehour,
					Emin);

			// COMPROBAMOS QUE EL RECURSO NO ESTA OCUPADO EN ESE MOMENTO
			ReserveDAO reservedao = ReserveDAOImpl.getInstance();
			List<Reserve> reserves = new ArrayList<Reserve>();
			reserves = reservedao.getPropuestas();
//			Reserve reserve2 = new Reserve(start, end, user,
//					Long.parseLong(resourceId));
//			boolean ocupado = false;
//			for (long reservesId : daoresource.getResource(
//					Long.parseLong(resourceId)).getPropuestas()) {
//				// TODO:Reserve comprobation
//				Reserve reserve = reservedao.getReserve(reservesId);
//				System.out.println("reserveId" + reservesId + "ResourceID"
//						+ resourceId + "Reserve2" + reserve2);
//				if (reserve.ocupado(reserve2))
//					ocupado = true;
//			}
//			System.out.println("Esta ocupado?  " + ocupado);
			// /////

//			if (!ocupado)
//				reserveid = daoreserve.add(start, end, user,
//						Long.parseLong(resourceId));
//			System.out.println(reserveid);
//
//			PrintWriter out = resp.getWriter();
//			try {
//				if (!ocupado) {
//					System.out.println(reserveid);
//
//					daoresource.addReserve(reserveid, user,
//							Long.parseLong(resourceId));
//					// alertHTML(out, "Reservado el recurso " + title + "!!");
//					req.getSession().setAttribute("dialogo",
//							"Reserva realizada!");
//					// System.out.println("llego aqui");
//					resp.sendRedirect("/mandamail?title=" + title + "&date="
//							+ startdate + "&mishoras=" + starthour);
//				} else {
//					req.getSession().setAttribute("dialogo",
//							"Lo sentimos, esta ocupado a esa hora!");
//				}
//
//			} finally {
//				out.println("<script>location='/reserve';</script>");
//
//			}
//
//		} else {
//			resp.sendRedirect(userService.createLoginURL("/createUser"));
		}

	}
}
