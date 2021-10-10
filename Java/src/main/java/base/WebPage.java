package base;

import entidades.Producto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.ArrayList;
import java.util.List;

public class WebPage extends SeleniumDriver {

    WebDriver driver;

    //Elemento pages
    String nombrePage = "https://www.amazon.com/";

    String idcomboCategoria = "searchDropdownBox";
    String idTextoBuscar ="twotabsearchtextbox";
    String idBotonBuscar ="nav-search-submit-button";
    String selectordivResultado ="div.s-main-slot>div.s-result-item>div>span>div>div>div>div.sg-col-12-of-20>div>div";
    String xpathlabelResultado = "//*[@id=\"search\"]/span/div/span/h1/div/div[1]/div/div/span[1]";
    String idcomboFiltro = "s-result-sort-select";
    String selectorDescripcionProducto = "div>h2>a>span";
    String selectorPrecioValorEntero = "div.sg-row>div>div>div>div>a>span[class*=a-price]:not([class*='a-text-price'])>span>span.a-price-whole";
    String selectorPrecioValorFraccion = "div.sg-row>div>div>div>div>a>span[class*=a-price]:not([class*='a-text-price'])>span>span.a-price-fraction";


    public WebPage(WebDriver driver){
        super(driver);
        this.driver = getDriver();
    }

    public void getPageIndexAmazon(){
        this.driver.get(nombrePage);
    }
    public void pageMaximizada(){
        pageMax();
    }
    public void SeleccionarComboCategoria(String texto){
        comboBoxSeleccion(idcomboCategoria,texto);
    }

    public void filtroBusqueda(String filtro){
        inputTexto(idTextoBuscar,filtro);
    }
    public void hacerClickBuscar(){
        click(buscarPorId(idBotonBuscar));
    }
    public String resultadoBusqueda(){
        By by = byXPath(xpathlabelResultado);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return buscarElement(by).getText();
    }
    public void ordenarResultados(String orden){
        comboBoxSeleccion(idcomboFiltro,orden);
        buscarPorId(idcomboFiltro).submit();
    }

    public  List<Producto> listadoResultadoBusqueda(){

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(bycssSelector(selectordivResultado)));
        List<WebElement> listaResultado=this.driver.findElements(bycssSelector(selectordivResultado));

        List<Producto> lista = new ArrayList<>();
        Producto producto;
        By elementDescripcion = By.cssSelector(selectorDescripcionProducto);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elementDescripcion));

        for(WebElement e:listaResultado){
            producto = new Producto();
            WebElement nombre = e.findElement(elementDescripcion);
            producto.setDescripcion(nombre.getText());

            String preciotexto="";
            try {
                By selectorPrecioEntero = By.cssSelector(selectorPrecioValorEntero);
                WebElement precioEntero = e.findElement(selectorPrecioEntero);
                By selectorPrecioFraccion = By.cssSelector(selectorPrecioValorFraccion);
                WebElement precioFraccion = e.findElement(selectorPrecioFraccion);

                preciotexto = precioEntero.getText()+"."+precioFraccion.getText();
                preciotexto = preciotexto.replace(",","");
            }
            catch(Exception ex) {
                preciotexto = "0";
            }
            producto.setPrecio(Double.parseDouble(preciotexto));
            lista.add(producto);

        }
        return lista;
    }

}
