package votos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import votos.model.Voto;
import es.upm.dit.isst.resource.dao.EMFService;
import es.upm.dit.isst.resource.model.Resource;
import es.upm.dit.isst.user.model.AppUser;

public class VotoDAOImpl implements VotoDAO {

	private static VotoDAOImpl instance;

	private VotoDAOImpl() {
	}

	public static VotoDAOImpl getInstance() {
		if (instance == null)
			instance = new VotoDAOImpl();
		return instance;
	}

	// @Override
	// public void add(String googleId, String name, boolean isCandidato) {
	// synchronized (this) {
	// EntityManager em = EMFService.get().createEntityManager();
	// AppUser user = new AppUser(googleId, name, isCandidato);
	// em.persist(user);
	// em.close();
	// }
	// }
	//
	// @Override
	// public void remove(long id) {
	// EntityManager em = EMFService.get().createEntityManager();
	// try {
	// AppUser user = em.find(AppUser.class, id);
	// em.remove(user);
	// } finally {
	// em.close();
	// }
	// }

	@Override
	public void add(String user_id, String propuesta_id) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Voto voto = new Voto(user_id, propuesta_id);
			em.persist(voto);
			em.close();
		}
	}

	@Override
	public void remove(String voto_id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Voto voto = em.find(Voto.class, voto_id);
			em.remove(voto);
		} finally {
			em.close();
		}
	}

}
