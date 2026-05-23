package com.framework.tests;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.framework.base.BaseTest;
import com.framework.models.BookingDates;
import com.framework.models.BookingRequest;
import com.framework.models.BookingResponse;
import com.framework.utils.RequestBuilder;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class BookingTest extends BaseTest


{
    private int bookingId;

    @Test(priority = 1, description = "Get all bookings")
    public void validateShouldGetAllbookings(){

        RequestBuilder.getBasicReqSpec().when().get("/booking")
                        .then().log().all()
                        .statusCode(200)
                        .body("$", not(empty()));
    }

    @Test(priority = 3, description = "get a new booking", dependsOnMethods = "validateCreateBookingUsingPOST")
    public void valideGetBookinsByID(){

        RequestBuilder.getBasicReqSpec().when().get("/booking/"+bookingId).then().log().all()
                .statusCode(200)
                .body("firstname", notNullValue())
                .body("totalprice", equalTo(400))
                .body("depositpaid", is(true));

    }

    @Test(priority = 2, description = "Create a new booking")
    @Story("POST create booking")
    public void validateCreateBookingUsingPOST(){
       /*Map<String,Object> jsonValues = new HashMap<>();
        jsonValues.put("firstname","dha");
        jsonValues.put("lastname","singh");
        jsonValues.put("totalprice",111);
        jsonValues.put("depositpaid",true);
        Map<String,Object> bookingDate = new HashMap<>();
        bookingDate.put("checkin","2018-01-01");
        bookingDate.put("checkout","2019-01-01");
        jsonValues.put("bookingdates",bookingDate);
        jsonValues.put("additionalneeds","Breakfast");
*/
        BookingDates dates = BookingDates.builder()
                .checkin("2026-01-01")
                .checkout("2026-01-02")
                .build();
        BookingRequest req = BookingRequest.builder()
                .firstname("dee")
                .lastname("hat")
                .depositpaid(true)
                .bookingdates(dates)
                .totalprice(400)
                .additionalneeds("dinner")
                .build();

        Response response = RequestBuilder.getBasicReqSpec().contentType("application/json").body(req).when()
                .post("/booking").then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/create-booking-response-schema.json"))
                .body("booking.firstname", notNullValue()).extract().response();

       // int bookingId = response.jsonPath().getInt("bookingid");
        BookingResponse res = response.as(BookingResponse.class);
         bookingId= res.getBookingid();
        System.out.println(bookingId);
        System.out.println(res.getBooking().getBookingdates().getCheckin());

        Assert.assertTrue(bookingId>0,"Booking id should be greater value");
    }
}
