package es.upm.dit.isst.reserve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votos.dao.VotoDAO;
import votos.dao.VotoDAOImpl;
import votos.model.Voto;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
//import com.google.appengine.labs.repackaged.com.google.common.io.Resources;

import es.upm.dit.isst.reserve.dao.ReserveDAO;
import es.upm.dit.isst.reserve.dao.ReserveDAOImpl;
import es.upm.dit.isst.reserve.model.Reserve;

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
		
		if (user != null) {
			url = userService.createLogoutURL(req.getRequestURI());
			urlLinktext = "Logout";
		}
		// ////////////////Gestion Recursos y reservas//////////////////////

		ReserveDAO reservedao = ReserveDAOImpl.getInstance();
		//ResourceDAO resourcedao = ResourceDAOImpl.getInstance();
		
		List<Reserve> reserves = null;
		reserves = new ArrayList<Reserve>();
		reserves = reservedao.getPropuestas();

		// ///////////////Gestion de req y resp////////////////////////////
		VotoDAO votodao = VotoDAOImpl.getInstance();
		Map<Long, Integer> numVotos = new HashMap<Long, Integer>();
		Map<Long, String> isVotada = new HashMap<Long, String>();

		for (Reserve propuesta : reserves) {
			int votos = votodao.numVotoPropuesta(Long.toString(propuesta
					.getId()));
			numVotos.put(propuesta.getId(), Integer.valueOf(votos));
			isVotada.put(propuesta.getId(), "0");
			if (user != null){
				for (Voto voto : votodao.getVotos()) {
					if (voto.getUser_id().equals(user.getUserId())) {
						//System.out.println(voto.getPropuesta_id() + " "
						//		+ Long.toString(propuesta.getId()));
	
						if (voto.getPropuesta_id().equals(
								Long.toString(propuesta.getId()))) {
							//System.out.println("Coincide propuestaid");
	
							isVotada.put(propuesta.getId(), "1");
						}
					}
				}
			}
			//System.out.println(isVotada.get(propuesta.getId()));
		}

		req.getSession().setAttribute("user", user);

		req.getSession().setAttribute("propuestas",	new ArrayList<Reserve>(reserves));
		req.getSession().setAttribute("url", url);
		req.getSession().setAttribute("urlLinktext", urlLinktext);
		req.setAttribute("Map", numVotos);
		req.setAttribute("isVotada", isVotada);

		RequestDispatcher view = req
				.getRequestDispatcher("listPropuestas.jsp");
		view.forward(req, resp);
	}

//	private void alertHTML(PrintWriter out, String message) throws IOException {
//		out.println("<script type=\"text/javascript\">");
//		out.println("alert('" + message + "');");
//		out.println("</script>");
//	}

}