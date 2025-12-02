package ex1B.upload.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://the-internet.herokuapp.com/upload
public class UploadPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- 1. Mapeamento de Elementos (Locators) ---

    // O input oculto ou visível que recebe o caminho do ficheiro
    @FindBy(id = "file-upload")
    private WebElement fileInput;

    // O botão de submissão "Upload"
    @FindBy(id = "file-submit")
    private WebElement uploadButton;

    // A área de "Drag and Drop" (opcional, caso queira testar a área de arraste)
    @FindBy(id = "drag-drop-upload")
    private WebElement dragDropArea;

    // Elemento que exibe o nome do ficheiro após o sucesso
    @FindBy(id = "uploaded-files")
    private WebElement uploadedFilesList;

    // Título de confirmação (h3)
    @FindBy(tagName = "h3")
    private WebElement resultHeader;

    // --- 2. Construtor ---
    public UploadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- 3. Métodos de Ação ---

    /**
     * Define o caminho do ficheiro no input.
     * @param absolutePath O caminho absoluto do ficheiro no sistema de ficheiros.
     */
    public void setFileToUpload(String absolutePath) {
        // Em Selenium, não clicamos no botão "Browse".
        // Enviamos o caminho diretamente para o elemento <input type="file">.
        fileInput.sendKeys(absolutePath);
    }

    public void clickUploadButton() {
        uploadButton.click();
    }

    // Método de fluxo completo (Facilitador)
    public void uploadFile(String absolutePath) {
        setFileToUpload(absolutePath);
        clickUploadButton();
    }

    // --- 4. Métodos de Validação ---

    public String getSuccessTitle() {
        wait.until(ExpectedConditions.visibilityOf(resultHeader));
        return resultHeader.getText();
    }

    public String getUploadedFileName() {
        wait.until(ExpectedConditions.visibilityOf(uploadedFilesList));
        return uploadedFilesList.getText().trim();
    }

    public boolean isFileUploaded(String fileName) {
        return getUploadedFileName().contains(fileName);
    }

    @FindBy(tagName = "body")
    private WebElement body;

    public String getBodyText() {
        return body.getText();
    }
}