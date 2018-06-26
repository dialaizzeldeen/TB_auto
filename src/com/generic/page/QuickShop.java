package com.generic.page;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.generic.selector.QuickShopSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class QuickShop extends SelTestCase {
    
    public static class keys {
		public static final String caseId = "caseId";
	}
    
    
	public static void clickQuickshopBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, QuickShopSelectors.quickShopBtn));
		subStrArr.add(QuickShopSelectors.quickShopBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
	public static String getQuickshopDes() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, QuickShopSelectors.QuickshopDes));
		subStrArr.add(QuickShopSelectors.QuickshopDes);
		
		String title = SelectorUtil.getTextOfItemNumber(subStrArr,0);
		logs.debug("Title Of Item: "+ title);
		getCurrentFunctionName(false);
		return title;	
	}

	public static String getQuickshopPrice() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, QuickShopSelectors.QuickshopPrice));
		subStrArr.add(QuickShopSelectors.QuickshopPrice);
		
		String price = SelectorUtil.getTextOfItemNumber(subStrArr,0);
		logs.debug("Price Of Item: "+ price);
		getCurrentFunctionName(false);
		return price;
		
	}

	public static boolean checkQuickshopSizeLabel() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.QuickshopsizeLabel);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Quickshop size label is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;	
	}
	
	public static boolean checkQuickshopAddToBagDisable() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.QuickshopAddToBagDisable);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Quickshop Add To Bag is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;	
	}
	
	public static boolean checkQuickshopAddToBagEnable() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.QuickshopAddToBagDisable);
		boolean isNotDisplayed = SelectorUtil.isNotDisplayed(subStrArr);
		logs.debug("Quickshop Add To Bag is displayed as expected " + isNotDisplayed);
		getCurrentFunctionName(false);
		return isNotDisplayed;	
	}
	
	public static String getQuickshopDroBoxSizeDefault() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, QuickShopSelectors.QuickshopDropBoxSizeDefault));
		subStrArr.add(QuickShopSelectors.QuickshopDropBoxSizeDefault);
		
		String size = SelectorUtil.getTextOfItemNumber(subStrArr,0);
		logs.debug("size Of Item: "+ size);
		getCurrentFunctionName(false);
		return size;
	}
	
    public static void clickQuickshopCloseBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, QuickShopSelectors.QuickshopCloseBtn));
		subStrArr.add(QuickShopSelectors.QuickshopCloseBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void chooseFirstSize() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "size "));
		subStrArr.add(QuickShopSelectors.sizeDropBoxItem);
		valuesArr.add("index,0");
		SelectorUtil.clickButton(subStrArr);
		Thread.sleep(1500);
		getCurrentFunctionName(false);
	}

	public static void clickOnSizeDropBox() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, QuickShopSelectors.sizeDropBoxClick));
		subStrArr.add(QuickShopSelectors.sizeDropBoxClick);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	

	public static String getGridViewFirstProductPrice() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, QuickShopSelectors.gridViewProductPrice));
		subStrArr.add(QuickShopSelectors.gridViewProductPrice);
		
		String price = SelectorUtil.getTextOfItemNumber(subStrArr,0);
		logs.debug("Price Of Item: "+ price);
		getCurrentFunctionName(false);
		return price;
		
	}

	public static String getGridViewFirstProductDes() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, QuickShopSelectors.gridViewProductDes));
		subStrArr.add(QuickShopSelectors.gridViewProductDes);
		
		String title = SelectorUtil.getTextOfItemNumber(subStrArr,0);
		logs.debug("Title Of Item: "+ title);
		getCurrentFunctionName(false);
		return title;
		
	}

	public static String getNumberOfQuantityItems() {
		// TODO Auto-generated method stub
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.quantityDropBoxItems);
		valuesArr.add("noClick");
		try{
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		logs.debug(MessageFormat.format(LoggingMsg.NUMBER_OF_MENUE_ITEMS, SelectorUtil.numberOfFoundElements.get()));
		getCurrentFunctionName(false);
		return (SelectorUtil.numberOfFoundElements.get());
        } catch (Exception e) {
        	return "0";
		}	
	}
	
	public static String getSelectedSizeItem() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, QuickShopSelectors.sizeDropBoxItemSelected));
		subStrArr.add(QuickShopSelectors.sizeDropBoxItemSelected);
		
		String size = SelectorUtil.getTextOfItemNumber(subStrArr,0);
		logs.debug("Size Of Item: "+ size);
		getCurrentFunctionName(false);
		return size;	
	}
	
	public static String getQuickshopProductDescription() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, QuickShopSelectors.productDescriptionText));
		subStrArr.add(QuickShopSelectors.productDescriptionText);
		
		String productDes = SelectorUtil.getTextOfItemNumber(subStrArr,0);
		logs.debug("Product Description Of Item: "+ productDes);
		getCurrentFunctionName(false);
		return productDes;	
	}
	
	public static void clickQuickshopFullDetails() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, QuickShopSelectors.productFullDetailsBtn));
		subStrArr.add(QuickShopSelectors.productFullDetailsBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	
	public static String getPDPPrice() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.price);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	public static String getPDPTitle() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.title);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	
	public static String getPDPDesc() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.desc);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	public static String getNameOfSelectedColor() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.quickShopColorSelectedBtn);
		valuesArr.add("");
		SelectorUtil.getAttr(subStrArr, QuickShopSelectors.quickShopColorSelectedName);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
	
	public static String getCurrentNameOfSelectedColor() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(QuickShopSelectors.quickShopCurrentColorName);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		return SelectorUtil.textValue.get();
	}
}
