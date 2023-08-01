package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.equalTo;

public class PostsAssertionTest extends TestBase {

    static ValidatableResponse response;

    @Before
    public void setup() {
        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page", "1");
        qParam.put("per_page", "25");
        response = given()
                .queryParams(qParam)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Test
    //1. Verify the if the total record is 25
    public void test001() {
        response.body("total.size", equalTo(25));
    }

    @Test
    //2. Verify the if the title of id = 57232 is equal to ”Suus attero absum tum velit.”
    public void test002() {
        response.body("find{it.id == 57232}.title", equalTo("Suus attero absum tum velit."));
    }

    @Test
    //3. Check the single user_id in the Array list (4104804)
    public void test003() {
        response.body("user_id", hasItem(4104804));
    }

    @Test
    //4. Check the multiple ids in the ArrayList (57249,57246,57241)
    public void test004() {
        response.body("id", hasItems(57249, 57246, 57241));
    }

    @Test
    //5. Verify the body of userid = 57230 is equal "Caelum censura aegrus. Adficio defaeco aptus.
    // Quasi antiquus cibo. Charisma utor cumque. Versus trepide voluptas. Acervus defetiscor autem.
    // Comburo est doloremque. Sed articulus usque. Suscipit depulso sumo. Itaque amitto subnecto.
    // Depono ulterius nulla. Deleniti colo dicta. Suscipit sed tabula. Spiculum tandem capio.
    // Conduco adultus nam. Urbs natus armarium. Et decumbo carbo."
    public void test005() {
        response.body("find{it.id ==57230}.body",
                equalTo("Caelum censura aegrus. Adficio defaeco aptus. Quasi antiquus cibo. " +
                        "Charisma utor cumque. Versus trepide voluptas. Acervus defetiscor autem." +
                        " Comburo est doloremque. Sed articulus usque. Suscipit depulso sumo. " +
                        "Itaque amitto subnecto. Depono ulterius nulla. Deleniti colo dicta. " +
                        "Suscipit sed tabula. Spiculum tandem capio. Conduco adultus nam. " +
                        "Urbs natus armarium. Et decumbo carbo."));
    }
}
