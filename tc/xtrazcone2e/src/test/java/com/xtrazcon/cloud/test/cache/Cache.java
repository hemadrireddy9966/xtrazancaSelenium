package com.xtrazcon.cloud.test.cache;

import com.xtrazcon.cloud.test.pageactions.learnerPage;
import com.xtrazcon.cloud.test.pageactions.loginpage;
import com.xtrazcon.cloud.test.pageactions.trainerPage;
import org.openqa.selenium.WebDriver;
import test.Common;
import test.DriverFactor;

public class Cache {
    private WebDriver driver;
Common common;
    public Cache() {
this.driver= DriverFactor.getDriver();
    }
    public loginpage getLoginPage(){
//        loginpage onboard;
//        try {
        loginpage onboard = new loginpage(driver);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return onboard;
    }
    public learnerPage getLearnerPage(){
        learnerPage l=new learnerPage(driver);
        return l;
    }
    public trainerPage getTrainerPage(){
        trainerPage t=new trainerPage(driver);
        return t;
    }
}
