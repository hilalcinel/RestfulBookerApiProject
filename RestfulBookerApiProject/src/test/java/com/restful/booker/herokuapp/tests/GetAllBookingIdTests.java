package com.restful.booker.herokuapp.tests;

import com.restful.booker.herokuapp.models.Booking;
import com.restful.booker.herokuapp.models.BookingDates;
import com.restful.booker.herokuapp.models.BookingResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetAllBookingIdTests extends BaseTest{

    BookingResponse bookingResponse;



    @Test
    public void getAllBookingIdTest(){

        Response response = given(spec)
                .when()
                .get("/booking");

        response
                .then()
                .statusCode(200);

    }

    @Test
    public void getAllBookingWithFilters(){

        BookingDates bookingDates = new BookingDates("2025-03-04","2025-03-07");
        Booking booking = new Booking("Led","Zeppelin",1881,true, bookingDates,"Temiz havlu Olsun Lütfen");

        bookingResponse = createBooking(booking);

        spec.queryParam("firstname","Led");
        spec.queryParam("lastname","Zeppelin");


        Response response = given(spec)
                .when()
                .get("/booking");

        response
                .then()
                .statusCode(200);

        List<Integer> filteredBookingList = response.jsonPath().getList("bookingid");
        System.out.println(filteredBookingList);

        Assertions.assertTrue(filteredBookingList.contains(bookingResponse.getBookingid()));

    }



}
