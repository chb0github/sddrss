package org.bongiorno.sdrss.domain.resources;

import java.time.LocalDate;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PastOrPresent;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import org.springframework.hateoas.Identifiable;

@Getter
@EqualsAndHashCode
@Entity
@ToString
@NoArgsConstructor
public class Report  implements Identifiable<Long> {


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

    public Report(Type type, Employee employee) {
        this.type = type;
        this.employee = employee;
    }

    public enum Type {
        TPS,
        EXPENSE
    }
}
