package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sushi")
@RequiredArgsConstructor
public class SushiController {

    private final SushiRepository sushiRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sushi>> getSushi(@PathVariable("id") Long id) {
        Optional<Sushi> sushi = sushiRepository.findById(id);
        if (sushi.isPresent()) {
            return ResponseEntity.ok(sushi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Sushi> createSushi(@RequestParam("name") String name) {
        Sushi newSushi = Sushi.builder()
                .name(name)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(sushiRepository.save(newSushi));
    }
}
