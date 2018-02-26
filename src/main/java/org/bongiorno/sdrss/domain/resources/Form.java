package org.bongiorno.sdrss.domain.resources;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

@Getter
@EqualsAndHashCode
@Entity
@ToString
@NoArgsConstructor
public class Form implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    private Employee employee;

    @NotNull
    @PastOrPresent
    private LocalDate fileDate = LocalDate.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    public Form(Type type, Employee employee) {
        this.type = type;
        this.employee = employee;
    }

    public enum Type {
        ON_BOARDING,
        TERMINATION,
        PROMOTION
    }
}
