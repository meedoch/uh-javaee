package tn.undefined.universalhaven.buisness;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.undefined.universalhaven.entity.ApplicationForm;
import tn.undefined.universalhaven.entity.Attachment;
import tn.undefined.universalhaven.entity.User;
import tn.undefined.universalhaven.buisness.ApplicationFormServiceLocal;

@Stateless
public class ApplicationFormService implements ApplicationFormServiceLocal {

	@PersistenceContext
	EntityManager em;

	@Override
	public int apply(ApplicationForm app) {
		try {
			em.persist(app);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public List<ApplicationForm> listApplication() {

		TypedQuery<ApplicationForm> query = em.createQuery("select a from ApplicationForm a", ApplicationForm.class);
		return query.getResultList();
	}

	@Override
	public List<ApplicationForm> listApplicationPerCountry(String country) {
		TypedQuery<ApplicationForm> query = em.createQuery("select a from ApplicationForm a where country like :c",
				ApplicationForm.class);
		query.setParameter("c", country);
		return query.getResultList();
	}

	@Override
	public List<ApplicationForm> listApplicationPerGender(String gender) {
		TypedQuery<ApplicationForm> query = em.createQuery("select a from ApplicationForm a where gender like :c",
				ApplicationForm.class);
		query.setParameter("c", gender);
		return query.getResultList();
	}

	@Override
	public int reviewApplication(ApplicationForm application, boolean review, long revieww) {

		User u = em.find(User.class, revieww);

		application.setReviewer(u);
		application.setAccepted(review);
		em.merge(application);

		return 1;
	}

	@Override
	public int addAttachment(int application, String name) {
		ApplicationForm applications = em.find(ApplicationForm.class, application);
		Attachment a = new Attachment();
		em.persist(a);
		a.setApplicationFrom(applications);
		a.setName(name);
		return 1;
	}

}
