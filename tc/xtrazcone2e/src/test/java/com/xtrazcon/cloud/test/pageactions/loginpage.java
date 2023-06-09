package com.xtrazcon.cloud.test.pageactions;

import com.xtrazcon.cloud.test.pageobject.pageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test.Common;

import java.util.Map;
import java.util.logging.Logger;

public class loginpage extends Common {
    private static final Logger log = Logger.getLogger(loginpage.class.getName());
    Map<String, String> login = super.readFileWithJavaProperties(pageObject.corporate);
//    Common common;

    public loginpage(WebDriver driver)  {
        super(driver);
    }

public void selectLoginType(String loginType) throws Exception {
        try{

            System.out.println("hem"+retriveLocators(dynamicXpathGenerator(login.get("loginAsCorporate"),loginType)));
            super.elementClick(retriveLocators(dynamicXpathGenerator(login.get("loginAsCorporate"),loginType)));
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
}
    public void setUserName(String userName) {
        try {
            super.enterText(retriveLocators(login.get("emailtbx")), userName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void setPassword(String password){
            try{
                super.enterText(retriveLocators(login.get("password")), password);
            } catch(Exception e){
                throw new RuntimeException(e);
            }
    }
    public void clickLoginbtn(){
        try{
            System.out.println("hemadrirrexjdsjzxlkxlk");
            super.elementClick(retriveLocators(login.get("loginbtn")));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
}
    public void homePageLogoVerify(){
        try{
           Boolean b= super.isElementVisible(retriveLocators(login.get("siteLogo")));
            Assert.assertTrue(b);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
}
public void logOut() {
        try{
           super.elementClick(retriveLocators(login.get("homebtn")));
           super.elementClick(retriveLocators(login.get("toLogot")));
           super.elementClick(retriveLocators(login.get("logoutbtn")));
        }catch(Exception e){
        throw new RuntimeException(e);
        }
}


}

