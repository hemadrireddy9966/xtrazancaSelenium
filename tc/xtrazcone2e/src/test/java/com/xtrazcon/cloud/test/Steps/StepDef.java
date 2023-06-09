package com.xtrazcon.cloud.test.Steps;

import com.xtrazcon.cloud.test.cache.Cache;
import com.xtrazcon.cloud.test.pageactions.loginpage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef extends Cache {
//    Common common;
    public StepDef(){
//        this.common=new Common(DriverFactor.driver);
        super();
    }
       loginpage l=super.getLoginPage();

    @Given("User opens corporate {string} login page")
    public void user_opens_corporate_login_page(String logintype) throws Exception {
        System.out.println("checking");
        l.selectLoginType(logintype);
    }

    @When("User fills emailid {string}")
    public void user_fills_emailid(String emailid) {
        l.setUserName(emailid);
    }

    @When("User fills the password {string}")
    public void user_fills_the_password(String password) {
        l.setPassword(password);
        l.clickLoginbtn();
    }

    @Then("User should be able to verify dashboard")
    public void user_should_be_able_to_verify_dashboard()  {
        l.homePageLogoVerify();
            l.logOut();
    }
}
