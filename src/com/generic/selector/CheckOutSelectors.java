package com.generic.selector;

public class CheckOutSelectors {

	// Payment Type
	public static final String PaymentTypeSelection_CARD = "PaymentTypeSelection_CARD";
	public static final String PaymentTypeSelection_ACCOUNT = "PaymentTypeSelection_ACCOUNT";
	public static final String PurchaseOrderNumber = "text form-control";
	public static final String PaymentType_continue_button = "btn btn-primary btn-block checkout-next";
	public static final String PaymentTypeCostCenterSelect = "costCenterSelect";

	// Shipping Address

	public static final String currentPage = "css,#checkout-progress>.current";
	public static final String addAdress = "address-line1";
	public static final String country = "address-country";
	public static final String counteryVGC = "css,address-country";
	public static final String state = "address-region";
	public static final String stateVGC = "css,address-region";
	public static final String firstName = "address-first-name";
	public static final String firstNameTGC = "css,.controls>input";
	public static final String firstNameVGC = "css,address-first-name";

	public static final String lastName = "address-surname";
	public static final String lastNameVGC = "css,.controls>#address-surname";
	public static final String address = "address-line1";
	public static final String addressVGC = "#.controls>#address-line1";
	public static final String city = "address-town-city";
	public static final String billingAddressCity = "address-townCity";
	public static final String billingAddressCityVGC = "#.controls>#address-townCity";
	public static final String postal = "address-postcode";
	public static final String postalVGC = "#.controls>#address-postcode";
	public static final String phone = "address-phone";
	public static final String CheckSaveAddress = "save-address";
	public static final String submitShippingAddressBtn = "continue-checkout left btn btn-secondary-blue small-text";
	public static final String useSuggestedAddress = "use-suggested-address";
	public static final String addNewAddress = "add-address";
	public static final String chooseFromAddressBook = "view-address-book";
	public static final String addressEntry = "address-entry";
	public static final String cboxClose = "cboxClose";
	public static final String addressBookBtn = "btn btn-default btn-block js-address-book";
	public static final String selectFirstAddress = "Use This Address";
	public static final String orderTotalShippingAddress = "test_cart_totalPrice_label_$";
	public static final String orderSubTotalShippingAddress = "css,.subtotal";
	// public static final String totalItem = "css,.total-item>span";
	public static final String totalItemPromotion = "total-item promotion";
	public static final String fnameError = "firstName.errors";
	public static final String lnameError = "lastName.errors";
	public static final String address1Error = "line1.errors";
	public static final String cityError = "townCity.errors";
	public static final String postcodeEerror = "postcode.errors";
	public static final String alertInfo = "globalMessages";
	public static final String stateError = "regionIso.errors";
	public static final String phoneError = "phone.errors";
	public static final String countryError = "countryIso.errors";

	// shipping method
	public static final String shippingMethod = "delivery-method-";
	public static final String continueCheckout = "Continue Checkout";
	public static final String continueCheckoutTGC = "continue-checkout btn btn-secondary-blue small-text";
	public static final String continueCheckoutShipping = "css,.checkout-summary-container>button";
	public static final String orderTotalShippingMethod = "css,.order-summary-totals>span";
	public static final String orderSubTotalShippingMethod = "css,.subtotal";
	public static final String orderTaxShippingMethod = "totaltax";
	public static final String orderShippingShippingMethod = "deliverycost";

	// kiosk Details
	public static final String waiveShipping = "waiveShipping";
	public static final String associateID = "associateID";
	public static final String storeNumber = "storeNumber";

	// Gift Services
	public static final String giftOptionTrue = "gift_option_true";
	public static final String giftSelectOption = "gift-select-option";
	public static final String giftContainerTo1 = "container-to-1";
	public static final String giftContainerFrom1 = "container-from-1";
	public static final String giftContainerMessage1 = "container-message-1";
	public static final String removeGiftOption = "remove-giftoption btn btn-pass";
	public static final String orderTotalGiftOption = "css,.order-summary-totals>span";
	public static final String orderShippingGiftOption = "deliverycost";
	public static final String orderSubtotalGiftOption = "css,.subtotal";
	public static final String orderTaxGiftOption = "totaltax";
	public static final String giftServices = "css,.total-item>span";
	public static final String totalItem = "css,.total-item>span";
	public static final String totalItemsPromotion = "css,.total-item.promotion>span";
	// Payment info
	public static final String cardtype = "card_cardType";
	public static final String cardHolder = "name-on-card";
	public static final String cardNumber = "card-number";
	public static final String expireDay = "expiry-month";
	public static final String expireYear = "expiry-year";
	public static final String CVC = "verification-number";
	public static final String CVCVGC = "css,.controls>input.x-small";

	public static final String checkSavePayment = "saved-details";
	public static final String checkSame = "useDeliveryAddress";
	public static final String submitPayment = "btn btn-primary btn-block submit_silentOrderPostForm checkout-next";
	public static final String selectPayment = "saved-payment-select";
	public static final String savedPaymentBtn = "btn btn-default btn-block js-saved-payments";
	public static final String selectFirstPayment = "Use these payment details";
	public static final String orderTotalPymentInfo = "css,.order-summary-totals>span";
	public static final String orderShippingPymentInfo = "deliverycost";
	public static final String orderSubtotalPymentInfo = "css,.subtotal";
	public static final String orderTaxPymentInfo = "totaltax";
	public static final String cardNameError = "nameOnCard.errors";
	public static final String cardNumberError = "cardNumber.errors";
	public static final String expirationMonthError = "expiryMonth.errors";
	public static final String expirationYearError = "expiryYear.errors";
	public static final String cvNumberError = "securityCode.errors";
	public static final String billToFNameError = "billingAddress.firstName.errors";
	public static final String billToLNameError = "billingAddress.lastName.errors";
	public static final String billToAddressError = "billingAddress.line1.errors";
	public static final String billToCountryError = "billingAddress.countryIso.errors";
	public static final String billToCityError = "billingAddress.townCity.errors";
	public static final String billToStateError = "billingAddress.regionIso.errors";
	public static final String billToPostCodeError = "billingAddress.postcode.errors";

	// summary - review / place order
	public static final String summaryTotal = "css,.subtotal";
	public static final String shippingCost = "deliverycost";
	public static final String acceptTerm = "Terms1";
	public static final String placeOrderBtn = "Place Order";
	public static final String orderTotalOrderSumary = "css,.order-summary-totals>span";
	public static final String orderTaxOrderSumary = "totaltax";

	// Order confirmation
	public static final String orderConfirmationMessage = "confirm-message";
	public static final String orderId = "order-number";
	public static final String orderConfirmationTotal = "totalcost";
	public static final String orderConfirmationSubtotal = "css,.subtotal";
	public static final String orderConfirmationShippingCost = "deliverycost";
	public static final String orderConfirmationTax = "totaltax";
	public static final String orderConfirmationProductPromotion = "total-item promotion";
	public static final String orderconfirmationshippingAddress = "css,.confirm-container>.container";
	public static final String orderconfirmationDeliveryMethod = "css,.confirm-container>.container";
	public static final String orderconfirmationPaymentMethod = "payment-method";
	public static final String orderconfirmationBillingAddress = "payment-address";

	// B2B Order confirmation
	public static final String B2BorderConfirmationTotal = "text-right totals";
	public static final String B2BorderconfirmationBillingAddress = "col-sm-6 order-billing-address";
	public static final String B2BorderconfirmationshippingAddress = "col-md-5 order-ship-to";

	// Guest order
	public static final String guestMail = "guest.email";
	public static final String guestConfirmationMail = "confirmGuestEmail form-control";
	public static final String guestCheckoutButton = "Continue As A Guest";
	public static final String guestCreateAccButton = "test_guest_Register_button_$";
	public static final String guestCreateAccOtpin = "btn btn-accent2-dark-gray small-text tb-modal";
	public static final String guestFirstName = "guest-first-name";
	public static final String guestLastName = "guest-last-name";
	public static final String guestCreateAccCPwd = "guest-check-pwd";
	public static final String guestCreateAccPwd = "password";
	public static final String returningcustomerUsername = "j_username";
	public static final String returningcustomerPassword = "j_password";
	public static final String returningcustomerSignInBtn = "accountSignIn";

	public static final String emailShippingVGC = "css,.shipping-method-item>input";
	public static final String phoneVGC = "css,.shipping-description>input";

	public static final String addressLine2 = "css,address.line2";
	public static final String checkOutTGC = "css,.cart-checkout-outer-box>#cart-checkout-button";

}
