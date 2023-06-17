package com.dfch.apppizzeria.persistence.repository;

import com.dfch.apppizzeria.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends ListCrudRepository<CustomerEntity, String> {

    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phoneNumber")
    CustomerEntity findByPhone(@Param("phoneNumber") String phoneNumber);
}
