package tn.undefined.universalhaven.service;

import javax.ejb.Remote;

@Remote
public interface PaypalServiceRemote {
	public boolean pay (String total, String creditCardType, String creditCardNumber
			, int expireMonth, int expireYear, String cvv2, String firstName, String lastName);
}
