package com.nets.loadtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Request payload for UVMC /notification/token endpoint.
 */
public class NotificationTokenRequest {
  @SerializedName("deviceType")
  private String deviceType;

  @SerializedName("token")
  private String token;

  public NotificationTokenRequest(String deviceType, String token) {
    this.deviceType = deviceType;
    this.token = token;
  }

  // Getters and setters
  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
