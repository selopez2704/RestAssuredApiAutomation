package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTest {
  @Test
  public void getBookingTest(){
//    get response with
    Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/1");
    SoftAssert softAssert =  new SoftAssert();

    Assert.assertEquals(response.getStatusCode(),200, "Status code should be 200 but it's not");
//  Validate firstname
    String actualFirstName  = response.jsonPath().getString("firstname");
    softAssert.assertEquals(actualFirstName, "Susan","firstname in the response is not the expected"  );

//  Validate lastname
    String actualLastName  = response.jsonPath().getString("lastname");
    softAssert.assertEquals(actualLastName, "Jones","lastname in the response is not the expected"  );

    softAssert.assertAll();
  }
}
