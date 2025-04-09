import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardApplicationNegativeTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void testLatinName() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ivanov Aleksei");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79824622505");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void testEmptyName() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79824622505");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void testPhone10() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7982462250");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void testPhone12() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+798246225055");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void testPhoneWithoutPlus() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("79824622505");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void testEmptyPhone() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        Assertions.assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void testPhoneWithoutCheckbox() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79824622505");
        form.findElement(By.className("button")).click();
        boolean actual = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid .checkbox__text")).isDisplayed();
        Assertions.assertTrue(actual);
    }
}
