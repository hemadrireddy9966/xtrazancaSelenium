package com.xtrazcon.cloud.test.Steps;

import com.xtrazcon.cloud.test.cache.Cache;
import com.xtrazcon.cloud.test.pageactions.loginpage;
import com.xtrazcon.cloud.test.pageactions.trainerPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class trainerStepDef extends Cache {

    public trainerStepDef(){
//        this.common=new Common(DriverFactor.driver);
        super();
    }
    trainerPage l=super.getTrainerPage();
    loginpage lp=super.getLoginPage();
    @Given("User opens trainer {string} login page")
    public void user_opens_trainer_login_page(String logintype) throws Exception {
        System.out.println("checking");
        l.selecttrainerType(logintype);
    }

    @When("User fills emailid {string} as trainer")
    public void user_fills_emailid(String emailid) {
        l.setTrainerUserName(emailid);
    }

    @When("User fills the password {string} as trainer")
    public void user_fills_the_password(String password) {
        l.setTrainerPassword(password);
        l.clicktrainerbtn();
    }

    @Then("User should be able to verify trainer dashboard")
    public void user_should_be_able_to_verify_dashboard() {
        lp.homePageLogoVerify();
    }
}
