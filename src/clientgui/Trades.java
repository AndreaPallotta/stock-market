/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.io.Serializable;

/**
 *
 * @author andreapallotta
 */
public class Trades implements Serializable {
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
