package util.uiUtil;

import org.openqa.selenium.WebElement;

public class UIUtility {

    public static boolean checkAvailability(WebElement element)
    {
        boolean result = false;
        try {
            result = element.isDisplayed();
        }
        catch (Exception e)
        {

        }
        return result;
    }

    public static boolean checkEnability(WebElement element)
    {
        boolean result = false;
        try {
            result = element.isEnabled();
        }
        catch (Exception e)
        {

        }
        return result;
    }


    public static String checkSpelling(WebElement element)
    {
        String result = "";
        try {
            result = element.getText();
        }
        catch (Exception e)
        {

        }
        return result;
    }

    public static String checkPlaceholder(WebElement element)
    {
        String result = "";
        try {
            result = element.getAttribute("placeholder");
        }
        catch (Exception e)
        {

        }
        return result;
    }


    public static String checkAttribute(WebElement element,String attribute)
    {
        String result = "";
        try {
            result = element.getAttribute(attribute);
        }
        catch (Exception e)
        {

        }
        return result;
    }

    public static String checkCssValue(WebElement element,String cssProperty)
    {
        String result = "";
        try {
            result = element.getCssValue(cssProperty);
        }
        catch (Exception e)
        {

        }
        return result;
    }
}
