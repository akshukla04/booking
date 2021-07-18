package com.paypal.bfs.test.bookingserv.service;

import java.util.List;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

public interface BookingService {

	Booking saveBooking(Booking bookingInput);

	List<Booking> getAllBooking();

	Booking getBooking(int id);

}