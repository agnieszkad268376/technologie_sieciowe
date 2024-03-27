package org.example.libraryDataBase.Repositories;

import org.example.libraryDataBase.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {

}


