package org.bongiorno.sdrss.handlers;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.hateoas.Identifiable;
import org.springframework.stereotype.Component;

/**
 * @author cbongiorno on 2/19/18.
 */
@Component
public class GenericEventHandler extends AbstractRepositoryEventListener<Identifiable<Long>> {

  @Override
  protected void onBeforeCreate(Identifiable<Long> entity) {
    System.out.println("onBeforeCreate : " + entity);

  }

  @Override
  protected void onAfterCreate(Identifiable<Long> entity) {
    System.out.println("onAfterCreate : " + entity);
  }

  @Override
  protected void onBeforeSave(Identifiable<Long> entity) {
    System.out.println("onBeforeSave : " + entity);

  }

  @Override
  protected void onAfterSave(Identifiable<Long> entity) {
    System.out.println("onAfterSave : " + entity);
  }

}
