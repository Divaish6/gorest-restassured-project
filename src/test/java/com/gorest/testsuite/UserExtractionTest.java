package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {
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
    //1. Extract the All Ids
    public void test001() {
        List<Integer> id = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Ids : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //2. Extract the all Names
    public void test002() {
        List<String> storeNames = response.extract().jsonPath().getList("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All storeNames  : " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //3. Extract the name of 5th object
    public void test003() {
        String storeName5 = response.extract().path("name[4]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object : " + storeName5);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //4. Extract the names of all object whose status = inactive
    public void test004() {
        List<String> inactive = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All object whose status is inactive : " + inactive);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //5. Extract the gender of all the object whose status = active
    public void test005() {
        List<String> statusActive = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All object whose status is active : " + statusActive);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //6. Print the names of the object whose gender = female
    public void test006() {
        List<String> genderFemale = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All object whose gender is female : " + genderFemale);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //7. Get all the emails of the object where status = inactive
    public void test007() {
        List<String> status = response.extract().path("findAll{it.status=='inactive'}.emails");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All object whose gender is female : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //8. Get the ids of the object where gender = male
    public void test008() {
        List<Integer> allId = response.extract().path("findAll{it.gender==male}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of store Ids are : " + allId);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //9. Get all the status
    public void test009() {
        List<String> allStatus = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are :" + allStatus);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //10. Get email of the object where name = Bhima Chaturvedi
    public void test010() {
        List<String> email = response.extract().path("findAll{it.name=='Bhima Chaturvedi'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the object where name is Bhima Chaturvedi : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    //11. Get gender of id = 4040695,
    public void test011() {
        List<?> id = response.extract().path("findAll{it.id=='4040695'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Gender of id is 4040695: " + id);
        System.out.println("------------------End of Test---------------------------");
    }

}
