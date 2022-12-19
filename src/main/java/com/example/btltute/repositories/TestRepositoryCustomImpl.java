package com.example.btltute.repositories;

import com.example.btltute.domains.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepositoryCustomImpl implements TestRepositoryCustom{
  @PersistenceContext
  private EntityManager entityManager;
  @Override
  public List<Book> getAllBook() {
    StringBuilder sql = new StringBuilder();
    sql.append(" select * from book ");
    String nativeQuery = String.valueOf(sql);
    Query query = entityManager.createNativeQuery(nativeQuery);
    return query.getResultList();
  }
}
