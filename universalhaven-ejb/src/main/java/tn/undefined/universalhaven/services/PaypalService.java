package tn.undefined.universalhaven.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaypalService {
	
	
	final static String clientId = 
			"ASGETJUOmYvjixZl9AULkdhwcNlnVVOZcXSqz5B1J7aB3R_ifuGq3wovxR7qZ7WD2uM6BD3g3_z16-d-";
	final static String clientSecret = 
			"EGiAGWgUytYdW5LKy7iXWn9u5oBXyzLVLvT2XP12w6K_2NH3cMCeQiHXpIL7ZH62i1DqE4p238hEf9pr";
	
	public static void main(String[] args) {
		/*System.out.println(pay("500", 
				"visa", 
				"4032038218713117", 
				11, 
				2022, 
				"012", 
				"Ben HEmdene", 
				"Mehdi")
		);*/
		
	}
	
	
	public static String pay (String total, String creditCardType, String creditCardNumber
			, int expireMonth, int expireYear, String cvv2, String firstName, String lastName) {
		CreditCard card = new CreditCard()
			    .setType(creditCardType)
			    .setNumber(creditCardNumber)
			    .setExpireMonth(expireMonth)
			    .setExpireYear(expireYear)
			    .setCvv2(cvv2)
			    .setFirstName(firstName)
			    .setLastName(lastName);
		
		Payment createdPayment = null;
		
		
	
		
		Details details = new Details();
		details.setShipping("0");
		details.setSubtotal(total);
		details.setTax("0");

		// ###Amount
		// Let's you specify a payment amount.
		Amount amount = new Amount();
		amount.setCurrency("USD");
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal(total);
		amount.setDetails(details);

		// ###Transaction
		// A transaction defines the contract of a
		// payment - what is the payment for and who
		// is fulfilling it. Transaction is created with
		// a `Payee` and `Amount` types
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction
				.setDescription("This is the payment transaction description.");

		// ### Items
		Item item = new Item();
		item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice(total);
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		itemList.setItems(items);
		
		transaction.setItemList(itemList);
		
		
		// The Payment creation API requires a list of
		// Transaction; add the created `Transaction`
		// to a List
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod("credit_card");
		
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		
		payment.setTransactions(transactions);
		// Create a Credit Card
		
		FundingInstrument instrument = new FundingInstrument();
		instrument.setCreditCard(card);
		List<FundingInstrument> instruments=  new ArrayList<>();
		instruments.add(instrument);
		payer.setFundingInstruments(instruments);
		try {
		    APIContext context = new APIContext(clientId, clientSecret, "sandbox");
		    card.create(context);
		    createdPayment = payment.create(context);
		    
			return ("Created payment with id = "
					+ createdPayment.getId() + " and status = "
					+ createdPayment.getState());
			
		} catch (PayPalRESTException e) {
		    System.err.println(e.getDetails());
		    return "An error has occured..";
		}
	}
}
