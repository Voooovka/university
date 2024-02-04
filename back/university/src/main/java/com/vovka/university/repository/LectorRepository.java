package com.vovka.university.repository;

import com.vovka.university.model.Lector;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query(value = "SELECT * FROM lector l WHERE LOWER(l.name) " +
            "LIKE LOWER(CONCAT('%', :userQuery, '%')) " +
            "OR LOWER(l.surname) " +
            "LIKE LOWER(CONCAT('%', :userQuery, '%'))", nativeQuery = true)
    List<Lector> getLectorsByUserQuery(String userQuery);
}
