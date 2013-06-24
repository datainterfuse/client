package com.di.client.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.di.common.controller.PersonController;
import com.di.common.controller.impl.PersonControllerImpl;
import com.di.common.model.Person;

@Path("person")
public class PersonService {
	@Autowired
	PersonController personController;

	@GET
	@Produces("application/xml")
	public String getAll() {
		if (personController == null) {
			personController = new PersonControllerImpl();
		}
		List<Person> people = personController.getAll();
		StringBuilder sb = new  StringBuilder();
		sb.append("<people>");
		for (Person p : people) {
			sb.append("<person>");
			sb.append("<firstName>").append(p.getFirstName()).append("</firstName>");
			if (p.getDob() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-DD-MM HH:mm:ss");
				sb.append("<dob>").append(sdf.format(new Date(p.getDob().getTime()))).append("</dob>");
			} 
			sb.append("</person>");
		}
		sb.append("</people>");
		return sb.toString();
	}
	
	
}
