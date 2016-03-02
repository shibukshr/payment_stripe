package src.com.payment;

import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
//import com.medpanel.persistence.model.User;
//import com.medpanel.util.PaymentConstants;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
//import urn.ebay.api.PayPalAPI.DoDirectPaymentReq;
//import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;
public class StripePOCImpl {
	// extends AbstractHibernateDao


	
	 private static final String stripeApiKey = "sk_test_nJuUbk18ljWm8IseXIS0mXzK";
	 private static final String stripeKeyVersion = "2015-01-26";

	 public static void main(String[] args) {
		 StripePOCImpl test = new StripePOCImpl();
		 test.makePayment("token");
		}


	/**
	 * This method for send payments to the clients
	 * for corresponding payment request.
	 * @return 
	 */
	public void makePayment( String tokenKey)	 {

		String descriptionStatus = "Failed";
		System.out.println("...!Stripe payments  Started :");
		
		try {
//			DoDirectPaymentReq test = new DoDirectPaymentReq();
//			new PayPalAPIInterfaceServiceService();
//			tt.setAccessPermissions(setAccessPermissionsReq)
			RequestOptions options = loadStripePayment(tokenKey);
			
			
			
			
			Map<String, Object> customerParams = new HashMap<String, Object>();
			customerParams.put("description", "Customer for test@example.com");
//			customerParams.put("source", "tok_16Ho1E2eZvKYlo2CsqGq9vK6"); // obtained with Stripe.js
			customerParams.put("email", "test@plexuus.com");
			Customer customer1 = Customer.create(customerParams);
			System.out.println("\n...!Customer  Started :"+customer1);
			System.out.println("\n...!Customer  Id :"+customer1.getId());
			System.out.println("\n...!Customer  maild :"+customer1.getEmail());

			
			/* For retrieve */
			Customer customerNew = Customer.retrieve(customer1.getId());
			System.out.println("\n...!Customer  Started :"+customerNew);
			System.out.println("\n...!Customer  Id :"+customerNew.getId());
			System.out.println("\n...!Customer  maild :"+customerNew.getEmail());
			


			/*
			 * For create card
			 * */
			Customer cu = Customer.retrieve(customer1.getId());
			Map<String, Object> cardMap3 = new HashMap<String, Object>();
			final Map<String, Object> sourceCardDetail = getCard();
			cardMap3.put("source", sourceCardDetail);
			Card newCard = cu.createCard(cardMap3);	
			
			Map<String, Object> cardMap2 = new HashMap<String, Object>();
			final Map<String, Object> sourceCardDetail2 = getCard();
			cardMap2.put("source", sourceCardDetail2);
			Card newCard2 = cu.createCard(cardMap2);	
			System.out.println("\n...!Card  Id :"+newCard.getId());
			
			System.out.println("\n...!Card  Id :"+newCard2.getId());
			
			

			Customer cuss = Customer.retrieve(customer1.getId());
//			Card ccc = cuss.getCards().getData().get(0);
			String defaultcc = cuss.getDefaultCard();
			System.out.println("\n...!Card  default :"+defaultcc);
			
			
			cuss.setDefaultCard(newCard2.getId());
			String defaultcc1 = cuss.getDefaultCard();
			System.out.println("\n...!Card  default :"+defaultcc1);

			
			Customer cuupdate = Customer.retrieve(customer1.getId());
			Map<String, Object> updateParams = new HashMap<String, Object>();
			updateParams.put("description", "card changed");
			updateParams.put("default_source", defaultcc1);
			cu.update(updateParams);
			//			Customer cu = Customer.retrieve("cus_6VjWCPpYc3NwDU");
////			PaymentSource source = cu.getSources().retrieve("card_16IjDLFygNv2Qex7iendLLTS");
//			System.out.println("\n...!Card  Started :"+cu.getDefaultCard());
////			if (source.getObject().equals("card")) {
////			 Card newCard = (Card) source;
////			  // use the card!
////				System.out.println("\n...!Card  Started :"+newCard);
////				System.out.println("\n...!Card  Id :"+newCard.getId());
////				System.out.println("\n...!Card  maild :"+newCard.getBrand());
////				System.out.println("\n...!Card  maild :"+newCard.getLast4());
////			}
//			
////			Customer cu = Customer.retrieve("cus_6VjWCPpYc3NwDU");
//			PaymentSource source2=cu.getCards().retrieve("card_16IjFEFygNv2Qex77ipB1kDX");
////			PaymentSource source2 = cu.getSources().retrieve("card_16IjFEFygNv2Qex77ipB1kDX");
//			if (source2.getObject().equals("card")) {
//				Card newCard1  = (Card) source2;
//				System.out.println("\n...!Card  Started :"+newCard1);
//				System.out.println("\n...!Card  Id :"+newCard1.getId());
//				System.out.println("\n...!Card  maild :"+newCard1.getBrand());
//				System.out.println("\n...!Card  maild :"+newCard1.getLast4());
//				
//							
//			}
//			===========================
//			final Map<String, Object> stripeChargeMap = getStripeChargeMap(paymentTransaction);
//			final Map<String, Object> stripeChargeMap = new HashMap<String, Object>();
//			final Charge stripeCharge = Charge.create(stripeChargeMap, options);
			
			
			/*Map<String, Object> customerParams = new HashMap<String, Object>();
			customerParams.put("description", "Customer for test@example.com");
			customerParams.put("email", "test@plexuus.com");
			Customer customer1 = Customer.create(customerParams);
			System.out.println("\n...!Customer  Started :"+customer1);
			 */
			
			/* For retrieve 
			 * Customer customer1 = Customer.retrieve("cus_6VjWCPpYc3NwDU");
			System.out.println("\n...!Customer  Started :"+customer1);
			System.out.println("\n...!Customer  Id :"+customer1.getId());
			System.out.println("\n...!Customer  maild :"+customer1.getEmail());
			*/

			 // For create card
//			User user = sessionManager.getLoggedInUser();
//			creditCard.setEmail(user.getEmail());
//			creditCard.setIsActive(true);
//			final Map<String, Object> cardMap = new HashMap<String, Object>();
//			final Map<String, Object> sourceCardDetail = getCreditCard(creditCard);
			
			/*Customer cu = Customer.retrieve("cus_6VjWCPpYc3NwDU"); //Default Customer
			cardMap.put("source", sourceCardDetail);  for extra card creation
			Card newCard = cu.createCard(cardMap);
			System.out.println("\n...!Card  Last4 :"+newCard.getLast4());*/
			
			/*String sEmail = user.getEmail();
			String description = "Owner: "+user.getUserName()+":"+user.getLastName();
			Map<String, Object> customerParams = new HashMap<String, Object>();
			customerParams.put("description", description);
			customerParams.put("email", sEmail);
			customerParams.put("card", sourceCardDetail);
			Customer newCustomer = Customer.create(customerParams);
			String stripeCardKey = newCustomer.getId();
			Card newCard = newCustomer.getCards().getData().get(0);*/
//			cardid = $customer->default_card;
//			$card = $customer->cards->retrieve($cardid);
//			System.out.println("\n...!Customer  Id :"+stripeCardKey);
			/*String cardType =newCard.getBrand();
			creditCard.setStripeKey(stripeCardKey);
			creditCard.setStripeStatus(PaymentConstants.PAY_SUCCESS);
			creditCard.setBrand(cardType);
			descriptionStatus = PaymentConstants.PAY_SUCCESS;*/

			// For Retrieve card
			/*Customer cu = Customer.retrieve("cus_6VjWCPpYc3NwDU");
			System.out.println("\n...!Card  Started :"+cu.getDefaultCard());
			PaymentSource source2 = cu.getSources().retrieve("card_16IjFEFygNv2Qex77ipB1kDX"); 
			PaymentSource source2=cu.getCards().retrieve(stripeCardKey);
			if (source2.getObject().equals("card")) {
				Card newCard1  = (Card) source2;
				System.out.println("\n...!Card  Id :"+newCard1.getId()+"\n...!Card\n");
			}
			*/
			

//			================================
			  
			
			
			
			/*final Map<String, Object> stripeChargeMap = getStripeChargeMap();
			final Charge stripeCharge = Charge.create(stripeChargeMap, options);
			System.out.println(stripeCharge);
			System.out.println("...1:");
			if(stripeCharge.getPaid()){
				descriptionStatus = "Success";
				System.out.println("...!Stripe charge id:"+stripeCharge.getId());
				
			}else{
				System.out.println("...Transaction Failed");
			}*/
			
			// for account creation
			Map<String, Object> defaultRecipientParams = new HashMap<String, Object>();
			Map<String, Object> defaultBankAccountParams = new HashMap<String, Object>();
			defaultBankAccountParams.put("country", "US");
			defaultBankAccountParams.put("routing_number", "110000000");
			defaultBankAccountParams.put("account_number", "000123456789");
				
			defaultRecipientParams.put("name", "J Test");
			defaultRecipientParams.put("type", "individual");
			
//			Account acct = Account.retrieve("acct_16agT9HMq1s6RoWr");
//			Recipient newRecipient = new Recipient();
//			newRecipient.cre
//			com.stripe.model.Account acct = com.stripe.model.Account.retrieve("sk_test_5x3WDbpoX6LWeDNRqWH8GIPL");
//			acct_15UcqZEw6Pes3AD4
			/*com.stripe.model.Account acct = com.stripe.model.Account.retrieve("acct_15UcqZEw6Pes3AD4");
			*/
//			com.stripe.model.Account acct = com.stripe.model.Account.create(params)
//			acct.getExternalAccounts().create(params);
			
			
//			final Map<String, Object> params = new HashMap<String, Object>();
//			final Map<String, Object> bankData = new HashMap<String, Object>();
//			
//			bankData.put("object", "bank_account");
//			bankData.put("country", "US");
//			bankData.put("currency", "usd");
//			bankData.put("routing_number", "110000000");
//			bankData.put("account_number", "000123456789");
//			
////			params.put("external_account", bankData);
//			params.put("bank_account", bankData);
//			Token token = Token.create(params);
//			System.out.println(token);
//			String tokenId = token.getId();
//			System.out.println(acct);
//			acct.getExternalAccounts().create(params);
////		 acct.create(params);
//		System.out.println(acct);
			
			
			// for rec cretion and transfer
			/*Map<String, Object> defaultRecipientParams = new HashMap<String, Object>();
			Map<String, Object> defaultBankAccountParams = new HashMap<String, Object>();
			defaultBankAccountParams.put("country", "US");
			defaultBankAccountParams.put("routing_number", "110000000");
			defaultBankAccountParams.put("account_number", "000123456789");
				
				
			defaultRecipientParams.put("name", "J Test");
			defaultRecipientParams.put("type", "individual");
			defaultRecipientParams.put("tax_id", "000000000");//For type individual, 
			//the full SSN; for type corporation, the full EIN.
			defaultRecipientParams.put("bank_account", defaultBankAccountParams);
			defaultRecipientParams.put("email", "shibu@foundingminds.com");
			defaultRecipientParams.put("description", "Account for Shibu");
//			defaultRecipientParams.put("metadata", "000000000")
			Recipient newRecipient = new Recipient();
			newRecipient = Recipient.create(defaultRecipientParams);
			System.out.println("newRecipient  :"+newRecipient);
			// descriptionStatus = stripeCharge.getInvoice();
			String recipientId = newRecipient.getId();
			System.out.println("recipientId  :"+recipientId);
			////////////////
			Map<String, Object> transferParams = new HashMap<String, Object>();
			transferParams.put("amount", 200);
			transferParams.put("currency", "usd");
			transferParams.put("description", "Transfer for shibu@foundingminds.com");
			transferParams.put("recipient", recipientId);
//			transferParams.put("statement_descriptor", recipientId); //TODO
//			transferParams.put("metadata", "metadata");
//			transferParams.put("source_transaction", recipientId);
			Transfer newTransfer = Transfer.create(transferParams);
			System.out.println("transfer  :"+newTransfer);*/
		} catch (CardException e) {
			// Since it's a decline, CardException will be caught
			System.out.println("Stripe Payment:: CardException will be caught: "
					+ e.getCode());
			System.out.println("Stripe Payment:: CardException Message is: "
					+ e.getParam());
//			return paymentTransaction;
		} catch (InvalidRequestException e) {
			System.out.println("Status is: " + e.getMessage());
			System.out.println("Stripe Payment:: Invalid parameters were supplied to Stripe's API: "
					+ e.getMessage());
//			return paymentTransaction;

		} catch (AuthenticationException e) {
			System.out.println("Status is: " + e.getMessage());
			System.out.println("Stripe Payment::  Authentication with Stripe's API failed: "
					+ e.getMessage());
			// (maybe you changed API keys recently)
//			return paymentTransaction;

		} catch (APIConnectionException e) {
			// Network communication with Stripe failed
			System.out.println("Stripe Payment:: Network communication with Stripe failed: "
					+ e.getMessage());
//			return paymentTransaction;

		} catch (StripeException e) {
			System.out.println("Status is: " + e.getMessage());
			System.out.println("Stripe Payment:: generic error to the user,: "
					+ e.getMessage());
			// generic error to the user, and maybe send yourself an email
//			return paymentTransaction;

		} catch (Exception e) {
			System.out.println("Status is: " + e.getMessage());
			System.out.println("Stripe Payment:: completely unrelated to Stripe: "
					+ e.getMessage());
//			return paymentTransaction;
		}

		
	}
	
	/**
	 * This method for set the bank account details
	 * @param paymentTransaction
	 * @return
	 */
	private Map<String, Object> getBankAccount() {
		final Map<String, Object> bankAccount = new HashMap<String, Object>();
		bankAccount.put("country", "US");
		bankAccount.put("routing_number", "110000000");
		bankAccount.put("account_number", "000123456789");
		
		
		/*
		 *  //  Need to set from Payment Form 
		 * bankAccount.put("routing_number",
		paymentTransaction.getBankAccount().getRoutingNumber());
		bankAccount.put("account_number",
		paymentTransaction.getBankAccount().getAccountNumber());
		bankAccount.put("accountName", paymentTransaction.getBankAccount().getAccountHolderName());
		bankAccount.put("name", paymentTransaction.getBankAccount()
				.getBankName());*/
		
		/*bankAccount.put("key", "myKey");
		bankAccount.put("id", "3");
		bankAccount.put("last4", "myKey");
		bankAccount.put("currency", "usd");
		bankAccount.put("status", "Y");
		bankAccount.put("bankName", "HDFC");
		bankAccount.put("fingerprint", "fingerprint");
		*/
		
		
		return bankAccount;

	}
	
	/**
	 * This method for set the Stripe Charge details
	 * @param paymentTransaction
	 * @return
	 */
	private Map<String, Object> getStripeChargeMap(	) {
		final Map<String, String> paymentOrder = new HashMap<String, String>();
		final Map<String, Object> stripeChargeMap = new HashMap<String, Object>();
		String reference ="121221";
		String reffNumber ="reference1";
		String payeeName = "shibu";
		paymentOrder.put("order_id", reffNumber);
		int amaountInCents = (int) Math.round(2);
		int payAmaount = amaountInCents * 100;
		stripeChargeMap.put("amount", payAmaount);
		String stripeDescription = "Payment to " + payeeName + ":" + reference;
		stripeChargeMap.put("description", stripeDescription);
		stripeChargeMap.put("metadata", paymentOrder);
		stripeChargeMap.put("currency", "usd");
		
		
//		if (paymentTransaction.getTransactionMode().equals(
//				PaymentConstants.PAY_BY_CARD)) {
			final Map<String, Object> cardDetail = getCard();
			stripeChargeMap.put("card", cardDetail);
//		} 
//		else if (paymentTransaction.getTransactionMode().equals(
//				PaymentConstants.PAY_BY_CHECK)) {
//			final Map<String, Object> bankAccount = getBankAccount(paymentTransaction);
//			stripeChargeMap.put("bank_account", bankAccount);  //Not Working
//			
			
			/*Map<String, Object> defaultRecipientParams = new HashMap<String, Object>();
			Map<String, Object> defaultBankAccountParams = new HashMap<String, Object>();
			defaultBankAccountParams.put("country", "US");
			defaultBankAccountParams.put("routing_number", "110000000");
			defaultBankAccountParams.put("account_number", "000123456789");
				
				
			defaultRecipientParams.put("name", "J Test");
			defaultRecipientParams.put("type", "individual");
			defaultRecipientParams.put("tax_id", "000000000");
			defaultRecipientParams.put("bank_account", defaultBankAccountParams);*/
		//	defaultRecipientParams.put("card", defaultDebitCardParams);

			
			
			
//			Recipient bankRecipient=Recipient.create(defaultRecipientParams);
//			stripeChargeMap.put("source", bankRecipient.getId());
//			
			
			///   **********Account******
//			callback: "sjsonp1373746168110"
//			_method: "POST"
	/* 
	 * // Create a Token for bank account..
	Map<String, Object> tokenParams = new HashMap<String, Object>();
	Map<String, Object> bank_accountParams = new HashMap<String, Object>();
	bank_accountParams.put("country", "US");
	bank_accountParams.put("routing_number", "110000000");
	bank_accountParams.put("account_number", "000123456789");
	tokenParams.put("bank_account", bank_accountParams);
	String accountToken="tok_15UZyjFygNv2Qex7lAA86UFg";
	try {
//		accountToken = Token.create(tokenParams);
		Token aToken = Token.create(tokenParams);
		stripeChargeMap.put("source", aToken); 
	} catch (AuthenticationException | InvalidRequestException
			| APIConnectionException | CardException | APIException e) {
		e.printStackTrace();
	}
	stripeChargeMap.put("source", accountToken); 
	*/
			
			
		return stripeChargeMap;

	}
	
	/**
	 * This method for set the card details
	 * @param paymentTransaction
	 * @return
	 */
	private Map<String, Object> getCard(){
		final Map<String, Object> cardDetail = new HashMap<String, Object>();
		String cardNumber = "4242424242424242";
		String object = "card";
		String cardName = "VISA";
		String cardCVC = "123";
		int cardexpMonth = 12;
		int cardexpYear = 2015;
		cardDetail.put("object", object);	
		cardDetail.put("number", cardNumber);	
		cardDetail.put("exp_month", cardexpMonth);
		cardDetail.put("exp_year", cardexpYear);
		cardDetail.put("cvc", cardCVC);
		cardDetail.put("name", cardName);
		cardDetail.put("email", "shibu@foundingminds.com");
/*		cardDetail.put("address_line1", "MedPanel1"); // TODO Need to set Address later.
		cardDetail.put("address_line2", "MedPanel 2");
		cardDetail.put("address_city", "San Francisco");
		cardDetail.put("address_zip", "12398");
		cardDetail.put("address_state", "CA");
		cardDetail.put("address_country", "USA");*/
//		cardDetail.setLast4("4242");
//		cardDetail.setType("Visa");
		return cardDetail;
		
	}

	
	
	
	private RequestOptions loadStripePayment(String tokenKey)
			throws APIConnectionException, StripeException {

		try {
			
			

			
			/*File file = new File("F:\\medconfig"); // Local
//			File file = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\medconfig"); // Production
			URL[] urls = {file.toURI().toURL()};
			ClassLoader loader = new URLClassLoader(urls);
			Locale locale = new Locale("en", PaymentConstants.LOCALE_US);	// Locale locale = Locale.getDefault();
			ResourceBundle resourceBundle =ResourceBundle.getBundle("medpanel-config", locale, loader);
			final String stripeApiKey = resourceBundle.getString(PaymentConstants.STRIPE_API_KEY);
			final String apiVersion = resourceBundle.getString(PaymentConstants.STRIPE_API_KEY);
			 */
			
			Stripe.apiKey = stripeApiKey;
			Stripe.apiVersion = stripeKeyVersion;
//			Customer customer = new Customer();

			String idempotencyKey = "4242";
			idempotencyKey = null; // TODO Need to generate Later
			/*
			 * // For create Tokens Map<String, Object> tokenByCard = new
			 * HashMap<String, Object>(); tokenByCard.put("card", cardDetail);
			 * Token.create(tokenByCard);
			 * 
			 * Map<String, Object> tokenByBankAccount = new HashMap<String,
			 * Object>(); Map<String, Object> bankAccount = new HashMap<String,
			 * Object>(); bankAccount.put("country", "US");
			 * bankAccount.put("routing_number", "110000000");
			 * bankAccount.put("account_number", "000123456789");
			 * tokenByBankAccount.put("bank_account", bankAccount);
			 * Token.create(tokenByBankAccount); // Token End
			 * 
			 * // chargeMap.put("card", "tok_16VbhPEw6Pes3AD41MU9p2x9"); // //
			 * obtained with Stripe.js // Charge ch1=Charge.create(chargeMap);
			 */

			RequestOptions options = RequestOptions.builder()
					.setIdempotencyKey(idempotencyKey).setApiKey(stripeApiKey)
					.setStripeVersion(stripeKeyVersion).build();
			return options;
		} catch (Exception e) {
			System.out.println("Stripe Payment:: completely unrelated to Stripe: "
					+ e.getMessage());
		}
		return null;
	}

	
	
	
/*//	@Test
	public void testCustomerCreateWithSource() throws StripeException {
		HashMap<String, Object> customerCreationParams = new HashMap<String, Object>();
		HashMap<String, Object> cardParams = new HashMap<String, Object>(defaultCardParams);
		cardParams.put("object", "card");
		customerCreationParams.put("source", cardParams);
		Customer customer = Customer.create(customerCreationParams);
//		assertNotNull(customer);
//		assertNotNull(customer.getId());
//		assertNotNull(customer.getSources());
//		assert(customer.getSources().getData().get(0) instanceof Card);
//		assertNotNull(customer.getDefaultSource());
		ExternalAccount card = customer.getSources().retrieve(customer.getDefaultSource());
		assertEquals(card.getId(), customer.getDefaultSource());
	}*/

	/*
	 * 
	 * @Test
public void testCustomerCardAddition() throws StripeException {
Customer createdCustomer = Customer.create(defaultCustomerParams, cardSupportedRequestOptions);
String originalDefaultCard = createdCustomer.getDefaultCard();
Map<String, Object> creationParams = new HashMap<String, Object>();
creationParams.put("card", defaultCardParams);
Card addedCard = createdCustomer.createCard(creationParams);
Token token = Token.create(defaultTokenParams);
createdCustomer.createCard(token.getId());
Customer updatedCustomer = Customer.retrieve(createdCustomer.getId(), cardSupportedRequestOptions);
assertEquals((Integer) updatedCustomer.getCards().getData().size(), (Integer) 3);
assertEquals(updatedCustomer.getDefaultCard(), originalDefaultCard);
Map<String, Object> updateParams = new HashMap<String, Object>();
updateParams.put("default_card", addedCard.getId());
Customer customerAfterDefaultCardUpdate = updatedCustomer.update(updateParams, cardSupportedRequestOptions);
assertEquals((Integer) customerAfterDefaultCardUpdate.getCards().getData().size(), (Integer) 3);
assertEquals(customerAfterDefaultCardUpdate.getDefaultCard(), addedCard.getId());
assertEquals(customerAfterDefaultCardUpdate.getCards().retrieve(originalDefaultCard).getId(), originalDefaultCard);
assertEquals(customerAfterDefaultCardUpdate.getCards().retrieve(addedCard.getId()).getId(), addedCard.getId());
}

	 * 
	 * 
	 * @Test
	public void testRecipientCardAddition() throws StripeException {
	Recipient createdRecipient = Recipient.create(defaultRecipientParams);
	String originalDefaultCard = createdRecipient.getDefaultCard();
	Map<String, Object> creationParams = new HashMap<String, Object>();
	creationParams.put("card", defaultDebitCardParams);
	Card addedCard = createdRecipient.createCard(creationParams);
	Token token = Token.create(defaultDebitTokenParams);
	createdRecipient.createCard(token.getId());
	Recipient updatedRecipient = Recipient.retrieve(createdRecipient.getId());
	assertEquals((Integer) 3, (Integer) updatedRecipient.getCards().getData().size());
	assertEquals(updatedRecipient.getDefaultCard(), originalDefaultCard);
	Map<String, Object> updateParams = new HashMap<String, Object>();
	updateParams.put("default_card", addedCard.getId());
	Recipient recipientAfterDefaultCardUpdate = updatedRecipient.update(updateParams);
	assertEquals((Integer) recipientAfterDefaultCardUpdate.getCards().getData().size(), (Integer) 3);
	assertEquals(recipientAfterDefaultCardUpdate.getDefaultCard(), addedCard.getId());
	assertEquals(recipientAfterDefaultCardUpdate.getCards().retrieve(originalDefaultCard).getId(), originalDefaultCard);
	assertEquals(recipientAfterDefaultCardUpdate.getCards().retrieve(addedCard.getId()).getId(), addedCard.getId());
	}*/
	
}
