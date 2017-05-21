package com.example.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Manager;
import com.example.domain.Supply;

@Repository
public interface SupplyRepository extends CrudRepository<Supply, Long> {

}
