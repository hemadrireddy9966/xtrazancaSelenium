package test;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;
public class Common {

private static final Logger log = Logger.getLogger(Common.class.getName());
    public static By local_by = null;
//    public WebElement local_element = null;
    protected WebDriver driver;

    public String label = null;
    public Common(WebDriver driver) {
//        super(driver);
        this.driver=driver;
    }


    public String readPropertesFail(String s, String key) throws IOException {
        FileInputStream f = new FileInputStream(s);
        Properties p = new Properties();
        p.load(f);
        return p.getProperty(key);
    }

//    public Map<String, String> readFileWithJavaProperties(String s) throws IOException {
//        FileInputStream f = new FileInputStream(s);
//        Properties properties = new Properties();
//        Map<String, String> propertyMap = new HashMap<>();
//        properties.load(f);
//        for (String key1 : properties.stringPropertyNames()) {
//            System.out.println("reddy"+key1);
//            String value = properties.getProperty(key1);
//            String[] v = value.split(":");
//            System.out.println("reddy1"+v[0]);
//            propertyMap.put(key1, v[0]);
//        }
//        return propertyMap;
//    }

    public void naviagteToUrl(String s) {

    }

//    public By retriveLocators(String value) throws IOException {
////        Map<String, String> va=readFileWithJavaProperties(s);
////              String value= Arrays.toString(va.get(key).split("="));
//        String[] v = value.split("=",2);
////        String k;
////        if (v[0].length()>=5){
////            k=v[1].concat("="+v[2]);
////        }
////        else {
////            k=v[1];
////        }
////        String xpathh= v[1];
////        String afterXpath="";
////        if (v.length > 2){
////            afterXpath=v[1]+v[2].trim();
////        }
//        By locator;
//        switch (v[0]){
//            case "id":
//                locator= By.id(v[1]);
//                break;
//            case "name":
//                locator=By.name(v[1]);
//                break;
//            case "xpath":
//                locator=By.xpath(v[1]);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + v[0]);
//        }
//          this.local_by=locator;
//        return locator;
//    }
    public By retriveLocators(String element) throws Exception {
        log.info("RetriveLocators are started");
        By locator = null;
        String[] beforeSplit = element.split(":", 2);
        this.label = beforeSplit[0];
        String[] defineLocator = beforeSplit[1].split("=", 2);
        String locatorType = defineLocator[0];
        String locatorValue = defineLocator[1];
        switch (locatorType) {
            case "id":
                locator = By.id(locatorValue);
                break;
            case "class":
                locator = By.className(locatorValue);
                break;
            case "css":
                locator = By.cssSelector(locatorValue);
                break;
            case "xpath":
                locator = By.xpath(locatorValue);
        }

        this.local_by = locator;
        log.info("RetriveLocators has returned with locator Type as " + locatorType + " and value = " + locator);
        return locator;
    }


    public void quitBrowser(){
        driver.quit();
    }
    public void elementClick(By locter) throws Exception {
        waitForTheElement(locter,"CLICKABILITY_OF_ELEMENT_LOCATED");
        driver.findElement(locter).click();
    }
    public void waitForTheElement(By locator, String waitType) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
        log.info("Explicit Wait Started");
        if ("CLICKABILITY_OF_ELEMENT_LOCATED".equalsIgnoreCase(waitType)) {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } else if ("PRESENCE_OF_ELEMENT_LOCATED".equalsIgnoreCase(waitType)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } else if ("INVISIBILITY_OF_ELEMENT".equalsIgnoreCase(waitType)) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } else if ("IS_ALERT_PRESENT ".equalsIgnoreCase(waitType)) {
            wait.until(ExpectedConditions.alertIsPresent());
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        log.info("Explicit Wait Ended");
    }
    public void enterText(By locator, String valueToEnter) throws Exception {
        log.info("enterText Started");
        this.waitForTheElement(locator, "VISIBILITY_OF_ELEMENT_LOCATED");
        WebElement e=driver.findElement(locator);
        this.scroll(this.local_by);
//        WebElement e = driver.findElement(this.local_by);
        e.click();

        e.clear();
       e.sendKeys(new CharSequence[]{valueToEnter});
        log.info(valueToEnter + " has been entered in " + this.label);
        log.info("enterText Ended");
    }
    public Boolean isElementVisible(By locter) throws Exception{
        waitForTheElement(locter,"CLICKABILITY_OF_ELEMENT_LOCATED");
        Boolean b=driver.findElement(locter).isDisplayed();
        return b;
    }
    public void clickUsingJSexe(By a_element) throws Exception {
        log.info("clickUsingJSexe started");
        new WebDriverWait(driver, Duration.ofSeconds(15L));
        WebElement w_element1 = driver.findElement(a_element);
        JavascriptExecutor w_executor = (JavascriptExecutor)driver;
        w_executor.executeScript("arguments[0].click();", new Object[]{w_element1});
        log.info("clickUsingJSexe completed");
    }

    public void elementDoubleClick(By a_element) throws Exception {
        log.info("elementDoubleClick started");
        Actions w_actions = new Actions(driver);
        WebDriverWait w_wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
        w_wait.until(ExpectedConditions.visibilityOfElementLocated(a_element));
        WebElement w_ele = driver.findElement(a_element);
        w_actions.doubleClick(w_ele).perform();
        log.info("elementDoubleClick completed");
    }

    public boolean verifyElementDisplayed(String element) throws Exception {
        this.waitForTheElement(this.retriveLocators(element), "VISIBILITY_OF_ELEMENT_LOCATED");
        this.scroll(this.local_by);
       WebElement e = driver.findElement(this.local_by);
        boolean visible = e.isDisplayed();
        if (visible) {
            log.info(this.label + " is displayed");
        } else {
            log.info(this.label + " is not displayed");
        }

        return visible;
    }

    /** @deprecated */
    @Deprecated
    public String getText(WebElement element) throws Exception {
        String elementText = element.getText();
        log.info(this.label + "  has this text : " + elementText);
        return elementText;
    }

    public String getText(String element) throws Exception {
        log.info("Get Text Started");
        this.waitForTheElement(this.retriveLocators(element), "VISIBILITY_OF_ELEMENT_LOCATED");
        this.scroll(this.local_by);
        WebElement e = driver.findElement(this.local_by);
        String elementText = e.getText();
        log.info("Text : " + elementText + " is retirved");
        return elementText;
    }

    /** @deprecated */
    @Deprecated
    public boolean assertText(String expectedString, String actualString) throws Exception {
        boolean notNull = false;
        if (expectedString.equalsIgnoreCase(actualString)) {
            notNull = true;
            log.info("Expected :" + expectedString + " And Actual : " + actualString + " are same");
        }

        this.assertResult(true, notNull);
        return notNull;
    }

    public void textAssertions(String expectedString, String actualString) throws Exception {
        boolean notNull = false;
        if (expectedString.equalsIgnoreCase(actualString)) {
            notNull = true;
            log.info("Expected :" + expectedString + " And Actual : " + actualString + " are same");
        }

        this.assertResult(true, notNull);
    }

    public void assertResult(boolean a_expected, boolean a_actual) throws Exception {
        log.info("assertResult started");
        Assert.assertEquals(a_expected, a_actual);
        log.info("assertResult completed");
    }

    public void scroll(By element) throws Exception {
        log.info("Scroll to Element Started");
        JavascriptExecutor w_js = (JavascriptExecutor)driver;
        WebElement e = driver.findElement(element);
        w_js.executeScript("arguments[0].scrollIntoView();", new Object[]{e});
        log.info("Scroll to Element Completed");
    }

    public List<WebElement> getListOfElements(String elements) throws Exception {
        log.info("Get List of Elements Started");
        List<WebElement> locators = driver.findElements(this.retriveLocators(elements));
        if (locators.size() == 0) {
            throw new NoSuchElementException("ERR : Empty List is retrived");
        } else {
            log.info("Get List of Elements Completed");
            return locators;
        }
    }

    public void navigate(String url) throws Exception {
        driver.get(url);
    }

    public Map<String, String> readFileWithJavaProperties(String propertyFile) {
        Map<String, String> map = null;

        try {
            FileReader read = new FileReader(propertyFile);
            Properties p = new Properties();
            p.load(read);
            map = new HashMap();
            Iterator var5 = p.stringPropertyNames().iterator();

            while(var5.hasNext()) {
                String key = (String)var5.next();
                map.put(key, p.getProperty(key));
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return map;
    }

    public void appendText(String locator, String textToBeAppended) throws Exception {
        log.info("appendText Started");
        WebElement e = driver.findElement(this.retriveLocators(locator));
        e.sendKeys(new CharSequence[]{Keys.CONTROL, Keys.END});
        e.sendKeys(new CharSequence[]{" " + textToBeAppended});
        e.sendKeys(new CharSequence[]{Keys.ENTER});
        log.info("appendText completed");
    }

    /** @deprecated */
    @Deprecated
    public List<WebElement> getElements(String locator) throws Exception {
        List<WebElement> listOfElements = driver.findElements(this.retriveLocators(locator));
        return listOfElements;
    }

    /** @deprecated */
    public String dynamicXpathGenerator(String identifier, String inputValue) {
        log.info("dynamicXpathGenerator started");
        String str = identifier.replace("<dynamic value>", inputValue);
        log.info("dynamicXpathGenerator completed");
        return str;
    }

    public String multipleDynamicValueXpathGen(String identifier, String inputValue) {
        log.info("dynamicXpathGenerator started");
        String[] dynamicValues = inputValue.split(",");
        String finalLocator = "";

        for(int i = 0; i < dynamicValues.length; ++i) {
            identifier = identifier.replace("<dynamic value" + (i + 1) + ">", dynamicValues[i]);
        }

        log.info("dynamicXpathGenerator completed");
        return identifier;
    }

    /** @deprecated */
    @Deprecated
    public void backNavigation() {
        log.info("backNavigation started");
        driver.navigate().back();
        log.info("backNavigation completed");
    }

    public void enterChordKeys(By a_element, Keys a_key, String b_key) throws Exception {
        log.info("enterChordKeys started");
        this.waitForTheElement(a_element, "VISIBILITY_OF_ELEMENT_LOCATED");
        driver.findElement(a_element).sendKeys(new CharSequence[]{a_key, Keys.chord(new CharSequence[]{b_key})});
        log.info("enterChordKeys completed");
    }

    public void tearDown() throws Exception {
        log.info("tearDown started");
        driver.quit();
        log.info("tearDown completed");
    }

    public void switchFrame(String a_framename) throws Exception {
        log.info("switchFrame started");
        driver.switchTo().frame(a_framename);
        log.info("switchFrame completed");
    }

    public void switchToDefaultFrame() throws Exception {
        log.info("switchToDefaultFrame started");
        driver.switchTo().defaultContent();
        log.info("switchToDefaultFrame completed");
    }

    public void switchToParentFrame() throws Exception {
        log.info("switchToParentFrame started");
        driver.switchTo().parentFrame();
        log.info("switchToParentFrame completed");
    }

    public void switchFrameByXpath(By w_element) throws Exception {
        log.info("switchFrameByXpath started");
        WebElement w_identifiedElement = driver.findElement(w_element);
        driver.switchTo().frame(w_identifiedElement);
        log.info("INFO ---- Switched to frame through an element identified using xpath");
        log.info("switchFrameByXpath completed");
    }

    public void pasteFromClipBoard() {
        try {
            Actions builder = new Actions(driver);
            builder.keyDown(Keys.CONTROL).sendKeys(new CharSequence[]{"v"}).keyUp(Keys.CONTROL).build().perform();
            Thread.sleep(4000L);
        } catch (Exception var2) {
            var2.getLocalizedMessage();
        }

    }

    public void handleAlert(String action) {
        switch (action) {
            case "Accept":
            case "accept":
                driver.switchTo().alert().accept();
                break;
            case "Dismiss":
            case "dismiss":
            case "Cancel":
            case "cancel":
                driver.switchTo().alert().dismiss();
        }

    }

    public void refreshBrowser() {
        driver.navigate().refresh();
    }

    public void browserNavigation(String navigateType) {
        if (navigateType.equalsIgnoreCase("back")) {
            driver.navigate().back();
        }

        if (navigateType.equalsIgnoreCase("forward")) {
            driver.navigate().forward();
        }

    }

    public void elementimplicitlyWait(int timeunit) throws Exception {
        log.info("elementimplicitlyWait started");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((long)timeunit));
        log.info("elementimplicitlyWait completed");
    }

    public void selectFromDropDown(By a_element, String visibleText) throws Exception {
        this.waitForTheElement(a_element, "VISIBILITY_OF_ELEMENT_LOCATED");
        Select dropdown = new Select(driver.findElement(a_element));
        dropdown.selectByVisibleText(visibleText);
    }

    public String getDatenTime(String dateFormat, String duration, Integer amount) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        if (!duration.equalsIgnoreCase("Today")) {
            switch (duration.toLowerCase()) {
                case "years":
                    c.add(1, amount);
                    break;
                case "months":
                    c.add(2, amount);
                    break;
                case "days":
                    c.add(5, amount);
                    break;
                case "hours":
                    c.add(10, amount);
                    break;
                case "minutes":
                    c.add(12, amount);
            }
        }

        String date = formatter.format(c.getTime());
        return date;
    }

}
