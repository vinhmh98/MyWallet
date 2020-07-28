package vn.itsol.MyWallet.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import vn.itsol.MyWallet.entity.user.User;
import vn.itsol.MyWallet.service.user.UserService;
import vn.itsol.MyWallet.util.UserLoginUtilJwt;

import java.util.List;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginUtilJwt userLoginUtilJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "/authenticate")
    public String generateToken(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        }
        catch (Exception ex){
            throw new Exception("invalid username/password");
        }
        return userLoginUtilJwt.generateToken(user.getUserName());
    }

    @PostMapping(path = "/user",consumes ={"application/json"} )
    public User addUser(@RequestBody User user)
    {
        userService.save(user);
        return user;
    }

    @DeleteMapping(path = "/user/{user_id}}",consumes = {"application/json"})
    public String deleteUser(@PathVariable("user_id") int user_id){
        userService.delete(user_id);
        return "deleted"+ user_id;
    }

    @GetMapping(path ="/users")
    public ResponseEntity<List<User>> getUser(){
        log.info("---------------"+userService.findAll().toString());
      List<User> list=(List<User>) userService.findAll();
      return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping(path ="/user",consumes = {"application/json"})
    public User updateOrAddUser(@RequestBody User user){
        userService.update(user);
        return user;
    }

    @GetMapping(path = "/user/{userId}")
    public User getUser(@PathVariable("userId") int userId){
       return userService.findById(userId);
    }
}
