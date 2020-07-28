package vn.itsol.MyWallet.entity.userWallet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_wallet")
public class UserWallet {
    @Id
    @Column(name = "user_wallet_id")
    private int userWalletID;

    @Column(name = "user_id")
    private int userID;

    @Column(name = "wallet_id")
    private int walletID;

    @Column(name = "role")
    private int role;

    public int getUserWalletID() {
        return userWalletID;
    }

    public void setUserWalletID(int userWalletID) {
        this.userWalletID = userWalletID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public UserWallet(int userWalletID,int userID , int walletID, int role ){
        this.userWalletID=userWalletID;
        this.userID=userID;
        this.walletID=walletID;
        this.role=role;
    }
    public UserWallet(){}
}
