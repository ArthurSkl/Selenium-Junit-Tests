// Importações do JUnit e Selenium para escrita e execução dos testes
import org.junit.After;                // Anotação @After para métodos executados após cada teste
import org.junit.Assert;               // Classe Assert para validações nasserções nos testes
import org.junit.Before;               // Anotação @Before para métodos executados antes de cada teste
import org.junit.Test;                 // Anotação @Test para definir métodos de teste
import org.openqa.selenium.Alert;       // Interface Alert para manipular diálogos JavaScript
import org.openqa.selenium.By;          // Classe By para localizar elementos na página
import org.openqa.selenium.WebDriver;   // Interface WebDriver para controlar o navegador
import org.openqa.selenium.firefox.FirefoxDriver; // Implementação do WebDriver para Firefox
import org.openqa.selenium.support.ui.ExpectedConditions; // Classes de condição para WebDriverWait
import org.openqa.selenium.support.ui.WebDriverWait;       // Classe WebDriverWait para esperas explícitas

import java.time.Duration;             // Classe Duration para especificar tempo em WebDriverWait

public class TesteAlert {
    // Declara o driver do navegador
    private WebDriver driver;
    // Declara o WebDriverWait para esperas explícitas
    private WebDriverWait wait;

    @Before
    public void inicializa() {
        // Instancia o FirefoxDriver e abre o navegador
        driver = new FirefoxDriver();
        // Configura o WebDriverWait com timeout de 5 segundos
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Carrega a página de teste a partir do sistema de arquivos
        driver.get(
            "file:///" + System.getProperty("user.dir") + 
            "/src/main/resources/campo_treinamento/componentes.html"
        );
    }

    @After
    public void finalizar() {
        // Encerra a sessão do WebDriver e fecha o navegador
        driver.quit();
    }

    @Test
    public void deveInteragirComAlertSimples() {
        // Clica no botão que gera o alert simples
        driver.findElement(By.id("alert")).click();
        // Aguarda até que o alert esteja presente
        wait.until(ExpectedConditions.alertIsPresent());
        // Muda o contexto para o alert e obtém sua instância
        Alert alert = driver.switchTo().alert();
        // Verifica se o texto do alert é o esperado
        Assert.assertEquals("Alert Simples", alert.getText());
        // Fecha o alert clicando em OK
        alert.accept();
        // Escreve o texto do alert no campo de nome para validação extra
        driver.findElement(By.id("elementosForm:nome"))
              .sendKeys("Alert Simples");
    }

    @Test
    public void deveInteragirComAlertConfirm() {
        // === Caminho OK ===
        // Clica no botão que gera o confirm
        driver.findElement(By.id("confirm")).click();
        // Aguarda o confirm aparecer
        wait.until(ExpectedConditions.alertIsPresent());
        // Captura o confirm e verifica o texto inicial
        Alert confirmOk = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", confirmOk.getText());
        // Aceita o confirm (OK)
        confirmOk.accept();

        // Aguarda e captura o alerta gerado pela aceitação
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirmado = driver.switchTo().alert();
        // Verifica se o texto do alert é "Confirmado"
        Assert.assertEquals("Confirmado", confirmado.getText());
        // Fecha o alert gerado pela confirmação
        confirmado.accept();

        // === Caminho Cancel ===
        // Reinicia o fluxo clicando novamente no confirm
        driver.findElement(By.id("confirm")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirmCancel = driver.switchTo().alert();
        // Verifica texto do confirm novamente
        Assert.assertEquals("Confirm Simples", confirmCancel.getText());
        // Clica em Cancel no confirm
        confirmCancel.dismiss();

        // Aguarda e captura o alert gerado pela negação
        wait.until(ExpectedConditions.alertIsPresent());
        Alert negado = driver.switchTo().alert();
        // Verifica se o texto do alert é "Negado"
        Assert.assertEquals("Negado", negado.getText());
        // Fecha o alert final
        negado.accept();
    }

    @Test
    public void deveInteragirComAlertPrompt() {
        // Clica no botão que gera o prompt
        driver.findElement(By.id("prompt")).click();
        // Espera até que o prompt esteja presente
        wait.until(ExpectedConditions.alertIsPresent());
        // Captura o prompt
        Alert prompt = driver.switchTo().alert();
        // Verifica se o texto do prompt é "Digite um numero"
        Assert.assertEquals("Digite um numero", prompt.getText());

        // Envia o valor "12" para o prompt e aceita
        prompt.sendKeys("12");
        prompt.accept();

        // Aguarda e captura o confirm subsequente ao prompt
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirm = driver.switchTo().alert();
        // Verifica se o texto é "Era 12?"
        Assert.assertEquals("Era 12?", confirm.getText());
        confirm.accept();

        // Aguarda e captura o alerta final após o confirm
        wait.until(ExpectedConditions.alertIsPresent());
        Alert resultado = driver.switchTo().alert();
        // Verifica se o texto final é ":D"
        Assert.assertEquals(":D", resultado.getText());
        // Fecha o último alert
        resultado.accept();
    }
}
