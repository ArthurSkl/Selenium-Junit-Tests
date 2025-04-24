import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    // Escreve em um campo de texto ou textarea
    public void escreve(String idCampo, String texto) {
        driver.findElement(By.id(idCampo)).clear();
        driver.findElement(By.id(idCampo)).sendKeys(texto);
    }

    // Retorna o valor de um campo de texto
    public String obterValorCampo(String idCampo) {
        return driver.findElement(By.id(idCampo)).getAttribute("value");
    }

    // Clica em um botão
    public void clicarBotao(String id) {
        driver.findElement(By.id(id)).click();
    }

    // Clica em um link
    public void clicarLink(String texto) {
        driver.findElement(By.id(texto)).click();
    }

    // Retorna o texto visível de um elemento (pode ser localizado por ID ou outro seletor)
    public String obterTexto(String id) {
        return driver.findElement(By.id(id)).getText();
    }

    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }
    
    public String obterTextoComboSelecionado(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    // Interage com radio button
    public void clicarRadio(String id) {
        driver.findElement(By.id(id)).click();
    }

    public boolean isRadioMarcado(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }

    // Interage com checkbox
    public void clicarCheck(String id) {
        driver.findElement(By.id(id)).click();
    }

    public boolean isCheckMarcado(String id) {
        return driver.findElement(By.id(id)).isSelected();
    }

    // Interage com combo box (select)
    public void selecionarCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public void deselecionarCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
    }

    public int obterQuantidadeOpcoesCombo(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getOptions().size();
    }

    public boolean verificarOpcaoCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getOptions().stream().anyMatch(opt -> opt.getText().equals(valor));
    }

    public List<String> obterValoresCombo(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getAllSelectedOptions().stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }
}
