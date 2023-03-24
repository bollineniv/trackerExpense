package com.example.demo.repository;

import com.example.demo.model.Expense;
import com.example.demo.model.FilesModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<FilesModel, Long> {

    FilesModel findByName(String name);

}
