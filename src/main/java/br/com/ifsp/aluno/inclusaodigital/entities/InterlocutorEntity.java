package br.com.ifsp.aluno.inclusaodigital.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "interlocutor")
public class InterlocutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String currentState;

    @Column(nullable = false)
    private String currentCity;
    private String profilePicture;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
