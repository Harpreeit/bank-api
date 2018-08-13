package com.usbank.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.usbank.entity.User;
import com.usbank.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/users")
@Api(description = "User information endpoints")
public class UserController {

	
    @Autowired
    private UserService service;

    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find All Users",
    notes = "Returns a list of all users available in the database")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
    public List<User> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Find User by ID",
                  notes = "Return a single user or throw 404")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public User findOne(
            @ApiParam(value = "id of the user", required = true) @PathVariable("id") String usrId) {
        return service.findOne(usrId);
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User create(@RequestBody User usr) {
        return service.create(usr);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User update(@PathVariable("id") String usrId, @RequestBody User usr) {
        return service.update(usrId, usr);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") String usrId) {
        service.delete(usrId);
    }
}
