package com.generic.tests.QuickShop;

import java.text.MessageFormat;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.QuickShop;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class QuickShopValidation extends SelTestCase {
	
	public static final String testDataSheet = SheetVariables.QuickShopSheet;
	private static XmlTest testObject;

	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	@BeforeTest
	public static void initialSetUp(XmlTest test) throws Exception {
		Testlogs.set(new SASLogger(""));
		testObject = test;
	}
	
	@DataProvider(name = "QuickShop", parallel = true)

	public static Object[][] loadTestData() throws Exception {
		getBrowserWait(testObject.getParameter("browserName"));

		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}
	
	@Test(dataProvider = "QuickShop")
	public void verifyQuickShop(String caseId, String runTest, String desc, 
			String prop,String PLPView) throws Exception {
		
		Testlogs.set(new SASLogger("QuickShop" + getBrowserName()));

		setTestCaseReportName("QuickShop Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc));
		
		try{
			
			String url = PagesURLs.getPLP();
			getDriver().get(url);
			
			
			if (!"".equals(PLPView)){
				if (PLPView.contains("Grid")) {

					String titleAtPLPPage = QuickShop.getGridViewFirstProductDes();
					String priceAtPLPPage = QuickShop.getGridViewFirstProductPrice();
					
					QuickShop.clickQuickshopBtn();
					
					String titleAtQuickShop = QuickShop.getQuickshopDes();
					String priceAtQuickShop = QuickShop.getQuickshopPrice();
					
					if(QuickShop.checkQuickshopSizeLabel()){
						sassert().assertTrue(QuickShop.getQuickshopDroBoxSizeDefault().equals(""), "Default of size not empty");
						int numberOfQuantityItems = Integer.parseInt(QuickShop.getNumberOfQuantityItems());
						sassert().assertTrue(numberOfQuantityItems == 1, "Quantity in sot the default");
						sassert().assertTrue(QuickShop.checkQuickshopAddToBagDisable(), "Quickshop add to bag is not displayed as expected");
						
						QuickShop.clickOnSizeDropBox();
						Thread.sleep(3000);
						QuickShop.chooseFirstSize();
						Thread.sleep(3000);
						String size = QuickShop.getSelectedSizeItem();
						sassert().assertTrue(!size.equals(""), "size not ok");
						Thread.sleep(3000);
						sassert().assertTrue(QuickShop.checkQuickshopAddToBagEnable(), "Quickshop add to bag is not displayed as expected");

					}
					
					sassert().assertTrue(titleAtPLPPage.equals(titleAtQuickShop), "Title not similar for both (PLP,QS)");
					sassert().assertTrue(priceAtPLPPage.equals(priceAtQuickShop), "Price not similar for both (PLP,QS)");
					sassert().assertTrue(QuickShop.getCurrentNameOfSelectedColor().equals(QuickShop.getNameOfSelectedColor()), "Color name not similar for both");

					QuickShop.clickQuickshopCloseBtn();					

					QuickShop.clickQuickshopBtn();
					String descriptionATQuickShop = QuickShop.getQuickshopProductDescription();
					QuickShop.clickQuickshopFullDetails();
					sassert().assertTrue(QuickShop.getPDPTitle().equals(titleAtQuickShop), "Title not similar for both (PDP,QS)");
					sassert().assertTrue(QuickShop.getPDPPrice().equals(priceAtQuickShop), "Price not similar for both (PDP,QS)");
					sassert().assertTrue(QuickShop.getPDPDesc().equals(descriptionATQuickShop), "Desc not similar for both (PDP,QS)");
					
				}
			}		
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
