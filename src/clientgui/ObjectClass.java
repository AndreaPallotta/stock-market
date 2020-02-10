/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author andreapallotta
 */
public class ObjectClass implements Serializable {
    final static long serialVersionUID = 12L;
    
    // SECURITIES CLASS
    public static class Securities implements Serializable {
    final static long serialVersionUID = 1L;
    String username;
    String ticker;
    Timestamp timestamp;
    int numberOfShares;

    public Securities(String username, String ticker, Timestamp timestamp, int numberOfShares) {
        this.username = username;
        this.ticker = ticker;
        this.timestamp = timestamp;
        this.numberOfShares = numberOfShares;
    }

    public Securities(String ticker, int numberOfShares) {
        this.ticker = ticker;
        this.numberOfShares = numberOfShares;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }
    
    
}
  
// TRADE CLASS    
public static class Trades implements Serializable {
    final static long serialVersionUID = 2L;
    String username;
    String ticker;
    int tradedShares;
    double valuePerShare;
    String transactionType;
    double cost;

    public Trades(String username, String ticker, int tradedShares, double valuePerShare, String transactionType, double cost) {
        this.username = username;
        this.ticker = ticker;
        this.tradedShares = tradedShares;
        this.valuePerShare = valuePerShare;
        this.transactionType = transactionType;
        this.cost = cost;
    }

    public Trades(String ticker, double valuePerShare) {
        this.ticker = ticker;
        this.valuePerShare = valuePerShare;
    }

    public Trades(String username, String ticker, int tradedShares, String transactionType) {
        this.username = username;
        this.ticker = ticker;
        this.tradedShares = tradedShares;
        this.transactionType = transactionType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getTradedShares() {
        return tradedShares;
    }

    public void setTradedShares(int tradedShares) {
        this.tradedShares = tradedShares;
    }

    public double getValuePerShare() {
        return valuePerShare;
    }

    public void setValuePerShare(double valuePerShare) {
        this.valuePerShare = valuePerShare;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Trades{" + "username=" + username + ", ticker=" + ticker + ", tradedShares=" + tradedShares + ", valuePerShare=" + valuePerShare + ", transactionType=" + transactionType + ", cost=" + cost + '}';
    }

    
}
    
// COMPANY

public static class Company implements Serializable {
    final static long serialVersionUID = 3L;
    String ticker;
    String name;
    double value;
    Timestamp timestamp;
    int numberOfShares;

    public Company(String ticker, String name, double value, Timestamp timestamp, int numberOfShares) {
        this.ticker = ticker;
        this.name = name;
        this.value = value;
        this.timestamp = timestamp;
        this.numberOfShares = numberOfShares;
    }
    
    public Company(String ticker, double value) {
        this.ticker = ticker;
        this.value = value;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    } 

    @Override
    public String toString() {
        return "Company{" + "ticker=" + ticker + ", name=" + name + ", value=" + value + ", timestamp=" + timestamp + ", numberOfShares=" + numberOfShares + '}';
    }
    
}


// USER 
public static class User implements Serializable {
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
    
    public User(double balance) {
        this.balance = balance;
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

public static class Message implements Serializable {
    final static long serialVersionUID = 5L;
    String message;
    String type;

    public Message(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", type=" + type + '}';
    }
    
}


    
}
