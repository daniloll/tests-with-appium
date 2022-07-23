import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class TestCadastros {

    private AndroidDriver driver;

    @Before
    public void SetUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "MyCell");
        capabilities.setCapability("appium:appPackage", "com.example.cursoappium");
        capabilities.setCapability("appium:app", "C:\\Users\\danilo.leandro.lima\\apps\\app-curso-appium.apk");


        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);

    }

    @Test
    public void casdastrarUsuarioSemNome(){

        //Aguardar alguma informação da tela principal
            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.id("image_appium")));

        //Clicar na opção cadastrar pessoa
            MobileElement botaoCadastrarPessoa = (MobileElement) driver.findElementById("button_cadastrar");
            botaoCadastrarPessoa.click();

        //Inserir informações
            MobileElement campoDeEmail = (MobileElement) driver.findElementById("TextInputEmail");
            MobileElement opcaoSexoMaculino = (MobileElement) driver.findElementById("radioButton_homem");
            MobileElement botaoCadastrar = (MobileElement) driver.findElementById("BotaoCadastrar");

            campoDeEmail.sendKeys("meu-email@mail.com");
            driver.hideKeyboard();
            opcaoSexoMaculino.click();
            botaoCadastrar.click();

        //Verificação
            new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.id("textinput_error")));
            MobileElement msgErroNome = (MobileElement) driver.findElementById ("textinput_error");
            Assert.assertEquals("Insira o nome completo",msgErroNome.getText());

    }

    @After
    public void tearDown(){
        driver.quit();
    }


}
