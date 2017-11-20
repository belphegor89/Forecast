package Pages;

import Utils.PropertiesReader;
import Utils.Reporter;
import Utils.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;

/**
 * Created by yzosin on 04-Sep-17.
 */
public class SendMail extends BasePage {

    private static SendMail instance;
    public static SendMail Instance =(instance!=null) ? instance: new SendMail();

    String writeTo = PropertiesReader.getConfigProperty("recipient");

    By writeMail = By.xpath(".//*[@id='content']//button");
    By recipient = By.xpath(".//div[@class='sendmsg__form-label']/div[4]");
    By subject = By.xpath(".//input[@name='subject']");
    By file = By.id("file-to-upload");
    By sendButton = By.xpath(".//button[@class='default send']");
    By frame = By.xpath("//iframe[contains(@title,'Текстове')]");
    By text = By.id("tinymce");
    By message = By.xpath(".//div[@class='sendmsg__ads-ready']");

    public void sendMailWithFile () {

        Reporter.log("Clicking Write Mail link");
        findElement(writeMail).click();
        Reporter.log("Entering recipient: " + writeTo);
        isElementPresentAndDisplay(subject);
        findElement(recipient).sendKeys(writeTo);
        Reporter.log("Entering subject");
        findElement(subject).sendKeys("Weather report for " + Tools.getCurrentTime());
        Reporter.log("Adding weather report to mail");
        fileUpload(file);
        switchToFrame(frame);
        Reporter.log("Adding mail body");
        findElement(text).sendKeys("Today's forecast");
        switchToDefaultContent();
        Reporter.log("Sending mail");
        findElement(sendButton).click();
        try{
            acceptAlertMessage();
        } catch(NoAlertPresentException Ex){
            Reporter.log("No alert message found");
            validateMessageSent();
        }
        Reporter.log("Mail sent!");
    }

    public void validateMessageSent(){
        String validationMessage = findElement(message).getText();
        Assert.assertEquals(validationMessage,"Ваш лист надіслано\n" +
                        "Написати щеПовернутись у вхідні",
                "Success message doesn't match the expected one");
    }
}
