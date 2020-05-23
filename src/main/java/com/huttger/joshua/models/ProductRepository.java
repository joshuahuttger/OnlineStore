package com.huttger.joshua.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

  List<Product> findByNameLike(String keyword);
  List<Product> findAll();
  Product findById(long id);
}
