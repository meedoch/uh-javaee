package tn.undefined.universalhaven.buisness;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.undefined.universalhaven.entity.ResourcesHistory;
import tn.undefined.universalhaven.entity.User;

@Stateless
public class ResourcesHistoryService implements ResourcesHistoryServiceLocal {
	@PersistenceContext(name = "universalhaven-ejb")
	private EntityManager em;

	@Override
	public Boolean addResourcesHistory(ResourcesHistory resWithdrawalHistory, long userId) {
		try {
			User user = em.find(User.class, userId);
			if (user==null){
				throw new Exception();
			}
			resWithdrawalHistory.setUser(user);
			em.persist(resWithdrawalHistory);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public List<ResourcesHistory> getResourcesHistory() {
		return em.createQuery("SELECT resHis FROM ResourcesHistory resHis", ResourcesHistory.class).getResultList();
	}

	@Override
	public Map<String, Double> getDepositedResourcesHistory() {
		// TODO Auto-generated method stub
		return null;
	}

}
