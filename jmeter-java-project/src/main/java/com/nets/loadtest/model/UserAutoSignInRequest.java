package com.nets.loadtest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Request payload for UVMC /auth/userAutoSignIn endpoint.
 */
public class UserAutoSignInRequest {
    @SerializedName("custName")
    private String custName;

    @SerializedName("enrollmentID")
    private String enrollmentID;

    public UserAutoSignInRequest(String custName, String enrollmentID) {
        this.custName = custName;
        this.enrollmentID = enrollmentID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(String enrollmentID) {
        this.enrollmentID = enrollmentID;
    }
}
