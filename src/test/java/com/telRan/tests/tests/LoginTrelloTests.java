package com.telRan.tests.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTrelloTests extends  TestBase{
    ExtentReports extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        //extent.attachReporter(spark);

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.header().isAvatarPresent()){
           app.header().logout();
            extent.attachReporter(spark);
            extent.createTest("Before method")
                    .log(Status.PASS, "Logout had done");
            extent.flush();
       }
    }

    @Test
    public void loginAtlassianAccPositiveTest() throws InterruptedException {
        //clickOnLoginButtonOnWelcomePage
        app.session().login("rochman.elena@gmail.com", "12345.com");
        //er userLoggedIn (isAvatar present)
        Assert.assertTrue(app.header().isAvatarPresent());
        //is user correct
        extent.attachReporter(spark);
        extent.createTest("Login test")
                .log(Status.PASS, "Possitive");
        extent.flush();
    }

    @Test
    public void negativeLoginAtlassianAccPositiveTest() throws InterruptedException {
        app.session().login("rochman.elena@gmail.com", "12345.coM");
        Assert.assertTrue(app.session().isLoginErrorPresent());
        Assert.assertFalse(app.header().isAvatarPresent());
        extent.attachReporter(spark);
        extent.createTest("Negative login")
                .log(Status.PASS, "Login impossible");
        extent.flush();


    }


}
