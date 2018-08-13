package com.usbank.service;
import java.util.List;
import com.usbank.entity.User;

public interface UserService {

    List<User> findAll();

    User create(User usr);

    User update(String id, User usr);

    void delete(String id);

	User findOne(String usrId);    
    
}
