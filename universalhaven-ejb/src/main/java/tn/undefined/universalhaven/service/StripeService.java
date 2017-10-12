package tn.undefined.universalhaven.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stripe.Stripe;
import com.stripe.model.Charge;

@Stateless
public class StripeService implements StripeServiceLocal, StripeServiceRemote{
	
	
	public String pay(String token, int amount, String nomPrenom) {
		Stripe.apiKey="sk_test_wnmKqMazFr951HJfDovuPINB";
		amount =  amount * 100;
		
		// Charge the user's card:
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("amount", amount);
		params.put("currency", "usd");
		params.put("description", "Donation from "+nomPrenom);
		params.put("source", token);

		try {
			Charge charge = Charge.create(params);
			return charge.getId();
		} catch (Exception e) {
			return "";
		}
		
	}
	
	
}
