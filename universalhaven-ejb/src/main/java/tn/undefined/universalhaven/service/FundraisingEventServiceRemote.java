package tn.undefined.universalhaven.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.FundraisingEvent;
import tn.undefined.universalhaven.entity.User;
@Remote
public interface FundraisingEventServiceRemote {
	public Map<String, Double> getAverageCompletionDate();
	public Map<String, Long> getEventCountByCountry();
	public List<FundraisingEvent> listActiveEvents();
	public List<FundraisingEvent> listEventsByUser(User user);
	public void startEvent(FundraisingEvent event/*,long idCamp,long idUser*/);
	public void updateEvent(FundraisingEvent event);
	public void disableEvent(FundraisingEvent event);
	public List<FundraisingEventDto> listActiveEventsDto();
	public List<FundraisingEventDto> listEventsByUserDto(User user);

}
