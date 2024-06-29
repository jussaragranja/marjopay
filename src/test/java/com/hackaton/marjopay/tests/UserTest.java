package com.hackaton.marjopay.tests;

import com.hackaton.marjopay.common.Endpoints;
import com.hackaton.marjopay.config.ConfigTest;
import com.hackaton.marjopay.repository.UserRepository;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static com.hackaton.marjopay.util.Constant.MESSAGE_USER_NOT_FOUND;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author jussara.granja
 * @version 1.0
 */

@SpringBootTest
@ActiveProfiles("test")
public class UserTest extends ConfigTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void getAllListTest() {
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get(Endpoints.GET_ALL_USER)
		.then()
				.log().all()
			.assertThat()
			.statusCode(HttpStatus.OK.value())
			.log().all();
	}
	
	@Test
	public void getByInexistentIdTest() {
		
		int invalidId = 1568;
	
		given()
			.contentType(ContentType.JSON)
		.when()
			.get(Endpoints.GET_USER_BY_ID, invalidId)
		.then()
				.log().all()
			.assertThat()
			.statusCode(HttpStatus.NOT_FOUND.value()).and().body("message", equalTo(MESSAGE_USER_NOT_FOUND))
			.log().all();
	}

}

