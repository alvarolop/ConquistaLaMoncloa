package es.upm.dit.isst.reserve.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.users.User;

import es.upm.dit.isst.reserve.model.Reserve;
import es.upm.dit.isst.resource.model.Resource;

public class ReserveDAOImpl implements ReserveDAO {

	private static ReserveDAOImpl instance;

	private ReserveDAOImpl() {
	}

	public static ReserveDAOImpl getInstance() {
		if (instance == null)
			instance = new ReserveDAOImpl();
		return instance;
	}

	@Override
	public List<Reserve> listPropuestas() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from Reserve m");
		List<Reserve> propuestas = q.getResultList();
		return propuestas;
	}

	@Override
	public long add(String title, String description) {
		synchronized (this) {
			long propuestaid;

			EntityManager em = EMFService.get().createEntityManager();

			Reserve propuesta = new Reserve(title, description);
			em.persist(propuesta);
			em.close();
			propuestaid = propuesta.getId();

			System.out.println(propuestaid);
			return propuestaid;
		}

	}

	@Override
	public List<Reserve> getPropuestas() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Reserve t ");
		System.out.println(q.getResultList());
		// q.setParameter("userId", userId);
		List<Reserve> propuestas = q.getResultList();
		return propuestas;
	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Reserve propuesta = em.find(Reserve.class, id);
			em.remove(propuesta);
		} finally {
			em.close();
		}
	}

	// @Override
	// public List<Propuesta> getPropuestas(String userId) {
	//
	// EntityManager em = EMFService.get().createEntityManager();
	// Query q = em
	// .createQuery("select t from Propuesta t where t.user = :userId");
	// System.out.println(q);
	// q.setParameter("userId", userId);
	// List<Propuesta> propuestas = q.getResultList();
	// return propuestas;
	// }

	@Override
	public Reserve getPropuesta(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Reserve propuesta = em.find(Reserve.class, id);
			return propuesta;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(long id, String title, String description) {
		EntityManager em = EMFService.get().createEntityManager();
		Reserve newPropuesta = null;
		try {
			Reserve propuesta = em.find(Reserve.class, id);
			String title1 = propuesta.getTitle();
			String description1 = propuesta.getDescription();
			em.remove(propuesta);
			newPropuesta = new Reserve(title1, description1);

		} finally {
			em.close();
			EntityManager am = EMFService.get().createEntityManager();
			am.persist(newPropuesta);
			am.close();

		}
	}

	// @Override
	// public int[][] mapCheck(Programa[][] resourceMap, Propuesta hora) {
	// int[][] mapBoolean = new int[resourceMap[0].length][resourceMap.length];
	// for (int i = 0; i < resourceMap.length; i++) {
	// for (int j = 0; j < resourceMap[0].length; j++) {
	// if (resourceMap[i][j] == null) {
	// // System.out.println(mapBoolean[i][j]);
	// mapBoolean[i][j] = 0;
	//
	// } else {
	// System.out.println("i " + i + "j " + j);
	// mapBoolean[i][j] = 1;
	// for (long reserveid : resourceMap[i][j].getPropuestas()) {
	// // System.out.println(reserveid);
	//
	// Propuesta reserva = this.getReserve(reserveid);
	// // System.out.println(reserva);
	//
	// if (hora.ocupado(reserva)) {
	// mapBoolean[i][j] = 2;
	// }
	// // mapBoolean[i][j] = hora.ocupado(reserva);
	// // System.out.println(mapBoolean[i][j]);
	// }
	// }
	//
	// }
	// }
	//
	// return mapBoolean;
	// }

}
