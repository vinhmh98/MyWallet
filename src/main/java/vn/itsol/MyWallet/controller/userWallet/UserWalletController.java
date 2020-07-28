package vn.itsol.MyWallet.controller.userWallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.itsol.MyWallet.controller.user.UserController;
import vn.itsol.MyWallet.entity.userWallet.UserWallet;
import vn.itsol.MyWallet.service.userWallet.UserWalletService;

import java.util.List;

@RestController
public class UserWalletController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserWalletService userWalletService;

    @PostMapping(path = "/userWallet",consumes ={"application/json"} )
    public UserWallet addUserWallet(@RequestBody UserWallet userWallet)
    {
        userWalletService.save(userWallet);
        return userWallet;
    }

    @DeleteMapping(path = "/userWallet/{userWalletID}")
    public String deleteUserWallet(@PathVariable("userWalletID") int userWalletID){
        log.info("------------- userWalletID: " + userWalletID);
        userWalletService.delete(userWalletID);
        return "deleted"+ userWalletID;
    }

    @GetMapping(path ="/userWallets")
    public ResponseEntity<List<UserWallet>> getUserWallet(){
        List<UserWallet> list=(List<UserWallet>) userWalletService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping(path ="/userWallet/{userWalletID}",consumes = {"application/json"})
    public ResponseEntity<UserWallet> updateUserWallet(@PathVariable(value = "userWalletID") int userWalletID, @RequestBody UserWallet userWallet){
        final UserWallet updatedUserWallet = userWalletService.update(userWalletID);
        return ResponseEntity.ok(updatedUserWallet);
    }

    @GetMapping(path = "/userWallet/{userWalletID}")
    public UserWallet getUserWallet(@PathVariable("userWalletID") int userWalletID){
        return userWalletService.findById(userWalletID);
    }
}
