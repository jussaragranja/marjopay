package com.hackaton.marjopay.config;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

/**
 * @author jussara.granja
 * @version 1.0
 */
public class ConfigTest {
		
	@BeforeAll
	public static void setup() {
		
		baseURI = "http://localhost";
		port = 8080;
	}
}
