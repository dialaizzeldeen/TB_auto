/**
 * this generic test for PDP regression that will pull tests from PDPRegression tab from
 * datasheet.xlsx. 
*/
package com.generic.tests.PDP;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.generic.page.PDP;
import com.generic.page.Registration;
import com.generic.page.SignIn;
import com.generic.setup.Common;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.RandomUtilities;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.dataProviderUtils;

public class Base_PDP extends SelTestCase {

	private static LinkedHashMap<String, Object> invintory = null ;
	private static LinkedHashMap<String, Object> users;

	// user types
	public static final String guestUser = "guest";
	public static final String loggedInUser = "loggedin";

	// used sheet in test
	public static final String testDataSheet = SheetVariables.PDPSheet;

	private static XmlTest testObject;
	private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
	private String email;
	LinkedHashMap<String, Object> productDetails;

	@BeforeClass
	public static void initialSetUp(XmlTest test) throws Exception {
		testCaseRepotId = SheetVariables.PDPCaseId;
		Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
		testObject = test;
		invintory = Common.readLocalInventory();
		users = Common.readUsers();
	}

	@DataProvider(name = "PDPs", parallel = true)
	public static Object[][] loadTestData() throws Exception {
		// concurrency maintenance on sheet reading
		getBrowserWait(testObject.getParameter("browserName"));
		dataProviderUtils TDP = dataProviderUtils.getInstance();
		Object[][] data = TDP.getData(testDataSheet);
		Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
		return data;
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "PDPs")
	public void checkOutBaseTest(String caseId, String runTest, String desc, String proprties, String product,
			String email, String ValidationMsg) throws Exception {
		// Important to add this for logging/reporting
		Testlogs.set(new SASLogger("PDP_" + getBrowserName()));
		setTestCaseReportName("PDP Case");
		logCaseDetailds(MessageFormat.format(LoggingMsg.CARTDESC, testDataSheet + "." + caseId,
				this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));
		this.email = getSubMailAccount(email);
		try {

			
			LinkedHashMap<String, Object> productDetails = (LinkedHashMap<String, Object>) invintory.get(product);
			LinkedHashMap<String, Object> usersdetails = (LinkedHashMap<String, Object>) users.get(email);

			Testlogs.get().debug("productDetails" + Arrays.asList(productDetails));
			Testlogs.get().debug("url key " + PDP.keys.url);
			Testlogs.get().debug("url key value " + (String) productDetails.get(PDP.keys.url));
			getDriver().get((String) productDetails.get(PDP.keys.url));
			Testlogs.get().debug("selecting amount");
//Diala izz
			String ToName =(String) usersdetails.get(Registration.keys.firstName);
            String amount=(String)  productDetails.get(PDP.keys.price);
            String FromName= RandomUtilities.getRandomName();
			if (proprties.contains("Virtual Gift Card verification")) {
				PDP.addVGCToCart(amount, this.email, this.email, ToName,FromName, "");
				sassert().assertTrue(!PDP.getProductQtyInCartPopup().equals(""), "product is not added as expeceted");
			}
			
			if (proprties.contains("Traditional Gift Card verification")) {
				PDP.addTGCToCart(amount, this.email,ToName, FromName, "");
				sassert().assertTrue(!PDP.getProductQtyInCartPopup().equals(""), "product is not added as expeceted");

			}
			
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

}
