package tn.undefined.universalhaven.service;

import javax.ejb.Local;

@Local
public interface CampStatsServiceLocal {
	public int getCampCountPerCountry();
	public int getVacantCampCount();
	

}
