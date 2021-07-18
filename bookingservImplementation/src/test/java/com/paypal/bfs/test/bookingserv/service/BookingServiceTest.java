package com.paypal.bfs.test.bookingserv.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.entity.BookingEntity;
import com.paypal.bfs.test.bookingserv.repository.BookingRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {
	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private ModelMapper mapper;

	@InjectMocks
	BookingService bookingService = new BookingServiceImpl(bookingRepository, mapper);

	private Booking bookingDTO = new Booking();
	private BookingEntity bookingEntity = new BookingEntity();

	@Before
	public void setUp() {
		when(mapper.map(any(), any())).thenReturn(bookingDTO);

		bookingDTO.setId(1);
		bookingDTO.setFirstName("Unittest-FirstName");
		bookingDTO.setLastName("Unittest-LastName");

	}

	@Test
	public void testBookingById() throws Throwable {
		when(bookingRepository.findById(1)).thenReturn(Optional.of(bookingEntity));

		Booking result = bookingService.getBooking(1);

		assertEquals("Unittest-FirstName", result.getFirstName());
		assertNotNull(result);

	}

	@Test
	public void testGetAllBookings() throws Throwable {

		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setId(1);
		bookingEntity.setFirstName("Unittest-FirstName");
		bookingEntity.setLastName("Unittest-LastName");

		List<BookingEntity> expectedProducts = Arrays.asList(bookingEntity);

		when(bookingRepository.findAll()).thenReturn(expectedProducts);

		List<Booking> result = bookingService.getAllBooking();

		assertEquals(1, result.size());
		assertNotNull(result);

	}
	
	
//	@Test
	public void testSaveBooking() throws Throwable {

		BookingEntity bookingEntity = new BookingEntity();
		//bookingEntity.setId(1);
		bookingEntity.setFirstName("Unittest-FirstName");
		bookingEntity.setLastName("Unittest-LastName");


		when(bookingRepository.save(any())).thenReturn(bookingEntity);

		Booking savedBookingDTO = bookingService.saveBooking(bookingDTO);

		assertEquals("1", savedBookingDTO.getId());
		assertNotNull(savedBookingDTO);

	}
	
	@Test
	public void testGetAllBookingsWithNoBooking() throws Throwable {
		List<BookingEntity> bookings = new ArrayList<>();

		when(bookingRepository.findAll()).thenReturn(bookings);

		List<Booking> result = bookingService.getAllBooking();

		assertEquals(0, result.size());
		assertNotNull(result);

	}

}
