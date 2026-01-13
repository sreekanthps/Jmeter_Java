package com.example.loadtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Request payload for UVMC /updateDevice endpoint.
 */
public class UpdateDeviceRequest {
    @SerializedName("imeiNumber")
    private String imeiNumber;

    @SerializedName("ipAddress")
    private String ipAddress;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("mobileDeviceId")
    private String mobileDeviceId;

    @SerializedName("mobileModel")
    private String mobileModel;

    @SerializedName("mobileOS")
    private String mobileOS;

    public UpdateDeviceRequest(String imeiNumber, String ipAddress, String latitude,
            String longitude, String mobileDeviceId, String mobileModel,
            String mobileOS) {
        this.imeiNumber = imeiNumber;
        this.ipAddress = ipAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mobileDeviceId = mobileDeviceId;
        this.mobileModel = mobileModel;
        this.mobileOS = mobileOS;
    }

    // Getters and setters
    public String getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMobileDeviceId() {
        return mobileDeviceId;
    }

    public void setMobileDeviceId(String mobileDeviceId) {
        this.mobileDeviceId = mobileDeviceId;
    }

    public String getMobileModel() {
        return mobileModel;
    }

    public void setMobileModel(String mobileModel) {
        this.mobileModel = mobileModel;
    }

    public String getMobileOS() {
        return mobileOS;
    }

    public void setMobileOS(String mobileOS) {
        this.mobileOS = mobileOS;
    }
}
