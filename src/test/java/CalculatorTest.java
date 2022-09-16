import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;

import java.net.MalformedURLException;
import java.net.URL;


public class CalculatorTest {
    private AndroidDriver<AndroidElement> driver = null;

    @Before
    public void initialize() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", "11.0");
        capabilities.setCapability("udid", "emulator-5554"); //уникальный номер девайса
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.calculator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
        capabilities.setCapability("appium:ensureWebviewsHavePages", true);
        capabilities.setCapability("appium:nativeWebScreenshot", true);
        capabilities.setCapability("appium:newCommandTimeout", 3600);
        capabilities.setCapability("appium:connectHardwareKeyboard", true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true); //Опция, которая отменяет сброс приложения до заводских настроек

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void checkAddition() {

        driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
        driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();
        driver.findElement(By.id("com.google.android.calculator:id/eq")).click();

        MobileElement result = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/result_final"));
        Assert.assertEquals("16", result.getText());

        /*String result = driver.findElement(By.id("com.google.android.calculator:id/result_final")).getText();
        if (result.equals("16")) {
            System.out.println("Test Addition Passed");
        } else {
            System.out.println("Test Addition Failed");
        }*/
    }

    @Test
    public void checkSubtraction() {

        MobileElement four = driver.findElement(By.id("com.google.android.calculator:id/digit_4"));
        four.click();
        MobileElement minus = driver.findElement(By.id("com.google.android.calculator:id/op_sub"));
        minus.click();
        MobileElement three = driver.findElement(By.id("com.google.android.calculator:id/digit_3"));
        three.click();
        MobileElement equals = driver.findElement(By.id("com.google.android.calculator:id/eq"));
        equals.click();

        MobileElement result = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/result_final"));
        Assert.assertEquals("1", result.getText());
    }

    @Test
    public void checkMultiplication() {

        driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
        driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(By.id("com.google.android.calculator:id/eq")).click();

        MobileElement result = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/result_final"));
        Assert.assertEquals("40", result.getText());
    }

    @Test
    public void checkDivision() {

        driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
        driver.findElement(By.id("com.google.android.calculator:id/op_div")).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(By.id("com.google.android.calculator:id/eq")).click();

        MobileElement result = (MobileElement) driver.findElement(By.id("com.google.android.calculator:id/result_final"));
        Assert.assertEquals("82", result.getText());
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
