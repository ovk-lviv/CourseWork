package org.ui;

public class Application {
    public static Response createBooking(String token) {
        File jsonFileCreateBooking = new File("src/test/resources/Payloads/bookingCreate.json");
        RequestSpecification requestPost = RestAssured.given();
        requestPost.header("Accept", "application/json");
        requestPost.contentType(ContentType.JSON);
        requestPost.baseUri("https://restful-booker.herokuapp.com/booking");
        requestPost.auth().basic("token", token);
        requestPost.body(jsonFileCreateBooking);
        return requestPost.post();
    }
}
