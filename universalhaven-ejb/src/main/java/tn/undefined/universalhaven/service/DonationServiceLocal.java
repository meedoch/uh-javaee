package tn.undefined.universalhaven.service;

import javax.ejb.Local;

import tn.undefined.universalhaven.entity.Donation;
@Local
public interface DonationServiceLocal {
	public boolean add(Donation donation);
}
