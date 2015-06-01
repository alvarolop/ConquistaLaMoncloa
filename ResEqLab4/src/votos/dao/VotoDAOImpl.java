package votos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import votos.model.Voto;
import es.upm.dit.isst.reserve.model.Reserve;
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
	public boolean add(String user_id, String propuesta_id) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Voto t ");
		System.out.println(q.getResultList());
		// q.setParameter("userId", userId);
		List<Voto> votos = q.getResultList();
		em.close();

		synchronized (this) {
			EntityManager am = EMFService.get().createEntityManager();
			Voto voto = new Voto(user_id, propuesta_id);
			for (Voto voto2 : votos)
				if (voto.getUser_id().equals(voto2.getUser_id())
						&& voto.getPropuesta_id().equals(
								voto2.getPropuesta_id()))
					return false;
			am.persist(voto);
			am.close();
		}

		return true;
	}

	@Override
	public void remove(long voto_id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Voto voto = em.find(Voto.class, voto_id);
			em.remove(voto);
		} finally {
			em.close();
		}
	}

	@Override
	public int numVotoPropuesta(String propuesta_id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Voto t ");
		System.out.println(q.getResultList());
		List<Voto> votos = q.getResultList();
		int cuenta = 0;
		for (Voto voto : votos)
			if (voto.getPropuesta_id().equals(propuesta_id))
				cuenta++;
		return cuenta;
	}

	@Override
	public String getVotoId(String user_id, String propuesta_id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Voto t ");
		System.out.println(q.getResultList());
		List<Voto> votos = q.getResultList();
		for (Voto voto : votos) {
			if (voto.getPropuesta_id().equals(propuesta_id)
					&& voto.getUser_id().equals(user_id)) {
				return Long.toString(voto.get_id());
			}
		}
		return null;
	}

	@Override
	public List<Voto> getVotos() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Voto t ");
		List<Voto> votos = q.getResultList();
		return votos;
	}
}
