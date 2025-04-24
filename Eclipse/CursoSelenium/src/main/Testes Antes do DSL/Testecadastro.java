// Importa a classe Duration para definir durações de tempo em WebDriverWait
import java.time.Duration;

// JUnit annotations e Assert para estruturação e validação dos testes
import org.junit.After;            // @After: executa método após cada @Test
import org.junit.Assert;           // Assert: para validar expectativas nos testes
import org.junit.Before;           // @Before: executa método antes de cada @Test
import org.junit.Test;             // @Test: marca métodos como casos de teste

// Selenium WebDriver e suporte a interações na página
import org.openqa.selenium.Alert;          // Alert: manipula diálogos JavaScript
import org.openqa.selenium.By;             // By: localiza elementos na página
import org.openqa.selenium.WebDriver;      // WebDriver: interface principal do Selenium
import org.openqa.selenium.WebElement;     // WebElement: representa elementos HTML
import org.openqa.selenium.firefox.FirefoxDriver; // FirefoxDriver: implementa WebDriver para Firefox
import org.openqa.selenium.support.ui.Select;      // Select: facilita interação com <select>
import org.openqa.selenium.support.ui.WebDriverWait; // WebDriverWait: espera explícita por condições

public class Testecadastro {
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
    public void deveRealizarCadastroComSucesso() {
        // Preenche o campo de nome com texto
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Arthur Augusto");
        // Preenche o campo de sobrenome
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Coelho Frantz");
        // Seleciona o rádio de sexo masculino (índice 0)
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        // Seleciona o checkbox de comida favorita (pizza, índice 2)
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        // Localiza o elemento <select> de escolaridade
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        // Cria objeto Select para interagir com o combo
        Select combo = new Select(element);
        // Seleciona a opção "Superior"
        combo.selectByVisibleText("Superior");
        // Localiza o <select> de esportes
        WebElement esportes = driver.findElement(By.id("elementosForm:esportes"));
        // Cria segundo objeto Select para múltiplas seleções
        Select combo2 = new Select(esportes);
        // Seleciona a opção "O que eh esporte?"
        combo2.selectByVisibleText("O que eh esporte?");
        // Preenche a textarea de sugestões
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Sem nem uma sugestão");
        // Clica no botão Cadastrar para submeter o formulário
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        
        // Verifica se a mensagem de sucesso começa com "Cadastrado!"
        Assert.assertTrue(driver.findElement(By.id("resultado")).getText()
                          .startsWith("Cadastrado!"));
        // Verifica se o campo Nome foi registrado corretamente
        Assert.assertTrue(driver.findElement(By.id("descNome")).getText()
                          .endsWith("Arthur Augusto"));
        // Verifica o sobrenome exato
        Assert.assertEquals("Sobrenome: Coelho Frantz", 
                            driver.findElement(By.id("descSobrenome")).getText());
        // Verifica indicação de sexo
        Assert.assertEquals("Sexo: Masculino", 
                            driver.findElement(By.id("descSexo")).getText());
        // Verifica a comida favorita exibida
        Assert.assertEquals("Comida: Pizza", 
                            driver.findElement(By.id("descComida")).getText());
        // Verifica a escolaridade exibida (valor em lowercase conforme HTML)
        Assert.assertEquals("Escolaridade: superior", 
                            driver.findElement(By.id("descEscolaridade")).getText());
        // Verifica esporte selecionado
        Assert.assertEquals("Esportes: O que eh esporte?", 
                            driver.findElement(By.id("descEsportes")).getText());
        // Verifica sugestões apresentadas
        Assert.assertEquals("Sugestoes: Sem nem uma sugestão", 
                            driver.findElement(By.id("descSugestoes")).getText());
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        // Tenta cadastrar sem preencher nome
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        // Muda o foco para o alert gerado pelo JS de validação
        Alert alert = driver.switchTo().alert();
        // Verifica mensagem de alerta para nome obrigatório
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        // Preenche apenas o nome
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome aleatorio");
        // Clica em cadastrar sem sobrenome
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        // Captura o alert de validação
        Alert alert = driver.switchTo().alert();
        // Verifica mensagem de alerta para sobrenome obrigatório
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        // Preenche nome e sobrenome, mas não seleciona sexo
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome aleatorio");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobreNome aleatorio");
        // Tenta cadastrar sem escolher sexo
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        // Captura e verifica alert para sexo obrigatório
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        // Preenche nome, sobrenome e sexo feminino
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome aleatorio");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobreNome aleatorio");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        // Seleciona carne e vegetariano simultaneamente
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        // Clica em cadastrar para disparar o alerta
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        // Captura e verifica alerta de incoerência vegetariana
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        // Preenche nome, sobrenome e sexo feminino
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome aleatorio");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobreNome aleatorio");
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        // Seleciona comida carne
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        // Localiza e configura combo de esportes
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        // Seleciona duas opções conflitantes
        combo.selectByVisibleText("Karate");
        combo.selectByVisibleText("O que eh esporte?");
        // Clica em cadastrar para disparar o alerta
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        // Captura e verifica alerta de indecisão esportiva
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    }
}
