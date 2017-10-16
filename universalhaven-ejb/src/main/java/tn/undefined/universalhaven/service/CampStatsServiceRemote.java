package tn.undefined.universalhaven.service;

import javax.ejb.Remote;

@Remote
public interface CampStatsServiceRemote {
	public int getCampCountPerCountry();
	public int getVacantCampCount();

}
