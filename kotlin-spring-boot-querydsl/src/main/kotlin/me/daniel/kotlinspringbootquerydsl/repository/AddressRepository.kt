package me.daniel.kotlinspringbootquerydsl.repository

import me.daniel.kotlinspringbootquerydsl.domain.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long>, QuerydslPredicateExecutor<Address>