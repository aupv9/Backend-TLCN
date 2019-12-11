package datve.com.controller;

import datve.com.model.ResponseLogin;
import datve.com.model.User;
import datve.com.service.JWT.JwtService;
import datve.com.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImpl userService;


    /*
    Method login
    @param User user truyền từ client vào
    return Response
    * */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseLogin> login(@RequestBody User user) {

        ResponseLogin result = new ResponseLogin();
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)) {
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());

                result.setToken(jwtService.generateTokenLogin(user.getUsername()));
                List<String> roles = userService.loadUserByUsername(user.getUsername()).getRoles();
                result.setRoles(roles);
                httpStatus = HttpStatus.OK;
            } else {
                result.setToken("Wrong userId and password");
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result.setToken("Server Error");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<ResponseLogin>(result, httpStatus);
    }

    //    @RequestMapping(value = "/api/login",method = RequestMethod.POST,produces = "text/plain")
//    public ResponseEntity<String> login(@RequestBody User user){
//
//        String result = "";
//        HttpStatus httpStatus = null;
//        try {
//            if (userService.checkLogin(user)) {
//                result=jwtService.generateTokenLogin(user.getUsername());
////                result.setToken(jwtService.generateTokenLogin(user.getUsername()));
////                List<String> roles=userService.loadUserByUsername(user.getUsername()).getRoles();
////                result.setRoles(roles);
//                httpStatus = HttpStatus.OK;
//            } else {
//                result="Wrong userId and password";
//                httpStatus = HttpStatus.BAD_REQUEST;
//            }
//        } catch (Exception ex) {
//            result="Server Error";
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<String>(result, httpStatus);
//    }
    @RequestMapping(value = "/api/logout", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity<String> logout() {
        String result = "logout success";
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/sign-up", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println(user.get_id());
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

    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }
}
