package clientgui;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author andreapallotta
 */
public class User implements Serializable {
    final static long serialVersionUID = 4L;
    String username;
    String password;
    double balance;
    Timestamp timestamp;

    public User(String username, String password, double balance, Timestamp timestamp) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.timestamp = timestamp;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", balance=" + balance + ", timestamp=" + timestamp + '}';
    }
    
    
}



    
    