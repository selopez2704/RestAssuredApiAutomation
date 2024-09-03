package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTest {
  @Test
  public void createBookingTest() {
    Response response = createBooking();

//    response.print();
    // Verify response 200
    Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200, but it's not");

    // Verify All fields
    SoftAssert softAssert = new SoftAssert();
    String actualFirstName = response.jsonPath().getString("booking.firstname");
    softAssert.assertEquals(actualFirstName, "Sebas", "firstname in response is not expected");

    String actualLastName = response.jsonPath().getString("booking.lastname");
    softAssert.assertEquals(actualLastName, "ABC", "lastname in response is not expected");

    int price = response.jsonPath().getInt("booking.totalprice");
    softAssert.assertEquals(price, 111, "totalprice in response is not expected");

    boolean depositpaid = response.jsonPath().getBoolean("booking.depositpaid");
    softAssert.assertFalse(depositpaid, "depositpaid should be false, but it's not");

    String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
    softAssert.assertEquals(actualCheckin, "2024-01-01", "checkin in response is not expected");

    String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
    softAssert.assertEquals(actualCheckout, "2024-02-01", "checkout in response is not expected");

    String actualAdditionalneeds = response.jsonPath().getString("booking.additionalneeds");
    softAssert.assertEquals(actualAdditionalneeds, "Breakfast", "additionalneeds in response is not expected");

    softAssert.assertAll();

  }

  private static Response createBooking() {

    JSONObject body = new JSONObject();
    JSONObject bookingDates = new JSONObject();

    bookingDates.put("checkin", "2024-01-01");
    bookingDates.put("checkout", "2024-02-01");

    body.put("firstname", "Sebas");
    body.put("lastname", "ABC");
    body.put("totalprice", 111);
    body.put("depositpaid", false);
    body.put("bookingdates", bookingDates);
    body.put("additionalneeds", "Breakfast");

    return RestAssured.given()
      .contentType(ContentType.JSON)
      .body(body.toString())
      .post("https://restful-booker.herokuapp.com/booking");
  }
}
