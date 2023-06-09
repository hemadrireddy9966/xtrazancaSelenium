package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactor {
    private static WebDriver driver;
    public static String browser = "chrome";
    public static String url = getUrl();

    public static String getUrl() {
        try {
            FileInputStream f = new FileInputStream("src/test/resources/application.properties");
            Properties p = new Properties();
            p.load(f);
            return p.getProperty("url");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    //---------------------------***************************************----------------------------------------------------
//     DriverFactor(WebDriver driver){
//        DriverFactor.driver=driver;
//    }
//    public WebDriver lunchBrowser() throws IOException {
//        System.out.println("chrome broser started");
////        String browserType = readPropertesFail("src/test/resources/application.properties", "browser");
//        String browserType=browser;
//        if (browserType.equalsIgnoreCase("chrome")) {
//            WebDriverManager.chromedriver().setup();
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("--remote-allow-origins=*");
//            driver = new ChromeDriver(chromeOptions);
//            driver.manage().window().maximize();
//        } else if (browserType.equalsIgnoreCase("firefox")) {
//            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
//        }
//        return driver;
//    }
//    public WebDriver getDriver() {
//        try{
//            if (driver == null)
//                driver = lunchBrowser();
//        }
//        catch (IOException e){
//
//        }
//        return driver;
//    }
//---------------------------********************************************----------------------------------------------
    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("chrome broser started");
            String browserType=browser;
        if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.get(url);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.get(url);
        }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            // Quit the WebDriver instance
            driver.quit();
            driver = null;

        }
    }
}