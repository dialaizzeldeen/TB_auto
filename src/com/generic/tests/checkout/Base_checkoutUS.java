package com.generic.tests.checkout;

import java.text.MessageFormat;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import java.util.LinkedHashMap;

import com.generic.page.PDP;
import com.generic.page.Registration;
import com.generic.page.Cart;
import com.generic.page.CheckOut;
import com.generic.page.HomePage;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.dataProviderUtils;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;

public class Base_checkoutUS extends SelTestCase {

	private static LinkedHashMap<String, Object> addresses = null;
	private static LinkedHashMap<String, Object> invintory = null;
	private static LinkedHashMap<String, Object> paymentCards = null;
	private static LinkedHashMap<String, Object> users = null;

	// user types
	public static final String guestUser = "guest";
	public static final String freshUser = "fresh";
	public static final String loggedInUser = "loggedin";
	public static final String loggedDuringChcOt = "logging During Checkout";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.checkoutSheet;

	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();

	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger("checkout_setup"));
		testObject = test;
		addresses = Common.readAddresses();
		invintory = Common.readLocalInventory();
		paymentCards = Common.readPaymentcards();
		users = Common.readUsers();
	}

	@DataProvider(name = "Orders", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		//concurrency mentainance on sheet reading 
		getBrowserWait(testObject.getParameter("browserName"));
		
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked") // avoid warning from linked hashmap
	@Test(dataProvider = "Orders")
	public void checkOutUSBaseTest(String caseId, String runTest, String desc, String proprties, String products,
			String shippingMethod, String payment, String shippingAddress, String billingAddress, String coupon,
			String email) throws Exception {
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("checkout_" + getBrowserName()));
		setTestCaseReportName("Checkout Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.CHECKOUTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- "), payment, shippingMethod));

		String Pemail;
		String GCemail;
		String cartID;
		String orderTotal = null;
		String orderSubtotal = null;
		String handlingFee = null;
		String orderTax = null;
		String orderShipping = null;
		String orderConfirmationOrderId;
		String orderConfirmationDeliveryAddress;
		String orderConfirmationDeliveryMethod;
		String orderConfirmationPaymentMethod;
		String orderConfirmationBillingAddress;
		Boolean IsShippingPromotionApplied = false;
		Pemail = getSubMailAccount(email);
		GCemail=getCONFIG().getProperty("GCemail");
        String FromName= RandomUtilities.getRandomName();


		try {
			Thread.sleep(3000);
			HomePage.closeSubcriptionPopup();
			Thread.sleep(3000);
			LinkedHashMap<String, Object> addressDetails = (LinkedHashMap<String, Object>) addresses
					.get(shippingAddress);
			if (proprties.contains("loggedin")) {
				// you need to maintain the concurrency and get the main account information and
				// log in in browser account
				LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);
				Testlogs.get().debug(Pemail);
				Testlogs.get().debug((String) userdetails.get(Registration.keys.password));
				// getDriver().get("https://10.30.50.17:9002/en/login");

				SignIn.logIn(Pemail, (String) userdetails.get(Registration.keys.password));
				
			}
			if (proprties.contains(freshUser)) {
				Pemail = RandomUtilities.getRandomEmail();

				// take any user as template
				LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.entrySet().iterator()
						.next().getValue();

				Registration.fillAndClickRegister(Pemail, Pemail, (String) userdetails.get(Registration.keys.firstName),
						(String) userdetails.get(Registration.keys.lastName),
						(String) userdetails.get(Registration.keys.country),
						(String) userdetails.get(Registration.keys.postalCode),
						(String) userdetails.get(Registration.keys.password),
						(String) userdetails.get(Registration.keys.password), true);
			}
			if (!proprties.contains("VGC") && !proprties.contains("TGC") ) {
				for (String product : products.split("\n")) {
					Testlogs.get().debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
					LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory
							.get(product);
					PDP.addProductsToCartAndClickCheckOut((String) productDetails.get(PDP.keys.url),
							(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
							(String) productDetails.get(PDP.keys.qty));
				}
			}
			
			LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);

			
				for (String product : products.split("\n")) {
					
					Testlogs.get().debug(MessageFormat.format(LoggingMsg.ADDING_PRODUCT, product));
					LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory
							.get(product);

					getDriver().get((String) productDetails.get(PDP.keys.url));
					
					if (!product.equals("P12") && ! product.equals("P13")) {
						PDP.addProductsToCartAndClickCheckOut((String) productDetails.get(PDP.keys.url),
								(String) productDetails.get(PDP.keys.color), (String) productDetails.get(PDP.keys.size),
								(String) productDetails.get(PDP.keys.qty));
					}
					if (product.equals("P13")) {
						getDriver().get((String) productDetails.get(PDP.keys.url));
						PDP.addTGCToCart("75", GCemail,FromName,FromName, "");
						getDriver().get(PagesURLs.getShoppingCartPage());
					}
					if (product.equals("P12")) {
						getDriver().get((String) productDetails.get(PDP.keys.url));
						PDP.addVGCToCart("75", GCemail, GCemail,FromName ,
								FromName, "sssss");
						
					
					getDriver().get(PagesURLs.getShoppingCartPage());}
					if (proprties.contains("logOut")) {
						SignIn.clickLogOut();
						
					}
					
				}
			
			

			// flow to support coupon validation
			if (!"".equals(coupon)) {
				Cart.applyCoupon(coupon);
				if (coupon.contains(Cart.keys.invalidCoupon)) {
					Cart.validateCoupon();
				}
			}
			// Cart.getNumberOfproducts();
			orderSubtotal = Cart.getOrderSubTotal();
			orderTotal = Cart.getOrderTotal();
			cartID = Cart.getCartId();
			Testlogs.get().debug("Cart ID: " + cartID);
			if (proprties.contains(CheckOut.keys.handlingFee)) {
				handlingFee = Cart.getHandlingFeeTotal();
			}
			Boolean IsProductPromotionApplied = Cart.checkProductPromotionMessage();

			Cart.clickCheckout();
			if (proprties.contains(loggedDuringChcOt)) {
				Testlogs.get().debug("Login during checkout with: "+Pemail);
				Testlogs.get().debug("Using password: "+(String) userdetails.get(Registration.keys.password) );
				CheckOut.guestCheckout.returningCustomerLogin(Pemail, (String) userdetails.get(Registration.keys.password));
				if(Cart.isCartPageOpened()) {
					orderSubtotal = Cart.getOrderSubTotal();
					cartID = Cart.getCartId();
					CheckOut.guestCheckout.clickCheckout();
				}
				else if (proprties.contains(CheckOut.keys.employeeCustomer)) {
					// Update Order sub total after applying employee discount
					orderSubtotal = CheckOut.shippingAddress.getOrdersubTotal();
				}
				
			}

			if (proprties.contains(guestUser)) {
				Pemail = RandomUtilities.getRandomEmail();
				CheckOut.guestCheckout.fillAndClickGuestCheckout(Pemail);
			}

			if (proprties.contains("VGC only")) {
				String actualGCSubtotal = CheckOut.shippingMethod.getOrderSubTotal();
				CheckOut.shippingMethod.choseEmailShipping();
				CheckOut.shippingMethod.VGCtypePhone((String) addressDetails.get(CheckOut.shippingAddress.keys.phone));
				sassert().assertEquals(actualGCSubtotal, orderSubtotal,
						"<font color=#f442cb>Order subtotal inShipping address page is not as expected. Expectd: "
								+ orderSubtotal + "Actual: " + actualGCSubtotal + "</font>");
				sassert().assertEquals(actualGCSubtotal, orderTotal);
				sassert().assertEquals(orderTax, "$0.00");
				CheckOut.shippingMethod.clickNext();
			}
		 else {
				Thread.sleep(2000);
				// Validate the order subtotal in shipping address form section
				String actualOrderSubtotal = CheckOut.shippingAddress.getOrdersubTotal();
				sassert().assertEquals(actualOrderSubtotal, orderSubtotal,
						"<font color=#f442cb>Order subtotal in delivry address page is not as expected. Expectd: "
								+ orderSubtotal + "Actual: " + actualOrderSubtotal + "</font>");
				if (proprties.contains(CheckOut.keys.handlingFee)) {
					String actualHandlingFee = CheckOut.shippingAddress.getHandlingFeeTotal();
					sassert().assertEquals(actualHandlingFee, handlingFee,
							"<font color=#f442cb>Order Handling Fee in delivry address page is not as expected. Expectd: "
									+ handlingFee + "Actual: " + actualHandlingFee + "</font>");
				}}
				// checkout- shipping address

			if (proprties.contains(CheckOut.shippingAddress.keys.isSavedShipping) && !proprties.contains(freshUser)
					&& !proprties.contains(guestUser) && proprties.contains(CheckOut.shippingAddress.keys.isNotDefaultAddress)) {
				CheckOut.shippingAddress.fillAndClickNext(false);
				logs.debug("user clicks on one btn");

				Thread.sleep(1000);
			}else if (proprties.contains(CheckOut.shippingAddress.keys.isSavedShipping) && !proprties.contains(freshUser)
					&& !proprties.contains(guestUser)) {
				CheckOut.shippingAddress.fillAndClickNext(true);
				logs.debug("user clicks on two btn");
				Thread.sleep(1000);}
			
			
				else if (!proprties.contains("VGC only")&& (!proprties.contains("TGC only"))) {
				

					boolean saveShipping = !proprties.contains(guestUser);

					// in case guest the save shipping check-box is not exist
					if (saveShipping) {
						CheckOut.shippingAddress.clickAddAddressBtn();
						logs.debug("user clicks on address btn");
						CheckOut.shippingAddress.fillAndClickNext(
								(String) addressDetails.get(CheckOut.shippingAddress.keys.firstName),
								(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
								(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
								(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
								(String) addressDetails.get(CheckOut.shippingAddress.keys.state),
								(String) addressDetails.get(CheckOut.shippingAddress.keys.postal),
								(String) addressDetails.get(CheckOut.shippingAddress.keys.phone), true);
					} else {CheckOut.shippingAddress.fillAndClickNext(
							(String) addressDetails.get(CheckOut.shippingAddress.keys.firstName),
							(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
							(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
							(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
							(String) addressDetails.get(CheckOut.shippingAddress.keys.state),
							(String) addressDetails.get(CheckOut.shippingAddress.keys.postal),
							(String) addressDetails.get(CheckOut.shippingAddress.keys.phone));}}
						
						if (proprties.contains("TGC only")){
							
							String actualGCSubtotal = CheckOut.shippingMethod.getOrderSubTotal();
							sassert().assertEquals(actualGCSubtotal, orderSubtotal,
									"<font color=#f542cb>Order subtotal in delivry address page is not as expected. Expectd: "
											+ orderSubtotal + "Actual: " + actualGCSubtotal + "</font>");
							sassert().assertEquals(actualGCSubtotal, orderTotal);
							sassert().assertEquals(orderTax, "$0.00");
							
							CheckOut.shippingAddress.fillAndClickNextTGC(
									(String) addressDetails.get(CheckOut.shippingAddress.keys.firstName),
									(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName),
									(String) addressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
									(String) addressDetails.get(CheckOut.shippingAddress.keys.countery),
									(String) addressDetails.get(CheckOut.shippingAddress.keys.city),
									(String) addressDetails.get(CheckOut.shippingAddress.keys.state),
									(String) addressDetails.get(CheckOut.shippingAddress.keys.postal),
									(String) addressDetails.get(CheckOut.shippingAddress.keys.phone));
						}
				
			

			// Shipping method
			if (!proprties.contains("VGC only")) {

				CheckOut.shippingMethod.selectShippingMethod(shippingMethod);

				// Validate the order sub-total in shipping method section
				String actualOrderSubtotal = CheckOut.shippingMethod.getOrderSubTotal();
				sassert().assertEquals(actualOrderSubtotal, orderSubtotal,
						"<font color=#f442cb>Order subtotal in delivry method page is not as expected. Expectd: "
								+ orderSubtotal + "Actual: " + actualOrderSubtotal + "</font>");
				orderShipping = CheckOut.shippingMethod.getOrderShipping();
				orderTax = CheckOut.shippingMethod.getOrderTax();
				orderTotal = CheckOut.shippingMethod.getOrderTotal();
				IsShippingPromotionApplied = CheckOut.shippingMethod.checkPromotionMessage();

			
				// Gift Services
				if (proprties.contains(CheckOut.giftServices.keys.addGiftServices)) {
					CheckOut.giftServices.clicGiftOptionTrue();
					Thread.sleep(1000);
				}
			
				CheckOut.shippingMethod.clickNext();}
				// end GC shipping method

				if (proprties.contains(CheckOut.giftServices.keys.addGiftServices)) {
					CheckOut.giftServices.selectGiftSelectOption("Gift Box");
					// CheckOut.giftServices.selectGiftSelectOption("Gift Message");
					Thread.sleep(3000);
					CheckOut.giftServices.typeGiftContainerTo1("Emad");
					CheckOut.giftServices.typeGiftContainerFrom1("Emad");
					CheckOut.giftServices.typeGiftContainerMessage1("test");
					CheckOut.giftServices.clickNext();

					// Update the initial vaules for orderTax and orderTotal.
					String actualOrderTax = CheckOut.paymentInformation.getOrderTax();
					String actualOrderTotal = CheckOut.paymentInformation.getOrderTotal();
					String giftServices = CheckOut.paymentInformation.getGiftServices();
					sassert().assertTrue(giftServices.contains("$5.00"),
							"<font color=#f442cb>Gift Services in payment page is not as expected. Expectd:$5.00.Actual:"
									+ giftServices + "</font>");
					sassert().assertTrue(
							Double.parseDouble(actualOrderTax.trim().replace("$", "")) > Double
									.parseDouble(orderTax.trim().replace("$", "")),
							"<font color=#f442cb>Order Taxes in payment page is not updated " + actualOrderTax + "<="
									+ orderTax + "</font>");
					sassert().assertTrue(
							Double.parseDouble(actualOrderTotal.trim().replace("$", "")) > Double
									.parseDouble(orderTotal.trim().replace("$", "")),
							"<font color=#f442cb>Order total in payment page is not updated" + actualOrderTotal + "<="
									+ orderTotal + "</font>");
					orderTax = CheckOut.paymentInformation.getOrderTax();
					orderTotal = CheckOut.paymentInformation.getOrderTotal();
				}
			

			// Validate the order total in billing form section
			String actualOrderSubtotal = CheckOut.paymentInformation.getOrderSubTotal();
			String actualOrderTax = CheckOut.paymentInformation.getOrderTax();
			String actualOrderTotal = CheckOut.paymentInformation.getOrderTotal();

			if (!proprties.contains("VGC only")) {
				String actualOrderShipping = CheckOut.paymentInformation.getOrdershipping();
				sassert().assertEquals(actualOrderShipping, orderShipping,
						"<font color=#f442cb>Order shipping in payment page is not as expected. Expectd: "
								+ orderShipping + "Actual: " + actualOrderShipping + "</font>");
			}
			sassert().assertEquals(actualOrderSubtotal, orderSubtotal,
					"<font color=#f442cb>Order subtotal in payment page is not as expected. Expectd: " + orderSubtotal
							+ "Actual: " + actualOrderSubtotal + "</font>");
			sassert().assertEquals(actualOrderTax, orderTax,
					"<font color=#f442cb>Order Taxes in payment page is not as expected. Expectd: " + orderTax
							+ "Actual: " + actualOrderTax + "</font>");
			sassert().assertEquals(actualOrderTotal, orderTotal,
					"<font color=#f442cb>Order total in payment page is not as expected. Expectd: " + orderTotal
							+ "Actual: " + actualOrderTotal + "</font>");
		//	CheckOut.shippingMethod.clickNext();


			LinkedHashMap<String, Object> paymentDetails = (LinkedHashMap<String, Object>) paymentCards.get(payment);
			LinkedHashMap<String, Object> billAddressDetails = (LinkedHashMap<String, Object>) addresses
					.get(billingAddress);
			if (proprties.contains("VGC only")) {

				CheckOut.paymentInformation.fillAndclickNextVGC(
						(String) paymentDetails.get(CheckOut.paymentInformation.keys.name),
						(String) paymentDetails.get(CheckOut.paymentInformation.keys.number),
						(String) paymentDetails.get(CheckOut.paymentInformation.keys.expireMonth),
						(String) paymentDetails.get(CheckOut.paymentInformation.keys.expireYear),
						(String) paymentDetails.get(CheckOut.paymentInformation.keys.CVCC),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.firstName),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.lastName),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.countery),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.city),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.state),
						(String) billAddressDetails.get(CheckOut.shippingAddress.keys.postal));
			}
			if (proprties.contains(CheckOut.paymentInformation.keys.isSavedPayement) && !proprties.contains(freshUser)
					&& !proprties.contains(guestUser)) {

				CheckOut.paymentInformation.pickFirstpaymentsaved(payment);
				CheckOut.paymentInformation.typeCVC((String) paymentDetails.get(CheckOut.paymentInformation.keys.CVCC));
				CheckOut.paymentInformation.clickNext();
				logs.debug("user clicks on saved btn");

			}
			 else {

				// do not save address if scenario is guest user
				boolean saveBilling = !proprties.contains(guestUser);
				// LinkedHashMap<String, Object> paymentDetails = (LinkedHashMap<String,
				// Object>) paymentCards
				// .get(payment);

				if (saveBilling) {
					logs.debug("user clicks on billing btn");
					CheckOut.paymentInformation.fillAndclickNext(
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.name),
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.number),
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.expireMonth),
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.expireYear),
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.CVCC), saveBilling,
							billingAddress.equalsIgnoreCase(shippingAddress),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.firstName),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.lastName),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.countery),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.city),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.state),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.postal));
				} else {
					CheckOut.paymentInformation.fillAndclickNext(
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.name),
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.number),
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.expireMonth),
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.expireYear),
							(String) paymentDetails.get(CheckOut.paymentInformation.keys.CVCC),
							billingAddress.equalsIgnoreCase(shippingAddress),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.firstName),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.lastName),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.adddressLine),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.countery),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.city),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.state),
							(String) billAddressDetails.get(CheckOut.shippingAddress.keys.postal));
				}

			}
			// CheckOut.paymentInformation.clickNext();
			if(getBrowserName().equals("firefox"))
				Thread.sleep(3000);

			// Validate the order total in order review section

			actualOrderSubtotal = CheckOut.reviewInformation.getSubtotal();
			if (!proprties.contains("VGC only")) {
				String actualOrderShipping = CheckOut.reviewInformation.shippingCost();
				sassert().assertEquals(actualOrderShipping, orderShipping,
						"<font color=#f442cb>Order shipping in Order Review page is not as expected. Expectd: "
								+ orderShipping + "Actual: " + actualOrderShipping + "</font>");
			}

			actualOrderTax = CheckOut.reviewInformation.getOrderTax();
			actualOrderTotal = CheckOut.reviewInformation.getOrderTotal();
			sassert().assertEquals(actualOrderSubtotal, orderSubtotal,
					"<font color=#f442cb>Order subtotal in Order Review page is not as expected. Expectd: "
							+ orderSubtotal + "Actual: " + actualOrderSubtotal + "</font>");
			sassert().assertEquals(actualOrderTax, orderTax,
					"<font color=#f442cb>Order Taxes in Order Review page is not as expected. Expectd: " + orderTax
							+ "Actual: " + actualOrderTax + "</font>");
			sassert().assertEquals(actualOrderTotal, orderTotal,
					"<font color=#f442cb>Order total in Order Review page is not as expected. Expectd: " + orderTotal
							+ "Actual: " + actualOrderTotal + "</font>");
			if (proprties.contains(CheckOut.giftServices.keys.addGiftServices)) {
				String giftServices = CheckOut.paymentInformation.getGiftServices();
				sassert().assertTrue(giftServices.contains("$5.00"),
						"<font color=#f442cb>Gift Services in Order Review page is not as expected. Expectd:$5.00.Actual:"
								+ giftServices + "</font>");
			}
			 CheckOut.reviewInformation.placeOrder();

			// Validate the order total in order confirmation page

			actualOrderSubtotal = CheckOut.orderConfirmation.getSubTotal();
			String actualOrderShipping = CheckOut.orderConfirmation.getShippingCost();
			actualOrderTax = CheckOut.orderConfirmation.getOrderTax();
			actualOrderTotal = CheckOut.orderConfirmation.getOrderTotal();

			if (!proprties.contains("VGC only")) {
				sassert().assertEquals(actualOrderShipping, orderShipping,
						"<font color=#f442cb>Order hshipping in Order Confirmation page is not as expected. Expectd: "
								+ orderShipping + "Actual: " + actualOrderShipping + "</font>");

			}
			sassert().assertTrue(CheckOut.orderConfirmation.checkOrderConfirmationPage(),
					"<font color=#f442cb>Order cofirmation page is not displayed as expected. Current Page: "
							+ CheckOut.shippingAddress.getCurrentPage() + "</font>");
			sassert().assertEquals(actualOrderSubtotal, orderSubtotal,
					"<font color=#f442cb>Order subtotal in Order Confirmation page is not as expected. Expectd: "
							+ orderSubtotal + "Actual: " + actualOrderSubtotal + "</font>");
			sassert().assertEquals(actualOrderTax, orderTax,
					"<font color=#f442cb>Order Taxes in Order Confirmation page is not as expected. Expectd: "
							+ orderTax + "Actual: " + actualOrderTax + "</font>");
			sassert().assertEquals(actualOrderTotal, orderTotal,
					"<font color=#f442cb>Order total in Order Confirmation page is not as expected. Expectd: "
							+ orderTotal + "Actual: " + actualOrderTotal + "</font>");

			if (proprties.contains(CheckOut.giftServices.keys.addGiftServices)) {
				String giftServices = CheckOut.orderConfirmation.getGiftServices(IsShippingPromotionApplied,
						IsProductPromotionApplied);
				sassert().assertTrue(giftServices.contains("$5.00"),
						"<font color=#f442cb>Gift Services in Order Confirmation page is not as expected. Expectd:$5.00.Actual:"
								+ giftServices + "</font>");
			}
			if (proprties.contains(CheckOut.keys.handlingFee)) {
				String actualHandlingFee = CheckOut.shippingMethod.getHandlingFeeTotal(IsShippingPromotionApplied);
				sassert().assertEquals(actualHandlingFee, handlingFee,
						"<font color=#f442cb>Order Handling Fee in delivry address page is not as expected. Expectd: "
								+ handlingFee + "Actual: " + actualHandlingFee + "</font>");
			}
			orderTotal = CheckOut.orderConfirmation.getOrderTotal();
			orderShipping = CheckOut.orderConfirmation.getShippingCost();
			orderConfirmationOrderId = CheckOut.orderConfirmation.getOrderId();
			orderConfirmationDeliveryAddress = CheckOut.orderConfirmation.getShippingAddrerss();
			orderConfirmationDeliveryMethod = CheckOut.orderConfirmation.getDeliveryMethod();
			orderConfirmationPaymentMethod = CheckOut.orderConfirmation.getPaymentMethod();
			orderConfirmationBillingAddress = CheckOut.orderConfirmation.getBillingAddrerss();
			Testlogs.get().debug("Order confirmation-Delivery address: " + orderConfirmationDeliveryAddress);
			Testlogs.get().debug("Order confirmation-Delivery method: " + orderConfirmationDeliveryMethod);
			Testlogs.get().debug("Order confirmation-Payment method: " + orderConfirmationPaymentMethod);
			Testlogs.get().debug("Order confirmation-Billing address: " + orderConfirmationBillingAddress);
			// TODO: compare addresses

			if (proprties.contains(guestUser) && proprties.contains("register-guest")) {
				CheckOut.guestCheckout.fillPreRegFormAndClickRegBtn(
						(String) addressDetails.get(CheckOut.shippingAddress.keys.firstName),
						(String) addressDetails.get(CheckOut.shippingAddress.keys.lastName), "passw0rd");
			}

			Testlogs.get().debug(MessageFormat.format(LoggingMsg.CHECKOUT_RESULT, Pemail, orderConfirmationOrderId,
					orderTotal, orderSubtotal, orderTax, orderShipping));
			sassert().assertAll();
			Common.testPass();
		} catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver());
			Assert.assertTrue(false, t.getMessage());
		} // catch
	}// test
}// class
