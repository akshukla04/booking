package com.paypal.bfs.test.bookingserv.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.service.BookingService;

@Component
public class BookingResourceImpl implements BookingResource {
	private BookingService bookingService;
	
	public BookingResourceImpl(BookingService bookingService) {
		this.bookingService = bookingService;
	}
    @Override
 
    @RequestMapping(value="/v1/bfs/booking", method = RequestMethod.POST)
    public ResponseEntity<Booking> create(@RequestBody @Valid Booking newbooking) {
    	Booking booking =  bookingService.saveBooking(newbooking);
    	if (booking == null) {
			return ResponseEntity.noContent().build();
    	}
    	return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
    
    @Override
    @GetMapping
    public ResponseEntity<List<Booking>> getAll() {
    	return new ResponseEntity<>(
    			bookingService.getAllBooking(), 
    			HttpStatus.OK);
    			
    }
}
