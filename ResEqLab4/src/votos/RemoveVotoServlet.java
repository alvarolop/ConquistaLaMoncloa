package votos;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import votos.dao.VotoDAO;
import votos.dao.VotoDAOImpl;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.reserve.dao.ReserveDAO;
import es.upm.dit.isst.reserve.dao.ReserveDAOImpl;
import es.upm.dit.isst.resource.dao.ResourceDAO;
import es.upm.dit.isst.resource.dao.ResourceDAOImpl;

public class RemoveVotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		// /////COMPROBACION ADMIN///////////
		UserService userService = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = userService.getCurrentUser();

		String propuesta_id = req.getParameter("propuesta_id");
		VotoDAO votodao = VotoDAOImpl.getInstance();
		String user_id = user.getUserId();

		String voto_id = votodao.getVotoId(user_id, propuesta_id);
		if (voto_id != null)
			votodao.remove(Long.parseLong(voto_id));
		// alertHTML(out, "Reserva eliminada!!");

		out.println("<script>location='/listPropuestas';</script>");

	}
}
