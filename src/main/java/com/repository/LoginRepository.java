package com.repository;

import com.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    @Query("select l from Login l where l.emailid = :emailid")
    public Optional<Login> findByEmail(@Param("emailid") String emailid);

    @Query("select l from Login l where l.emailid = :emailid and l.password = :password")
    public Optional<Login> findByEmailAndPassword(@Param("emailid") String emailid, @Param("password") String password);

}
