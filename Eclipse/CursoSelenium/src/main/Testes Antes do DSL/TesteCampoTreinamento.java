// Importa classes necessárias do Java para manipulação de listas
import java.util.List;  

// Importa a anotação @After do JUnit, executada após cada teste
import org.junit.After;  
// Importa a classe Assert do JUnit para realizar verificações nos testes
import org.junit.Assert;  
// Importa a anotação @Before do JUnit, executada antes de cada teste
import org.junit.Before;  
// Importa a anotação @Test do JUnit para definir métodos de teste
import org.junit.Test;  

// Importa classe By do Selenium para localizar elementos na página
import org.openqa.selenium.By;  
// Importa classe Dimension do Selenium para manipular tamanho da janela
//import org.openqa.selenium.Dimension;  
// Importa interface WebDriver do Selenium para controlar o navegador
import org.openqa.selenium.WebDriver;  
// Importa implementação FirefoxDriver para usar o navegador Firefox
import org.openqa.selenium.firefox.FirefoxDriver;  
// Importa Select do Selenium para interagir com elementos <select>
import org.openqa.selenium.support.ui.Select;  
// Importa WebElement do Selenium, representa elementos HTML
import org.openqa.selenium.WebElement;  

// Declaração da classe de testes automatizados
public class TesteCampoTreinamento {
    
    // Declaração da variável de instância para o WebDriver
    private WebDriver driver;  
    
    // Método anotado com @Before executa antes de cada método de teste
    @Before
    public void inicializa() {
        // Instancia o driver do Firefox (abre o navegador)
        driver = new FirefoxDriver();  
        // Opcional: define o tamanho da janela se necessário
        // driver.manage().window().setSize(new Dimension(1200, 765));
        
        // Navega até a página de componentes locais usando URL do sistema de arquivos
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    }
    
    
    // Método anotado com @After executa após cada método de teste
    @After
    public void finalizar() {
        // Fecha o navegador e encerra a sessão do WebDriver
        driver.quit();
    };

    // Primeiro teste: interage com um campo de texto
    @Test
    public void testeTestField() {
        
        // Localiza o campo de nome pelo ID e insere texto
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita !");  
        // Verifica se o valor inserido corresponde ao esperado
        Assert.assertEquals("Teste de escrita !", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        
    }

    // Segundo teste: interage com uma área de texto (textarea)
    @Test
    public void deveInteragirComTextArea() {
        
        // Localiza o textarea pelo ID e insere texto
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("testando a escrita ");
        // Verifica se o valor inserido corresponde ao esperado
        Assert.assertEquals("testando a escrita ", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
      
    }

    // Terceiro teste: interage com botão de rádio (radio button)
    @Test
    public void deveInteragirComRadioButton() {
        
        // Clica na opção de sexo masculino (primeira opção, índice 0)
        driver.findElement(By.id("elementosForm:sexo:0")).click();  
        // Verifica se o radio button está selecionado
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        
    }

    // Quarto teste: interage com caixa de seleção (checkbox)
    @Test
    public void deveInteragirComCheckBox() {
        
        // Clica na terceira opção de comida favorita (índice 2)
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        // Verifica se o checkbox está selecionado
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
        
    }

    // Quinto teste: interage com um combo box (select)
    @Test 
    public void deveInteragirComCombo() {
        
        // Localiza o elemento <select> de escolaridade pelo ID
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        // Cria um objeto Select para facilitar interação com o <select>
        Select combo = new Select(element);
        // Seleciona a opção "2o grau completo" pelo texto visível
        combo.selectByVisibleText("2o grau completo");
        // Verifica se a opção selecionada corresponde ao esperado
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
        
    }

    // Sexto teste: verifica os valores presentes em um combo box
    @Test 
    public void deveVerificarValoresCombo() {
       
        // Localiza e instancia o objeto Select para escolaridade
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        // Obtém todas as opções disponíveis no combo
        List<WebElement> options = combo.getOptions();
        // Verifica se o combo possui exatamente 8 opções
        Assert.assertEquals(8, options.size());

        // Flag para verificar se encontrou a opção desejada
        boolean encontrou = false;
        // Itera por todas as opções buscando por "Mestrado"
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        // Confirma que a opção "Mestrado" está presente
        Assert.assertTrue(encontrou);
       
    }

    // Sétimo teste: interage com combo box múltiplo (permite seleção múltipla)
    @Test 
    public void deveVerificarValoresComboMultiplo() {
       
        // Localiza o combo múltiplo de esportes
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        // Seleciona múltiplas opções pelo texto visível
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        // Obtém todas as opções atualmente selecionadas
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        // Verifica se 3 opções foram selecionadas
        Assert.assertEquals(3, allSelectedOptions.size());

        // Deseleciona a opção "Corrida"
        combo.deselectByVisibleText("Corrida");

        // Atualiza a lista de opções selecionadas e verifica se restam 2
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());

    }
    
    // Teste de interação com botões
    @Test
    public void deveinteragirComBotoes() {
        
        // Localiza o botão simples pelo ID
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        
        // Clica no botão
        botao.click();
        
        // Verifica se o valor (label) do botão mudou para "Obrigado!"
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
        
    }
    
    // Teste de interação com links
    @Test
    public void deveinteragirComLinks() {
        	
        // Localiza o link para voltar pelo ID
        WebElement link = driver.findElement(By.id("linkvoltar"));
        // Clica no link
        link.click();
        // Verifica se o texto do elemento de resultado foi alterado para "Voltou!"
        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
        	
    }
    
    // Teste de busca de textos estáticos na página
    @Test
    public void deveBuscarTextosNaPagina() {
        	
        // Verifica título principal usando tag <h3>
        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
        // Verifica texto de alerta usando classe CSS "facilAchar"
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
        	
    }
    	
}
