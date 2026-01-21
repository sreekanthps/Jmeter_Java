package com.nets.loadtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Request payload for /registration/profile endpoint.
 */
public class ProfileRequest {
  @SerializedName("params")
  private String params;

  public ProfileRequest(String params) {
    this.params = params;
  }

  public String getParams() {
    return params;
  }

  public void setParams(String params) {
    this.params = params;
  }
}
