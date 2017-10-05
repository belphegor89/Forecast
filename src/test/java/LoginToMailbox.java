import Pages.LoginPage;
import Utils.BaseTest;
import org.testng.annotations.Test;

/**
 * Created by owner on 05-Oct-17.
 */
public class LoginToMailbox extends BaseTest {

    @Test
    public void loginToEmail(){
        LoginPage loginPage = LoginPage.Instance;
        loginPage.open();
        loginPage.login();
    }
}
