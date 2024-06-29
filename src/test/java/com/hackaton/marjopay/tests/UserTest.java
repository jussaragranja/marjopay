package com.hackaton.marjopay.tests;

import com.hackaton.marjopay.common.Endpoints;
import com.hackaton.marjopay.config.ConfigTest;
import com.hackaton.marjopay.factory.Useractory;
import com.hackaton.marjopay.repository.UserRepository;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Test
	public void getAllListTest() {
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get(Endpoints.GET_ALL_USER)
		.then()
				.log().all()
			.assertThat()
			.statusCode(HttpStatus.SC_OK)
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
			.statusCode(HttpStatus.SC_NOT_FOUND).and().body("message", equalTo(MESSAGE_USER_NOT_FOUND))
			.log().all();
	}

	@Test
	public void postNewUser() throws JSONException {

		given()
				.contentType(ContentType.JSON)
				.body(Useractory.createNewUserRequestBody())
			.when()
				.post(Endpoints.POST_USER)
			.then()
				.log().all()
				.assertThat()
				.statusCode(HttpStatus.SC_CREATED)
				.log().all();
	}

}

