package com.herokuapp.restfulbooker;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetBookingIdsTest {
  @Test
  public void getBookingIdsTestWithoutFilterTest(){
//    get response with
    Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
    response.print();

    Assert.assertEquals(response.getStatusCode(),200, "Status code should be 200 but it's not");
    List<Integer> bookingIds =  response.jsonPath().getList("bookingId");
    Assert.assertFalse(bookingIds.isEmpty(), "listo of bookingIds is empty");
  }
}
