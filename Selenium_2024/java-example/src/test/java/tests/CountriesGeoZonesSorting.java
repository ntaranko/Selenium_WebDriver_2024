package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class CountriesGeoZonesSorting extends TestBase {
    @Test
    public void countriesSorted() {
        app.adminHelper().select(By.xpath("//*[contains(text(),'Countries')]"));
        var countryList = app.driver.findElements(By.cssSelector("tr.row"));

        List<String> countryNamesList = new ArrayList<>();
        for (var countryRow : countryList) {
            var countryName = countryRow.findElements(By.cssSelector("tr.row a[href]")).get(0).getText();
            countryNamesList.add(countryName);
        }

        if (!app.adminHelper().isListSorted(countryNamesList)) {
            System.out.println("Country list is not sorted");
        }
    }

    @Test
    public void geoZonesSorted() {
        app.adminHelper().select(By.xpath("//*[contains(text(),'Countries')]"));
        var countryList = app.driver.findElements(By.cssSelector("tr.row"));

        for (int i = 0; i < countryList.size(); i++) {
            var column = countryList.get(i).findElements(By.cssSelector("tr.row td")).get(5).getText();
            int zonesNumber = Integer.valueOf(column);
            if (zonesNumber > 0) {
                countryList.get(i).findElement(By.cssSelector("tr.row a[href]")).click();
                var zonesList = app.driver.findElements(By.cssSelector("table.dataTable tr:not(.header)"));

                List<String> zonesNamesList = new ArrayList<>();
                for (int j = 0; j < zonesList.size() - 1; j++) {
                    var zoneName = zonesList.get(j).findElements(By.cssSelector("[name$=\"][name]\"]")).get(0).getAttribute("value");
                    zonesNamesList.add(zoneName);
                }
                if (! app.adminHelper().isListSorted(zonesNamesList)) {
                    System.out.println("Zone list is not sorted");
                }

                app.adminHelper().select(By.cssSelector("button[name=cancel]"));
                countryList = app.driver.findElements(By.cssSelector("tr.row"));
            }
        }
    }
}
