package datve.com.controller;

import datve.com.model.User;
import datve.com.service.JWT.JwtService;
import datve.com.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImpl userService;



    @RequestMapping(value = "/api/login",method = RequestMethod.POST,produces = "text/plain")
    public ResponseEntity<String> login(@RequestBody User user){
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<String>(result, httpStatus);
    }
    @RequestMapping(value = "/api/logout",method = RequestMethod.POST,produces = "text/plain")
    public ResponseEntity<String> logout(){
        String result = "logout success";
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/api/sign-up",method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user){

        HttpStatus httpStatus = null;
        try {
            if (userService.addUser(user)) {
                httpStatus = HttpStatus.OK;
            } else {
                httpStatus = HttpStatus.NOT_EXTENDED;
            }
        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<User>(user, httpStatus);
    }
    @RequestMapping(value = "/api/users",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }
}
