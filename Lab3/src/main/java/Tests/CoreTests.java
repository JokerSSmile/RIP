package Tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

class CoreTests {

    private static FirefoxDriver driver;

    private WebElement GetModelField()
    {
        return driver.findElement(By.xpath("//input[@name='model']"));
    }

    private WebElement GetSubModelField()
    {
        return driver.findElement(By.xpath("//input[@name='subModel']"));
    }

    private WebElement GetPriceField()
    {
        return driver.findElement(By.xpath("//input[@name='price']"));
    }

    private WebElement GetDateField()
    {
        return driver.findElement(By.xpath("//input[@name='date']"));
    }

    private WebElement GetSubmitBtn()
    {
        return driver.findElement(By.id("submitButton"));
    }

    private void OpenAddCarUrl()
    {
        driver.get("http://localhost:8080/CarServlet");
    }

    private void CheckForNoError()
    {
        (new WebDriverWait(driver, 10)).until((ExpectedCondition<Boolean>) d -> driver.getTitle().equals("Cars"));
    }

    private void ExpectingInvalidElements(int count)
    {
        (new WebDriverWait(driver, 10)).until((ExpectedCondition<Boolean>) d ->
                driver.findElements(By.cssSelector("input:invalid")).size() == count);
    }

    private void ClearElements(WebElement... elements)
    {
        for (WebElement element: elements) {
            element.clear();
        }
    }

    @BeforeAll
    static void Init()
    {
        driver = new FirefoxDriver();
    }

    @Test
    void Correct_Form_Filling_String_Fields_Are_String()
    {
        OpenAddCarUrl();

        WebElement modelField = GetModelField();
        modelField.sendKeys("Tesla");

        WebElement subModelField = GetSubModelField();
        subModelField.sendKeys("Model S");

        WebElement priceField = GetPriceField();
        priceField.sendKeys("5000");

        WebElement dateField = GetDateField();
        dateField.sendKeys("05.05.2015");

        GetSubmitBtn().click();

        (new WebDriverWait(driver, 10)).until((ExpectedCondition<Boolean>) d -> {
            String url = d.getCurrentUrl();
            return url.equals("http://localhost:8080/CarServlet");
        });
    }

    @Test
    void Correct_Form_Filling_String_Fields_Are_Numbers()
    {
        OpenAddCarUrl();

        WebElement modelField = GetModelField();
        modelField.sendKeys("8800");

        WebElement subModelField = GetSubModelField();
        subModelField.sendKeys("555");

        WebElement priceField = GetPriceField();
        priceField.sendKeys("3535");

        WebElement dateField = GetDateField();
        dateField.sendKeys("05.05.2015");

        GetSubmitBtn().click();

        (new WebDriverWait(driver, 10)).until((ExpectedCondition<Boolean>) d -> {
            String url = d.getCurrentUrl();
            return url.equals("http://localhost:8080/CarServlet");
        });
    }

    @Test
    void Date_Field_Correct_If_Data_Correct()
    {
        OpenAddCarUrl();

        WebElement dateField = GetDateField();
        dateField.sendKeys("01.01.01");
        ExpectingInvalidElements(1);
        dateField.clear();

        dateField = GetDateField();
        dateField.sendKeys("1.1.1");
        ExpectingInvalidElements(1);
        dateField.clear();

        dateField = GetDateField();
        dateField.sendKeys("10.01.2010");
        ExpectingInvalidElements(1);
        dateField.clear();

        dateField = GetDateField();
        dateField.sendKeys("31.12.3000");
        ExpectingInvalidElements(1);
    }

    @Test
    void No_Error_If_Not_Required_Fields_Are_Not_Filled() throws InterruptedException {
        OpenAddCarUrl();

        // 1
        WebElement modelField = GetModelField();
        modelField.sendKeys("");

        WebElement subModelField = GetSubModelField();
        subModelField.sendKeys("Model S");

        WebElement priceField = GetPriceField();
        priceField.sendKeys("5000");

        WebElement dateField = GetDateField();
        dateField.sendKeys("05.05.2015");

        GetSubmitBtn().click();
        CheckForNoError();

        // 2
        modelField = GetModelField();
        modelField.sendKeys("Tesla");

        subModelField = GetSubModelField();
        subModelField.sendKeys("");

        priceField = GetPriceField();
        priceField.sendKeys("10");

        dateField = GetDateField();
        dateField.sendKeys("05.05.2015");

        GetSubmitBtn().click();
        CheckForNoError();

        // 3
        modelField = GetModelField();
        modelField.sendKeys("");

        subModelField = GetSubModelField();
        subModelField.sendKeys("");

        priceField = GetPriceField();
        priceField.sendKeys("10");

        dateField = GetDateField();
        dateField.sendKeys("10.10.2010");

        GetSubmitBtn().click();

        CheckForNoError();
    }

    @Test
    void Error_If_Required_Fields_Are_Not_Filled()
    {
        OpenAddCarUrl();

        // 1
        WebElement modelField = GetModelField();
        modelField.sendKeys("Tesla");

        WebElement subModelField = GetSubModelField();
        subModelField.sendKeys("Model S");

        WebElement priceField = GetPriceField();
        priceField.sendKeys("");

        WebElement dateField = GetDateField();
        dateField.sendKeys("05.05.2015");

        GetSubmitBtn().click();
        ExpectingInvalidElements(1);

        // 2
        ClearElements(modelField, subModelField, priceField, dateField);
        modelField = GetModelField();
        modelField.sendKeys("Tesla");

        subModelField = GetSubModelField();
        subModelField.sendKeys("Model S");

        priceField = GetPriceField();
        priceField.sendKeys("10");

        dateField = GetDateField();
        dateField.sendKeys("");

        GetSubmitBtn().click();
        ExpectingInvalidElements(1);

        // 3
        ClearElements(modelField, subModelField, priceField, dateField);
        modelField = GetModelField();
        modelField.sendKeys("Tesla");

        subModelField = GetSubModelField();
        subModelField.sendKeys("Model S");

        priceField = GetPriceField();
        priceField.sendKeys("");

        dateField = GetDateField();
        dateField.sendKeys("");

        GetSubmitBtn().click();
        ExpectingInvalidElements(2);
    }

    @Test
    void Error_If_Invalid_Number_Field()
    {
        OpenAddCarUrl();

        WebElement modelField = GetModelField();
        modelField.sendKeys("Tesla");

        WebElement subModelField = GetSubModelField();
        subModelField.sendKeys("Model S");

        WebElement priceField = GetPriceField();
        priceField.sendKeys("saa");

        WebElement dateField = GetDateField();
        dateField.sendKeys("10.10.2010");

        GetSubmitBtn().click();
        ExpectingInvalidElements(1);
    }

    @Test
    void Correct_Big_Data_Processing()
    {
        OpenAddCarUrl();

        WebElement dateField = GetDateField();
        dateField.sendKeys("01.01.41411");
        ExpectingInvalidElements(1);
        dateField.clear();

        WebElement model = GetModelField();
        model.sendKeys("longlonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglong");
        ExpectingInvalidElements(2);
        model.clear();

        WebElement subModel = GetModelField();
        subModel.sendKeys("longlonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglong");
        ExpectingInvalidElements(2);
        subModel.clear();

        WebElement price = GetPriceField();
        price.sendKeys("465435423546456");
        ExpectingInvalidElements(1);
        price.clear();
    }

    @AfterAll
    static void Dispose()
    {
        driver.quit();
    }
}