package com.xtrazcon.cloud.test.pageactions;

import com.xtrazcon.cloud.test.pageobject.pageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import test.Common;

import java.util.Map;

public class trainerPage extends Common {
    Map<String, String> trainer = super.readFileWithJavaProperties(pageObject.trainer);
//    Common common;

    public trainerPage(WebDriver driver)  {
        super(driver);
    }

    public void selecttrainerType(String trainerType) throws Exception {
        try{

            System.out.println("hem"+retriveLocators(dynamicXpathGenerator(trainer.get("loginAsTrainer"),trainerType)));
            super.elementClick(retriveLocators(dynamicXpathGenerator(trainer.get("loginAsTrainer"),trainerType)));
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void setTrainerUserName(String userName) {
        try {
            super.enterText(retriveLocators(trainer.get("emailtbx")), userName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void setTrainerPassword(String password){
        try{
            super.enterText(retriveLocators(trainer.get("password")), password);


        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public void clicktrainerbtn(){
        try{
            System.out.println("hemadrirrexjdsjzxlkxlk");
            super.elementClick(retriveLocators(trainer.get("loginbtn")));
//             if(!super.isElementVisible(retriveLocators(trainer.get("siteLogo"))));{
//                 driver.findElement(retriveLocators(trainer.get("password"))).sendKeys(Keys.ENTER);
//             }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
