package vttp.nus.iss.day22workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.nus.iss.day22workshop.model.Rsvp;
import vttp.nus.iss.day22workshop.repo.RsvpRepo;

@RestController
@RequestMapping("/api")
public class RsvpController {

    @Autowired
    RsvpRepo rsvpRepo;

    @GetMapping("/rsvps")
    public ResponseEntity<List<Rsvp>> getAllRsvp() {
        List<Rsvp> allRsvps = rsvpRepo.getAllRsvp();

        return new ResponseEntity<>(allRsvps, HttpStatus.OK);
    }

    @GetMapping("/rsvp/{name}")
    public ResponseEntity<List<Rsvp>> searchRsvp(@PathVariable String name) {
        List<Rsvp> searchResult = rsvpRepo.searchRsvpByName(name);

        if (!searchResult.isEmpty()) {
            return new ResponseEntity<>(searchResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/rsvp")
    public ResponseEntity<Void> addRsvp(@RequestBody Rsvp rsvp) {
        rsvpRepo.dropRsvp();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/rsvp/{email}")
    public ResponseEntity<Void> updateRsvp(@RequestBody Rsvp rsvp, @PathVariable String email) {
        rsvpRepo.updateRsvp(email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/rsvps/count")
    public ResponseEntity<Integer> countRsvps() {
        int count = rsvpRepo.getNoOfRsvps();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
