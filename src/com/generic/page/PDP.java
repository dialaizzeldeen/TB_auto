package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.CartSelectors;
import com.generic.selector.PDPSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class PDP extends SelTestCase {
	
	public static class keys
	{
		public static final String id = "id";
		public static final String name = "name";
		public static final String title = "title";
		public static final String url = "url";
		public static final String color = "color";
		public static final String size = "size";
		public static final String qty = "qty";
		public static final String summary = "summary";
		public static final String price = "price";
		public static final String desc = "desc";
		public static final String reviews = "reviews";
		public static final String rating = "rating";
		public static final String scene7Image = "scene7Image";
			
	}
	public static void getURL(String url) {
        logs.debug("Current URL: " + url);
	    String env = getCONFIG().getProperty("testEnvironment").split("\\.")[0];
	    String currentenv = url.split("\\.")[0];
		String newURL = url.replaceAll(currentenv, env);
	    logs.debug("Expected URL: " + newURL);
	    getDriver().get(newURL);

}
	public static void addProductsToCartAndClickCheckOut(String url, String color, String size, String qty) throws Exception {
		getURL(url);
		getCurrentFunctionName(true);
		if (!"".equals(color))
			selectcolor(color);

		if (!"".equals(size))
			selectsize(size);

		defineQty(qty);
		clickAddToCartBtn();
	//	clickProceedToCheckout();
		getDriver().get(PagesURLs.getShoppingCartPage());
		getCurrentFunctionName(false);
	}
	
	public static void addProductsToCart(String url, String color, String size, String qty) throws Exception {
		getURL(url);
		getCurrentFunctionName(true);
		if (!"".equals(color))
			selectcolor(color);
		
		Thread.sleep(2000);
		if (!"".equals(size))
			selectsize(size);

		defineQty(qty);
		clickAddToCartBtn();
		Thread.sleep(2000);
		getCurrentFunctionName(false);
	}
	
	public static void clickProceedToCheckout() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_CART_BUTTON, "proceedToCheckout"));
		subStrArr.add(CartSelectors.proceedToCheckout);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
	public static String getPrice() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.price);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
public static String getImageUrl() throws Exception {
	getCurrentFunctionName(true);
		/*List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.image);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();*/
	List<String> subStrArr = new ArrayList<String>();
	subStrArr.add("pdp-main-image");
	String url=SelectorUtil.getAttr(subStrArr, "src");
	
	return url;
	
}
	private static void clickAddToCartBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.addToCartBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	private static void defineQty(String qty) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.qty);
		valuesArr.add(qty);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void selectsize(String size) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.size);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void selectcolor(String color) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(color);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static String getTitle() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.title);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
//diala izz
	public static void selectAmount(String amount) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_GC_AMOUNT, amount ));
		subStrArr.add(PDPSelectors.GCAmount);
        valuesArr.add(amount);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	public static void writeEmail(String email) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.GCRecipientEmail);
		valuesArr.add(email);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	public static void confirmEmail(String email) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.VGCconfirmRecipientEmail);
		valuesArr.add(email);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	public static void GCtoName(String name) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.GCRecipientName);
		valuesArr.add(name);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	public static void GCfromName(String name) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.GCFromName);
		valuesArr.add(name);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	public static void writeMessage(String message) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.GCRecipientMessage);
		valuesArr.add(message);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	public static void VGCaddToBag() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.addToCartVGCBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	public static void TGCaddToBag() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.addToCartTGCBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	public static void addVGCToCart(String amount, String email, String confirmEmail,String ToName,String FromName, String message) throws Exception {
		getCurrentFunctionName(true);
		selectAmount(amount);
		writeEmail(email);
		confirmEmail(confirmEmail);
		GCtoName(ToName);
		GCfromName(FromName);
		writeMessage(message);
		VGCaddToBag();
		getCurrentFunctionName(false);

	}
	
	public static void addTGCToCart(String amount, String email, String ToName, String FromName, String message) throws Exception {
		getCurrentFunctionName(true);
		selectAmount(amount);
		GCtoName(ToName);
		GCfromName(FromName);
		writeMessage(message);
		writeEmail(email);
		TGCaddToBag();
		getCurrentFunctionName(false);

	}
	
	public static String getSummary() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.summary);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static String getDesc() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.desc);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}

	public static boolean checkAddToCartButton() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.addToCartBtn);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("existence check result is " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static String getRating() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.rating);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String numberOfFoundElements = SelectorUtil.textValue.get().replace("Reviews", "").replace("Review", "").trim();
		getCurrentFunctionName(false);
		return numberOfFoundElements;
	}
	

	public static void clickShowReviewsBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.showReviews);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	
	public static String getReviewEntry() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.reviewEntry);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	public static String getCartPopupError() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.cartPopupError);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	public static String getProductQtyInCartPopup() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.cartPopupProductQty);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
    public static String getRatingCalc() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, PDPSelectors.ratingValue));
		subStrArr.add(PDPSelectors.ratingValue);
		String dataRating = SelectorUtil.getAttr(subStrArr, "data-rating");
		logs.debug("data-rating is: " + dataRating);
		getCurrentFunctionName(false);
		return dataRating;
	}
    
    public static String getActiveStars() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.activeStars);
		valuesArr.add("noClick");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String numberOfFoundElements = SelectorUtil.textValue.get().replace("Reviews", "").trim();
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_ACTIVE_STARS, numberOfFoundElements));
		getCurrentFunctionName(false);
		return numberOfFoundElements;
    }
    
	public static String getSizeOptions() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.size);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	public static String getDisplayedSizeName() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.displayedVariantSizeName);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	public static String getSelectedSizeName() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.size);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
    public static String getSizeOptionsCount() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.sizeOptions);
		valuesArr.add("noClick");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String numberOfFoundElements = SelectorUtil.numberOfFoundElements.get();
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_ACTIVE_SIZES, numberOfFoundElements));
		getCurrentFunctionName(false);
		return numberOfFoundElements;
    }
    
	public static String getSizeOptionByIndex(int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();	
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, PDPSelectors.sizeOptions));
		subStrArr.add(PDPSelectors.sizeOptions);
		valuesArr.add("index,"+index);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
    
    public static String getVariantListCount() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.variantList);
		valuesArr.add("noClick");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String numberOfFoundElements = SelectorUtil.numberOfFoundElements.get();
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_ACTIVE_VARIANTS, numberOfFoundElements));
		getCurrentFunctionName(false);
		return numberOfFoundElements;
    }
    
	public static String getVariantList(int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, PDPSelectors.variantList));
		subStrArr.add(PDPSelectors.variantList);
		String variantName = SelectorUtil.getAttr(subStrArr, "title",index);
		logs.debug("Variant name is: " + variantName);
		getCurrentFunctionName(false);
		return variantName;
	}
	
	public static String getcurrentStyleValue() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, PDPSelectors.currentStyleValue));
		subStrArr.add(PDPSelectors.currentStyleValue);
		String variantName = SelectorUtil.getAttr(subStrArr, "title");
		logs.debug("current Style Value is: " + variantName);
		getCurrentFunctionName(false);
		return variantName;
	}
	
	public static String getVariantSelectedStyleName() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, PDPSelectors.variantSelectedStyleName));
		subStrArr.add(PDPSelectors.variantSelectedStyleName);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	public static String getColorOptions() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.size);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	public static String getMiniImageUrl()  throws Exception{
		// TODO Auto-generated method stub
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(PDPSelectors.mini_image);
		String url=SelectorUtil.getAttr(subStrArr, "src");
		String mini_url=url.split("[?]")[1];
		if(mini_url.equals("$main_minicart$"))
		return url;
		else return "";
		
	}
}
