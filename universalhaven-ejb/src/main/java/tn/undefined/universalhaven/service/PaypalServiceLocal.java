package tn.undefined.universalhaven.service;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface PaypalServiceLocal {
	public boolean pay (String total, String creditCardType, String creditCardNumber
			, int expireMonth, int expireYear, String cvv2, String firstName, String lastName);
}
