package tn.undefined.universalhaven.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jwt.JWTTokenNeeded;
import tn.undefined.universalhaven.entity.Donation;
import tn.undefined.universalhaven.enumerations.UserRole;
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
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Collection<Donation> getAll() {
		return serviceDonation.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response donate(Donation donation, @QueryParam(value = "method") String method,
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
				return ResponseUtil.buildOk("Paypal payment successful and donation persisted");			
						
			} else {
				return ResponseUtil.buildError("Paypal payment failed");
			}
		} else {
			Double amountDouble = donation.getAmount();
			int amountInt = amountDouble.intValue();
			String reference = serviceStripe.pay(token, amountInt, donation.getContributorName());
			if (reference.equals("") == false) {
				donation.setPaymentMethod("stripe");
				donation.setTransactionReference(reference);
				serviceDonation.add(donation);
				return ResponseUtil.buildOk("Stripe payment successful and donation persisted");
			}
			return ResponseUtil.buildError("Stripe payment failed");
		}
	}
	
	
	@GET
	@Path("bycountry")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response donationsPerCountry(){
		return ResponseUtil.buildOk(serviceDonation.getDonationsByCountry()); 
	}
	
	@GET
	@Path("/bycountry/average")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response countriesDonationAverage(){
		return ResponseUtil.buildOk(serviceDonation.getCountriesDonationAverage());
	}
	
	
	
	@GET
	@Path("bydate/overall")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response overAllDonationsByDate(){
		Map<String, Double> stats=  new HashMap<>();
		stats.put("thismonth", serviceDonation.getDonationsThisMonth());
		stats.put("thisyear", serviceDonation.getDonationsThisYear());
		stats.put("today", serviceDonation.getDonationsToday());
		return ResponseUtil.buildOk(stats); 
	}
	
	@GET
	@Path("bydate")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response donationsPerDay(){
		return ResponseUtil.buildOk(serviceDonation.getDonationsByDay());
	}
	
	
	@GET
	@Path("bymethod")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response donationsByMethod(){
		return ResponseUtil.buildOk(serviceDonation.getDonationsByPaymentMethod());
	}
	
	
	@GET
	@Path("average")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response averageDonation(){
		return ResponseUtil.buildOk(serviceDonation.getAverage());
	}
	
	
	
	

}
