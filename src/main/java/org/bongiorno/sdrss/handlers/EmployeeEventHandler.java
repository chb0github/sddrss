package org.bongiorno.sdrss.handlers;

import org.bongiorno.sdrss.domain.resources.Employee;
import org.bongiorno.sdrss.domain.resources.Form;
import org.bongiorno.sdrss.domain.resources.Report;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author cbongiorno on 2/19/18.
 */
@Component
@RepositoryEventHandler
public class EmployeeEventHandler {
  // 2 different ways to handle events
  @HandleBeforeCreate
  public void handleBeforeCreate(Employee e) {
    System.out.format("about to create Employee %s%n",e);
  }

  @HandleAfterCreate
  public void handAfterCreate(Report f) {
    System.out.format(" How about your put this in a queue or on a websocket?: %s%n",f);
  }
}
