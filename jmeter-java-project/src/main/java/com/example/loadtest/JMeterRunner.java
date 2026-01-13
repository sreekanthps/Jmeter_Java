
package com.example.loadtest;

import com.example.loadtest.apis.RegistrationApiTest;
import com.example.loadtest.apis.RegistrationUVMCApiTest;

public class JMeterRunner {
    public static void main(String[] args) throws Exception {
        RegistrationUVMCApiTest.run();
    }
}
