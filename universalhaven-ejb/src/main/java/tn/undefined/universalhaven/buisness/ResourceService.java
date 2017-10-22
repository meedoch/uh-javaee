package tn.undefined.universalhaven.buisness;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.entity.Resource;

@Stateless
public class ResourceService implements ResourceServiceLocal {
	@PersistenceContext(name = "universalhaven-ejb")
	private EntityManager em;

	@Override
	public boolean addResource(Resource resource) {

		em.persist(resource);

		return true;
	}

	@Override
	public boolean addResourceToCamp(Resource resource, int campId) {
		try {
			Camp camp = em.find(Camp.class, campId);
			resource.setCamp(camp);
			em.persist(resource);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Boolean depositResource(Resource resource) {
		try {
			em.merge(resource);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Set<Resource> getCampResources(long idCamp) {
		Camp camp = em.find(Camp.class, idCamp);
		return camp.getResources();
	}

	@Override
	public boolean withdrawResource(Resource resource, Double qte) {
		try {
			em.find(Resource.class, resource.getId());
			resource.setQuantity(resource.getQuantity() - qte);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public Boolean removeResource(Resource resource) {
		try {
			Resource res = em.find(Resource.class, resource.getId());
			em.remove(res);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
