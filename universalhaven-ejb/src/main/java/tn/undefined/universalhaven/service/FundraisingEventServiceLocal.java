package tn.undefined.universalhaven.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.FundraisingEvent;
import tn.undefined.universalhaven.entity.User;
@Local
public interface FundraisingEventServiceLocal {
	public Map<String, Double> getAverageCompletionDate();
	public Map<String, Long> getEventCountByCountry();
	public List<FundraisingEvent> listActiveEvents();
	public List<FundraisingEvent> listEventsByUser(User user);
	public void startEvent(FundraisingEvent event/*,long idCamp,long idUser*/);
	public void updateEvent(FundraisingEvent event);
	public void changeEventState(FundraisingEvent event);
	public List<FundraisingEventDto> listActiveEventsDto();
	public List<FundraisingEventDto> listEventsByUserDto(User user);
	public double getSumAmountByEvent(FundraisingEvent event);
	

}
