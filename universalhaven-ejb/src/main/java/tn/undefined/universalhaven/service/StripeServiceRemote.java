package tn.undefined.universalhaven.service;

import javax.ejb.Remote;

@Remote
public interface StripeServiceRemote {
	public String pay(String token, int amount, String nomPrenom) ;
}
