import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TesteFramesEJanelas {
	
	@Test
    public void deveInteragirComFrames() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        
        
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();
        
        driver.switchTo().defaultContent();        
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
	
        driver.quit();		
	}
	
	
	@Test
    public void deveInteragirComJanelas() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
      
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        String janelaPrincipal = driver.getWindowHandle();
        
        
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("escreveu ?");
        driver.close();
        driver.switchTo().window(janelaPrincipal);
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("escreveu 2.0 ?");
        		      		        		
	}
	
	
	@Test
    public void deveInteragirComJanelasSemTitulos() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
      
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        //String janelaPrincipal = driver.getWindowHandle();
        
        
        driver.findElement(By.id("buttonPopUpHard")).click();
        //System.out.println(driver.getWindowHandle());
        //System.out.println(driver.getWindowHandles());
        
        //ID do pop = 50fd138c-322e-43e0-935a-c379c012f89f
        
        driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);        
        driver.findElement(By.tagName("textarea")).sendKeys("Escreveu popup");
        driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]); 
        driver.findElement(By.tagName("textarea")).sendKeys("Escreveu janela principal !");
        
        driver.quit();
        
        
	}

}
