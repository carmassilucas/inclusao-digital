package br.com.ifsp.aluno.inclusaodigital.module.interlocutor.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_interlocutor_type")
public class InterlocutorType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, updatable = false, unique = true)
    private String description;

    public InterlocutorType() {

    }

    public InterlocutorType(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Values {
        refugee(1L, "refugee"),
        immigrant(2L, "immigrant"),
        collaborator(3L, "collaborator");

        Values(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        private Long id;
        private String description;

        public InterlocutorType get() {
            return new InterlocutorType(id, description);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        InterlocutorType that = (InterlocutorType) object;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
