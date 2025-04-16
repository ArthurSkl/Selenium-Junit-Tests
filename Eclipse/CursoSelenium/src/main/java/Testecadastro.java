import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Testecadastro {
	@Test
    public void deveRealizarCadastroComSucesso() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	driver.findElement(By.id("elementosForm:nome")).sendKeys("Arthur Augusto");
    	driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Coelho Frantz");
    	driver.findElement(By.id("elementosForm:sexo:0")).click();    	
    	driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
    	WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
    	Select combo = new Select(element);
    	combo.selectByVisibleText("Superior");
    	WebElement esportes = driver.findElement(By.id("elementosForm:esportes"));
    	Select combo2 = new Select(esportes);
    	combo2.selectByVisibleText("O que eh esporte?");
    	driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Sem nem uma sugestão");
    	driver.findElement(By.id("elementosForm:cadastrar")).click();
    	
    	Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
    	Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Arthur Augusto"));
    	Assert.assertEquals("Sobrenome: Coelho Frantz", driver.findElement(By.id("descSobrenome")).getText());
    	Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
    	Assert.assertEquals("Comida: Pizza", driver.findElement(By.id("descComida")).getText());
    	Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
    	Assert.assertEquals("Esportes: O que eh esporte?", driver.findElement(By.id("descEsportes")).getText());
    	Assert.assertEquals("Sugestoes: Sem nem uma sugestão", driver.findElement(By.id("descSugestoes")).getText());
    	
    	driver.quit();
   	   	   	    	
	}
	
	@Test
    public void deveValidarNomeObrigatorio() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	
    	driver.findElement(By.id("elementosForm:cadastrar")).click();
    	Alert alert = driver.switchTo().alert();	
    	Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    	driver.quit();
	
	}
	
	
	@Test
    public void deveValidarSobrenomeObrigatorio() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	
    	driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome aleatorio");
    	driver.findElement(By.id("elementosForm:cadastrar")).click();
    	Alert alert = driver.switchTo().alert();	
    	Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    	driver.quit();
	
	}
	
	@Test
    public void deveValidarSexoObrigatorio() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	
    	driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome aleatorio");
    	driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobreNome aleatorio");
    	driver.findElement(By.id("elementosForm:cadastrar")).click();
    	Alert alert = driver.switchTo().alert();	
    	Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    	driver.quit();
	
	}
	
	
	@Test
    public void deveValidarComidaVegetariana() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	
    	driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome aleatorio");
    	driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobreNome aleatorio");
    	driver.findElement(By.id("elementosForm:sexo:1")).click();
    	
    	driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
    	driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
    	driver.findElement(By.id("elementosForm:cadastrar")).click();
    	
    	Alert alert = driver.switchTo().alert();	
    	Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    	driver.quit();
	
	}
	
	@Test
    public void deveValidarEsportistaIndeciso() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	
    	driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome aleatorio");
    	driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobreNome aleatorio");
    	driver.findElement(By.id("elementosForm:sexo:1")).click();
    	
    	driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
    	Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));   
    	combo.selectByVisibleText("Karate");
    	combo.selectByVisibleText("O que eh esporte?");
    	driver.findElement(By.id("elementosForm:cadastrar")).click();
    	Alert alert = driver.switchTo().alert();	
    	Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    	driver.quit();
	
	}
	
	
}
