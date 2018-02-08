package org.bongiorno.sdrss.repositories.resources

import org.bongiorno.sdrss.domain.resources.Report
import org.springframework.data.repository.CrudRepository
import org.springframework.security.access.prepost.PostFilter
import java.util.*


interface ReportRepository : CrudRepository<Report, Long> {

    @PostFilter("hasPermission(filterObject, 'READ')")
    override fun findAll(): Iterable<org.bongiorno.sdrss.domain.resources.Report>

    @org.springframework.security.access.prepost.PostAuthorize("hasPermission(returnObject, 'READ')")
    override fun findById(aLong: Long?): Optional<Report>?
}
