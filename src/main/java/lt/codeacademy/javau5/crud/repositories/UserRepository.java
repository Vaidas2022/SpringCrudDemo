package lt.codeacademy.javau5.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.javau5.crud.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
