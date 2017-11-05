package tn.undefined.universalhaven.buisness;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.FundraisingEvent;
import tn.undefined.universalhaven.entity.User;
import tn.undefined.universalhaven.enumerations.Urgency;
@Local
public interface FundraisingEventServiceLocal {
	public Map<String, Double> getAverageCompletionDate();
	public Map<String, Long> getEventCountByCountry();
	public Map<String, Long> getCountEventByMonth(int year);
	public List<FundraisingEvent> listActiveEvents();
	public List<FundraisingEvent> listEventsByUser(User user);
	public boolean startEvent(FundraisingEvent event);
	public boolean updateEvent(FundraisingEvent event);
	public void changeEventState(FundraisingEvent event);
	public List<FundraisingEventDto> listActiveEventsDto();
	public List<FundraisingEventDto> listEventsByUserDto(User user);
	public List<FundraisingEventDto> listEventsByState(String state);
	public List<FundraisingEventDto> listEventsByUrgency(Urgency urgency);
	public List<FundraisingEventDto> listEventsByYearAndMonth(int  month,int year);
	public double getSumAmountByEvent(FundraisingEvent event);
	

}
