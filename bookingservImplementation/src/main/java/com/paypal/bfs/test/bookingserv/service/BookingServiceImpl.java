package com.paypal.bfs.test.bookingserv.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;


@Service
public class BookingServiceImpl implements BookingService {
	private BookingRepository bookingRepository;
	private ModelMapper mapper;
	public BookingServiceImpl(BookingRepository bookingRepository , ModelMapper mapper) {
		this.bookingRepository = bookingRepository;
		this.mapper = mapper;
	}
	
	@Override
	public Booking saveBooking(Booking bookingInput) {
		BookingEntity booking = mapper.map(bookingInput, BookingEntity.class);
		BookingEntity savedBooking = bookingRepository.save(booking);
		if (savedBooking == null) {
			return null;
		} else {
			return mapper.map(savedBooking, Booking.class);
		}
	}
	
	@Override
	public List<Booking> getAllBooking() {
		
		return bookingRepository.findAll().stream()
				.map((e) ->  mapper.map(e, Booking.class)).collect(Collectors.toList());
		
	}
	
	@Override
	public Booking getBooking(int id) {
		
		return mapper.map(bookingRepository.findById(id), Booking.class);
		
	}
}
