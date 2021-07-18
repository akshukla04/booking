package com.paypal.bfs.test.bookingserv.api;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
//@RestController
//@Path("/v1/bfs/booking")
@RestController
@Path("/v1/bfs/booking")
public interface BookingResource {
    /**
     * Create {@link Booking} resource
     *
     * @param booking the booking object
     * @return the created booking
     */
//	@PostMapping
    ResponseEntity<Booking> create(@RequestBody Booking booking);

    // ----------------------------------------------------------
    // TODO - add a new operation for Get All the bookings resource.
    // ----------------------------------------------------------
    
    ResponseEntity<List<Booking>> getAll();
}
