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
import org.openqa.selenium.WebDriver;      // WebDriver: interface principal do Selenium    // WebElement: representa elementos HTML
import org.openqa.selenium.firefox.FirefoxDriver; // FirefoxDriver: implementa WebDriver para Firefox
import org.openqa.selenium.support.ui.Select;      // Select: facilita interação com <select>
import org.openqa.selenium.support.ui.WebDriverWait; // WebDriverWait: espera explícita por condições

public class Testecadastro {
    // Variável que controla o navegador
    private WebDriver driver;
    
    
    private DSL dsl;
    @Before
    public void inicializa() {
        // Cria instância do FirefoxDriver e abre o navegador
        driver = new FirefoxDriver();
        new WebDriverWait(driver, Duration.ofSeconds(5));
        // Carrega a página local de teste usando URL de arquivo
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finalizar() {
        // Fecha o navegador e encerra a sessão WebDriver
        driver.quit();
    }

    @Test
    public void deveRealizarCadastroComSucesso() {
        // Preenche o campo de nome com texto
    	dsl.escreve("elementosForm:nome", "Arthur Augusto");
        // Preenche o campo de sobrenome
    	dsl.escreve("elementosForm:sobrenome","Coelho Frantz");
        // Seleciona o rádio de sexo masculino (índice 0)
        dsl.clicarRadio("elementosForm:sexo:0");
        // Seleciona o checkbox de comida favorita (pizza, índice 2)
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
        // Localiza o elemento <select> de escolaridade
        dsl.selecionarCombo("elementosForm:escolaridade","Mestrado");
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.clicarBotao("elementosForm:cadastrar");
       
      
        
        // Verifica se a mensagem de sucesso começa com "Cadastrado!"
        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        // Verifica se o campo Nome foi registrado corretamente
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Arthur Augusto"));
        // Verifica o sobrenome exato
        Assert.assertEquals("Sobrenome: Coelho Frantz",dsl.obterTexto("descSobrenome"));
        // Verifica indicação de sexo
        Assert.assertEquals("Sexo: Masculino",dsl.obterTexto("descSexo"));
        // Verifica a comida favorita exibida
        Assert.assertEquals("Comida: Pizza", 
        		dsl.obterTexto("descComida"));
        // Verifica a escolaridade exibida (valor em lowercase conforme HTML)
        Assert.assertEquals("Escolaridade: superior",dsl.obterTexto("descEscolaridade"));
        // Verifica esporte selecionado
        Assert.assertEquals("Esportes: O que eh esporte?",dsl.obterTexto("descEsportes"));
        // Verifica sugestões apresentadas
        Assert.assertEquals("Sugestoes: Sem nem uma sugestão",dsl.obterTexto("descSugestoes"));
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
