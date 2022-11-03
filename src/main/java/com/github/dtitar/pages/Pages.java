package com.github.dtitar.pages;

import static com.codeborne.selenide.Selenide.page;

public class Pages {

    private MainPage mainPage;
    private LoginPage loginPage;
    private ResetPasswordPage resetPasswordPage;
    private MobileAppsPage mobileAppsPage;
    private WheelOfFortuneRegistrationPage wheelOfFortuneRegistrationPage;
    private RegistrationPage registrationPage;
    private SimpleFormPage simpleFormPage;
    private ShopsPage shopsPage;
    private EventDescriptionPage eventDescriptionPage;
    private MyBetsPage myBetsPage;
    private AccountsPage accountsPage;
    private ShopsOnlinePage shopsOnlinePage;
    private DocumentsUploadPage documentsUploadPage;
    private DocumentsProcessingPage documentsProcessingPage;
    private LivePage livePage;

    public <PageObjectClass> PageObjectClass getPage(Class<PageObjectClass> pageObjectClass) {
        return page(pageObjectClass);
    }

    public MainPage mainPage() {
        return mainPage = (mainPage == null ? page(MainPage.class) : mainPage);
    }

    public LoginPage loginPage() {
        return loginPage = (loginPage == null ? page(LoginPage.class) : loginPage);
    }

    public ResetPasswordPage resetPasswordPage() {
        return resetPasswordPage = (resetPasswordPage == null ? page(ResetPasswordPage.class) : resetPasswordPage);
    }

    public RegistrationPage registrationPage() {
        return registrationPage = (registrationPage == null ? page(RegistrationPage.class) : registrationPage);
    }

    public MobileAppsPage mobileAppsPage() {
        return mobileAppsPage = (mobileAppsPage == null ? page(MobileAppsPage.class) : mobileAppsPage);
    }

    public WheelOfFortuneRegistrationPage wheelOfFortuneRegistrationPage() {
        return wheelOfFortuneRegistrationPage = (wheelOfFortuneRegistrationPage == null ? page(WheelOfFortuneRegistrationPage.class) : wheelOfFortuneRegistrationPage);
    }

    public SimpleFormPage simpleFormPage() {
        return simpleFormPage = (simpleFormPage == null ? page(SimpleFormPage.class) : simpleFormPage);
    }

    public ShopsPage shopsPage() {
        return shopsPage = (shopsPage == null ? page(ShopsPage.class) : shopsPage);
    }

    public ShopsOnlinePage shopsOnlinePage() {
        return shopsOnlinePage = (shopsOnlinePage == null ? page(ShopsOnlinePage.class) : shopsOnlinePage);
    }

    public EventDescriptionPage eventDescriptionPage() {
        return eventDescriptionPage = (eventDescriptionPage == null ? page(EventDescriptionPage.class) : eventDescriptionPage);
    }

    public MyBetsPage myBetsPage() {
        return myBetsPage = (myBetsPage == null ? page(MyBetsPage.class) : myBetsPage);
    }

    public AccountsPage accountsPage() {
        return accountsPage = (accountsPage == null ? page(AccountsPage.class) : accountsPage);
    }

    public DocumentsUploadPage documentsUploadPage() {
        return documentsUploadPage = (documentsUploadPage == null ? page(DocumentsUploadPage.class) : documentsUploadPage);
    }

    public DocumentsProcessingPage documentsProcessingPage() {
        return documentsProcessingPage = (documentsProcessingPage == null ? page(DocumentsProcessingPage.class) : documentsProcessingPage);
    }

    public LivePage livePage() {
        return livePage = (livePage == null ? page(LivePage.class) : livePage);
    }
}
