package com.nets.loadtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Request payload for /registration/init endpoint.
 */
public class RegistrationInitRequest {
  @SerializedName("key")
  private String key;

  @SerializedName("context")
  private String context;

  @SerializedName("app_version")
  private String appVersion;

  public RegistrationInitRequest(String key, String context, String appVersion) {
    this.key = key;
    this.context = context;
    this.appVersion = appVersion;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }
}
