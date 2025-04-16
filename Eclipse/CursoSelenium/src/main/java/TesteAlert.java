import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TesteAlert {

	@Test
    public void deveInteragirComAlertSimples() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));
     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	driver.findElement(By.id("alert")).click();
    	Alert alert = driver.switchTo().alert();
    	String textoalert = alert.getText();
    	Assert.assertEquals("Alert Simples",textoalert);
    	alert.accept();
    	driver.findElement(By.id("elementosForm:nome")).sendKeys(textoalert);
	}
	@Test
	public void deveInteragirComAlertConfirm() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));
     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	driver.findElement(By.id("confirm")).click();
    	Alert alertConfirm = driver.switchTo().alert();
    	Assert.assertEquals("Confirm Simples", alertConfirm.getText());
    	alertConfirm.accept();
    	Assert.assertEquals("Confirmado", alertConfirm.getText());
    	alertConfirm.accept();
    	
    	
    	driver.findElement(By.id("confirm")).click();
    	alertConfirm = driver.switchTo().alert();
    	Assert.assertEquals("Confirm Simples", alertConfirm.getText());
    	alertConfirm.dismiss();
    	Assert.assertEquals("Negado", alertConfirm.getText());
    	alertConfirm.dismiss();
    	
    	driver.quit();
    	
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
    	WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(600, 400));
     
    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
    	driver.findElement(By.id("prompt")).click();
    	Alert alert = driver.switchTo().alert();
    	Assert.assertEquals("Digite um numero", alert.getText());
    	alert.sendKeys("12");
    	alert.accept();
    	Assert.assertEquals("Era 12?", alert.getText());
    	alert.accept();
    	Assert.assertEquals(":D", alert.getText());
    	alert.accept();
    	driver.quit();
    	}
	
	
}
