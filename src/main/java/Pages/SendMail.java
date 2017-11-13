package Pages;

import Utils.PropertiesReader;
import Utils.Reporter;
import Utils.Tools;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by yzosin on 04-Sep-17.
 */
public class SendMail extends BasePage {

    private static SendMail instance;
    public static SendMail Instance =(instance!=null) ? instance: new SendMail();

    String writeTo = PropertiesReader.getConfigProperty("recipient");

    By writeMail = By.xpath(".//*[@id='sidebar']/div[1]/a");
    By recipient = By.id("toField");
    By subject = By.xpath("//span[@class='field']/input[@name='subject']");
    By file = By.xpath("//div[@class='upl-control']//input[@name='file']");
    By sendButton = By.xpath("//div[@class='controls controls-bottom']//input[@value='Надіслати']");
    By text = By.xpath(".//div[@class='fmedit']/div[2]");
    By message = By.xpath(".//div[@class='content status-message']");

    public void sendMailWithFile () {
        Reporter.log("Clicking Write Mail link");
        findElement(writeMail).click();
        Reporter.log("Entering recipient: " + writeTo);
        findElement(recipient).sendKeys(writeTo);
        Reporter.log("Entering subject");
        findElement(subject).sendKeys("Weather report for " + Tools.getCurrentTime());
        Reporter.log("Adding weather report to mail");
        fileUpload(file);
        Reporter.log("Adding mail body");
        findElement(text).sendKeys("Today's forecast");
        Reporter.log("Sending mail");
        findElement(sendButton).click();
        validateMessageSent();
        Reporter.log("Mail sent!");
    }

    public void validateMessageSent(){
        String validationMessage = findElement(message).getText();
        Assert.assertEquals(validationMessage,"Вашого листа відправлено",
                "Success message doesn't match the expected one");
    }
}
