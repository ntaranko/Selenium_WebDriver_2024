package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class GeoZonesSorting extends TestBase {
    @Test
    public void geozonesSorted() {
        app.adminHelper().select(By.xpath("//*[contains(text(),'Geo Zones')]"));
        var countriesList = app.driver.findElements(By.cssSelector("tr.row"));
        for (int i = 0; i < countriesList.size(); i++) {
            String selector = String.format("a[href$=\"geo_zone_id=%s\"]", i + 1);
            app.adminHelper().select(By.cssSelector(selector));
            var rowsList = app.driver.findElements(By.cssSelector("table.dataTable tr:not(.header)"));
            List<String> zoneList = new ArrayList<>();
            for (int j = 1; j < rowsList.size() - 1; j++) {
                var zone = rowsList.get(j).findElements(By.cssSelector("[name$=\"zone_code]\"] option[selected]")).get(0).getText();
                zoneList.add(zone);}
            if (!app.adminHelper().isListSorted(zoneList)) {
                System.out.println("Zone list is not sorted");
            }
            app.adminHelper().select(By.cssSelector("button[name=cancel]"));
        }
    }
}

