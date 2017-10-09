import Pages.LoginPage;
import Pages.SendMail;
import Utils.BaseTest;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by owner on 05-Oct-17.
 */
public class SendReportInMail extends BaseTest {

    public static Logger logger = Logger.getLogger(SendReportInMail.class);

    @Test
    public void loginToEmail(){
        LoginPage loginPage = LoginPage.Instance;
        SendMail sendMail = SendMail.Instance;
        logger.info("Starting test for sending forecast via email");
        loginPage.open();
        loginPage.login();
        sendMail.sendMailWithFile();
    }
}
