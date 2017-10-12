package tn.undefined.universalhaven.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.undefined.universalhaven.entity.Donation;

@Stateless
public class DonationService implements DonationServiceLocal {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean add(Donation donation) {
		try {
			em.persist(donation);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
