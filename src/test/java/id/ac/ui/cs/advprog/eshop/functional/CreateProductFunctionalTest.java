
package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        createProduct(driver, "Soto Babat", "2");

        List<WebElement> productRows = driver.findElements(By.cssSelector("table tbody tr"));
        WebElement lastProductRow = productRows.get(productRows.size() - 1);

        verifyProductDetails(lastProductRow, "Soto Babat", "2");
    }

    @Test
    void createMoreThanOneProduct_isCorrect(ChromeDriver driver) throws Exception {
        String[][] products = {
                {"Abon Kikil", "3"},
                {"Ayam Geprek", "2"},
                {"Bayam Saos Tiram", "1"}
        };

        for (String[] product : products) {
            createProduct(driver, product[0], product[1]);
        }

        List<WebElement> productRows = driver.findElements(By.cssSelector("table tbody tr"));
        int startIndex = productRows.size() - products.length;

        for (int i = 0; i < products.length; i++) {
            WebElement productRow = productRows.get(startIndex + i);
            verifyProductDetails(productRow, products[i][0], products[i][1]);
        }
    }

    void createProduct(ChromeDriver driver, String name, String quantity) {
        driver.get(baseUrl + "/product/list");

        WebElement createProductButton = driver.findElement(By.id("create"));
        createProductButton.click();

        WebElement productNameInput = driver.findElement(By.id("nameInput"));
        productNameInput.clear();
        productNameInput.sendKeys(name);

        WebElement productQuantityInput = driver.findElement(By.id("quantityInput"));
        productQuantityInput.clear();
        productQuantityInput.sendKeys(quantity);

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
    }

    void verifyProductDetails(WebElement productRow, String expectedName, String expectedQuantity) {
        String actualProductName = productRow.findElement(By.cssSelector("td:nth-child(1)")).getText();
        assertEquals(expectedName, actualProductName);

        String actualProductQuantity = productRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
        assertEquals(expectedQuantity, actualProductQuantity);
    }
}
