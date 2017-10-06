package Pages;

import Utils.PropertiesReader;
import Utils.Reporter;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

/**
 * Created by yzosin on 04-Sep-17.
 */
public class SendMail extends BasePage {

    private static SendMail instance;
    public static SendMail Instance =(instance!=null) ? instance: new SendMail();

    public SendMail(){

    }
    By writeMail = By.xpath(".//*[@id='sidebar']/div[1]/a");
    By recipient = By.id("toField");
    By subject = By.xpath("//span[@class='field']/input[@name='subject']");
    By file = By.xpath("//div[@class='upl-control']//input[@name='file']");
    By sendButton = By.xpath("//div[@class='controls controls-bottom']//input[@value='Надіслати']");
    By text = By.xpath(".//div[@class='fmedit']/div[2]");

    public void sendMailWithFile () {
        Reporter.log("Clicking Write Mail link");
        findElement(writeMail).click();
        Reporter.log("Entering recipient");
        findElement(recipient).sendKeys(PropertiesReader.getConfigProperty("recipient"));
        Reporter.log("Entering subject");
        findElement(subject).sendKeys("Weather report");
        Reporter.log("Adding weather report to mail");
        fileUpload(file);
        Reporter.log("Adding mail body");
        findElement(text).sendKeys("Today's forecast");
        Reporter.log("Sending mail");
        findElement(sendButton).click();
        waitForAlert(driver(), 1);
        Reporter.log("Mail sent");
    }
}
