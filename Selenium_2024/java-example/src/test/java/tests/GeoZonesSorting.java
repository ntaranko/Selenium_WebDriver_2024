package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class CountriesGeoZonesSorting extends TestBase {
    @Test
    public void countriesSorted() {
        //app.driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        app.adminHelper().clickMenu(By.xpath("//*[contains(text(),'Countries')]"));

        var rowsList = app.driver.findElements(By.cssSelector("tr.row"));

        List<String> countriesList = new ArrayList<>();
        for (var row : rowsList) {
            var column = row.findElements(By.cssSelector("tr.row a[href]")).get(0).getText();
            countriesList.add(column);
        }

        for (int i = 0; i < countriesList.size() - 1; i++) {
            if (countriesList.get(i).compareTo(countriesList.get(i + 1)) > 0) {
                System.out.println("Country list is not sorted " + i);
                break;
            }
        }
    }

    @Test
    public void geoZonesSorted() {
        app.adminHelper().clickMenu(By.xpath("//*[contains(text(),'Countries')]"));

        var rowsList = app.driver.findElements(By.cssSelector("tr.row"));
        List<String> countriesList = new ArrayList<>();
        for (var row : rowsList) {
            var column = row.findElements(By.cssSelector("tr.row td")).get(5).getText();
            int zonesNumber = Integer.valueOf(column);
            if (zonesNumber > 0) {
                app.adminHelper().clickMenu(By.cssSelector("tr.row a[href]"));

                var table = app.driver.findElement(By.cssSelector("table.dataTable"));
                var zonesList = table.findElements(By.cssSelector("input[name$=\"][name]\"]"));
                //var zonesList = app.driver.findElements(By.cssSelector("input[name$=\"][name]\"]"));
                // var zonesList = app.driver.findElements(By.xpath("//*[contains(@name,'][name]')]"));
                System.out.println("zoneList size = " + zonesList.size());
            }
        }
    }
}

