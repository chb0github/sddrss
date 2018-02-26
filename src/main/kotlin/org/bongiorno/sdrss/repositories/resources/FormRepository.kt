package org.bongiorno.sdrss.repositories.resources

import org.bongiorno.sdrss.domain.resources.Form
import org.springframework.data.repository.CrudRepository


interface FormRepository : CrudRepository<Form, Long>