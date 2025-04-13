import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TesteCampoTreinamento {
	@Test
	public void testeTestField() {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\coelh\\Desktop\\java\\Driver FireFox\\geckodriver.exe");
		driver.manage().window().setSize(new Dimension(600, 400));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita !");
		Assert.assertEquals("Teste de escrita !", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.quit();
	}
	@Test
	public void deveInteragirComTextArea() {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\coelh\\Desktop\\java\\Driver FireFox\\geckodriver.exe");
		driver.manage().window().setSize(new Dimension(600, 400));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("testando a escrita ");
		Assert.assertEquals("testando a escrita ", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		driver.quit();
	}
	@Test
	public void deveInteragirComRadioButton() {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\coelh\\Desktop\\java\\Driver FireFox\\geckodriver.exe");
		driver.manage().window().setSize(new Dimension(600, 400));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.quit();
	}
	@Test
	public void deveInteragirComCheckBox() {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\coelh\\Desktop\\java\\Driver FireFox\\geckodriver.exe");
		driver.manage().window().setSize(new Dimension(600, 400));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
		driver.quit();
	}
	
	@Test 
	public void deveInteragirComCombo() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(600,400));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//combo.selectByIndex(2);
		//combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		driver.quit();
		
	}
	@Test 
	public void deveVerificarValoresCombo() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(600,400));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		driver.quit();
		
	}
}
