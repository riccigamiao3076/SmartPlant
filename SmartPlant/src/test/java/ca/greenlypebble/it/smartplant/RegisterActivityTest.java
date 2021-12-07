package ca.greenlypebble.it.smartplant;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class RegisterActivityTest {

    @Test
    public void testIsEmailValid() {

        //passed
        String testEmail = "admin@admin.ca";
        Assert.assertThat(String.format("Email Test failed for %s ", testEmail), RegisterActivity.checkEmailForValidity(testEmail), is(true));
    }

    @Test
    public void testIsfNameValid() {

        //passed
        String testfName = "Admin Plant";
        Assert.assertThat(String.format("Name Test failed for %s ", testfName), RegisterActivity.checkfNameForValidity(testfName), is(true));
    }

    @Test
    public void testIspWord1Valid() {

        //passed
        String testpWord1 = "Splant#06";
        Assert.assertThat(String.format("Password Test failed for %s ", testpWord1), RegisterActivity.checkpWord1ForValidity(testpWord1), is(true));
    }


}