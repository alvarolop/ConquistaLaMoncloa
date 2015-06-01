package es.upm.dit.isst.resource.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.reserve.dao.EMFService;
import es.upm.dit.isst.resource.model.Resource;

public class ResourceDAOImpl implements ResourceDAO {

	private static ResourceDAOImpl instance;

	private ResourceDAOImpl() {
	}

	public static ResourceDAOImpl getInstance() {
		if (instance == null)
			instance = new ResourceDAOImpl();
		return instance;
	}

	@Override
	public List<Resource> listResources() {
		EntityManager em = EMFService.get().createEntityManager();
		// read the existing entries
		Query q = em.createQuery("select m from Resources m");
		List<Resource> resources = q.getResultList();
		return resources;
	}
	@Override
	public List<Resource> getResources() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Resource t ");
		System.out.println(q.getResultList());
		// q.setParameter("userId", userId);
		List<Resource> resources = q.getResultList();
		return resources;
	}

	@Override
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Resource resource = em.find(Resource.class, id);
			em.remove(resource);
		} finally {
			em.close();
		}
	}

	@Override
	public void addReserve(long reserveid, String user, long resourceid) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Resource resource = em.find(Resource.class, resourceid);
			System.out.println(reserveid);
			em.merge(resource);
			System.out.println("Reservo");
		} finally {
			em.close();
			System.out.println("LlegueFinally :)" + reserveid);
		}
	}

	@Override
	public Resource getResource(long resourceId) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Resource resource = em.find(Resource.class, resourceId);
			return resource;
		} finally {
			em.close();
		}
	}

	@Override
	public void modifyResource(long resourceId, String title,
			String description, int sessionTime, boolean available) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Resource resource = em.find(Resource.class, resourceId);
			em.merge(resource);
		} finally {
			em.close();
		}
	}

	@Override
	public boolean proDisp(String startDate, String endDate, Resource resource) {
		return false;
	}

	@Override
	public void removeReserve(String reserveId, String resourceId) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Resource resource = em.find(Resource.class,
					Long.parseLong(resourceId));
			// resource.removeReserve(reserveId);
			em.merge(resource);
		} finally {
			em.close();
		}

	}

	// @Override
	// public void add(String title, String description, int sessionTime) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public void add(String title, String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(long parseLong) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Resource resource = new Resource(parseLong);
			em.persist(resource);
			em.close();
		}
	}

	@Override
	public long add(Long id, String titulo, String img_url) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Resource resource = new Resource(id, titulo, img_url);
			em.persist(resource);
			em.close();
			long programa_id = resource.getId();

			return programa_id;
		}
	}

	@Override
	public void addPropuesta(long propuesta_id, String programa_id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Resource programa = em.find(Resource.class,
					Long.parseLong(programa_id));

			programa.addPropuesta(propuesta_id);
			em.merge(programa);

		} finally {
			em.close();

		}
	}
}
