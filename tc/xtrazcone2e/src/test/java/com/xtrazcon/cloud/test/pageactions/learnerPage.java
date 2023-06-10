package com.xtrazcon.cloud.test.pageactions;

import com.xtrazcon.cloud.test.pageobject.pageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import test.Common;

import java.util.Map;

public class learnerPage extends Common {
    Map<String, String> learner = super.readFileWithJavaProperties(pageObject.learner);
//    Common common;

    public learnerPage(WebDriver driver)  {
        super(driver);
    }

    public void selectlearnerType(String learnerType) throws Exception {
        try{

            System.out.println("hem"+retriveLocators(dynamicXpathGenerator(learner.get("loginAsLearner"),learnerType)));
            super.elementClick(retriveLocators(dynamicXpathGenerator(learner.get("loginAsLearner"),learnerType)));
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void setLearnerUserName(String userName) {
        try {
            super.enterText(retriveLocators(learner.get("emailtbx")), userName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void setLearnerPassword(String password){
        try{
            super.enterText(retriveLocators(learner.get("password")), password);


        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void clicklearnerbtn(){
        try{
            System.out.println("hemadrirrexjdsjzxlkxlk");
            Thread.sleep(8000);
            super.elementClick(retriveLocators(learner.get("loginbtn")));
//             if(!super.isElementVisible(retriveLocators(learner.get("siteLogo"))));{
//                 driver.findElement(retriveLocators(learner.get("password"))).sendKeys(Keys.ENTER);
//             }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
