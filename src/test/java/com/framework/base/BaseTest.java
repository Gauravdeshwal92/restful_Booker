package com.framework.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.framework.config.ConfigManager.getProperty;

public class BaseTest {

    @BeforeSuite
    public void setup(){
        Path targetResults = Paths.get("target", "allure-results");
        System.setProperty("allure.results.directory", targetResults.toString());
        try {
            Files.createDirectories(targetResults);
            System.out.println("Allure results directory set to " + targetResults.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("Unable to create allure results directory: " + e.getMessage());
        }
        RestAssured.baseURI = getProperty("base.url");
        System.out.println("Base URI set to "+RestAssured.baseURI);
    }

    @BeforeClass
    public void cleanBeforeTestClass(){
        cleanAllureResults();
    }

    /**
     * Cleans up allure-results directory before running tests
     */
    private void cleanAllureResults() {
        try {
            // Clean allure-results at root level
            Path rootResultsPath = Paths.get("allure-results");
            if (Files.exists(rootResultsPath)) {
                deleteDirectory(rootResultsPath.toFile());
                System.out.println("Cleaned allure-results directory at root level");
            }
            
            // Clean target/allure-results
            Path targetResultsPath = Paths.get("target/allure-results");
            if (Files.exists(targetResultsPath)) {
                deleteDirectory(targetResultsPath.toFile());
                System.out.println("Cleaned target/allure-results directory");
            }
        } catch (Exception e) {
            System.out.println("Error cleaning allure results: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Recursively deletes a directory and all its contents
     */
    private void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }
        if (directory.exists()) {
            directory.delete();
        }
    }


}
