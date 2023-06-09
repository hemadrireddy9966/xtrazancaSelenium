package com.xtrazcon.cloud.test.Steps;

import com.xtrazcon.cloud.test.cache.Cache;
import com.xtrazcon.cloud.test.pageactions.learnerPage;
import com.xtrazcon.cloud.test.pageactions.loginpage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class learnerStepDef extends Cache {
    public learnerStepDef(){
//        this.common=new Common(DriverFactor.driver);
        super();
    }
    learnerPage l=super.getLearnerPage();
    loginpage lp=super.getLoginPage();

    @Given("User opens learner {string} login page")
    public void user_opens_learner_login_page(String logintype) throws Exception {
        System.out.println("checking");
        l.selectlearnerType(logintype);
    }

    @When("User fills emailid {string} as learner")
    public void user_fills_emailid(String emailid) {
        l.setLearnerUserName(emailid);
    }

    @When("User fills the password {string} as learner")
    public void user_fills_the_password(String password) {
        l.setLearnerPassword(password);
        l.clicklearnerbtn();
    }

    @Then("User should be able to verify learner dashboard")
    public void user_should_be_able_to_verify_dashboard() {
      lp.homePageLogoVerify();

    }

}
