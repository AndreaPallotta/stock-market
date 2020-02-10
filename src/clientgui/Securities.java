/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.io.Serializable;
import java.sql.Timestamp;

// SECURITIES CLASS
    public class Securities implements Serializable {
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
  