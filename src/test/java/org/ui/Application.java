package org.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.List;

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

    public Response createProject(String u, String p) {
        RequestSpecification requestSpecification;
        Response response;

        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"createProject\",\"id\":1797076613,\"params\":{\"name\":\"%s\"}}", projectFromAPi));
        requestSpecification.auth().basic(u, p);
        requestSpecification.header("Accept", "application/json");
        requestSpecification.baseUri("http://127.0.0.1/jsonrpc.php");
        return requestSpecification.post();


    }

    public Response getAllUsers(String u, String p) {
        RequestSpecification requestSpecification;
        String id = getProjectId(u, p);
        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"getProjectUsers\",\"id\":1601016721,\"params\":[\"%s\"]}", id));
        requestSpecification.auth().basic(u, p);
        requestSpecification.header("Accept", "application/json");
        requestSpecification.baseUri("http://127.0.0.1/jsonrpc.php");
        return requestSpecification.post();

    }

    public Response removeProject(String u, String p) {

        String projectId = getProjectId(u, p);
        RequestSpecification requestPost = RestAssured.given().log().all();
        requestPost.auth().basic(u, p);
        requestPost.contentType(ContentType.JSON);
        requestPost.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"removeProject\",\"id\":46285125,\"params\":{\"project_id\":\"%s\"}}", projectId));
        requestPost.header("Accept", "application/json");
        requestPost.baseUri("http://127.0.0.1/jsonrpc.php");
        return requestPost.post();
    }

    public Response createTask(String u, String p) {
        RequestSpecification requestSpecification;
        String title = "Test Task";
        String projectId = getProjectId(u, p);

        requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification
                .body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"createTask\",\"id\":1176509098,\"params\":{\"title\":\"%s\",\"project_id\": \"%s\"}}", title, projectId));
        requestSpecification.auth().basic(u, p);
        requestSpecification.header("Accept", "application/json");
        requestSpecification.baseUri("http://127.0.0.1/jsonrpc.php");
        return requestSpecification.post();

    }

    public String getTaskId(String u, String p, String id) {
//        JsonNode child = null;
        String taskId = "";
        RequestSpecification requestSpecification = RestAssured.given().log().all();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification
                .body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"getAllTasks\",\"id\":133280317,\"params\":{\"project_id\":\"%s\",\"status_id\":\"%s\"}}", id, 1));
        requestSpecification.auth().basic(u, p);
        requestSpecification.header("Accept", "application/json");
        requestSpecification.baseUri("http://127.0.0.1/jsonrpc.php");
        requestSpecification.post();

        Response r1 = requestSpecification.post();
        System.out.println(r1.asString());

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readValue(r1.asString(), JsonNode.class);
            JsonNode array = node.get("result").get(0);
            taskId = array.get("id").asText();
//            JsonNode child = node.get(result).get("id");
//            taskId= child.asText();

//            taskId = jsonNode.path("result").path("id").asText();

        } catch (JsonProcessingException e) {
            e.getMessage();


        }
        return taskId;
    }

    public Response removeTask(String u, String p, String id) {

        RequestSpecification requestPost = RestAssured.given().log().all();
        requestPost.auth().basic(u, p);
        requestPost.contentType(ContentType.JSON);
        requestPost.body(String.format("{\"jsonrpc\":\"2.0\",\"method\":\"removeTask\",\"id\":1423501287,\"params\":{\"task_id\":\"%s\"}}", id));
        requestPost.header("Accept", "application/json");
        requestPost.baseUri("http://127.0.0.1/jsonrpc.php");
        return requestPost.post();

    }
}




