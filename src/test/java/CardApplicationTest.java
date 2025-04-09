import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardApplicationTest {
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
    void test1() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79824622505");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void test2() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов-Ван Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79824622505");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void test3() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов-ван Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79824622505");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void test4() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Ван Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79824622505");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void test5() {
        driver.get("http://localhost:9999/");
        WebElement form = driver.findElement(By.className("form"));
        form.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Ван-Алексей");
        form.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79824622505");
        form.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        form.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
