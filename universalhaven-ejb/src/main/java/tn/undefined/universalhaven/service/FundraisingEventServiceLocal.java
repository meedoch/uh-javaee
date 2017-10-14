package tn.undefined.universalhaven.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import tn.undefined.universalhaven.dto.FundraisingEventDto;
import tn.undefined.universalhaven.entity.FundraisingEvent;
@Local
public interface FundraisingEventServiceLocal {
	public double getAverageCompetitionDate();
	public int getEventCountByCountry(String country);
	public List<FundraisingEvent> listActiveEvents();
	public List<FundraisingEvent> listEventsByUser();
	public void startEvent(FundraisingEvent event/*,long idCamp,long idUser*/);
	public void updateEvent(FundraisingEvent event);
	public void disableEvent(FundraisingEvent event);
	public List<FundraisingEventDto> listActiveEventsDto();
	

}
