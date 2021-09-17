package com.vicon.viconbackend.domain.common

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@MappedSuperclass
abstract class Persistable<PK>(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: PK? = null,

    @Version
    @JsonIgnore
    open val version: Long? = null
)