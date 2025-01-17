package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Listener;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(Listener.class)
public class Test_Happy_Flow_01_COD extends BaseTest {
	
	@Epic("Happy Flow COD-Delivered")
	@Feature("Verifying Happy Flow COD-Delivered")

	@Test(priority = 1, description = "Verify email and password",enabled=true)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Validating email and password")
	@Story("Story Name: To check email and password")
	public void verifyEmailTextboxAndPasswordTest() throws Exception {
		pomPages.POM_001_HappyFlow_COD verify = new pomPages.POM_001_HappyFlow_COD();
		verify.verifyEmailTextboxAndPassword();
	}

	@Test(priority = 2, description = "Verify sign in",enabled=true)
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Validating sign in")
	@Story("Story Name: To check sign in")
	public void verifySignInTest() throws Exception {
		pomPages.POM_001_HappyFlow_COD verify = new pomPages.POM_001_HappyFlow_COD();
		verify.verifySignIn();
	}

	@Test(priority = 3, description = "Verify dashboard")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Validating dashbaord")
	@Story("Story Name: To check dashboard")
	public void verifyDashboardTest() throws Exception {
		pomPages.POM_001_HappyFlow_COD verify = new pomPages.POM_001_HappyFlow_COD();
		verify.verifyDashboard();
	}

	@Test(priority = 4, description = "Verify client dashboard")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Validating client dashboard")
	@Story("Story Name: To check client dashboard")
	public void verifyClientDashboardTest() throws Exception {
		pomPages.POM_001_HappyFlow_COD verify = new pomPages.POM_001_HappyFlow_COD();
		verify.verifyClientDashboard();
	}

	@Test(priority = 5, description = "Verify company name")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Validating company name")
	@Story("Story Name: To check company name")
	public void verifyCompanyNameTest() throws Exception {
		pomPages.POM_001_HappyFlow_COD verify = new pomPages.POM_001_HappyFlow_COD();
		verify.verifyCompanyName();
	}

	@Test(priority = 6, description = "Verify view as dashboard")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Validating view as dashbaord")
	@Story("Story Name: To check view as dashboard")
	public void verifyViewAsDashboardTest() throws Exception {
		pomPages.POM_001_HappyFlow_COD verify = new pomPages.POM_001_HappyFlow_COD();
		verify.verifyViewAsDashboard();
	}

	@Test(priority = 7, description = "Verify order clone")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Validating order clone")
	@Story("Story Name: To check order clone")
	public void verifyOrderCloneTest() throws Exception {
		pomPages.POM_001_HappyFlow_COD verify = new pomPages.POM_001_HappyFlow_COD();
		verify.verifyOrderClone();
	}

	@Test(priority = 8, description = "Verify message received at whatsapp along with query upadted on database")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Description: Validating message received at whatsapp along with query upadted on database")
	@Story("Story Name: To check message received at whatsapp along with query upadted on database")
	public void verifyMsgReceivedTest() throws Exception {
		pomPages.POM_001_HappyFlow_COD verify = new pomPages.POM_001_HappyFlow_COD();
		verify.verifyMsgReceived();
	}

}
