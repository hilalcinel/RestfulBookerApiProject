package com.restful.booker.herokuapp.tests;

import com.restful.booker.herokuapp.models.*;
import com.restful.booker.herokuapp.utils.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

public class BaseTest {


    RequestSpecification spec;
    BookingResponse bookingResponse;


    @BeforeEach
    public void setUp(){

        spec = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getProperty("baseUrl"))
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

    }

    public BookingResponse createBooking(Object booking){



        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response
                .then()
                .statusCode(200);

        return response.as(BookingResponse.class);
    }

    public void deleteBooking(String token, int bookingId){

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId);

        response
                .then()
                .statusCode(201);

        Assertions.assertEquals(201,response.getStatusCode(),"Booking Id still exist!!");
    }

    public String createToken() {
        Auth authBody = new Auth(
                ConfigManager.getProperty("username"),
                ConfigManager.getProperty("password")
        );

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");
    }


    public Response updateBooking(String token, Object bookingEdited, int bookingId) {

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(bookingEdited)
                .put("/booking/" + bookingId);

        response
                .then()
                .statusCode(200);

        return response;
    }

    public Response partialUpdateBooking(String token, Object bookingPartialUpdate, int bookingId) {

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(bookingPartialUpdate)
                .when()
                .patch("/booking/" + bookingId);

        response
                .then()
                .statusCode(200);

        return response;


    }
}
