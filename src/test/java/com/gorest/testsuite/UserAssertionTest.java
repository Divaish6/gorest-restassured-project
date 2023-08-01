package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    @Before
    public void setup() {
        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page", "1");
        qParam.put("per_page", "20");
        response = given()
                .queryParams(qParam)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    @Test
    // 1. Verify the if the total record is 20
    public void test001() {
        response.body("total.size", equalTo(20));
    }

    @Test
    //2. Verify the if the name of id = 5318 is equal to ”Rameshwar Varman”
    public void test002() {
        response.body("[0].name", equalTo("Bhasvan Kapoor"));
    }

    @Test
    //3.Check the single ‘Name’ in the Array list (Saraswati Dhawan)
    public void test003() {
        response.body("name", hasItem("Saraswati Dhawan"));
    }

    @Test
    //4.Check the multiple ‘Names’ in the ArrayList (Tushar Ahluwalia, Dharani Kocchar)
    public void test004() {
        response.body("name", hasItems("Tushar Ahluwalia", "Dharani Kocchar"));
    }

    @Test
    //5.Verify the email of userid = 4040680 is equal "namboothiri_bankimchandra@boyle.test"
    public void test005() {
        response.body("find{it.id == 4040680}.email", equalTo("namboothiri_bankimchandra@boyle.test"));
    }

    @Test
    //6.Verify the status is “Active” of user name is “Vaijayanthi Achari”
    public void test006() {
        response.body("find{it.name == 'Vaijayanthi Achari'}.status", equalTo("active"));
    }

    @Test
    //7.Verify the Gender = male of user name is “Bhagwati Devar”
    public void test007() {
        response.body("find{it.name == 'Bhagwati Devar'}.gender", equalTo("male"));
    }
}
