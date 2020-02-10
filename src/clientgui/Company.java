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
public class Company implements Serializable {
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

