package com.restful.booker.herokuapp.tests;

import com.restful.booker.herokuapp.models.Booking;
import com.restful.booker.herokuapp.models.BookingDates;
import org.junit.jupiter.api.Test;


public class DeleteBookingTests extends BaseTest{


    @Test
    public void deleteBookingTest(){
        BookingDates bookingDates = new BookingDates("2025-03-04","2025-03-07");
        Booking booking = new Booking("Led","Zeppelin",1881,true, bookingDates,"Temiz havlu Olsun Lütfen");


        deleteBooking(createToken() ,createBooking(booking).getBookingid());


    }

}
