package com.usbank.repository;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.usbank.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

	Optional<User> findByEmail(String email);

}
