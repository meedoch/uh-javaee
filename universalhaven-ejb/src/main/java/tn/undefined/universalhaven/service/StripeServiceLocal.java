package tn.undefined.universalhaven.service;

import javax.ejb.Local;

@Local
public interface StripeServiceLocal {
	public boolean pay(String token, int amount, String nomPrenom);
}
