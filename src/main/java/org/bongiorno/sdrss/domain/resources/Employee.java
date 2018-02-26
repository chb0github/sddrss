package org.bongiorno.sdrss.domain.resources;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.Identifiable;

@Getter
@EqualsAndHashCode
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Identifiable<Long>{


    @NotNull
    @Pattern(regexp = "[A-Za-z0-9]+")
    @Size(min = 2, max = 32)
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private LocalDate hireDate = LocalDate.now();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Form> forms = Collections.emptyList();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Report> reports = Collections.emptyList();

    @Id
    @GeneratedValue
    private Long id;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Employee(String name) {
        this(name,name +"@sterlingts.com");
    }
}
