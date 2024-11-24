package com.restaurnt.restaurnt.app.repository;

import com.restaurnt.restaurnt.app.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address , Long> {

}
