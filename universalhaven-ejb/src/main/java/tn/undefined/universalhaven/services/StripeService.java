package tn.undefined.universalhaven.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;


import com.stripe.net.RequestOptions;

public class StripeService {
	
	
	public static String pay(String token, int amount, String nomPrenom) {
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
			
		} catch (AuthenticationException e) {
			return "Authenitification Error ! ";
			//e.printStackTrace();
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "Invalid Request";
		} catch (APIConnectionException e) {
			return "API connecion failed";
		} catch (CardException e) {
			return "Card exception!";
		} catch (APIException e) {
			return "Api exception ! ";
		}
		return "Success ! ";
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(pay("tok_visa",5,"Ben Hemdene Mehdi"));
		
	

	}
}
