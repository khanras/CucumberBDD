package com.uiautomation.framework.utils;

import com.uiautomation.framework.logger.CustomException;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.List;

public class ApiUtil {
    Response response;
    RequestSpecification httpRequest;
    JSONObject json = new JSONObject();

    public ApiUtil(String url) {
        RestAssured.baseURI = url;
        this.httpRequest = RestAssured.given();
    }

    public void sendHttpRequest(String requestType) {
        switch (requestType.toUpperCase()) {
            case "POST":
                this.response = this.httpRequest.request(Method.POST);
                break;
            case "PUT":
                this.response = this.httpRequest.request(Method.PUT);
                break;
            case "GET":
                this.response = this.httpRequest.request(Method.GET);
                break;
            default:
                new CustomException("Please configure authType parameter value properly. Only supported: POST/PUT/GET. But found: "+requestType);
        }
    }

    public List<Object> getResponseBodyAsListFromJsonPath(String jsonPath) {
        return this.response.getBody().jsonPath().getList(jsonPath);
    }

    public String getResponseBodyAsStringFromJsonPath(String jsonPath) {
        return this.response.getBody().jsonPath().getList(jsonPath).toString();
    }

    public String getResponseBodyAsString(){
        return this.response.getBody().asString();
    }

    public void setRequestPath(String path){
        this.httpRequest.basePath(path);
    }

    public void setRequestBody(Object body){
        this.httpRequest.body(body);
    }

    public void setRequestHeader(String header, String value){
        this.httpRequest.header(header,value,new Object[0]);
    }

    public void setRequestHeaders(String headers, String values){
        this.httpRequest.header(headers,values,new Object[0]);
    }

    public void setRequestParam(String parameterName, String parameterValue){
        this.httpRequest.param(parameterName,new Object[]{parameterValue});
    }

    public void setRequestParams(String parameterNames, String parameterValues){
        this.httpRequest.params(parameterNames,parameterValues,new Object[0]);
    }

    public void setRequestAuthentication(String authType,String username, String password){
        switch (authType.toUpperCase()){
            case "BASIC":
                this.httpRequest.auth().basic(username,password);
                break;
            case "DIGEST":
                this.httpRequest.auth().digest(username,password);
                break;
            default:
                new CustomException("Please configure authType parameter value properly. Only supported: BASIC/DIGEST. But found: "+authType);
        }
    }

    public void setRequestToAuth(String authType, String consumerKey,String consumerSecret,String accessToken, String secretToken){
        switch (authType.toUpperCase()){
            case "OAUTH1":
                this.httpRequest.auth().oauth(consumerKey,consumerSecret,accessToken,secretToken);
                break;
            case "OAUTH2":
                this.httpRequest.auth().oauth2(accessToken);
                break;
            default:
                new CustomException("Please configure authType parameter value properly. Only supported: OAUTH1/OAUTH2. But found: "+authType);
        }
    }

    public boolean validateStatusCode(int statusCode){
        boolean status = false;
        int actualStatusCode = this.response.getStatusCode();
        if (actualStatusCode == statusCode){
            status = true;
        }
        return status;
    }

    public boolean validateStatusLine(String statusLine){
        boolean status = false;
        String actualStatusLine = this.response.getStatusLine();
        if (actualStatusLine.equals(statusLine)){
            status = true;
        }
        return status;
    }
}
