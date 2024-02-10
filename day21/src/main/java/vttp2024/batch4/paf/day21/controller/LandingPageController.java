package vttp2024.batch4.paf.day21.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2024.batch4.paf.day21.model.Book;
import vttp2024.batch4.paf.day21.model.BookSummary;
import vttp2024.batch4.paf.day21.repo.BookRepo;

@Controller
public class LandingPageController {

    @Autowired
    BookRepo bookRepo;
    
    @GetMapping(path = {"/", "/index.html"})
    public String getLandingPage(Model model,
                                @RequestParam(defaultValue = "10") int limit,
                                @RequestParam(defaultValue = "0") int offset) {
        
        List<BookSummary> summary = bookRepo.findBooksOrderByTitle(limit, offset);
        model.addAttribute("summary", summary);

        return "index";
    }

    @GetMapping(path = {"/book/{bookId}"})
    public String getBookById(@PathVariable String bookId, Model model) {
        
        Optional<Book> optional = bookRepo.findBookById(bookId);
        Book book = optional.get();

        model.addAttribute("book", book);

        return "details";
    }
}
