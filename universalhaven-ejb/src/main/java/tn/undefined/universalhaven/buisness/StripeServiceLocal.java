package tn.undefined.universalhaven.buisness;

import javax.ejb.Local;

@Local
public interface StripeServiceLocal {
	public String pay(String token, int amount, String nomPrenom);
}
