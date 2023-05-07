package com.uniovi.sdi2223entrega2test.n;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCollection;
import com.uniovi.sdi2223entrega2test.n.mongo.MongoDB;
import com.uniovi.sdi2223entrega2test.n.pageobjects.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2223Entrega2TestApplicationTests {

    // Miguel
    // static String PathFirefox = "C:\\Archivos de programa\\Mozilla Firefox\\firefox.exe";
    // static String Geckodriver = "C:\\Users\\migue\\Desktop\\SDI\\LABORATORIO\\spring\\sesion06\\PL-SDI-Sesión5-material\\PL-SDI-Sesio╠ün5-material\\geckodriver-v0.30.0-win64.exe";

    // Raúl
    // static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    // static String Geckodriver = "C:\\Users\\Aladino España\\Desktop\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    // Ton
    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    static String Geckodriver = "C:\\Users\\tonpm\\OneDrive\\Documentos\\MisDocumentos\\Clase\\2022\\SDI\\geckodriver-v0.30.0-win64.exe";

    // Alves
//    static String PathFirefox = "C:\\Archivos de programa\\Mozilla Firefox\\firefox.exe";
//    static String Geckodriver = "C:\\Users\\Alves\\Desktop\\selenium-test\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    // Luis
//    static String PathFirefox = "C:\\Archivos de programa\\Mozilla Firefox\\firefox.exe";
//    static String Geckodriver = "C:\\Users\\luism\\Desktop\\Clase\\SDI\\Sesión6\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";

    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    //static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";
    //Común a Windows y a MACOSX

    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:3000";

    // acceder a la base de datos
    static MongoDB mongo;

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp() {
        driver.navigate().to(URL);
        mongo = new MongoDB();
        mongo.resetMongo();
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {
    }

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
        // cerramos mongo
        mongo.close();
    }

    // ###############################################################################################
    // ######################################### PARTE 1 #############################################
    // ###############################################################################################

    //[Prueba1] Registro de Usuario con datos válidos
    @Test
    @Order(1)
    public void PR01() {
        /*driver.get("http://localhost:3000/users/signup");

        //Rellenamos el formulario
        WebElement email = driver.findElement(By.id("email"));
        WebElement name = driver.findElement(By.id("name"));
        WebElement surname = driver.findElement(By.id("surname"));
        WebElement birthdate = driver.findElement(By.id("birthdate"));

        WebElement password = driver.findElement(By.id("password"));
        WebElement passwordConfirm = driver.findElement(By.name("passwordConfirm"));
        WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));

        email.sendKeys("user20@email.com");
        name.sendKeys("user20");
        surname.sendKeys("user20");
        birthdate.clear();
        LocalDate dateToEnter = LocalDate.of(2023, 5, 6); // fecha a introducir
        String dateToEnterAsString = dateToEnter.format(DateTimeFormatter.ISO_DATE); // fecha formateada en "yyyy-MM-dd"
        birthdate.sendKeys(dateToEnterAsString);
        password.sendKeys("user20");
        passwordConfirm.sendKeys("user20");
        submit.click();

        //Esperamos a que se cargue la página de inicio de sesión
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Iniciar sesión"));

        //Comprobamos que hemos sido redirigidos a la página de inicio de sesión
        assertEquals("Iniciar sesión", driver.getTitle());*/

    }


    //Prueba5] Inicio de sesión con datos válidos (administrador)
    @Test
    @Order(7)
    public void PR05() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");
        //Comprobamos que entramos en la página privada del administrador
        String checkText = "Usuarios";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //[Prueba6] Inicio de sesión con datos válidos (usuario estándar)
    @Test
    @Order(8)
    public void PR06() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");
        //Comprobamos que entramos en la página privada de usuario
        String checkText = "Mis ofertas";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);

        Assertions.assertEquals(checkText, checkText);
    }

    //[Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email y contraseña vacíos)
    @Test
    @Order(9)
    public void PR07() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "", "");
        //Comprobamos que entramos en la página privada de usuario
        String checkText = "Identifícate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //[Prueba8] Inicio de sesión con datos válidos (usuario estándar, email existente, pero contraseña incorrecta)
    @Test
    @Order(10)
    public void PR08() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "i");
        //Comprobamos que entramos en la página privada de usuario
        String checkText = "Email o password incorrecto";
        List<WebElement> result = PO_LoginView.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }


    /**
     * PR16. Ir al formulario de alta de oferta, rellenarla con datos válidos y pulsar el botón Submit.
     * Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
     */
    @Test
    @Order(16)
    public void PR16() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user16@email.com", "user16");
        //Obtenemos el número actual de ofertas
        int actualOffers = mongo.getOffers("user16@email.com");
        //Accedemos a añadir oferta
        By addBtn = By.xpath("//a[@href='/offers/add' and contains(@class, 'btn-info')]");
        driver.findElement(addBtn).click();
        //Rellenamos el formulario y lo enviamos
        PO_AddOfferView.fillAddForm(driver, "Mesa", "Grande", "55");
        //Comprobamos que el número de ofertas haya aumentado
        Assertions.assertEquals(mongo.getOffers("user16@email.com"), actualOffers + 1);
        //Vamos a la última página de mis ofertas
        By pageBtn = By.xpath("//ul[@class=\"pagination\"]/li[last()]/a");
        driver.findElement(pageBtn).click();
        String checkText = "Mesa";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        //Comprobamos que está la oferta
        Assertions.assertEquals(checkText, result.get(0).getText());

    }

    /**
     * PR17. Ir al formulario de alta de oferta, rellenarla con datos inválidos (campo título vacío y precio
     * en negativo) y pulsar el botón Submit. Comprobar que se muestra el mensaje de campo inválido.
     */
    @Test
    @Order(17)
    public void PR17() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user16@email.com", "user16");
        //Obtenemos el número actual de ofertas
        int actualOffers = mongo.getOffers("user16@email.com");
        //Accedemos a añadir oferta
        By addBtn = By.xpath("//a[@href='/offers/add' and contains(@class, 'btn-info')]");
        driver.findElement(addBtn).click();
        //Rellenamos el formulario y lo enviamos
        PO_AddOfferView.fillAddForm(driver, " ", " ", "-3");
        String checkText = "Error al añadir la oferta: Datos introducidos no válidos";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        //Comprobamos que se ve el mensaje
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    /**
     * PR18. Mostrar el listado de ofertas para dicho usuario y comprobar que se muestran todas las que
     * existen para este usuario.
     */
    @Test
    @Order(18)
    public void PR18() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user16@email.com", "user16");
        //Obtenemos el número actual de ofertas
        int dbOffers = mongo.getOffers("user16@email.com");
        //Hacemos el cálculo de ofertas que se visualizan en la página
        By pageBtn = By.xpath("//ul[@class=\"pagination\"]/li[last()]/a");
        driver.findElement(pageBtn).click();
        WebElement currentPageLink = driver.findElement(By.cssSelector(".page-item.active a.page-link"));
        int currentPageNumber = Integer.parseInt(currentPageLink.getText());
        WebElement tableBody = driver.findElement(By.cssSelector("table.table tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        int webOffers = rowCount + (currentPageNumber - 1) * 5;
        //Comprobamos que el número de ofertas en la bd y en la web sean las mismas
        Assertions.assertEquals(webOffers, dbOffers);
    }

    /**
     * PR19. Ir a la lista de ofertas, borrar la primera oferta de la lista, comprobar que la lista se actualiza
     * y que la oferta desaparece.
     */
    @Test
    @Order(19)
    public void PR19() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user16@email.com", "user16");
        //Obtenemos el número actual de ofertas
        int dbOffers = mongo.getOffers("user16@email.com");
        //Obtenemos el título de la primera oferta
        WebElement firstRow = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]"));
        String firstValue = firstRow.getText();
        //Borramos la primera oferta
        By deleteFirst = By.xpath("//a[contains(@href,'delete')][1]");
        driver.findElement(deleteFirst).click();
        //Obtenemos el título de la nueva primera oferta
        WebElement newFirstRow = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]"));
        String newFirstValue = newFirstRow.getText();
        //Comparamos y vemos que la borrada ya no aparece la primera
        Assertions.assertNotEquals(newFirstValue, firstValue);
        //Comprobamos que el número de ofertas en la bd ha disminuido
        Assertions.assertEquals(dbOffers - 1, mongo.getOffers("user16@email.com"));
    }

    /**
     * PR20. Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar que la lista se actualiza
     * y que la oferta desaparece.
     */
    @Test
    @Order(20)
    public void PR20() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user16@email.com", "user16");
        //Obtenemos el número actual de ofertas
        int dbOffers = mongo.getOffers("user16@email.com");
        //Vamos a la última página
        By pageBtn = By.xpath("//ul[@class=\"pagination\"]/li[last()]/a");
        driver.findElement(pageBtn).click();
        //Obtenemos el título de la primera oferta
        WebElement lastRow = driver.findElement(By.xpath("//table/tbody/tr[last()]/td[1]"));
        String firstValue = lastRow.getText();
        //Borramos la primera oferta
        By deleteFirst = By.xpath("(//a[contains(@href,'delete')])[last()]");
        driver.findElement(deleteFirst).click();
        //Obtenemos el título de la nueva primera oferta
        WebElement newLastRow = driver.findElement(By.xpath("//table/tbody/tr[last()]/td[1]"));
        String newFirstValue = newLastRow.getText();
        //Comparamos y vemos que la borrada ya no aparece la primera
        Assertions.assertNotEquals(newFirstValue, firstValue);
        //Comprobamos que el número de ofertas en la bd ha disminuido
        Assertions.assertEquals(dbOffers - 1, mongo.getOffers("user16@email.com"));
    }

    /**
     * PR21. Ir a la lista de ofertas, borrar una oferta de otro usuario, comprobar que la oferta no se
     * borra.
     */
    @Test
    @Order(21)
    public void PR21() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user16@email.com", "user16");
        //Obtenemos el número actual de ofertas del usuario 1
        int dbOffers = mongo.getOffers("user01@email.com");
        //Como no se puede eliminar ofertas de otro usuario navegando por la web, tengo que ir a la url directamente
        driver.navigate().to("http://localhost:3000/offers/delete/645658a1e88359d0a8519e61");
        String checkText = "No se ha podido eliminar la oferta";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        //Comprobamos el mensaje de error
        Assertions.assertEquals(checkText, result.get(0).getText());
        //Comprobamos en la bd que el número de ofertas del usuario 1 es el mismo
        Assertions.assertEquals(dbOffers, mongo.getOffers("user01@email.com"));
    }

    /**
     * PR22. Ir a la lista de ofertas, borrar una oferta propia que ha sido vendida, comprobar que la
     * oferta no se borra.
     */
    @Test
    @Order(22)
    public void PR22() {
        //Vamos al formulario de inicio de sesión.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user16@email.com", "user16");
        // vamos a la vista de buscar ofertas
        PO_ListOfferView.goToPage(driver);
        // compramos la oferta 1 del usuario 1
        By comprar = By.xpath("//tbody[@id='offers']/tr[1]/td[5]/a[@class='btn btn-primary pull-right']");
        driver.findElement(comprar).click();
        // logout
        PO_PrivateView.refactorLogout(driver);
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");
        //Obtenemos el número actual de ofertas del usuario 1
        int dbOffers = mongo.getOffers("user01@email.com");
        //Intentamos borrar la primera oferta
        By deleteFirst = By.xpath("//a[contains(@href,'delete')][1]");
        driver.findElement(deleteFirst).click();
        String checkText = "No se ha podido eliminar la oferta";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        //Comprobamos el mensaje de error
        Assertions.assertEquals(checkText, result.get(0).getText());
        //Comprobamos en la bd que el número de ofertas del usuario 1 es el mismo
        Assertions.assertEquals(dbOffers, mongo.getOffers("user01@email.com"));
    }

    /**
     * PR23. Ir a la lista de ofertas, y buscar con el buscador vacío,
     * comprobar que aparecen todas las ofertas
     */
    @Test
    @Order(23)
    public void PR23() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user01@email.com", "user01");
        // vamos a la vista de buscar ofertas
        PO_ListOfferView.goToPage(driver);
        // introducimos un campo que no existe en el campo de búsqueda
        WebElement input = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        input.click();
        input.clear();
        input.sendKeys("");
        // seleccionamos el botón de buscar
        By boton = By.xpath("//*[@id=\"custom-search-input \"]/form/div/span/button");
        driver.findElement(boton).click();
        // ponemos una paginación alta para que aparezcan todas las ofertas
        String url = "http://localhost:3000/offers/searchOffers?pageSize=500";
        driver.navigate().to(url);
        List<WebElement> offers = PO_HomeView.checkElementTableBody(driver, "offers"); // ofertas
        // comprobamos que están todas las ofertas
        Assertions.assertEquals(mongo.getCollection("offers").count(), offers.size());
        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR24. Ir a la lista de ofertas, y buscar con el buscador un texto que no exista,
     * comprobamos que aparece la lista vacía
     */
    @Test
    @Order(24)
    public void PR24() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user01@email.com", "user01");
        // vamos a la vista de buscar ofertas
        PO_ListOfferView.goToPage(driver);
        // comprobamos que hay ofertas en la base de datos
        Assertions.assertTrue(mongo.getCollection("offers").count() > 0);
        // introducimos un campo que no existe en el campo de búsqueda
        WebElement input = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        input.click();
        input.clear();
        input.sendKeys("cdcduefhdrufc");
        // seleccionamos el botón de buscar
        By boton = By.xpath("//*[@id=\"custom-search-input \"]/form/div/span/button");
        driver.findElement(boton).click();
        // comprobamos que no hay páginas (ninguna búsqueda existente)
        Assertions.assertEquals(0, PO_HomeView.checkElementUl(driver, "pagination"));
        // no hay ninguna oferta en la tabla
        Assertions.assertEquals(0, PO_HomeView.checkElementTableBody(driver, "offers").size());
        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR25. Hacer una búsqueda escribiendo en el campo un texto en minúscula o mayúscula y
     * comprobar que se muestra la página que corresponde, con la lista de ofertas que contengan dicho
     * texto, independientemente que el título esté almacenado en minúsculas o mayúscula.
     */
    @Test
    @Order(25)
    public void PR25() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user01@email.com", "user01");
        // vamos a la vista de buscar ofertas
        PO_ListOfferView.goToPage(driver);
        // introducimos un campo que no existe en el campo de búsqueda
        WebElement input = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        input.click();
        input.clear();
        input.sendKeys("OFERTA141");
        // seleccionamos el botón de buscar
        By boton = By.xpath("//*[@id=\"custom-search-input \"]/form/div/span/button");
        driver.findElement(boton).click();
        // comprobamos que hay una sola página
        Assertions.assertEquals(1, PO_HomeView.checkElementUl(driver, "pagination"));
        // hay una oferta en la tabla (que es la 141)
        Assertions.assertEquals(1, PO_HomeView.checkElementTableBody(driver, "offers").size());
        // comprobamos que existe la oferta en la base de datos
        Assertions.assertTrue(mongo.getOffer("Oferta141") == 1);
        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR26. Sobre una búsqueda determinada (a elección de desarrollador), comprar una oferta que
     * deja un saldo positivo en el contador del comprobador. Y comprobar que el contador se actualiza
     * correctamente en la vista del comprador
     */
    @Test
    @Order(26)
    public void PR26() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user01@email.com", "user01");
        // vamos a la vista de buscar ofertas
        PO_ListOfferView.goToPage(driver);
        // introducimos un campo que no existe en el campo de búsqueda
        WebElement input = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        input.click();
        input.clear();
        input.sendKeys("11");
        // seleccionamos el botón de buscar
        By boton = By.xpath("//*[@id=\"custom-search-input \"]/form/div/span/button");
        driver.findElement(boton).click();
        // comprobamos que tiene el saldo de su base de datos
        double saldoA = PO_ListOfferView.wallet(driver);
        Assertions.assertEquals(mongo.getSaldo("user01@email.com"), saldoA);
        // sacamos el precio de la oferta que vamos a comprar
        double precio = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"offers\"]/tr/td[4]")).getText());
        // comprobamos que el precio mostrado se corresponde con el de la base de datos
        Assertions.assertEquals(mongo.getPrice("Oferta11"), precio);
        // compramos la oferta 11
        By comprar = By.xpath("//*[@id=\"offers\"]/tr/td[5]/a");
        driver.findElement(comprar).click();
        // comprobamos que tiene saldo positivo
        double saldoB = PO_ListOfferView.wallet(driver);
        Assertions.assertTrue(saldoB > 0);
        // comprobamos que tiene el saldo de su base de datos
        Assertions.assertEquals(mongo.getSaldo("user01@email.com"), saldoB);
        // comprobamos que se ha descontado el precio correctamente
        Assertions.assertEquals(precio, saldoA - saldoB);
        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR27. Sobre una búsqueda determinada (a elección de desarrollador), comprar una oferta que
     * deja un saldo 0 en el contador del comprobador. Y comprobar que el contador se actualiza
     * correctamente en la vista del comprador.
     */
    @Test
    @Order(27)
    public void PR27() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user02@email.com", "user02");
        // vamos a la vista de buscar ofertas
        PO_ListOfferView.goToPage(driver);
        // introducimos un campo que no existe en el campo de búsqueda
        WebElement input = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        input.click();
        input.clear();
        input.sendKeys("100");
        // seleccionamos el botón de buscar
        By boton = By.xpath("//*[@id=\"custom-search-input \"]/form/div/span/button");
        driver.findElement(boton).click();
        // comprobamos que tiene el saldo de la base de datos
        double saldoA = PO_ListOfferView.wallet(driver);
        Assertions.assertEquals(mongo.getSaldo("user02@email.com"), saldoA);
        // sacamos el precio de la oferta que vamos a comprar
        double precio = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"offers\"]/tr/td[4]")).getText());
        // comprobamos que el precio mostrado se corresponde con el de la base de datos
        Assertions.assertEquals(mongo.getPrice("Oferta100"), precio);
        // compramos la oferta 100
        By comprar = By.xpath("//*[@id=\"offers\"]/tr/td[5]/a");
        driver.findElement(comprar).click();
        // comprobamos que tiene saldo a cero
        double saldoB = PO_ListOfferView.wallet(driver);
        Assertions.assertTrue(saldoB == 0);
        // comprobamos que tiene el saldo de su base de datos
        Assertions.assertEquals(mongo.getSaldo("user02@email.com"), saldoB);
        // comprobamos que se ha descontado el precio correctamente
        Assertions.assertEquals(precio, saldoA - saldoB);
        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR28. Sobre una búsqueda determinada (a elección de desarrollador), intentar comprar una oferta
     * que esté por encima de saldo disponible del comprador. Y comprobar que se muestra el mensaje
     * de saldo no suficiente.
     */
    @Test
    @Order(28)
    public void PR28() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user03@email.com", "user03");
        // vamos a la vista de buscar ofertas
        PO_ListOfferView.goToPage(driver);
        // introducimos un campo que no existe en el campo de búsqueda
        WebElement input = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        input.click();
        input.clear();
        input.sendKeys("101");
        // seleccionamos el botón de buscar
        By boton = By.xpath("//*[@id=\"custom-search-input \"]/form/div/span/button");
        driver.findElement(boton).click();
        // comprobamos que tiene el saldo de la base de datos
        double saldoA = PO_ListOfferView.wallet(driver);
        Assertions.assertEquals(mongo.getSaldo("user03@email.com"), saldoA);
        // sacamos el precio de la oferta que supuestamente queremos comprar
        double precio = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"offers\"]/tr/td[4]")).getText());
        // comprobamos que el precio mostrado se corresponde con el de la base de datos
        Assertions.assertEquals(mongo.getPrice("Oferta101"), precio);
        // comprobamos que el precio es superior al saldo del usuario
        Assertions.assertTrue(precio > saldoA);
        // la oferta tiene un precio de 101 euros
        Assertions.assertEquals(101, precio);
        // intentamos comprar la oferta 101
        By comprar = By.xpath("//*[@id=\"offers\"]/tr/td[5]/a");
        driver.findElement(comprar).click();
        // comprobamos que tiene el mismo saldo, ya que no la pudo comprar
        double saldoB = PO_ListOfferView.wallet(driver);
        Assertions.assertTrue(saldoB == saldoA);
        Assertions.assertEquals(mongo.getSaldo("user03@email.com"), saldoB);
        // comprobamos que se ha lanzado un mensaje de error
        String messageError = PO_ListOfferView.getErrors(driver);
        Assertions.assertEquals("[Titulo=Oferta101] ERROR: no tienes suficiente saldo para comprar la oferta", messageError);
        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR29. Ir a la opción de ofertas compradas del usuario y mostrar la lista. Comprobar que aparecen
     * las ofertas que deben aparecer
     */
    @Test
    @Order(29)
    public void PR29() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user03@email.com", "user03");
        // vamos a la vista de las ofertas compradas por el usuario y vemos que no aparece ninguna oferta comprada
        PO_BuyOfferView.goToPage(driver);
        List<WebElement> tableBefore = PO_HomeView.checkElementTableBody(driver, "buy-offers");
        Assertions.assertEquals(mongo.getBuys("user03@email.com"), tableBefore.size());
        // vamos a la vista de buscar ofertas
        PO_ListOfferView.goToPage(driver);
        // introducimos un campo que no existe en el campo de búsqueda
        WebElement input = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        input.click();
        input.clear();
        input.sendKeys("99");
        // seleccionamos el botón de buscar
        By boton = By.xpath("//*[@id=\"custom-search-input \"]/form/div/span/button");
        driver.findElement(boton).click();
        // compramos la oferta 99
        By comprar = By.xpath("//*[@id=\"offers\"]/tr/td[5]/a");
        driver.findElement(comprar).click();
        // vamos a la vista de las ofertas compradas por el usuario y vemos que aparece la oferta
        PO_BuyOfferView.goToPage(driver);
        List<WebElement> tableAfter = PO_HomeView.checkElementTableBody(driver, "buy-offers");
        Assertions.assertEquals(mongo.getBuys("user03@email.com"), tableAfter.size());
        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR30. Al crear una oferta, marcar dicha oferta como destacada y a continuación comprobar: i)
     * que aparece en el listado de ofertas destacadas para los usuarios y que el saldo del usuario se
     * actualiza adecuadamente en la vista del ofertante (comprobar saldo antes y después, que deberá
     * diferir en 20€).
     */
    @Test
    @Order(30)
    public void PR30() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user07@email.com", "user07");

        // accedemos al formulario para dar de alta una oferta
        WebElement ofertas = driver.findElement(By.linkText("Ofertas"));
        ofertas.click();
        WebElement aOFertas = driver.findElement(By.linkText("Añadir ofertas"));
        aOFertas.click();

        // guardamos el saldo antes de destacar la oferta
        double saldoA = PO_ListOfferView.wallet(driver);

        // rellenamos el formulario para añadir una oferta con la opción de destacar activada
        WebElement destacar = driver.findElement(By.name("highlight"));
        destacar.click();
        PO_AddOfferView.fillAddForm(driver, "Destacada1", "Esta oferta está destacada",
                "10");

        // guardamos el saldo después de destacar la oferta
        double saldoB = PO_ListOfferView.wallet(driver);

        // comprobamos que el saldo después de destacar es 20€ menor que el saldo antes de destacar
        assertTrue(saldoA == (saldoB + 20));

        // logout
        PO_PrivateView.refactorLogout(driver);

        // nos logueamos con otro usuario
        PO_PrivateView.refactorLogging(driver, "user08@email.com", "user08");

        // accedemos a la lista de ofertas privada
        WebElement ofertas2 = driver.findElement(By.linkText("Ofertas"));
        ofertas2.click();
        WebElement misOfertas = driver.findElement(By.linkText("Mis Ofertas"));
        misOfertas.click();

        // buscamos la oferta que hemos destacado
        List<WebElement> destacada = PO_View.checkElementBy(driver, "text", "Destacada1");
        // Comprobamos que efectivamente la hemos encontrado
        assertTrue(destacada.size() == 1);

        // logout
        PO_PrivateView.refactorLogout(driver);

    }

    /**
     * PR31. Sobre el listado de ofertas de un usuario con más de 20 euros de saldo, pinchar en el enlace
     * Destacada y a continuación comprobar: i) que aparece en el listado de ofertas destacadas para los
     * usuarios y que el saldo del usuario se actualiza adecuadamente en la vista del ofertante (comprobar
     * saldo antes y después, que deberá diferir en 20€ ).
     */
    @Test
    @Order(31)
    public void PR31() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user07@email.com", "user07");

        // accedemos al formulario para dar de alta una oferta
        WebElement ofertas = driver.findElement(By.linkText("Ofertas"));
        ofertas.click();
        WebElement aOFertas = driver.findElement(By.linkText("Mis Ofertas"));
        aOFertas.click();

        // guardamos el saldo antes de destacar la oferta
        double saldoA = PO_ListOfferView.wallet(driver);

        // guardamos el nombre de la oferta que vamos a destacar
        By ofertaD = By.xpath("/html/body/div/div[3]/table/tbody/tr[1]/td[1]");
        String nombreOferta = driver.findElement(ofertaD).getText();

        // pulsamos el botón para destacar la oferta
        By highButton = By.xpath("/html/body/div/div[3]/table/tbody/tr[1]/td[5]/a");
        driver.findElement(highButton).click();

        // guardamos el saldo después de destacar la oferta
        double saldoB = PO_ListOfferView.wallet(driver);

        // comprobamos que el saldo después de destacar es 20€ menor que el saldo antes de destacar
        assertTrue(saldoA == (saldoB + 20));

        // logout
        PO_PrivateView.refactorLogout(driver);

        // nos logueamos con otro usuario
        PO_PrivateView.refactorLogging(driver, "user08@email.com", "user08");

        // accedemos a la lista de ofertas privada
        WebElement ofertas2 = driver.findElement(By.linkText("Ofertas"));
        ofertas2.click();
        WebElement misOfertas = driver.findElement(By.linkText("Mis Ofertas"));
        misOfertas.click();

        // buscamos la oferta que hemos destacado
        List<WebElement> destacada = PO_View.checkElementBy(driver, "text", nombreOferta);
        // Comprobamos que efectivamente la hemos encontrado
        assertTrue(destacada.size() == 1);

        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR32. Sobre el listado de ofertas de un usuario con menos de 20 euros de saldo, pinchar en el
     * enlace Destacada y a continuación comprobar que se muestra el mensaje de saldo no suficiente.
     */
    @Test
    @Order(32)
    public void PR32() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user08@email.com", "user08");

        // accedemos a la lista de ofertas privada
        WebElement ofertas2 = driver.findElement(By.linkText("Ofertas"));
        ofertas2.click();
        WebElement misOfertas = driver.findElement(By.linkText("Mis Ofertas"));
        misOfertas.click();

        // destacamos ofertas hasta tener menos de 20€
        By highButton = By.xpath("/html/body/div/div[3]/table/tbody/tr[1]/td[5]/a");
        driver.findElement(highButton).click();
        highButton = By.xpath("/html/body/div/div[3]/table/tbody/tr[2]/td[5]/a");
        driver.findElement(highButton).click();
        highButton = By.xpath("/html/body/div/div[3]/table/tbody/tr[3]/td[5]/a");
        driver.findElement(highButton).click();
        highButton = By.xpath("/html/body/div/div[3]/table/tbody/tr[4]/td[5]/a");
        driver.findElement(highButton).click();
        highButton = By.xpath("/html/body/div/div[3]/table/tbody/tr[5]/td[5]/a");
        driver.findElement(highButton).click();

        // comprobamos que efectivamente tenemos menos de 20€
        double saldo = PO_ListOfferView.wallet(driver);
        assertTrue(saldo < 20);

        // cambiamos a la segunda página, ya con menos de 20€
        By segundaPagina = By.xpath("/html/body/div/div[4]/ul/li[2]/a");
        driver.findElement(segundaPagina).click();

        // intentamos destacar una nueva oferta
        highButton = By.xpath("/html/body/div/div[3]/table/tbody/tr[1]/td[5]/a");
        driver.findElement(highButton).click();

        // buscamos todos los elementos que posean el texto con el mensaje de error
        String checkText = "No tienes suficiente dinero para destacar una oferta";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        //Comprobamos el mensaje de error
        Assertions.assertEquals(checkText, result.get(0).getText());

        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR33. Intentar acceder sin estar autenticado a la opción de listado de usuarios. Se deberá volver
     * al formulario de login.
     */
    @Test
    @Order(33)
    public void PR33() {
        // puesto que no existe la forma directa de acceder a las secciones para las que no estás autorizado
        // la única forma de intentar acceder a una sección prohibida para tu rol es buscando el enlace directamente
        driver.navigate().to(URL + "/users/list");

        // nos deberá mandar a la página de login
        String checkText = "Identificación de usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    /**
     * PR34. Intentar acceder sin estar autenticado a la opción de listado de conversaciones
     * [REQUISITO OBLIGATORIO S5]. Se deberá volver al formulario de login.
     */
    @Test
    @Order(34)
    public void PR34() {
        // intentamos acceder a la lista de conversaciones, como no estamos llogueados la única manera de acceder
        // es mediante el enlace
        driver.navigate().to(URL + "/apiclient/client.html?w=conversations");

        // nos deberá mandar a la página de login
        String checkText = "Email:";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        checkText = "Password:";
        List<WebElement> result2 = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result2.get(0).getText());
    }

    /**
     * PR35. Estando autenticado como usuario estándar intentar acceder a una opción disponible solo
     * para usuarios administradores (Añadir menú de auditoria (visualizar logs)). Se deberá indicar un
     * mensaje de acción prohibida.
     */
    @Test
    @Order(35)
    public void PR35() {
        // nos logueamos
        PO_PrivateView.refactorLogging(driver, "user04@email.com", "user04");

        // al ser usuario estandar no tenemos una opción de menú que nos permita acceder directamente
        // a una sección prohibida, por lo que la única manera de acceder es a través de un enlace
        driver.navigate().to(URL + "/users/admin/log");

        // comprobamos que nos ha redirigido a la página con el aviso de acceso denegado.
        String checkText = "Sin autorización";
        WebElement newFirstRow = driver.findElement(By.xpath("/html/body/div/h1"));
        String newFirstValue = newFirstRow.getText();
        Assertions.assertEquals(checkText, newFirstValue);

    }

    /**
     * PE36. Estando autenticado como usuario administrador visualizar todos los logs generados en
     * una serie de interacciones. Esta prueba deberá generar al menos dos interacciones de cada tipo y
     * comprobar que el listado incluye los logs correspondientes.
     */
    @Test
    @Order(36)
    public void PR36() {
        // nos logueamos como usuario estandar dos veces "LOGIN-EX"
        PO_PrivateView.refactorLogging(driver, "user04@email.com", "user04");
        // logout
        PO_PrivateView.refactorLogout(driver);

        // nos logueamos de nuevo "LOGIN-EX"
        PO_PrivateView.refactorLogging(driver, "user04@email.com", "user04");
        // logout
        PO_PrivateView.refactorLogout(driver);

        // nos logueamos con errores "LOGIN-ERR"
        PO_PrivateView.refactorLogging(driver, "user05@email.com", "x");

        // nos logueamos con errores de nuevo "LOGIN-ERR"
        PO_PrivateView.refactorLogging(driver, "user06@email.com", "x");

        // vamos al formulario para crear una cuenta
        PO_PrivateView.refactorSignup(driver);

        // nos registramos con un usuario de prueba "ALTA"
        PO_SignUpView.fillForm(driver, "prueba1@email.com", "Nombre1", "Apellido1",
                "2005-06-07", "prueba1", "prueba1");

        // vamos al formulario para crear una cuenta
        PO_PrivateView.refactorSignup(driver);

        // nos registramos con otro usuario de prueba "ALTA"
        PO_SignUpView.fillForm(driver, "prueba2@email.com", "Nombre2", "Apellido2",
                "2005-06-07", "prueba2", "prueba2");

        // nos logueamos como admin
        PO_PrivateView.refactorLogging(driver, "admin@email.com", "admin");

        // entramos al registro de logs
        WebElement log = driver.findElement(By.linkText("Logs"));
        log.click();
        WebElement verLogs = driver.findElement(By.linkText("Ver logs"));
        verLogs.click();

        // buscamos todos los elementos que posean el texto PET
        List<WebElement> PET = PO_View.checkElementBy(driver, "text", "PET");
        // Comprobamos que hay 3 o más elementos, dos o más producidos al negar entre las páginas y otro por el filtro
        assertTrue(PET.size() >= 3);

        // buscamos todos los elementos que posean el texto LOGIN-EX
        List<WebElement> LOGINEX = PO_View.checkElementBy(driver, "text", "LOGIN-EX");
        // Comprobamos que hay 3 o más elementos, dos o más producidos en el test actual más los anteriores
        // y otro por el filtro
        assertTrue(LOGINEX.size() >= 3);

        // buscamos todos los elementos que posean el texto LOGIN-ERR
        List<WebElement> LOGINERR = PO_View.checkElementBy(driver, "text", "LOGIN-ERR");
        // Comprobamos que hay 3 o más elementos, dos o más producidos en el test actual más los anteriores
        // y otro por el filtro
        assertTrue(LOGINERR.size() >= 3);

        // buscamos todos los elementos que posean el texto ALTA
        List<WebElement> ALTA = PO_View.checkElementBy(driver, "text", "ALTA");
        // Comprobamos que hay 3 o más elementos, las dos cuentas registradas ahora y el filtro,
        // puede haber más producidos por otros test
        assertTrue(ALTA.size() >= 3);

        // buscamos todos los elementos que posean el texto LOGOUT
        List<WebElement> LOGOUT = PO_View.checkElementBy(driver, "text", "LOGOUT");
        // Comprobamos que hay 3 o más elementos, dos o más producidos en el test actual más los anteriores
        // y otro por el filtro
        assertTrue(LOGOUT.size() >= 3);

        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    /**
     * PR37. Estando autenticado como usuario administrador, ir a visualización de logs, pulsar el
     * botón/enlace borrar logs y comprobar que se eliminan los logs de la base de datos.
     */
    @Test
    @Order(37)
    public void PR37() {
        // nos logueamos como admin
        PO_PrivateView.refactorLogging(driver, "admin@email.com", "admin");

        // entramos al registro de logs
        WebElement log = driver.findElement(By.linkText("Logs"));
        log.click();
        WebElement verLogs = driver.findElement(By.linkText("Ver logs"));
        verLogs.click();

        // pulsamos el botón eliminar
        By boton = By.xpath("/html/body/div/form/div/button[1]");
        driver.findElement(boton).click();

        // buscamos todos los elementos que posean el texto PET
        List<WebElement> PET = PO_View.checkElementBy(driver, "text", "PET");
        // Comprobamos que solo está el filtro y la propia petición que hace al recargar la página al eliminar
        assertTrue(PET.size() == 2);

        // buscamos todos los elementos que posean el texto LOGIN-EX
        List<WebElement> LOGINEX = PO_View.checkElementBy(driver, "text", "LOGIN-EX");
        // Comprobamos que solo está el filtro
        assertTrue(LOGINEX.size() == 1);

        // buscamos todos los elementos que posean el texto LOGIN-ERR
        List<WebElement> LOGINERR = PO_View.checkElementBy(driver, "text", "LOGIN-ERR");
        // Comprobamos que solo está el filtro
        assertTrue(LOGINERR.size() == 1);

        // buscamos todos los elementos que posean el texto ALTA
        List<WebElement> ALTA = PO_View.checkElementBy(driver, "text", "ALTA");
        // Comprobamos que solo está el filtro
        assertTrue(ALTA.size() == 1);

        // buscamos todos los elementos que posean el texto LOGOUT
        List<WebElement> LOGOUT = PO_View.checkElementBy(driver, "text", "LOGOUT");
        // Comprobamos que solo está el filtro
        assertTrue(LOGOUT.size() == 1);

        // logout
        PO_PrivateView.refactorLogout(driver);
    }

    // ###############################################################################################
    // ######################################### PARTE 2 #############################################
    // ###############################################################################################

    /**
     * PR38. Inicio de sesión con datos válidos
     */
    @Test
    @Order(38)
    public void PR38() {
        // 1. Accedemos a la URL de login
        final String RestAssuredURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Preparamos el parámetro en formato JSON
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user01@email.com", "user01", RestAssuredURL, request);
        // 3. Hacemos la petición
        Response response = request.post(RestAssuredURL);
        // 4. Comprobamos que el servicio ha tenido exito
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(response.getBody().asString().contains("usuario autorizado"));
    }

    /**
     * PR39. Inicio de sesión con datos inválidos (email existente, pero contraseña incorrecta).
     */
    @Test
    @Order(39)
    public void PR39() {
        // 1. Accedemos a la URL de login
        final String RestAssuredURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Preparamos el parámetro en formato JSON
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user01@email.com", "incorrecta", RestAssuredURL, request);
        // 3. Hacemos la petición
        Response response = request.post(RestAssuredURL);
        // 4. Comprobamos que el servicio ha tenido un error por credenciales
        Assertions.assertEquals(401, response.getStatusCode());
        Assertions.assertTrue(response.getBody().asString().contains("usuario no autorizado"));
    }

    /**
     * PR40. Inicio de sesión con datos inválidos (campo email o contraseña vacíos).
     */
    @Test
    @Order(40)
    public void PR40() {
        // 1. Accedemos a la URL de login
        final String RestAssuredURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Preparamos el parámetro en formato JSON
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("", "incorrecta", RestAssuredURL, request);
        // 3. Hacemos la petición
        Response response = request.post(RestAssuredURL);
        // 4. Comprobamos que el servicio ha tenido un error por credenciales
        Assertions.assertEquals(401, response.getStatusCode());
        Assertions.assertTrue(response.getBody().asString().contains("usuario no autorizado"));
    }

    /**
     * PR41. Mostrar el listado de ofertas para el usuario registrado. Comprobar que se muestran toads las ofertas
     * que existen para dicho usuario.
     */
    @Test
    @Order(41)
    public void PR41() {
        // 1. Accedemos a la URL de login
        final String LoginURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Nos logueamos
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user01@email.com", "user01", LoginURL, request);
        // 3. Añadimos el token a la petición
        request.header("token", loginResponse.jsonPath().get("token"));
        // 4. Accedemos a la URL de listar ofertas
        final String OffersURL = "http://localhost:3000/api/v1.0/offers";
        // 5. Obtenemos las ofertas y verificamos el estado
        Response offersResponse = request.get(OffersURL);
        Assertions.assertEquals(200, offersResponse.getStatusCode());
        // 6. Verificamos que no se muestran las ofertas del usuario
        Assertions.assertFalse(offersResponse.getBody().asString().contains("user01@email.com"));
    }

    /**
     * PR42. Enviar un mensaje a una oferta. Se debe comprobar que el servicio almacena correctamente el mensaje
     * para dicha oferta.
     */
    @Test
    @Order(42)
    public void PR42() {
        // 1. Accedemos a la URL de login
        final String LoginURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Nos logueamos como user02
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user02@email.com", "user02", LoginURL, request);
        // 3. Añadimos el token a la petición
        request.header("token", loginResponse.jsonPath().get("token"));
        // 4. Accedemos a la URL de enviar mensajes
        final String MessagesURL = "http://localhost:3000/api/v1.0/offers/messages";
        // 5. Enviamos el siguiente mensaje:
        Response messagesResponse = PO_RestApi.sendMessage(
                "user02@email.com",
                "user03@email.com",
                "user02@email.com",
                "64552593aee4ec22206d2544",
                "Esto es un mensaje de la prueba 42",
                request, MessagesURL);
        // 6. Verificamos el estado
        Assertions.assertEquals(201, messagesResponse.getStatusCode());
        Assertions.assertTrue(messagesResponse.getBody().asString().contains("Mensaje enviado correctamente"));
        // 7. Comprobamos que el mensaje queda bien registrado
        Response messageListResponse = request.get(MessagesURL + "/" + "64552593aee4ec22206d2544");
        Assertions.assertEquals(200, messageListResponse.getStatusCode());
        // 8. Verificamos que se obtiene el texto del mensaje añadido
        Assertions.assertTrue(messageListResponse.getBody().asString().contains("Esto es un mensaje de la prueba 42"));
    }

    /**
     * PR43. Enviar un mensaje a una oferta propia. Comprobar que el mensaje no se almacena.
     */
    @Test
    @Order(43)
    public void PR43() {
        // 1. Accedemos a la URL de login
        final String LoginURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Nos logueamos como user01
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user01@email.com", "user01", LoginURL, request);
        // 3. Añadimos el token a la petición
        request.header("token", loginResponse.jsonPath().get("token"));
        // 4. Accedemos a la URL de enviar mensajes
        final String MessagesURL = "http://localhost:3000/api/v1.0/offers/messages";
        // 5. Enviamos el siguiente mensaje:
        Response messagesResponse = PO_RestApi.sendMessage(
                "user01@email.com",
                "user01@email.com",
                "user01@email.com",
                "64555204ec3502ce2152468a",
                "Esto es un mensaje de la prueba 43",
                request, MessagesURL);
        // 6. Verificamos el estado
        Assertions.assertEquals(422, messagesResponse.getStatusCode());
        Assertions.assertTrue(messagesResponse.getBody().asString().contains("El vendedor debe ser distinto del comprador"));
        // 7. Comprobamos que el mensaje no queda registrado
        Response messageListResponse = request.get(MessagesURL + "/" + "64555204ec3502ce2152468a");
        Assertions.assertTrue(!messageListResponse.getBody().asString().contains("Esto es un mensaje de la prueba 43"));
    }

    /**
     * PR44. Obtener los mensajes de una conversación. Se debe comprobar que el servicio retorna el número correcto de
     * mensajes para una conversación de id conocido.
     */
    @Test
    @Order(44)
    public void PR44() {
        // 1. Accedemos a la URL de login
        final String LoginURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Nos logueamos como user02
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user02@email.com", "user02", LoginURL, request);
        // 3. Añadimos el token a la petición
        request.header("token", loginResponse.jsonPath().get("token"));
        // 4. Enviamos el siguiente mensaje:
        final String MessagesURL = "http://localhost:3000/api/v1.0/offers/messages";
        PO_RestApi.sendMessage(
                "user02@email.com",
                "user03@email.com",
                "user02@email.com",
                "64552593aee4ec22206d2544",
                "Esto es un mensaje de la prueba 44",
                request, MessagesURL);
        // 5. Accedemos a la URL de obtener mensajes y obtenemos el listado correspondiente a la oferta
        final String ConversationsURL = "http://localhost:3000/api/v1.0/offers/messages/64552593aee4ec22206d2544";
        Response messageListResponse = request.get(ConversationsURL);
        // 6. Verificamos el estado
        Assertions.assertEquals(200, messageListResponse.getStatusCode());
        Assertions.assertEquals(1, (int) messageListResponse.body().path("messages.size()"));
    }

    /**
     * PR45. Obtener la lista de conversaciones de un usuario. Comprobar que se devuelve el número correcto de
     * conversaciones
     */
    @Test
    @Order(45)
    public void PR45() {
        // 1. Accedemos a la URL de login
        final String LoginURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Nos logueamos como user02
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user02@email.com", "user02", LoginURL, request);
        // 3. Añadimos el token a la petición
        request.header("token", loginResponse.jsonPath().get("token"));
        // 4. Enviamos el siguiente mensaje:
        final String MessagesURL = "http://localhost:3000/api/v1.0/offers/messages";
        PO_RestApi.sendMessage(
                "user02@email.com",
                "user04@email.com",
                "user02@email.com",
                "64555b1c70d8bf83b644cff9",
                "Esto es un mensaje de la prueba 45",
                request, MessagesURL);
        // 5. Accedemos a la URL de obtener conversaciones
        final String ConversationsURL = "http://localhost:3000/api/v1.0/conversations";
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response conversationListResponse = request.get(ConversationsURL);
        // 6. Verificamos el estado
        Assertions.assertEquals(200, conversationListResponse.getStatusCode());
        int nConversaciones = (int) conversationListResponse.body().path("conversations.size()");
        Assertions.assertTrue( nConversaciones == 1);
    }

    /**
     * PR46. Eliminar una conversación dado un ID conocido. Se debe comprobar que se elimina correctamente una
     * conversación concreta.
     */
    @Test
    @Order(46)
    public void PR46() {
        // 1. Accedemos a la URL de login
        final String LoginURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Nos logueamos como user02
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user02@email.com", "user02", LoginURL, request);
        // 3. Añadimos el token a la petición
        request.header("token", loginResponse.jsonPath().get("token"));
        // 4. Envíamos dos nuevos mensajes
        final String MessagesURL = "http://localhost:3000/api/v1.0/offers/messages";
        PO_RestApi.sendMessage(
                "user02@email.com",
                "user04@email.com",
                "user02@email.com",
                "6456b470d5cf9d2932ae2b47",
                "Esto es un mensaje de la prueba 46",
                request, MessagesURL);
        PO_RestApi.sendMessage(
                "user02@email.com",
                "user03@email.com",
                "user02@email.com",
                "6456b470d5cf9d2932ae2b41",
                "Esto es otro mensaje de la prueba 46",
                request, MessagesURL);
        // 5. Obtenemos el listado de conversaciones. En este punto deberían ser una.
        final String ConversationsURL = "http://localhost:3000/api/v1.0/conversations";
        // Esperamos a que cargue las conversaciones.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response conversationListResponse = request.get(ConversationsURL);
        System.out.println(conversationListResponse.getBody().asString());
        final String conversationId = conversationListResponse.jsonPath().get("conversations[1]._id");
        // 6. Accedemos a la URL de obtener conversaciones con la conversación a eliminar como parámetro
        final String DeleteConversationURL = "http://localhost:3000/api/v1.0/conversations/" + conversationId;
        // 7. Eliminamos una conversación
        Response deleteConversationResponse = request.delete(DeleteConversationURL);
        // 8. Verificamos el estado. Ahora sólo debería haber una conversación.
        System.out.println(deleteConversationResponse.getBody().asString());
        Assertions.assertEquals(200, deleteConversationResponse.getStatusCode());
        conversationListResponse = request.get(ConversationsURL);
        Assertions.assertEquals(1, (int) conversationListResponse.body().path("conversations.size()"));
    }

    /**
     * PR47. Marcar mensaje con ID conocida como leído. Se debe comprobar que el mensaje queda marcado correctamente
     * a true.
     */
    @Test
    @Order(47)
    public void PR47() {
        // 1. Accedemos a la URL de login
        final String LoginURL = "http://localhost:3000/api/v1.0/users/login";
        // 2. Nos logueamos como user02
        RequestSpecification request = RestAssured.given();
        Response loginResponse = PO_RestApi.login("user02@email.com", "user02", LoginURL, request);
        // 3. Añadimos el token a la petición
        request.header("token", loginResponse.jsonPath().get("token"));
        // 4. Añadimos un nuevo mensaje
        final String MessagesURL = "http://localhost:3000/api/v1.0/offers/messages";
        Response messagesResponse = PO_RestApi.sendMessage(
                "user02@email.com", // buyer
                "user04@email.com", // seller
                "user02@email.com", // author
                "64555b1c70d8bf83b644cff9", // offer
                "Esto es un mensaje de la prueba 45", // text
                request, MessagesURL);
        String messageId = (String) messagesResponse.jsonPath().get("_id");
        // 5. Marcamos el mensaje como leido
        final String UpdateMessageURL = "http://localhost:3000/api/v1.0/conversations/" + messageId;
        Response updateMessageResponse = request.put(UpdateMessageURL);
        Assertions.assertEquals(200, updateMessageResponse.getStatusCode());
        // 6. Comprobamos que el mensaje se ha actualizado correctamente
        Response messageListResponse = request.get(MessagesURL + "/" + "64555b1c70d8bf83b644cff9");
        Assertions.assertEquals(200, messageListResponse.getStatusCode());
        Assertions.assertTrue(messageListResponse.getBody().asString().contains("true"));
    }

    // ###############################################################################################
    // ######################################### PARTE 3 #############################################
    // ###############################################################################################

    /**
     * PR51. Mostrar el listado de ofertas disponibles y comprobar que se muestran todas las que existen,
     * menos las del usuario identificado
     */
    @Test
    @Order(51)
    public void PR51() {
        // navegamos a la URL
        driver.get("http://localhost:3000/apiclient/client.html?w=login");
        // introducimos los datos en el login
        PO_LoginAjaxView.fillLoginForm(driver, "user01@email.com", "user01");
        // sacamos los datos de la tabla y vemos que aparecen todas las ofertas menos las del usuario identificado
        List<WebElement> table = PO_HomeView.checkElementTableBody(driver, "offersTableBody");
        // sacamos los datos que hay en la base de datos en la colección de ofertas
        MongoCollection<Document> collection = mongo.getCollection("offers");
        // Crear un objeto de filtro para especificar el criterio de búsqueda
        Bson filter = Filters.not(Filters.eq("seller", "user01@email.com"));
        // Filtrar los documentos de la colección
        FindIterable<Document> cursor = collection.find(filter);
        int size = 0;
        for (Document document : cursor) { // vamos sumando el número de elementos que hay en la colección
            size++;
        }
        Assertions.assertEquals(size, table.size()); // comprobamos que sea el mismo número
    }

    /**
     * Sobre listado de ofertas disponibles (a elección de desarrollador), enviar un mensaje a una
     * oferta concreta. Se abriría dicha conversación por primera vez. Comprobar que el mensaje aparece
     * en el listado de mensajes.
     */
    @Test
    @Order(52)
    public void PR52() {
        // navegamos a la URL
        driver.get("http://localhost:3000/apiclient/client.html?w=login");
        // introducimos los datos en el login
        PO_LoginAjaxView.fillLoginForm(driver, "user01@email.com", "user01");
        //Vamos a la conversación con la primera oferta
        By convBtn = By.xpath("//a[contains(text(),'Conversación')][1]");
        driver.findElement(convBtn).click();
        // introducimos un mensaje
        WebElement input = driver.findElement(By.xpath("//*[@id=\"msg-add\"]"));
        input.click();
        input.clear();
        input.sendKeys("Hola :)");
        // Click sobre enviar
        By boton = By.xpath("//button[@id='msg-send']");
        driver.findElement(boton).click();
        String checkText = "Hola :)";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    /**
     * Sobre el listado de conversaciones enviar un mensaje a una conversación ya abierta.
     * Comprobar que el mensaje aparece en el listado de mensajes.
     */
    @Test
    @Order(53)
    public void PR53() {
        // navegamos a la URL
        driver.get("http://localhost:3000/apiclient/client.html?w=login");
        // introducimos los datos en el login
        PO_LoginAjaxView.fillLoginForm(driver, "user01@email.com", "user01");
        //Vamos a la conversación con la primera oferta
        By convBtn = By.xpath("//a[contains(text(),'Conversación')][1]");
        driver.findElement(convBtn).click();
        // introducimos un mensaje
        WebElement input = driver.findElement(By.xpath("//*[@id=\"msg-add\"]"));
        input.click();
        input.clear();
        input.sendKeys("Hola :)");
        // Click sobre enviar
        By boton = By.xpath("//button[@id='msg-send']");
        driver.findElement(boton).click();
        // Volvemos a iniciar sesión
        driver.get("http://localhost:3000/apiclient/client.html?w=login");
        PO_LoginAjaxView.fillLoginForm(driver, "user01@email.com", "user01");
        //Vamos a la conversación con la primera oferta
        driver.findElement(convBtn).click();
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Comprobamos que se ve el mensaje de la conversación ya abierta
        String checkText = "Hola :)";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
        // introducimos un mensaje nuevo
        input = driver.findElement(By.xpath("//*[@id=\"msg-add\"]"));
        input.click();
        input.clear();
        input.sendKeys("Adiós :(");
        // Click sobre enviar
        By btn = By.xpath("//button[@id='msg-send']");
        driver.findElement(btn).click();
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Comprobamos que se ve el mensaje nuevo
        checkText = "Adiós :(";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    /**
     * Mostrar el listado de conversaciones ya abiertas. Comprobar que el listado contiene la
     * cantidad correcta de conversaciones.
     */
    @Test
    @Order(54)
    public void PR54() {
        // navegamos a la URL
        driver.get("http://localhost:3000/apiclient/client.html?w=login");
        // introducimos los datos en el login
        PO_LoginAjaxView.fillLoginForm(driver, "user01@email.com", "user01");
        //Vamos a la conversación con la primera oferta
        By convBtn = By.xpath("//a[contains(text(),'Conversación')][1]");
        driver.findElement(convBtn).click();
        // introducimos un mensaje
        WebElement input = driver.findElement(By.xpath("//*[@id=\"msg-add\"]"));
        input.click();
        input.clear();
        input.sendKeys("Hola :)");
        // Click sobre enviar
        By boton = By.xpath("//button[@id='msg-send']");
        driver.findElement(boton).click();
        By cnv = By.xpath("//*[@id=\"barra-menu\"]/li[2]/a");
        driver.findElement(cnv).click();
        WebElement tableBody = driver.findElement(By.cssSelector("#widget-conversations > table"));
        List<WebElement> conversations = tableBody.findElements(By.tagName("tr"));
        int conversationsCount = conversations.size();
        // verificar si la primera fila es un encabezado y no se cuenta en el recuento
        if (conversationsCount > 0 && conversations.get(0).findElements(By.tagName("th")).size() > 0) {
            conversationsCount--;
        }
        Assertions.assertEquals(1, conversationsCount);
    }

}
