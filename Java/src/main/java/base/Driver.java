package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public  class Driver {

    WebDriver driver;

    public Driver(){
        System.setProperty("webdriver.edge.driver", "C://Users//leona//Desktop//eliminar//driver//msedgedriver.exe");
    }

    public  WebDriver getDriverEdge(){
        this.driver =  new EdgeDriver();
        return this.driver;
    }

}
