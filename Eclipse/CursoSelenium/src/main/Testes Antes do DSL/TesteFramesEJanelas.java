// Importa a classe Duration para definir durações em WebDriverWait
import java.time.Duration;

// Importações do JUnit para estruturar e validar testes
import org.junit.After;               // @After: executa após cada método de teste
import org.junit.Assert;              // Assert: realiza asserções para validar condições
import org.junit.Before;              // @Before: executa antes de cada método de teste
import org.junit.Test;                // @Test: marca métodos como casos de teste

// Importações do Selenium para interagir com frames, janelas e alertas
import org.openqa.selenium.Alert;    // Alert: manipula caixas de diálogo JavaScript
import org.openqa.selenium.By;       // By: localização de elementos na página
// import org.openqa.selenium.Dimension; // (Comentado) manipula tamanho de janela, não usado aqui
import org.openqa.selenium.WebDriver; // WebDriver: interface principal para controlar o navegador
import org.openqa.selenium.firefox.FirefoxDriver; // FirefoxDriver: implementação do WebDriver para Firefox
import org.openqa.selenium.support.ui.WebDriverWait; // WebDriverWait: aguarda condições explícitas

// Importa WebDriverManager para gerenciar automaticamente o driver do navegador
import io.github.bonigarcia.wdm.WebDriverManager;

// Declaração da classe de testes para frames e janelas
public class TesteFramesEJanelas {
    
    // Variável que armazenará a instância do WebDriver (navegador)
    private WebDriver driver;
    @Before
    public void inicializa() {
        // Configura o driver do Firefox automaticamente
        WebDriverManager.firefoxdriver().setup();
        // Instancia o FirefoxDriver e abre o navegador
        driver = new FirefoxDriver();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        // Carrega a página de teste (HTML local) no navegador
        driver.get("file:///" + System.getProperty("user.dir") +
                   "/src/main/resources/campo_treinamento/componentes.html");
    }

    @After
    public void finalizar() {
        // Fecha o navegador e encerra a sessão do WebDriver
        driver.quit();
    }

    @Test
    public void deveInteragirComFrames() {
        // Muda o contexto para o frame identificado por "frame1"
        driver.switchTo().frame("frame1");
        // Dentro do frame, clica no botão que dispara um alerta
        driver.findElement(By.id("frameButton")).click();
        // Alterna para o alerta que apareceu
        Alert alert = driver.switchTo().alert();
        // Captura a mensagem de texto do alerta
        String msg = alert.getText();
        // Verifica se a mensagem é exatamente "Frame OK!"
        Assert.assertEquals("Frame OK!", msg);
        // Aceita (OK) o alerta, fechando-o
        alert.accept();
        
        // Retorna ao contexto principal da página (fora de qualquer frame)
        driver.switchTo().defaultContent();
        // Localiza o campo de nome e insere a mensagem obtida do frame
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
    }
    
    @Test
    public void deveInteragirComJanelas() {
        // Armazena o identificador da janela principal
        String janelaPrincipal = driver.getWindowHandle();
        
        // Clica no botão que abre uma janela pop-up com título "Popup"
        driver.findElement(By.id("buttonPopUpEasy")).click();
        // Muda o contexto para a janela com título "Popup"
        driver.switchTo().window("Popup");
        // Na janela pop-up, localiza o textarea e digita um texto
        driver.findElement(By.tagName("textarea")).sendKeys("escreveu ?");
        // Fecha a janela pop-up atual
        driver.close();
        // Retorna o contexto para a janela principal
        driver.switchTo().window(janelaPrincipal);
        // Na janela principal, localiza outra textarea e digita texto de validação
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("escreveu 2.0 ?");
    }
    
    @Test
    public void deveInteragirComJanelasSemTitulos() {
        // Clica no botão que abre uma janela pop-up sem título definido
        driver.findElement(By.id("buttonPopUpHard")).click();
        
        // Obtém todos os handles de janela abertos e seleciona o segundo (índice 1)
        String popUpHandle = (String) driver.getWindowHandles().toArray()[1];
        // Muda o contexto para a janela pop-up recém-aberta
        driver.switchTo().window(popUpHandle);
        // Na janela pop-up, localiza o textarea e digita texto
        driver.findElement(By.tagName("textarea")).sendKeys("Escreveu popup");
        
        // Retorna ao handle da janela principal (índice 0)
        String mainHandle = (String) driver.getWindowHandles().toArray()[0];
        driver.switchTo().window(mainHandle);
        // Na janela principal, escreve em outro textarea
        driver.findElement(By.tagName("textarea")).sendKeys("Escreveu janela principal !");
        
        // Encerra toda a sessão e fecha todos os navegadores
        driver.quit();
    }
}
