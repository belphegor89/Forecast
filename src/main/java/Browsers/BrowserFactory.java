package Browsers;

import Utils.PropertiesReader;

public class BrowserFactory {
    public AbstractBrowser getDriver (String browserName) {

        AbstractBrowser abstractBrowser = null;

        if (PropertiesReader.getConfigProperty("driver").equalsIgnoreCase("firefox")) {
            abstractBrowser = new Firefox();
        } else {
            abstractBrowser = new Chrome();
        }
        return abstractBrowser;
    }
}
