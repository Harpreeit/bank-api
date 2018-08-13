package com.usbank.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.usbank.entity.User;
import com.usbank.exception.BadRequestException;
import com.usbank.exception.ResourceNotFoundException;
import com.usbank.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;

    @Transactional(readOnly = true)
	public List<User> findAll() {
    	return (List<User>) repository.findAll();
	}

    @Transactional(readOnly = true)
    public User findOne(String usrId) {
        return repository.findById(usrId)
        		.orElseThrow(() -> new ResourceNotFoundException("User with id " + usrId + " doesn't exist."));
    }
    
    @Transactional
	public User create(User usr) {
        Optional<User> existing = repository.findByEmail(usr.getEmail());
        if (existing.isPresent()) {
            throw new BadRequestException("User with email " + usr.getEmail() + " already exists.");
        }
        return repository.save(usr);
	}

    @Transactional
	public User update(String usrId, User usr) {
        Optional<User> existing = repository.findById(usrId);
        if (!existing.isPresent()) {
            throw new ResourceNotFoundException("User with id " + usrId + " doesn't exist.");
        }
        return repository.save(usr);
	}

    @Transactional
	public void delete(String usrId) {
        Optional<User> existing = repository.findById(usrId);
        if (!existing.isPresent()) {
            throw new ResourceNotFoundException("User with id " + usrId + " doesn't exist.");
        }
        repository.delete(existing.get());
	}

}
