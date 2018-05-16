package pages;


public class Google extends BasePage {

    private static Google instance;
    public static Google Instance =(instance!=null) ? instance: new Google();


    public void start(){
        open("https://www.google.com/");
    }

}
