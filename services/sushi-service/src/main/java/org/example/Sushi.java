package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "sushi")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sushi {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int cost;

}
