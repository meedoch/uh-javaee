package tn.undefined.universalhaven.service;

import javax.ejb.Stateless;

@Stateless

public class CampStatsService implements CampStatsServiceLocal,CampStatsServiceRemote {

	@Override
	public int getCampCountPerCountry() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVacantCampCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
