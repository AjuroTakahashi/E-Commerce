package com.ecommerce.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import java.util.Optional;

@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity); // Pour sauvegarder l’entité
    Optional<T> findById(ID primaryKey); // Pour trouver l’entité depuis la pk
    Iterable<T> findAll(); // SELECT * FROM entité
    long count(); // Nb de lignes dans la table
    void delete(T entity); // Supprime une ligne
    boolean existsById(ID primaryKey); // On a compris
}