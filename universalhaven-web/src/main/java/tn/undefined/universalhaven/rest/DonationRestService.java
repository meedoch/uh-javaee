package tn.undefined.universalhaven.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tn.undefined.universalhaven.entity.Donation;
import tn.undefined.universalhaven.service.DonationServiceLocal;
import tn.undefined.universalhaven.service.PaypalServiceRemote;
import tn.undefined.universalhaven.service.StripeServiceRemote;

@Path("donation")
@RequestScoped
public class DonationRestService {

	@EJB
	PaypalServiceRemote servicePaypal;

	@EJB
	StripeServiceRemote serviceStripe;

	@EJB
	DonationServiceLocal serviceDonation;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Donation> getAll() {
		return new ArrayList<Donation>();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)

	public String add(Donation donation, @QueryParam(value = "method") String method,
			@QueryParam(value = "token") String token, @QueryParam(value = "creditCardType") String creditCardType,
			@QueryParam(value = "creditCardNumber") String creditCardNumber,
			@QueryParam(value = "expireMonth") int expireMonth, @QueryParam(value = "expireYear") int expireYear,
			@QueryParam(value = "cvv2") String cvv2) {
		if (method.equals("paypal")) {
			System.out.println(String.valueOf(donation.getAmount()));
			String reference = servicePaypal.pay(String.valueOf(donation.getAmount()), creditCardType, creditCardNumber, expireMonth,
					expireYear, cvv2, donation.getContributorName(), donation.getContributorName());
			if (reference.equals("") == false) {
				donation.setPaymentMethod("paypal");
				donation.setTransactionReference(reference);
				serviceDonation.add(donation);
				return "Paypal payment successful and donation persisted";
			} else {
				return "Paypal payment failed";
			}
		} else {
			Double amountDouble = donation.getAmount();
			int amountInt = amountDouble.intValue();
			String reference = serviceStripe.pay(token, amountInt, donation.getContributorName());
			if (reference.equals("") == false) {
				donation.setPaymentMethod("stripe");
				donation.setTransactionReference(reference);
				serviceDonation.add(donation);
				return "Stripe payment successful and donation persisted";
			}
			return "Stripe payment failed";
		}
	}

}
