import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
        driver = new FirefoxDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");
        dsl = new DSL(driver);
        System.out.println("Caminho completo: file:///" + System.getProperty("user.dir") + "/src/main/resources/campo_treinamento/componentes.html");

    }

    @After
    public void finalizar() {
        driver.quit();
    }

    @Test
    public void testeTextField() {
        dsl.escreve("elementosForm:nome", "Teste de escrita !");
        Assert.assertEquals("Teste de escrita !", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testTextFieldDuplo() {
        dsl.escreve("elementosForm:nome", "Arthur");
        Assert.assertEquals("Arthur", dsl.obterValorCampo("elementosForm:nome"));
        dsl.escreve("elementosForm:nome", "Frantz");
        Assert.assertEquals("Frantz", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComTextArea() {
        dsl.escreve("elementosForm:sugestoes", "testando a escrita ");
        Assert.assertEquals("testando a escrita ", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckBox() {
        dsl.clicarCheck("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.obterTextoComboSelecionado("elementosForm:escolaridade"));
    }

    @Test
    public void deveVerificarValoresCombo() {
        Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        List<String> selecionados = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(3, selecionados.size());

        dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
        selecionados = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(2, selecionados.size());
    }

    @Test
    public void deveinteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterValorCampo("buttonSimple"));
    }

    @Test
    public void deveinteragirComLinks() {
        dsl.clicarLink("linkvoltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina() {
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
    }
}
