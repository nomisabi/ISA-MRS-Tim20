package com.example.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.TableTest;

@Repository
public interface TableRepository extends CrudRepository<TableTest, Long> {

}
