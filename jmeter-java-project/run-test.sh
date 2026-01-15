#!/bin/bash

# JMeter Load Test Runner Script
# This script runs the RegistrationUVMCApiTest using Maven

echo "Starting JMeter Load Test..."
echo "Configuration: UVMC SIT Environment"
echo "=================================="

# Run the test using Maven exec plugin
mvn clean compile exec:java -Dexec.mainClass="com.nets.loadtest.JMeterRunner"

# Check if the test completed successfully
if [ $? -eq 0 ]; then
    echo ""
    echo "=================================="
    echo "Test completed successfully!"
    echo "Check the following files for results:"
    echo "  - uvmc-results.jtl (raw results)"
    echo "  - uvmc-html-report/ (HTML report)"
else
    echo ""
    echo "=================================="
    echo "Test failed! Check the logs above for errors."
    exit 1
fi
