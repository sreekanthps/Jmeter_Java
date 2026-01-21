package com.nets.loadtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Request payload for /test/registration/encrypt/password endpoint.
 */
public class PasswordEncryptRequest {
  @SerializedName("passwordToEncrypt")
  private String passwordToEncrypt;

  @SerializedName("sessionId")
  private String sessionId;

  public PasswordEncryptRequest(String passwordToEncrypt, String sessionId) {
    this.passwordToEncrypt = passwordToEncrypt;
    this.sessionId = sessionId;
  }

  public String getPasswordToEncrypt() {
    return passwordToEncrypt;
  }

  public void setPasswordToEncrypt(String passwordToEncrypt) {
    this.passwordToEncrypt = passwordToEncrypt;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
}
