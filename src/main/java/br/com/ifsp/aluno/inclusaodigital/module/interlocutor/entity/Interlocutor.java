package br.com.ifsp.aluno.inclusaodigital.module.interlocutor.entity;

import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.InterlocutorChat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_interlocutor")
public class Interlocutor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interlocutor_type_id")
    private InterlocutorType interlocutorType;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "current_state", nullable = false)
    private String currentState;

    @Column(name = "current_city", nullable = false)
    private String currentCity;

    @Column(name = "profile_picture")
    private String profilePicture;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "interlocutor", fetch = FetchType.LAZY)
    private Set<InterlocutorChat> interlocutorChats = new HashSet<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Interlocutor() {
    }

    public Interlocutor(UUID id, String name, String aboutMe, String email, String password, InterlocutorType interlocutorType,
                        LocalDate dateOfBirth, String currentState, String currentCity, String profilePicture,
                        Set<InterlocutorChat> interlocutorChats, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.aboutMe = aboutMe;
        this.email = email;
        this.password = password;
        this.interlocutorType = interlocutorType;
        this.dateOfBirth = dateOfBirth;
        this.currentState = currentState;
        this.currentCity = currentCity;
        this.profilePicture = profilePicture;
        this.interlocutorChats = interlocutorChats;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Interlocutor(String name, String aboutMe, String email, LocalDate dateOfBirth, String currentState,
                        String currentCity, InterlocutorType.Values interlocutorType) {
        this.name = name;
        this.aboutMe = aboutMe;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.currentState = currentState;
        this.currentCity = currentCity;
        this.interlocutorType = interlocutorType.get();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InterlocutorType getInterlocutorType() {
        return interlocutorType;
    }

    public void setInterlocutorType(InterlocutorType interlocutorType) {
        this.interlocutorType = interlocutorType;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<InterlocutorChat> getInterlocutorChats() {
        return interlocutorChats;
    }

    public void setInterlocutorChats(Set<InterlocutorChat> interlocutorChats) {
        this.interlocutorChats = interlocutorChats;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
