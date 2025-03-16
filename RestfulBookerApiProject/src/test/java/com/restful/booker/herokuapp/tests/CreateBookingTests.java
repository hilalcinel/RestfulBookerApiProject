package com.restful.booker.herokuapp.tests;

import com.restful.booker.herokuapp.models.Booking;
import com.restful.booker.herokuapp.models.BookingDates;
import com.restful.booker.herokuapp.models.BookingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTests extends BaseTest{




    @Test
    public void createBookingTest(){
        BookingDates bookingDates = new BookingDates("2025-03-04","2025-03-07");
        Booking booking = new Booking("Pink","Floyd",1453,true, bookingDates,"Minibar Dolu Olsun Lütfen");

        BookingResponse bookingResponse = createBooking(booking);

        Assertions.assertEquals("Pink",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Floyd",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(1453,bookingResponse.getBooking().getTotalprice());
        Assertions.assertTrue(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("Minibar Dolu Olsun Lütfen",bookingResponse.getBooking().getAdditionalneeds());

    }
}
