import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	@Test
    public void testeFirefox() {
        // Define o caminho do geckodriver
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\coelh\\Desktop\\java\\Driver FireFox\\geckodriver.exe");

        
        // Inicializa o WebDriver para o Firefox
        WebDriver driver = new FirefoxDriver();
        
        // Acessa o Google
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.get("http://www.google.com");
        
        //Imprime o título da página
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
        
        //Fecha o navegador
        driver.quit();
    }
	
//public void TesteChrome(){
//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\coelh\\Desktop\\java\\Driver Chrome\\chromedriver.exe");
//	WebDriver driver = new ChromeDriver();
//	driver.get("http://www.google.com");
//	Assert.assertEquals("Google", driver.getTitle());
//}
}	

