package com.paypal.bfs.test.bookingserv.resource;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.service.BookingService;

@RunWith(SpringRunner.class)
//full server startup needed because of mandatory JNDI configuration / whole Spring application context populated
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookingResourceTest {

	@Mock
	private BookingService bookingService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	// we are only passing few parameters
	public void testCreateBookingBadRequest() throws Exception {

		Booking bookingDTO = new Booking();
		bookingDTO.setFirstName("Unittest-FirstName");
		bookingDTO.setLastName("Unittest-LastName");

		Booking responseBooking = new Booking();
		responseBooking.setFirstName("Unittest-FirstName");
		responseBooking.setLastName("Unittest-LastName");

		when(bookingService.saveBooking(bookingDTO)).thenReturn(responseBooking);

		mockMvc.perform(post("/v1/bfs/booking").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(bookingDTO))).andExpect(status().isBadRequest());

	}

	@Test
	public void testCreateBooking() throws Exception {
		Booking bookingDTO = new Booking();
		bookingDTO.setFirstName("Unittest-FirstName");
		bookingDTO.setLastName("Unittest-LastName");
		bookingDTO.setCheckin(new Date());
		bookingDTO.setCheckout(new Date());
		bookingDTO.setDateOfBirth("1979-10-11");
		bookingDTO.setDeposit(200.0);
		bookingDTO.setTotalprice(300.0);
		Address addressDTO = new Address();
		addressDTO.setLine1("addressline1");
		addressDTO.setCity("Noida");
		addressDTO.setCountry("India");
		addressDTO.setState("UP");
		addressDTO.setZipCode("201301");
		bookingDTO.setAddress(addressDTO);
		Booking responseBooking = new Booking();
		responseBooking.setFirstName("Unittest-FirstName");
		responseBooking.setLastName("Unittest-LastName");

		when(bookingService.saveBooking(bookingDTO)).thenReturn(responseBooking);

		mockMvc.perform(post("/v1/bfs/booking").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(bookingDTO))).andExpect(status().isCreated())
				// .andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.first_name", is("Unittest-FirstName")))
				.andExpect(jsonPath("$.last_name", is("Unittest-LastName")));

	}

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}
