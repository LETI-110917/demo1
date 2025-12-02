package ex1B.upload.tests;

import ex1B.upload.pages.UploadPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadTest {

    private WebDriver driver;
    private UploadPage uploadPage;
    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException, InterruptedException { // Adicionado InterruptedException
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/upload");

        // PAUSA VISUAL: Para ver a página carregada antes de começar
        Thread.sleep(2000);

        uploadPage = new UploadPage(driver);

        Path path = Files.createTempFile("teste_selenium_", ".txt");
        tempFile = path.toFile();
        Files.writeString(path, "Conteúdo de teste para validação de upload.");
        tempFile.deleteOnExit();
    }

    @Test
    public void testFileLoadingAndValidation() throws InterruptedException { // Adicionado InterruptedException
        String absolutePath = tempFile.getAbsolutePath();
        String fileName = tempFile.getName();

        // --- Act (Ação - Separada para visualização) ---

        // 1. Apenas coloca o caminho do ficheiro (sem clicar ainda)
        uploadPage.setFileToUpload(absolutePath);

        // PAUSA VISUAL: Aqui consegue ver o caminho do ficheiro escrito na caixa de input
        System.out.println(">>> Pausa para ver o ficheiro selecionado...");
        Thread.sleep(2000);

        // 2. Agora clica no botão
        uploadPage.clickUploadButton();

        // PAUSA VISUAL: Para ver o resultado "File Uploaded!" antes de o teste validar e fechar
        System.out.println(">>> Pausa para ver o resultado do upload...");
        Thread.sleep(2000);

        // --- Assert ---
        assertEquals("File Uploaded!", uploadPage.getSuccessTitle(),
                "O título de sucesso deveria estar visível.");

        String uploadedText = uploadPage.getUploadedFileName();

        System.out.println("Ficheiro enviado: " + fileName);
        System.out.println("Ficheiro recebido na UI: " + uploadedText);

        assertTrue(uploadPage.isFileUploaded(fileName),
                "O nome do ficheiro na lista de uploads deve corresponder ao ficheiro enviado.");
    }

    @Test
    public void testUploadWithoutFileSelection() throws InterruptedException { // Adicionado InterruptedException

        // PAUSA VISUAL: Antes de clicar no erro
        System.out.println(">>> Pausa antes de clicar no upload vazio...");
        Thread.sleep(2000);

        uploadPage.clickUploadButton();

        // PAUSA VISUAL: Para ver a página de "Internal Server Error"
        System.out.println(">>> Pausa para ver a página de erro...");
        Thread.sleep(2000);

        String pageText = driver.findElement(By.tagName("body")).getText();

        if (pageText.contains("Internal Server Error")) {
            assertTrue(true);
        } else {
            assertTrue(pageText.contains("Choose a file"), "Deveria pedir para escolher um ficheiro");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}