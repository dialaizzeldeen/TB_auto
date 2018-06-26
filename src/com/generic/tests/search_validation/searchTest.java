package com.generic.tests.search_validation;

import java.text.MessageFormat;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import com.generic.page.SearchResults;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class searchTest extends SelTestCase {
	
	public static final String testDataSheet = SheetVariables.SearchSheet;
	int ExpectedNumberOfMenuItems = 4;
	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
	}
	
	@DataProvider(name = "Search", parallel = true)

	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}
	
	@Test(dataProvider = "Search")
	public void verifySearchResultsPage(String caseId, String runTest, String desc, 
			String searchValues, String country, String currency, String click,
			String searchResultView, String SearchResultFacets) throws Exception {
		
		Testlogs.set(new SASLogger("Search" + getBrowserName()));

		setTestCaseReportName("Search Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		
		try{
			
			
			if(desc.equals("Search results page with valid product")){
				
				String[] curr = currency.split("\n");
				SearchResults.clickShipto();
				SearchResults.changeCountryAndUpdate(country);
				
				//HomePage.closeSubcriptionPopup();
				
				SearchResults.clickSearchBtn();
				SearchResults.typeSearchText(searchValues);	
				Thread.sleep(1000);

				int numberOfMenuItems = Integer.parseInt(SearchResults.getNumberOfMenuItems());
				sassert().assertTrue(numberOfMenuItems == ExpectedNumberOfMenuItems);
				
				for (int index = 0; index < numberOfMenuItems-1; index++) {

						SearchResults.getNthResponsiveListItemColumn(0, index);
						SearchResults.getNthResponsiveListItemImg(index);
						String title = SearchResults.getTitleOfItem(index);
						String price = SearchResults.getPriceOfItem(index);
					    sassert().assertTrue(title.toLowerCase().contains(searchValues.toLowerCase()),"<font color=#f442cb>NOT All title are ok</font>");
					    sassert().assertTrue(price.contains(curr[1]),"<font color=#f442cb>NOT All price are ok</font>");
					    sassert().assertTrue(SearchResults.checkNthResponsiveListItemImg(index),"<font color=#f442cb>NOT All product images are ok</font>");				
				}
				
				if(click.equals("True")){
					SearchResults.typeSearchTextAndPressEnter(searchValues);
					
				if (!"".equals(searchResultView)){
					if (searchResultView.contains("Grid")) {
						sassert().assertTrue(SearchResults.checkGridViewSelected(), "Grid view is not selected by default");
						sassert().assertTrue(SearchResults.checkGridViewProducts(), "Products are not displayed as expected");
						sassert().assertTrue(SearchResults.checkGridViewQuickshop(), "Quickshop button is displayed as expected");
						sassert().assertTrue(SearchResults.checkGridViewProductDes(), "Product Name is not displayed as expected");
						sassert().assertTrue(SearchResults.checkGridViewProductPrice(), "Product price is not displayed as expected");
						SearchResults.clickGridViewQuickshop();
						Thread.sleep(3000);

						
						sassert().assertTrue(SearchResults.checkQuickshopModalContent(), "Quickshop modal is not displayed as expected");					
					}
					else if (searchResultView.contains("List")) {
						sassert().assertTrue(SearchResults.checkGridViewSelected(), "Grid view is not selected by default");
						SearchResults.clickListView();
						sassert().assertTrue(SearchResults.checkListViewSelected(), "List view is not selected as expected");
						sassert().assertTrue(SearchResults.checkListViewProducts(), "Products are not displayed as expected");
						sassert().assertTrue(SearchResults.checkListViewQuickshop(), "Quickshop button is displayed as expected");
						sassert().assertTrue(SearchResults.checkListViewProductTitle(), "Product Name is not displayed as expected");
						sassert().assertTrue(SearchResults.checkListViewProductPrice(), "Product price is not displayed as expected");
						SearchResults.clickListViewQuickshop();
						Thread.sleep(3000);
						sassert().assertTrue(SearchResults.checkQuickshopModalContent(), "Quickshop modal is not displayed as expected");						
						sassert().assertTrue(SearchResults.checkListViewSelected(), "Listview is not persist for all categories");
					}
				}
				
				if (!"".equals(SearchResultFacets)) {

					if (SearchResultFacets.contains("Category")) {
						if (SearchResults.isCategoryFilterAvailable()) {
							int itemsNumber = SearchResults.countProductsInPage();
							SearchResults.clickFirstCategoryFilter();
							Thread.sleep(2000);
							sassert().assertTrue(SearchResults.countProductsInPage() <= itemsNumber, "Category Filter is not applied correctly");
						}
					}
					
					
				}
				
				
				
			}
				Thread.sleep(1500);
		}
			
			else if(desc.contains("currency")){
				
				String[] curr = currency.split("\n");
				SearchResults.clickShipto();
				SearchResults.changeCountryAndCurrencyAndUpdate(country,curr[0]);
				
				SearchResults.clickSearchBtn();
				SearchResults.typeSearchText(searchValues);	
				Thread.sleep(1000);

				int numberOfMenuItems = Integer.parseInt(SearchResults.getNumberOfMenuItems());
				sassert().assertTrue(numberOfMenuItems == ExpectedNumberOfMenuItems);
				
				for (int index = 0; index < numberOfMenuItems; index++) {

						SearchResults.getNthResponsiveListItemColumn(0, index);
						SearchResults.getNthResponsiveListItemImg(index);
						String title = SearchResults.getTitleOfItem(index);
						String price = SearchResults.getPriceOfItem(index);
					    sassert().assertTrue(title.toLowerCase().contains(searchValues.toLowerCase()),"<font color=#f442cb>NOT All title are ok</font>");
					    sassert().assertTrue(price.contains(curr[1]),"<font color=#f442cb>NOT All price are ok</font>");
					    sassert().assertTrue(SearchResults.checkNthResponsiveListItemImg(index),"<font color=#f442cb>NOT All product images are ok</font>");				
				}			
				Thread.sleep(1500);
			}
			
			else if(desc.contains("three character")){
				
				SearchResults.clickSearchBtn();
				SearchResults.typeSearchText(searchValues);	
				Thread.sleep(1000);

				int numberOfMenuItems = Integer.parseInt(SearchResults.getNumberOfSuggestion());

				for (int index = 0; index < numberOfMenuItems; index++) {

						String title = SearchResults.getTitleOfSuggestionItem(index);
					    sassert().assertTrue(title.toLowerCase().contains(searchValues.toLowerCase()),"<font color=#f442cb>NOT All title are ok</font>");
				}			
				Thread.sleep(1500);
			}
			
			else if(desc.contains("invalid")){
				
				String[] curr = currency.split("\n");
				
				//HomePage.closeSubcriptionPopup();
				
				SearchResults.clickSearchBtn();
				SearchResults.typeSearchText(searchValues);	
				Thread.sleep(1000);

				int numberOfMenuItems = Integer.parseInt(SearchResults.getNumberOfMenuItems());
				sassert().assertTrue(numberOfMenuItems == ExpectedNumberOfMenuItems);
				
				for (int index = 0; index < numberOfMenuItems; index++) {

						SearchResults.getNthResponsiveListItemColumn(0, index);
						SearchResults.getNthResponsiveListItemImg(index);
						String title = SearchResults.getTitleOfItem(index);
						String price = SearchResults.getPriceOfItem(index);
					    sassert().assertTrue(title.toLowerCase().contains(searchValues.substring(0, 3).toLowerCase()),"<font color=#f442cb>NOT All title are ok</font>");
					    sassert().assertTrue(price.contains(curr[1]),"<font color=#f442cb>NOT All price are ok</font>");
					    sassert().assertTrue(SearchResults.checkNthResponsiveListItemImg(index),"<font color=#f442cb>NOT All product images are ok</font>");				
				}
				Thread.sleep(1500);
			}
			
			else if(desc.contains("empty")){
				
				SearchResults.clickSearchBtn();
				SearchResults.typeSearchText(searchValues);	
				Thread.sleep(1000);

				int numberOfMenuItems = Integer.parseInt(SearchResults.getNumberOfMenuItems());
				sassert().assertTrue(numberOfMenuItems == 0);
			}
				
				sassert().assertAll();
			
		}
		catch (Throwable t) {
			setTestCaseDescription(getTestCaseDescription());
			Testlogs.get().debug(MessageFormat.format(LoggingMsg.DEBUGGING_TEXT, t.getMessage()));
			t.printStackTrace();
			String temp = getTestCaseReportName();
			Common.testFail(t, temp);
			ReportUtil.takeScreenShot(getDriver());
			Assert.assertTrue(false, t.getMessage());
		}	
	}
}