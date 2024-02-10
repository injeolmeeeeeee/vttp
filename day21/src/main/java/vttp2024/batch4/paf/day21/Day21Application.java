package vttp2024.batch4.paf.day21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2024.batch4.paf.day21.repo.BookRepo;

@SpringBootApplication
public class Day21Application implements CommandLineRunner {

	@Autowired
	BookRepo bookRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day21Application.class, args);
	}

	@Override
	public void run(String... args){
		// bookRepo.findBooksByTitile("murder");
		// bookRepo.findBooksByTitile("asdfasdf");
		// bookRepo.findBookByPaperbackAndRating("paperback", 4);
	}

}
