package com.remotehiring.Bookings;

import java.io.IOException;
import java.util.List;

import com.remotehiring.Mail.MailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookingsController {

	@Autowired
	private BookingsService service;

	@Autowired
	private MailService mailService;
	
	@PostMapping("/bookings/add")
	public Bookings add(@RequestBody Bookings booking) {
		 return service.add(booking);
	}
	
	@GetMapping("/bookings")
	public List<Bookings> listAll(){
		return service.listAll();
	}
	
	@GetMapping("bookings/getByUser/{email}")
	public List<Bookings> listByUsers(@PathVariable String email){
		return service.listByUsers(email);
	}
	
	@GetMapping("/bookings/endBooking/{bookingid}")
	public boolean endBooking(@PathVariable Integer bookingid) throws MessagingException, TemplateException, IOException {
        mailService.paymentMail(bookingid);
return service.endBooking(bookingid);
	}
	
	@GetMapping("/bookings/allBookings")
	public List<Bookings> allBookings(){
		return service.listAll();
	}
	
}
