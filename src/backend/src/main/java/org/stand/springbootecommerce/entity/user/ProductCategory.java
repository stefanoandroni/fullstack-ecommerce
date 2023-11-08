package org.stand.springbootecommerce.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "category")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank
    @Size(max = 255)
    @Column(name = "description")
    private String description;
}