package org.bongiorno.sdrss.repositories.resources

import org.bongiorno.sdrss.domain.resources.Form
import org.springframework.data.repository.CrudRepository
import java.util.*


interface FormRepository : CrudRepository<Form, Long> {


    @org.springframework.security.access.prepost.PostFilter("hasPermission(filterObject, 'READ')")
    override fun findAll(): Iterable<Form>

    @org.springframework.security.access.prepost.PostAuthorize("hasPermission(returnObject, 'WRITE')")
    override fun findById(aLong: Long?): Optional<Form>?
}
