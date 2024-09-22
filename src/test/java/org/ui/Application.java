package org.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Application {
    private String createdUserUsername = "user002";
    private String createdUserPassword = "wLjD5FM859ss";
    private String project = "TestProject";
    private String projectFromAPi = "ProjectFromAPI1";

    public String getProject() {
        return project;
    }

    public String getCreatedUserUsername() {
        return this.createdUserUsername;
    }

    public String getCreatedUserPassword() {
        return createdUserPassword;
    }

    public String getProjectFromAPi() {
        return projectFromAPi;
    }

    public static void main(String[] args) {
        Application app = new Application();
        System.out.println(app.createUser("jsonrpc", "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd").asString());
//        app.createProject("jsonrpc", "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd");
//        app.getUserId("jsonrpc", "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd");
//        app.linkUserToProject("jsonrpc", "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd");
//        System.out.println(app.removeUser("jsonrpc", "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd").asString());
//        System.out.println(app.getUserId("jsonrpc", "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd"));
//        app.getAllUsers("jsonrpc", "ba2f440e9674b545ee0f944cadfddf1a3bd9557d5a4b63cd7d7e045396cd");

    }

    public Response createUser(String u, String p) {

        RequestSpecification requestPost = RestAssured.given().log().all();
        requestPost.auth().basic(u, p);
        requestPost.contentType(ContentType.JSON);
        requestPost.body(
                String.format("{\"jsonrpc\":\"2.0\",\"method\":\"createUser\",\"id\":1518863034,\"params\":{\"username\":\"%s\",\"password\":\"%s\"}}", getCreatedUserUsername(), getCreatedUserPassword()));
        requestPost.header("Accept", "application/json");
        requestPost.baseUri("http://127.0.0.1/jsonrpc.php");
        return requestPost.post();
    }

    public String getUserId(String u, String p) {
        JsonNode jsonNode = null;
        try {
            RequestSpecification requestPost = RestAssured.given().log().all();
            requestPost.auth().basic(u, p);
            requestPost.contentType(ContentType.JSON);
            requestPost.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"getUserByName\",\"id\":1769674782,\"params\":{\"username\":\"%s\"}}", createdUserUsername));
            requestPost.header("Accept", "application/json");
            requestPost.baseUri("http://127.0.0.1/jsonrpc.php");
            String resString = requestPost.post().asString();
            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(resString);
            String id = jsonNode.path("result").path("id").asText();
            System.out.println(id);
        } catch (JsonProcessingException e) {
            e.getMessage();
        }
        assert jsonNode != null;
        return jsonNode.path("result").path("id").asText();
    }


    public Response removeUser(String u, String p) {

        String userId = getUserId(u, p);
        RequestSpecification requestPost = RestAssured.given().log().all();
        requestPost.auth().basic(u, p);
        requestPost.contentType(ContentType.JSON);
        requestPost.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"removeUser\",\"id\":2094191872,\"params\":{\"user_id\":\"%s\"}}", userId));
        requestPost.header("Accept", "application/json");
        requestPost.baseUri("http://127.0.0.1/jsonrpc.php");
        return requestPost.post();
    }


    public String getProjectId(String u, String p) {
        JsonNode jsonNode = null;

        try {
            RequestSpecification request = RestAssured.given().log().all();
            request.auth().basic(u, p);
            request.contentType(ContentType.JSON);
            request.header("Accept", "application/json");
            request.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"getProjectByName\",\"id\":1620253806,\"params\":{\"name\":\"%s\"}}", projectFromAPi));
            request.baseUri("http://127.0.0.1/jsonrpc.php");
            Response r = request.post();
            System.out.println(r.asString());
            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(r.asString());
            String id = jsonNode.path("result").path("id").asText();
            System.out.println(id);
        } catch (JsonProcessingException e) {
            e.getMessage();


        }
        return jsonNode.path("result").path("id").asText();
    }

    public void linkUserToProject(String u, String p) {

        String userId = getUserId(u, p);
        String projectId = getProjectId(u, p);
        RequestSpecification requestPost = RestAssured.given().log().all();
        requestPost.auth().basic(u, p);
        requestPost.contentType(ContentType.JSON);
        requestPost.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"addProjectUser\",\"id\":1294688355,\"params\":[\"%s\",\"%s\",\"project-viewer\"]}", projectId, userId));
        requestPost.header("Accept", "application/json");
        requestPost.baseUri("http://127.0.0.1/jsonrpc.php");
        requestPost.post();
        System.out.println(requestPost.post().asString());

    }

    public void createProject(String u, String p) {
        RequestSpecification requestSpecification;
        Response response;

        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"createProject\",\"id\":1797076613,\"params\":{\"name\":\"%s\"}}", projectFromAPi));
        requestSpecification.auth().basic(u, p);
        requestSpecification.header("Accept", "application/json");
        requestSpecification.baseUri("http://127.0.0.1/jsonrpc.php");
        response = requestSpecification.post();
        String responseString = response.prettyPrint();


    }

    public Response getAllUsers(String u, String p) {
        RequestSpecification requestSpecification;
        String id = getProjectId(u,p);

        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"getProjectUsers\",\"id\":1601016721,\"params\":[\"%s\"]}", id));
        requestSpecification.auth().basic(u, p);
        requestSpecification.header("Accept", "application/json");
        requestSpecification.baseUri("http://127.0.0.1/jsonrpc.php");
        return requestSpecification.post();

    }
}



