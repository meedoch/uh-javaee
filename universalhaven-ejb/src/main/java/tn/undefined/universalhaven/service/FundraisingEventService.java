package tn.undefined.universalhaven.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.undefined.universalhaven.entity.User;
import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.Camp;
import tn.undefined.universalhaven.entity.Donation;
import tn.undefined.universalhaven.entity.FundraisingEvent;
import tn.undefined.universalhaven.entity.Person;
@Stateless
public class FundraisingEventService implements FundraisingEventServiceLocal,FundraisingEventServiceRemote {

	@PersistenceContext
	private EntityManager em;
	@Override
	public double getAverageCompletionDate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Long> getEventCountByCountry() {
		// TODO Auto-generated method stub
//		Query query = em.createQuery("SELECT f.camp.address,Count(f.id) from FundraisingEvent f" + " GROUP BY f.camp.adrress");
		Query query = em.createQuery("SELECT c.address,count(c.id) from FundraisingEvent f join f.camp c" + " GROUP BY c.address ");
		List<Object[]> results = query.getResultList();
		Map<String,Long> resultMap = new HashMap<>();
		for (Object[] result : results) {
			resultMap.put((String) result[0], (Long) (result[1]));
			System.out.println("country: "+(String) result[0]+" number of events: "+(Long) (result[1]));
		}
		return resultMap;
	}

	@Override
	public List<FundraisingEvent> listActiveEvents() {
		List<FundraisingEvent> liste = ((List<FundraisingEvent>) em
				.createQuery("from FundraisingEvent ").getResultList());
		for(int i = 0 ; i< liste.size() ; i++){
			System.out.println(liste.get(i).getId());
		}
		return liste;
//		List<FundraisingEvent> ls=new ArrayList<FundraisingEvent>();
//		TypedQuery<FundraisingEvent> query=em.createQuery("select f from FundraisingEvent f",FundraisingEvent.class);
//		for(FundraisingEvent f:(List<FundraisingEvent>) query.getResultList()){
//			f.setCamp(null);
//			f.setPublisher(null);
//			ls.add(f);
//		}
//		return ls;
	}

	@Override
	public List<FundraisingEvent> listEventsByUser(Long idUser) {
		Query query=em
				.createQuery("select f from FundraisingEvent f where f.publisher.id=:idUser ");
		query.setParameter("idUser", idUser);
		List<FundraisingEvent> l=query.getResultList();
		for(int i = 0 ; i< l.size() ; i++){
			System.out.println(l.get(i).getPublisher().getId());
			System.out.println(l.get(i).getTitle());
		}
		return l;
	}

	@Override
	public void startEvent(FundraisingEvent event/*,long idCamp,long idUser*/){
		/*event=em.find(FundraisingEvent.class, event.getId());
		Camp c=new Camp();
		c=em.find(Camp.class, idCamp);
		User u = new User();
		u=em.find(User.class, idUser);
		//event.setPublisher(u);
		event.setCamp(c);*/
		em.persist(event);
	}

	@Override
	public void updateEvent(FundraisingEvent event) {
		em.merge(event);
		
	}

	@Override
	public void disableEvent(FundraisingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FundraisingEventDto> listActiveEventsDto() {
		List<FundraisingEventDto> listeDto = new ArrayList<>();
		List<FundraisingEvent> liste = ((List<FundraisingEvent>) em
				.createQuery("from FundraisingEvent ").getResultList());
		for(int i = 0 ; i< liste.size() ; i++){
			System.out.println(liste.get(i).getId());
			listeDto.add(new FundraisingEventDto(liste.get(i).getId(), liste.get(i).getTitle(),
					liste.get(i).getDescription(), liste.get(i).getGoal(),liste.get(i).getPublishDate(),
					liste.get(i).getUrgency(), liste.get(i).getFinishingDate(),
					liste.get(i).getImagePath(), liste.get(i).getState(),liste.get(i).getCamp().getId(),liste.get(i).getCamp().getName()));
		}
		
		return listeDto;
	}

	@Override
	public List<FundraisingEventDto> listEventsByUserDto(Long idUser) {
		List<FundraisingEvent> liste= listEventsByUser(idUser);
		List<FundraisingEventDto> l=new ArrayList<FundraisingEventDto>();
		for(FundraisingEvent f:liste){
			l.add(new FundraisingEventDto(f.getId(),f.getTitle(),
					f.getDescription(),f.getGoal(),f.getPublishDate(),
					f.getUrgency(),f.getFinishingDate(),
					f.getImagePath(),f.getState(),f.getCamp().getId(),f.getCamp().getName()));
		}
		return l;
	}

}