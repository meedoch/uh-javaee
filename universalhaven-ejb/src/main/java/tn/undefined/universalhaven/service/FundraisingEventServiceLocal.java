package tn.undefined.universalhaven.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.FundraisingEvent;
@Local
public interface FundraisingEventServiceLocal {
	public double getAverageCompletionDate();
	public Map<String, Long> getEventCountByCountry();
	public List<FundraisingEvent> listActiveEvents();
	public List<FundraisingEvent> listEventsByUser(Long idUser);
	public void startEvent(FundraisingEvent event/*,long idCamp,long idUser*/);
	public void updateEvent(FundraisingEvent event);
	public void disableEvent(FundraisingEvent event);
	public List<FundraisingEventDto> listActiveEventsDto();
	public List<FundraisingEventDto> listEventsByUserDto(Long idUser);
	

}