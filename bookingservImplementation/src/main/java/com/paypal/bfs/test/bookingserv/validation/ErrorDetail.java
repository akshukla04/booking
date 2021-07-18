package com.paypal.bfs.test.bookingserv.validation;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDetail {

	 private Date timestamp;
	  private String message;
	  private String details;

}
