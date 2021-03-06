/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bongiorno.sdrss.repositories.resources

import org.bongiorno.sdrss.domain.resources.Report
import org.springframework.data.repository.CrudRepository
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PostFilter


interface ReportRepository : org.springframework.data.repository.CrudRepository<Report, Long> {

    @org.springframework.security.access.prepost.PostFilter("hasPermission(filterObject, 'READ')")
    override fun findAll(): Iterable<org.bongiorno.sdrss.domain.resources.Report>

    @org.springframework.security.access.prepost.PostAuthorize("hasPermission(returnObject, 'READ')")
    override fun findOne(aLong: Long?): org.bongiorno.sdrss.domain.resources.Report
}
