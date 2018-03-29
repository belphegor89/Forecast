package Pages.Email;

import Pages.BasePage;
import Utils.PropertiesReader;
import Utils.Tools;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

        logger.info("Clicking Write Mail link");
        findElement(writeMail).click();
        logger.info("Entering recipient: " + writeTo);
        isElementPresentAndDisplay(subject);
        Actions actions = new Actions(BasePage.driver());
        WebElement element = BasePage.driver().findElement(recipient);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(writeTo);
        actions.build().perform();
        logger.info("Entering subject");
        findElement(subject).sendKeys("Weather report for " + Tools.getCurrentTime());
        logger.info("Adding weather report to mail");
        fileUpload(file);
        switchToFrame(frame);
        logger.info("Adding mail body");
        findElement(text).sendKeys("Today's forecast");
        switchToDefaultContent();
        logger.info("Sending mail");
        findElement(sendButton).click();
        try{
            acceptAlertMessage();
        } catch(NoAlertPresentException Ex){
            logger.info("No alert message found");
            validateMessageSent();
        }
        logger.info("Mail sent!");
    }

    public void validateMessageSent(){
        String validationMessage = findElement(message).getText();
        Assert.assertEquals(validationMessage,"Ваш лист надіслано\n" +
                        "Написати щеПовернутись у вхідні",
                "Success message doesn't match the expected one");
    }
}
