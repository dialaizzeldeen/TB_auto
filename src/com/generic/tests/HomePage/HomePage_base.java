package com.generic.tests.HomePage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;
import com.generic.setup.Common;
import com.generic.setup.EnvironmentFiles;
import com.generic.setup.LoggingMsg;
import com.generic.setup.PagesURLs;
import com.generic.setup.SelTestCase;
import com.generic.setup.SheetVariables;
import com.generic.util.ReportUtil;
import com.generic.util.SASLogger;
import com.generic.util.SelectorUtil;
import com.generic.util.dataProviderUtils;
import com.generic.page.HomePage;
import com.generic.selector.HomePageSelectors;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class HomePage_base extends SelTestCase {
	    private static LinkedHashMap<String, Object> users;
		public static final String testDataSheet = SheetVariables.HomePageSheet;
		private static ThreadLocal<String> products = new ThreadLocal<String>();
		private static XmlTest testObject;
		private static ThreadLocal<SASLogger> Testlogs = new ThreadLocal<SASLogger>();
		LinkedHashMap<String, Object> productDetails;

		@BeforeClass
		public static void initialSetUp(XmlTest test) throws Exception {
			testCaseRepotId = SheetVariables.cartCaseId;
			Testlogs.set(new SASLogger(test.getName() + test.getIndex()));
			testObject = test;
			users = Common.readUsers();
		}

		@DataProvider(name = "HomePage", parallel = true)
		public static Object[][] loadTestData() throws Exception {
			// concurrency mentainance on sheet reading
			getBrowserWait(testObject.getParameter("browserName"));
			dataProviderUtils TDP = dataProviderUtils.getInstance();
			Object[][] data = TDP.getData(testDataSheet);
			Testlogs.get().debug(Arrays.deepToString(data).replace("\n", "--"));
			return data;
		}

		@SuppressWarnings("unchecked")
		@Test(dataProvider = "HomePage")
		public void HomePageValidationTest(String caseId,String runTest, String desc, String proprties,String email, String baseline) {

			Testlogs.set(new SASLogger("HomePage "+getBrowserName()));
			//Important to add this for logging/reporting 
			setTestCaseReportName("Home Case");
			//Testlogs.get().debug("Case Browser: "  + testObject.getParameter("browserName") );
			logCaseDetailds(MessageFormat.format(LoggingMsg.TEST_CASE_DESC, testDataSheet + "." + caseId,
					this.getClass().getCanonicalName(), desc, proprties.replace("\n", "<br>- ")));
			//this.email.set(getSubMailAccount(email));
			LinkedHashMap<String, Object> userdetails = (LinkedHashMap<String, Object>) users.get(email);

		try {
			String baseline_browser = baseline + "_" + getBrowserName().replace(" ", "_");
			String url = PagesURLs.getHomePage();
			getDriver().get(url);

			if (proprties.contains("update logo")) {

				HomePage.updateLogoBaseline(baseline_browser);
			}
			if (proprties.contains("update RH for United State")) {

				HomePage.updateBaselineForRH(baseline_browser);
			}
			if (proprties.contains("update RH for internationally")) {

				HomePage.updateBaselineForRH(baseline_browser);
			}
			if (proprties.contains("update footer")) {

				HomePage.updateFooterBaseline(baseline_browser);
			}

			if (proprties.contains("verify logo")) {

				sassert().assertTrue(HomePage.verifyLogo(baseline_browser), "logo error ");
			}
			if (proprties.contains("verify MyAccountBttn for US and CAD users")) {

				sassert().assertTrue(HomePage.checkMyAccountBttn("United States"));
			}

			if (proprties.contains("verify MyAccountBttn is not all international users")) {
				HomePage.changeCountry("Germany");
				sassert().assertTrue(HomePage.checkMyAccountBttn("Germany"));
			}

			if (proprties.contains("verify RH for United State")) {

				sassert().assertTrue(HomePage.verifyRightHeader(baseline_browser), "right header error for US");
			}
			if (proprties.contains("verify RH for internationally")) {
				sassert().assertTrue(HomePage.verifyRightHeader(baseline_browser),
						"right header error for internationally");

			}

			if (proprties.contains("verify ShipTo US")) {

				sassert().assertTrue(HomePage.ShipTo().contains("US"));
			}
			if (proprties.contains("verify ShipTo IU")) {
				HomePage.changeCountry("Germany");
				sassert().assertTrue(HomePage.ShipTo().contains("DE"));
			}
			if (proprties.contains("verify footer")) {

				sassert().assertTrue(HomePage.verifyFooter(baseline_browser), "footer error  ");
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

