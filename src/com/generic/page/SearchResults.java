package com.generic.page;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import com.generic.selector.SearchResultSelectors;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

public class SearchResults extends SelTestCase {
    
    public static class keys {
		public static final String caseId = "caseId";
	}
    public static void typeSearchText(String searchValue) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE,SearchResultSelectors.searchInput, searchValue));
		subStrArr.add(SearchResultSelectors.searchInput);
		valuesArr.add(searchValue);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void typeSearchTextAndPressEnter(String searchValue) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE,SearchResultSelectors.searchInput, searchValue));
		subStrArr.add(SearchResultSelectors.searchInput);
		valuesArr.add(searchValue + ",pressEnter");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static String getNumberOfMenuItems() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.searchMenuItems);
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
    
    public static String getNumberOfSuggestion() throws Exception {
    	getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.searchMenuItemsWithSuggestion);
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
    
    public static String getNthResponsiveListItemColumn(int nthChild, int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();		
		String selText = MessageFormat.format(SearchResultSelectors.nthResponsiveListItemColumn, nthChild);
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, selText));
		subStrArr.add(selText);
		valuesArr.add("index," + index);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
		logs.debug(SelectorUtil.textValue.get());
		return SelectorUtil.textValue.get();
	}
    
    public static boolean checkNthResponsiveListItemImg(int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, SearchResultSelectors.nthResponsiveListItemListImg));
		subStrArr.add(SearchResultSelectors.nthResponsiveListItemListImg);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr, index);
		logs.debug("images check result is " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;		
	}
    
    public static String getNthResponsiveListItemImg(int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, SearchResultSelectors.nthResponsiveListItemListImg));
		subStrArr.add(SearchResultSelectors.nthResponsiveListItemListImg);
		String src = SelectorUtil.getAttr(subStrArr, "src", index);
		logs.debug("image src is: " + src);
		getCurrentFunctionName(false);
		return src;
	}
    
    
    public static String getTitleOfItem(int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, SearchResultSelectors.titleOfItem));
		subStrArr.add(SearchResultSelectors.titleOfItem);
		
		String title = SelectorUtil.getTextOfItemNumber(subStrArr,index);
		logs.debug("Title Of Item" +index+": "+ title);
		getCurrentFunctionName(false);
		return title;
	}
    
    public static String getPriceOfItem(int index) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, SearchResultSelectors.priceOfItem));
		subStrArr.add(SearchResultSelectors.priceOfItem);
		
		String price = SelectorUtil.getTextOfItemNumber(subStrArr,index);
		logs.debug("Price Of Item" +index+": "+ price);
		getCurrentFunctionName(false);
		return price;	
	}
    
    public static void clickSearchBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.searchBtn));
		subStrArr.add(SearchResultSelectors.searchBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    
    public static void clickShipto () throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.shipToBtn));
		subStrArr.add(SearchResultSelectors.shipToBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
    

	public static void changeCountryAndUpdate(String country) throws Exception {
		// TODO Auto-generated method stub
		changeCountry(country);
		UpdateCountryAndCurrency();		
	}
	
    public static void changeCountry(String country) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "Country ", country));
		subStrArr.add(SearchResultSelectors.selectShipToCountry);
		valuesArr.add(country);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		Thread.sleep(1500);
		getCurrentFunctionName(false);
		
	}
    
    public static void changeCurrency(String currency) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.SELECTING_ELEMENT_VALUE, "currency ", currency));
		subStrArr.add(SearchResultSelectors.selectCurrency);
		valuesArr.add(currency);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		Thread.sleep(1500);
		getCurrentFunctionName(false);
		
	}

	public static void UpdateCountryAndCurrency() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.updateCountryAndCurrencyBtn));
		subStrArr.add(SearchResultSelectors.updateCountryAndCurrencyBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void changeCountryAndCurrencyAndUpdate(String country, String currency) throws Exception {
		// TODO Auto-generated method stub
		changeCountry(country);
		changeCurrency(currency);
		UpdateCountryAndCurrency();
	}
	
	public static void changeCurrencyAndUpdate(String currency) throws Exception {
		// TODO Auto-generated method stub
		changeCurrency(currency);
		UpdateCountryAndCurrency();
	}

	public static String getTitleOfSuggestionItem(int index) throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();		
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, SearchResultSelectors.titleOfSuggestion));
		subStrArr.add(SearchResultSelectors.titleOfSuggestion);
		
		String title = SelectorUtil.getTextOfItemNumber(subStrArr,index);
		logs.debug("Title Of Item" +index+": "+ title);
		getCurrentFunctionName(false);
		return title;	
	}
	
	public static void clickGridView() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.gridView));
		subStrArr.add(SearchResultSelectors.gridView);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void clickListView() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.listView));
		subStrArr.add(SearchResultSelectors.listView);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	
	
	public static void clickQuickshopBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.quickShopBtn));
		subStrArr.add(SearchResultSelectors.quickShopBtn);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static boolean checkGridViewSelected() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.gridViewSelected);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Grid view is selected by default " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkListViewSelected() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.listViewSelected);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("List view is selected as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkGridViewProducts() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.gridViewProducts);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Products are displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkListViewProducts() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.listViewProducts);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Products are displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkListViewQuickshop() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.listViewQuickshop);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("list view Quickshop is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static void clickListViewQuickshop() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.listViewQuickshop));
		subStrArr.add(SearchResultSelectors.listViewQuickshop);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static boolean checkListViewProductTitle() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.listViewProductTitle);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Product Title is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkListViewProductPrice() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.listViewProductPrice);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Product price is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkGridViewQuickshop() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.gridViewQuickshop);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Grid view Quickshop is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static void clickGridViewQuickshop() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.gridViewQuickshop));
		subStrArr.add(SearchResultSelectors.gridViewQuickshop);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static boolean checkGridViewProductDes() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.gridViewProductDes);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Product Name is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkGridViewProductPrice() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.gridViewProductPrice);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Product price is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean checkQuickshopModalContent() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.quickshopModalContent);
		boolean isDisplayed = SelectorUtil.isDisplayed(subStrArr);
		logs.debug("Quickshop modal is displayed as expected " + isDisplayed);
		getCurrentFunctionName(false);
		return isDisplayed;
	}

	public static boolean isCategoryFilterAvailable() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		subStrArr.add(SearchResultSelectors.ctaegoryFilter);
		boolean isDisplayed = false;
		try {
			isDisplayed = SelectorUtil.isDisplayed(subStrArr);
			logs.debug("Style filter is availabe for this category");
		} catch (NoSuchElementException e) {
			logs.debug("Style filter is not availabe for this category");
		}
		getCurrentFunctionName(false);
		return isDisplayed;
	}
	
	public static int countProductsInPage() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		logs.debug("counting the products ");
		subStrArr.add(SearchResultSelectors.productLink);
		int itemsNumber = SelectorUtil.getAllElements(subStrArr).size();
		logs.debug("product count is :" + itemsNumber);
		getCurrentFunctionName(false);
		return itemsNumber;
	}
	
	public static void clickFirstCategoryFilter() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.ctaegoryFilter));
		subStrArr.add(SearchResultSelectors.ctaegoryFilter);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}

	public static void clickOnFirstProduct() throws Exception {
		// TODO Auto-generated method stub
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.CLICKING_SEL, SearchResultSelectors.searchMenuItems));
		subStrArr.add(SearchResultSelectors.searchMenuItems);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);
	}
	
}