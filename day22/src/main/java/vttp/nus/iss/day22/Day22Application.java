package vttp.nus.iss.day22;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.nus.iss.day22.model.Contact;
import vttp.nus.iss.day22.repo.BffRepo;

@SpringBootApplication
public class Day22Application implements CommandLineRunner{

	@Autowired
	private BffRepo bffRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day22Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Contact contact = new Contact();
		contact.setEmail(null);
		contact.setName("Betty");
		contact.setDob(new Date(1960, 2, 29));
		contact.setPhone("55567895");
		contact.setComments("comment");

		boolean result = bffRepo.insertContact(contact);
		System.out.printf("Inserted: %b\n", contact);
	}
}
