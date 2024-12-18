package org.example;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final UserClient userClient;
    private final SushiClient sushiClient;

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDTO> getDelivery(@PathVariable("id") Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isPresent()) {
            Sushi sushi = sushiClient.getSushiById(delivery.get().getSushiId());
            User user = userClient.getUserById(delivery.get().getUserId());
            DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                    .id(delivery.get().getId())
                    .sushi(sushi)
                    .user(user)
                    .build();
            return ResponseEntity.ok(deliveryDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Delivery> createDelivery(@RequestParam("sushiId") Long sushiId,
                                                   @RequestParam("userId") Long userId) {
        try {
            Sushi sushi = sushiClient.getSushiById(sushiId);
            User user = userClient.getUserById(userId);
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().build();
        }

        Delivery newDelivery = Delivery.builder()
                .userId(userId)
                .sushiId(sushiId)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryRepository.save(newDelivery));
    }
}
