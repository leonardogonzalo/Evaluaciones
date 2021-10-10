package auto;

import base.Driver;
import base.SeleniumDriver;
import base.WebPage;
import entidades.Producto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class main {

    public static void main(String[] args){

        Driver wdriver = new Driver();

        WebPage webPage = new WebPage(wdriver.getDriverEdge());
        webPage.pageMaximizada();
        webPage.getPageIndexAmazon();
        webPage.SeleccionarComboCategoria("Electronics");
        webPage.filtroBusqueda("Computers");
        webPage.hacerClickBuscar();
        //Mostrar resultado
        System.out.println("===========================================================================");
        System.out.println("Resultado de búsqueda: "+webPage.resultadoBusqueda());
        System.out.println("===========================================================================");
        //Ordenar resultados de la búsqueda
        String tipoOrdenamiento = "Price: Low to High";
        webPage.ordenarResultados(tipoOrdenamiento);
        System.out.println("Se realizó el ordenamiento por :"+tipoOrdenamiento);
        //Registrar resultado de búsqueda
        List<Producto> productos = new ArrayList<>();
        productos=webPage.listadoResultadoBusqueda();

        int cantidadregistros = 5;
        //Primeros 5 resultados de la búsqueda
        System.out.println("===========================================================================");
        System.out.println("Primeros 5 resultados de la búsqueda");
        System.out.println("===========================================================================");
        for(int i=0;i<cantidadregistros;++i){
            System.out.println(productos.get(i).getDescripcion());
            System.out.println(productos.get(i).getPrecio());
        };

        //Primeros 5 resultados de la búsqueda ordenado por nombre asc
        System.out.println("===========================================================================");
        System.out.println("búsqueda ordenado por descripción asc");
        System.out.println("===========================================================================");
        List<Producto> nombres_desc = new ArrayList<Producto>();
        nombres_desc= productos;
        Collections.sort(nombres_desc, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                return o1.getDescripcion().compareToIgnoreCase(o2.getDescripcion());
            }
        });
        for(int i=0;i<cantidadregistros;++i){
            System.out.println(nombres_desc.get(i).getDescripcion());
            System.out.println(nombres_desc.get(i).getPrecio());
        };

        //Primeros 5 resultados de la búsqueda ordenado por precio desc
        System.out.println("===========================================================================");
        System.out.println("búsqueda ordenado por precio desc");
        System.out.println("===========================================================================");
        List<Producto> precio_asc = new ArrayList<Producto>();
        precio_asc= productos;
        Collections.sort(precio_asc, new Comparator<Producto>() {
            @Override
            public int compare(Producto o1, Producto o2) {
                return o1.getPrecio().compareTo(o2.getPrecio());
            }
        });
        for(int i=cantidadregistros;i>=0;--i){
            System.out.println(precio_asc.get(i).getDescripcion());
            System.out.println(precio_asc.get(i).getPrecio());
        };



        System.out.println("Selenium");
    }


}
