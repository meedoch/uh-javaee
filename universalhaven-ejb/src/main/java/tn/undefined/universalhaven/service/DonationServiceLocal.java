package tn.undefined.universalhaven.service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.ejb.Local;

import tn.undefined.universalhaven.entity.Donation;
@Local
public interface DonationServiceLocal {
	public boolean add(Donation donation);
	public Collection<Donation> getAll();
	public Map<String,Double> getDonationsByCountry();
	public double getDonationsThisMonth();
	public double getDonationsThisYear();
	public double getDonationsToday();
	public Map<String,Double> getDonationsByDay();
	public Map<String,Double> getDonationsByPaymentMethod();
	public Double getAverage();
	public Map<String,Double> getCountriesDonationAverage();
}
