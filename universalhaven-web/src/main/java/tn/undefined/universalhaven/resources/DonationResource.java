package tn.undefined.universalhaven.resources;

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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.undefined.universalhaven.jwt.JWTTokenNeeded;
import tn.undefined.universalhaven.entity.Donation;
import tn.undefined.universalhaven.enumerations.UserRole;
import tn.undefined.universalhaven.buisness.DonationServiceLocal;
import tn.undefined.universalhaven.buisness.PaypalServiceRemote;
import tn.undefined.universalhaven.buisness.StripeServiceRemote;
import tn.undefined.universalhaven.util.DonationParam;

@Path("donation")
@RequestScoped
public class DonationResource {

	@EJB
	PaypalServiceRemote buisnessPaypal;

	@EJB
	StripeServiceRemote buisnessStripe;

	@EJB
	DonationServiceLocal buisnessDonation;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response getAll() {
		Collection<Donation> donations = buisnessDonation.getAll();
		if ((donations==null) || (donations.isEmpty())) {
			return Response.status(Response.Status.NO_CONTENT).entity("No data found").build();
		}
		return Response.status(Status.FOUND).entity(buisnessDonation.getAll()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response donate(DonationParam param) {
		System.out.println("Donating !! ");
		if ((param.getMethod()==null) || (param.getMethod().equals("")) ){
			return Response.status(Response.Status.PAYMENT_REQUIRED).entity("Payment method is required").build();
		}
		
		if (param.getMethod().equals("paypal")) {
			System.out.println(String.valueOf(param.getDonation().getAmount()));
			String reference = buisnessPaypal.pay(String.valueOf(param.getDonation().getAmount())
					, param.getCreditCardType(), param.getCreditCardNumber()
					, param.getExpireMonth(),
					param.getExpireYear(), param.getCvv2(), param.getDonation().getContributorName(), param.getDonation().getContributorName());
			if (reference.equals("") == false) {
				param.getDonation().setPaymentMethod("paypal");
				param.getDonation().setTransactionReference(reference);
				buisnessDonation.add(param.getDonation());
				return ResponseUtil.buildOk("Paypal payment successful and donation persisted");			
						
			} else {
				return ResponseUtil.buildError("Paypal payment failed");
			}
		} else {
			Double amountDouble = param.getDonation().getAmount();
			int amountInt = amountDouble.intValue();
			String reference = buisnessStripe.pay(param.getToken(), amountInt, param.getDonation().getContributorName());
			if (reference.equals("") == false) {
				param.getDonation().setPaymentMethod("stripe");
				param.getDonation().setTransactionReference(reference);
				buisnessDonation.add(param.getDonation());
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
		return ResponseUtil.buildOk(buisnessDonation.getDonationsByCountry()); 
	}
	
	@GET
	@Path("/bycountry/average")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response countriesDonationAverage(){
		return ResponseUtil.buildOk(buisnessDonation.getCountriesDonationAverage());
	}
	
	
	
	@GET
	@Path("bydate/overall")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response overAllDonationsByDate(){
		Map<String, Double> stats=  new HashMap<>();
		stats.put("thismonth", buisnessDonation.getDonationsThisMonth());
		stats.put("thisyear", buisnessDonation.getDonationsThisYear());
		stats.put("today", buisnessDonation.getDonationsToday());
		return ResponseUtil.buildOk(stats); 
	}
	
	@GET
	@Path("bydate")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response donationsPerDay(){
		return ResponseUtil.buildOk(buisnessDonation.getDonationsByDay());
	}
	
	
	@GET
	@Path("bymethod")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response donationsByMethod(){
		return ResponseUtil.buildOk(buisnessDonation.getDonationsByPaymentMethod());
	}
	
	
	@GET
	@Path("average")
	@Produces(MediaType.APPLICATION_JSON)
	@JWTTokenNeeded(role=UserRole.ICRC_MANAGER)
	public Response averageDonation(){
		return ResponseUtil.buildOk(buisnessDonation.getAverage());
	}
	
	
	
	

}
