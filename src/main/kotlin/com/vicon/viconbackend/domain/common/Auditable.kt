package com.vicon.viconbackend.domain.common

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class Auditable<PK>(
    @CreationTimestamp
    open var createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    open var modifiedAt: LocalDateTime? = null
) : Persistable<PK>()