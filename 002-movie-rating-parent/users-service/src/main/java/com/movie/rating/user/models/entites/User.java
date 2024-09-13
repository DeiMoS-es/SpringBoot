package com.movie.rating.user.models.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    // TODO crear la relación entre usuarios y puntuaciones
    /**
     * One user can have multiple ratings
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;
     */
}
