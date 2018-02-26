package org.bongiorno.sdrss.repositories.resources

import org.bongiorno.sdrss.domain.resources.Report
import org.springframework.data.repository.CrudRepository


interface ReportRepository : CrudRepository<Report, Long>
