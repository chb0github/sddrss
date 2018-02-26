package org.bongiorno.sdrss;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.of;
import static org.bongiorno.sdrss.domain.resources.Form.Type.ON_BOARDING;
import static org.bongiorno.sdrss.domain.resources.Form.Type.PROMOTION;

import java.util.List;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.bongiorno.sdrss.domain.resources.Employee;
import org.bongiorno.sdrss.domain.resources.Form;
import org.bongiorno.sdrss.domain.resources.Report;
import org.bongiorno.sdrss.domain.resources.Report.Type;
import org.bongiorno.sdrss.repositories.resources.EmployeeRepository;
import org.bongiorno.sdrss.repositories.resources.FormRepository;
import org.bongiorno.sdrss.repositories.resources.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataLoad {

  private final EmployeeRepository employees;
  private final ReportRepository reports;
  private final FormRepository forms;

  @PostConstruct
  private void init() {
    final Employee christian = employees.save(new Employee("Christian", "christian.bongiorno@sterlingts.com"));
    final Employee jake = employees.save(new Employee("Jake", "jake.litwicki@sterlingts.com"));

    final List<Employee> platTeam = rangeClosed(1, 10).mapToObj(it -> new Employee("Employee" + it))
        .map(employees::save).collect(toList());

    final List<Employee> glTeam = iterate(10, i -> i - 2).limit(5)
        .mapToObj(it -> new Employee("Employee" + it)).map(employees::save).collect(toList());

    final List<Form> allEmployees = of(Stream.of(christian,jake),
        platTeam.stream(), glTeam.stream()).flatMap(identity())
        .map(it -> new Form(ON_BOARDING, it)).map(forms::save).collect(toList());

    rangeClosed(1,10).mapToObj($ -> new Report(Type.EXPENSE,jake)).forEach(reports::save);
    rangeClosed(1,10).mapToObj($ -> new Form(PROMOTION,christian)).forEach(forms::save);

    Stream.concat(platTeam.stream(), glTeam.stream()).map(it -> new Report(Type.TPS,it)).forEach(reports::save);

    forms.save(new Form(Form.Type.TERMINATION,jake));
  }


}
