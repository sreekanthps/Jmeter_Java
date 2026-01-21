package com.nets.loadtest.model;

/**
 * Model class for card transaction request.
 */
public class CardTransactionRequest {
  private String can;
  private String cardName;
  private String cardNo;
  private String cardType;
  private String cvv;
  private String expDate;
  private int txnAmt;

  public CardTransactionRequest(String can, String cardName, String cardNo, String cardType,
      String cvv, String expDate, int txnAmt) {
    this.can = can;
    this.cardName = cardName;
    this.cardNo = cardNo;
    this.cardType = cardType;
    this.cvv = cvv;
    this.expDate = expDate;
    this.txnAmt = txnAmt;
  }

  // Getters and Setters

  public String getCan() {
    return can;
  }

  public void setCan(String can) {
    this.can = can;
  }

  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getExpDate() {
    return expDate;
  }

  public void setExpDate(String expDate) {
    this.expDate = expDate;
  }

  public int getTxnAmt() {
    return txnAmt;
  }

  public void setTxnAmt(int txnAmt) {
    this.txnAmt = txnAmt;
  }
}
