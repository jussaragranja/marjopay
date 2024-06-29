package com.hackaton.marjopay.factory;

import org.json.JSONException;
import org.json.JSONObject;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class Useractory {

    public static String createNewUserRequestBody() throws JSONException {
        JSONObject requestBody = new JSONObject();
        Faker faker = new Faker();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = format.format(faker.date().birthday());

        requestBody.put("name", faker.name().fullName());
        requestBody.put("cpf", faker.number().digits(11));
        requestBody.put("password", faker.number().digits(10));
        requestBody.put("status", true);
        requestBody.put("email", faker.internet().macAddress());
        requestBody.put("phone", faker.phoneNumber().cellPhone());
        requestBody.put("dateOfBirth", birthday);
        return requestBody.toString();
    }

}
