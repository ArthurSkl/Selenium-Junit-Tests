import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteGoogle {
	
	// Variável que controla o navegador
    private WebDriver driver;
    @Before
    public void inicializa() {
        // Cria instância do FirefoxDriver e abre o navegador
        driver = new FirefoxDriver();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        // Carrega a página local de teste usando URL de arquivo
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    }

    @After
    public void finalizar() {
        // Fecha o navegador e encerra a sessão WebDriver
        driver.quit();
    }
	
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
	
	
  
    @After
    public void tearDown() {
        // Fecha o navegador e encerra a sessão WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testeChromeTituloGoogle() {
        // Navega até o Google
        driver.get("https://www.google.com");
        
        // Obtém o título da página
        String titulo = driver.getTitle();
        System.out.println("Título da página: " + titulo);
        
        // Verifica se o título é exatamente "Google"
        Assert.assertEquals("Google", titulo);
    }
}	

