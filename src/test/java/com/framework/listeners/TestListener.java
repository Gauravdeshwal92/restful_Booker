package com.framework.listeners;

import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🚀 Starting Test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getMethod().getMethodName());
        System.out.println("⚠️ Error Cause: " + result.getThrowable().getMessage());

        // Automatically take a textual "snapshot" of the failure for your reports
        logFailureDetails(result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭️ Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🏁 Starting Test Suite Execution: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🛑 Finished Test Suite Execution: " + context.getName());
    }

    // This annotation tells Allure to grab this text block and embed it directly in the dashboard
    @Attachment(value = "Failure StackTrace", type = "text/plain")
    public String logFailureDetails(String message) {
        return message;
    }
}