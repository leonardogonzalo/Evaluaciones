package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumDriver {

    WebDriver driver;
    WebDriverWait webDriverWait;


    public SeleniumDriver(WebDriver driver) {
        this.driver = driver;
        webDriverWait= new WebDriverWait(this.driver,20);
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void pageMax(){
        this.driver.manage().window().maximize();
    }

    public WebDriver getDriver(){
        return this.driver;
    }
    public By byID(String id){
        return By.id(id);
    }
    public By byXPath(String xpath){
        return By.xpath(xpath);
    }
    public By bycssSelector(String css){
        return By.cssSelector(css);
    }
    public WebElement buscarElement(By by){
        return this.driver.findElement(by);
    }

    public WebElement buscarPorId(String id){
        return buscarElement(byID(id));
    }
    public WebElement buscarPorcssSelector(String selector){
        return buscarElement(bycssSelector(selector));
    }
    public WebElement buscarPorxPath(String xpath){
        return buscarElement(By.xpath(xpath));
    }
    public void comboBoxSeleccion(String id,String texto){
        Select seleccion = new Select(buscarElement(byID(id)));
        seleccion.selectByVisibleText(texto);
    }

    public void inputTexto(String id,String texto){
     buscarPorId(id).sendKeys(texto);
    }

    public void click(WebElement e){
        e.click();
    }

}
