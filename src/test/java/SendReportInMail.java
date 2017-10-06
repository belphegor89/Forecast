import Pages.LoginPage;
import Pages.SendMail;
import Utils.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by owner on 05-Oct-17.
 */
public class SendReportInMail extends BaseTest {

    @Test
    public void loginToEmail(){
        LoginPage loginPage = LoginPage.Instance;
        SendMail sendMail = SendMail.Instance;
        loginPage.open();
        loginPage.login();
        sendMail.sendMailWithFile();
    }
}
