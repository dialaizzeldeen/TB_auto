package com.generic.page;


import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.generic.selector.CartSelectors;
import com.generic.selector.CheckOutSelectors;
import com.generic.selector.HomePageSelectors;
import com.generic.selector.MyAccount_Selectors;
import com.generic.setup.EnvironmentFiles;
import com.generic.setup.ExceptionMsg;
import com.generic.setup.LoggingMsg;
import com.generic.setup.SelTestCase;
import com.generic.util.SelectorUtil;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
/**
 * The Class HomePage.
 */
public class HomePage extends SelTestCase {
	
	public static void closeSubcriptionPopup() throws Exception {
		try{
			getCurrentFunctionName(true);
			List<String> subStrArr = new ArrayList<String>();
			logs.debug(MessageFormat.format(LoggingMsg.CLICKING_CLOSE_ICON, "subcription closes icon"));
			subStrArr.add(HomePageSelectors.subcriptionCloseIcon);
			try {
				SelectorUtil.clickButton(subStrArr, 1);
			}catch (Exception e) {
				logs.debug("subcription popup is not appearing");
			}
		getCurrentFunctionName(false);
	} catch (NoSuchElementException e) {
		logs.debug(MessageFormat.format(ExceptionMsg.PageFunctionFailed, new Object() {
		}.getClass().getEnclosingMethod().getName()));
		throw e;
	}
	}

	public static void prepareBaselineforLogs(String baseline) throws Exception {
		getCurrentFunctionName(true);
		String VTAs =EnvironmentFiles.getVisualTestingAssetsPath();
		String baselineAbsPath = VTAs + "/" + baseline+ ".png";
		String logs_dir = EnvironmentFiles.getLogFilePath();	
    	File baseLineFile = new File(baselineAbsPath);
		FileUtils.copyFileToDirectory(baseLineFile, Paths.get(logs_dir).toFile());
		String baselinePathInLogs =  logs_dir + "/" + baseline + ".png";
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline + ".png"+"><img src=" + baseline + ".png"+" alt=" + baseline + ".png"+" style=\"width:150px\"></a>");
		getCurrentFunctionName(false);
	}
	
	public static void updateLogoBaseline(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.logo);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+".png"+"><img src=" + baseline+".png"+" alt=" + baseline+".png"+" style=\"width:150px\"></a>");
		getCurrentFunctionName(false);
	}

	public static void updateFooterBaseline(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.footer);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+".png"+"><img src=" + baseline+".png"+" alt=" + baseline+".png"+" style=\"width:150px\"></a>");
		getCurrentFunctionName(false);
	}

	public static void updateBodyBaseline(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.body);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+".png"+"><img src=" + baseline+".png"+" alt=" + baseline+".png"+" style=\"width:150px\"></a>");
		getCurrentFunctionName(false);
	}

	public static boolean verifyHeader(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.header);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getLogFilePath() + "/" + baseline+"_actual.png";
		BufferedImage actualImage = SelectorUtil.screenShot.get().getImage();
		
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+"_actual.png"+"><img src=" + baseline+"_actual.png"+" alt=" + baseline+"_actual.png"+" style=\"width:150px\"></a>");
		
		String BaseImagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		
		BufferedImage expectedImage = ImageIO.read(new File(BaseImagePath));
        
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		
		getCurrentFunctionName(false);
		return !diff.hasDiff();
	}	
	
	public static Boolean checkMyAccountBttn(String country) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, HomePageSelectors.MyAccount));
		subStrArr.add(HomePageSelectors.MyAccount);
		Boolean myAccountBtn = false;
		if (country.contains("United states")) {
			myAccountBtn = SelectorUtil.isDisplayed(subStrArr);
		}
		else {
			myAccountBtn =SelectorUtil.isNotDisplayed(subStrArr);
		}
		//SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		//email = SelectorUtil.textValue.get();
	//	logs.debug("The user email found in my account page: " + email);
		getCurrentFunctionName(false);
		return myAccountBtn;
	}
	public static Boolean MyAccountBttn(String email) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, HomePageSelectors.MyAccount));
		subStrArr.add(HomePageSelectors.MyAccount);
		valuesArr.add("");
		
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		//email = SelectorUtil.textValue.get();
	//	logs.debug("The user email found in my account page: " + email);
		getCurrentFunctionName(false);
		return SelectorUtil.isDisplayed(subStrArr);
	}
	public static boolean verifyLogo(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.logo);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getLogFilePath() + "/" + baseline+"_actual.png";
		BufferedImage actualImage = SelectorUtil.screenShot.get().getImage();
		
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+"_actual.png"+"><img src=" + baseline+"_actual.png"+" alt=" + baseline+"_actual.png"+" style=\"width:150px\"></a>");
		
		String BaseImagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		
		BufferedImage expectedImage = ImageIO.read(new File(BaseImagePath));
        
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		
		getCurrentFunctionName(false);
		return !diff.hasDiff();
	}
	// by Diala
	
	public static void updateBaselineForRH(String baseline) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.RightHeader);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+".png"+"><img src=" + baseline+".png"+" alt=" + baseline+".png"+" style=\"width:150px\"></a>");
		getCurrentFunctionName(false);
	}
	public static boolean verifyRightHeader(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.RightHeader);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getLogFilePath() + "/" + baseline+"_actual.png";
		BufferedImage actualImage = SelectorUtil.screenShot.get().getImage();
		
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+"_actual.png"+"><img src=" + baseline+"_actual.png"+" alt=" + baseline+"_actual.png"+" style=\"width:150px\"></a>");
		
		String BaseImagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		
		BufferedImage expectedImage = ImageIO.read(new File(BaseImagePath));
        
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		
		getCurrentFunctionName(false);
		return !diff.hasDiff();
	}
	

	
	public static String ShipTo() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		logs.debug(MessageFormat.format(LoggingMsg.GET_ELEMENT_BY_LOCATOR, HomePageSelectors.ShipTo));
		subStrArr.add(HomePageSelectors.ShipTo);
	
		
		//email = SelectorUtil.textValue.get();
	//	logs.debug("The user email found in my account page: " + email);
		getCurrentFunctionName(false);
		return SelectorUtil.getAttr(subStrArr, "src");
	}
	public static boolean verifyFooter(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.footer);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getLogFilePath() + "/" + baseline+"_actual.png";
		BufferedImage actualImage = SelectorUtil.screenShot.get().getImage();
		
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+"_actual.png"+"><img src=" + baseline+"_actual.png"+" alt=" + baseline+"_actual.png"+" style=\"width:150px\"></a>");
		
		String BaseImagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		
		BufferedImage expectedImage = ImageIO.read(new File(BaseImagePath));
        
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		
		getCurrentFunctionName(false);
		return !diff.hasDiff();
	}

	public static boolean verifyBody(String baseline) throws Exception {
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		getCurrentFunctionName(true);
		logs.debug(MessageFormat.format(LoggingMsg.TYPING_ELEMENT_VALUE, "guest mail", baseline));
		subStrArr.add(HomePageSelectors.body);
		valuesArr.add("VisualTesting");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		String imagePath = EnvironmentFiles.getLogFilePath() + "/" + baseline+"_actual.png";
		BufferedImage actualImage = SelectorUtil.screenShot.get().getImage();
		
		ImageIO.write(SelectorUtil.screenShot.get().getImage(),"PNG",new File(imagePath));
		logs.debug("IMAGE:<br><a target=\"_blank\" href="+ baseline+"_actual.png"+"><img src=" + baseline+"_actual.png"+" alt=" + baseline+"_actual.png"+" style=\"width:150px\"></a>");
		
		String BaseImagePath = EnvironmentFiles.getVisualTestingAssetsPath() + "/" + baseline+".png";
		
		BufferedImage expectedImage = ImageIO.read(new File(BaseImagePath));
        
		ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		
		getCurrentFunctionName(false);
		return !diff.hasDiff();
	}

	
	
	
	public static void changeCountry(String country) throws Exception{
		clickShipToBtn();
		selectCountry(country);
		clickupdateCountryBtn();
	}
	
	public static void clickShipToBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
	//	logs.debug(MessageFormat.format(LoggingMsg.CLICKING_CLOSE_ICON, "subcription closes icon"));
		subStrArr.add(HomePageSelectors.shipToIcon);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
	
	public static void selectCountry(String country) throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
		subStrArr.add(HomePageSelectors.selectCountry);
		valuesArr.add(country);
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
	
	public static void clickupdateCountryBtn() throws Exception {
		getCurrentFunctionName(true);
		List<String> subStrArr = new ArrayList<String>();
		List<String> valuesArr = new ArrayList<String>();
	//	logs.debug(MessageFormat.format(LoggingMsg.CLICKING_CLOSE_ICON, "subcription closes icon"));
		subStrArr.add(HomePageSelectors.updateCountryLink);
		valuesArr.add("");
		SelectorUtil.initializeSelectorsAndDoActions(subStrArr, valuesArr);
		getCurrentFunctionName(false);

	}
}
