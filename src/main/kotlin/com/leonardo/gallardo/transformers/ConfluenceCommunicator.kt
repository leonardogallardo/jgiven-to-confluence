package com.leonardo.gallardo.transformers

import com.leonardo.gallardo.util.ResourceLoader
import io.restassured.RestAssured
import io.restassured.RestAssured.given

class ConfluenceCommunicator {

    companion object {
        val AUTH_TYPE = "os_authType"
        val BASIC = "basic"
        val OS_USERNAME = "os_username"
        val OS_PASSWORD = "os_password"
        val CONTENT_TYPE = "Content-Type"
        val APPLICATION_JSON = "application/json"
        val VERSION = "#(version)"
        val CONTENT = "#(content)"
        val JGIVEN2PROP = "jgiven2confluence.properties"
    }

    fun sendToConfluence(htmlMinified: String) {

        RestAssured.useRelaxedHTTPSValidation()
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        val properties = ResourceLoader().loadProperties(JGIVEN2PROP)
        val url = properties.getProperty("url")
        val username = properties.getProperty("username")
        val password = properties.getProperty("password")
        val page = properties.getProperty("pageNumber")

        val actualVersion = getCurrentPageVersion(url, username, password, page)
        updatePage(htmlMinified, actualVersion, url, username, password, page)
    }

    private fun updatePage(
        htmlMinified: String,
        actualVersion: Int,
        url: String,
        username: String,
        password: String,
        page: String
    ) {
        given()
            .param(AUTH_TYPE, BASIC)
            .param(OS_USERNAME, username)
            .param(OS_PASSWORD, password)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .body(prepareBody(htmlMinified, actualVersion))
            .`when`()
            .put("$url$page")
            .then()
            .statusCode(200)
    }

    private fun prepareBody(htmlMinified: String, actualVersion: Int) =
        ResourceLoader().loadAsString("payload.json").replace(CONTENT, htmlMinified).replace(
            VERSION,
            "" + (1 + actualVersion)
        )

    private fun getCurrentPageVersion(url: String, username: String, password: String, page: String): Int {
        return given()
            .param("expand", "version")
            .param(AUTH_TYPE, BASIC)
            .param(OS_USERNAME, username)
            .param(OS_PASSWORD, password)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .`when`()
            .get("$url$page")
            .then()
            .statusCode(200)
            .extract().body().jsonPath().getInt("version.number")
    }

}
