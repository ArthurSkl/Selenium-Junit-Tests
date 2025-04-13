// Importa o método estático assertEquals da biblioteca JUnit para realizar asserções
import static org.junit.jupiter.api.Assertions.assertEquals;

// Importa classes necessárias do Java para manipulação de listas
import java.util.List;

// Importa a classe Assert do JUnit para realizar verificações
import org.junit.Assert;
// Importa a anotação @Test do JUnit para definir métodos de teste
import org.junit.Test;

// Importa classes do Selenium para localizar elementos na página
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

// Importa o WebDriverManager para configurar o driver automaticamente
import io.github.bonigarcia.wdm.WebDriverManager;

// Classe de testes automatizados com Selenium
public class TesteCampoTreinamento {

    // Primeiro teste: interage com um campo de texto
    @Test
    public void testeTestField() {
        // Configura o driver do Firefox automaticamente
        WebDriverManager.firefoxdriver().setup();
        // Cria uma instância do navegador Firefox
        WebDriver driver = new FirefoxDriver();
        // Define o tamanho da janela do navegador
        driver.manage().window().setSize(new Dimension(600, 400));
        // Abre o arquivo HTML local que será testado
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        // Localiza o campo de nome e insere um texto
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita !");
        // Verifica se o texto foi realmente inserido no campo
        Assert.assertEquals("Teste de escrita !", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        // Fecha o navegador
        driver.quit();
    }

    // Segundo teste: interage com uma área de texto (textarea)
    @Test
    public void deveInteragirComTextArea() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(600, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("testando a escrita ");
        Assert.assertEquals("testando a escrita ", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
        driver.quit();
    }

    // Terceiro teste: interage com botão de rádio (radio button)
    @Test
    public void deveInteragirComRadioButton() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(600, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        // Clica no botão de rádio de sexo masculino (índice 0)
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        // Verifica se está selecionado
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();
    }

    // Quarto teste: interage com caixa de seleção (checkbox)
    @Test
    public void deveInteragirComCheckBox() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(600, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        // Clica em uma das opções de comida favorita (índice 2)
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        // Verifica se foi selecionada
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
        driver.quit();
    }

    // Quinto teste: interage com um combo box (select)
    @Test 
    public void deveInteragirComCombo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(600,400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        // Localiza o elemento de combo (escolaridade)
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        // Cria um objeto Select para interagir com o combo
        Select combo = new Select(element);
        // Seleciona a opção "2o grau completo" pelo texto visível
        combo.selectByVisibleText("2o grau completo");
        // Verifica se a opção selecionada é realmente "2o grau completo"
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
        driver.quit();
    }

    // Sexto teste: verifica os valores presentes em um combo box
    @Test 
    public void deveVerificarValoresCombo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(600,400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        // Obtém todas as opções do combo
        List<WebElement> options = combo.getOptions();
        // Verifica se há exatamente 8 opções no combo
        Assert.assertEquals(8, options.size());

        // Verifica se existe a opção "Mestrado" entre as opções
        boolean encontrou = false;
        for(WebElement option: options) {
            if(option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        // Confirma que a opção foi encontrada
        Assert.assertTrue(encontrou);
        driver.quit();
    }

    // Sétimo teste: interage com combo box múltiplo (permite múltiplas seleções)
    @Test 
    public void deveVerificarValoresComboMultiplo() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(600,400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        // Seleciona múltiplas opções
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        // Obtém todas as opções selecionadas
        List<WebElement> allSelectedOpitions = combo.getAllSelectedOptions();
        // Verifica se 3 opções foram selecionadas
        Assert.assertEquals(3, allSelectedOpitions.size());

        // Deseleciona uma das opções
        combo.deselectByVisibleText("Corrida");

        // Verifica se agora há apenas 2 opções selecionadas
        allSelectedOpitions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOpitions.size());

        driver.quit();
    }
    
    @Test
    public void deveinteragirComBotoes() {
        // Cria uma instância do WebDriver usando o Firefox
        WebDriver driver = new FirefoxDriver();
        
        // Define o tamanho da janela do navegador
        driver.manage().window().setSize(new Dimension(600, 400));
        
        // Acessa uma página local (campo_treinamento/componentes.html)
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        
        // Localiza o botão com o id "buttonSimple" na página
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        
        // Clica no botão
        botao.click();
        
        // Verifica se o atributo "value" do botão mudou para "Obrigado!"
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
        
        // Fecha o navegador
        driver.quit();
    }
    
    @Test
    public void deveinteragirComLinks() {
    	WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(600, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        driver.findElement(By.linkText("Voltar")).click();
    			
    	
    	
    	
    	
    	
    	
    }

}
