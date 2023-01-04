package org.nttData.listeners;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Hook {
    public static WebDriver browserDriver;
    private String browser;
    private String url = "https://the-internet.herokuapp.com";
    private long timeout = 5

    @Before
    public void startBrowser(){
        this.browser = System.getProperty("browser");
        switch (browser){
            case "firefox" -> firefox();
            case "chrome" -> chrome();
            case "edge" -> edge()
        }
    }
    protected void firefox(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        browserDriver = new FirefoxDriver();
        browserDriver.manage().window().maximize();
        browserDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        browserDriver.get(url);
        System.out.println("firefoxDriver started");

    }
    protected void chrome(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-private");
        browserDriver = new ChromeDriver();
        browserDriver.manage().window().maximize();
        browserDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        browserDriver.get(url);
        System.out.println("chromeDriver started");
    }
    protected void edge(){
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("-private");
        browserDriver = new EdgeDriver();
        browserDriver.manage().window().maximize();
        browserDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        browserDriver.get(url);
        System.out.println("edgeDriver started");

    }

    @After
    public void closeBrowser(Scenario scenarios) {

        if(scenarios.isFailed()) {
            System.out.println(scenarios.getName());
        }
        browserDriver.quit();
    }
}
