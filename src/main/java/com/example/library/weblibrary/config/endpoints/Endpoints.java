package com.example.library.weblibrary.config.endpoints;

import lombok.Data;

import java.util.Arrays;

@Data
public class Endpoints {

    public static final String[] whiteListEndpoints = {
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/userEntity/logout"
    };

    public static Boolean hasInWhiteList(String path) {
        return Arrays.stream(whiteListEndpoints).anyMatch(path::contains);
    }

    public static final String[] adminEndpoints = {
            "/api/v1/book/add"
    };
    /**
     * Admin endpoints
     */
    public static final String LOGIN_USER = "/userEntity/login";
    public static final String LOGOUT_USER = "/userEntity/logout";
    public static final String REGISTER_USER = "/userEntity/register";


    /**
     * Student endpoints
     */
    public static final String ADD_STUDENT = "/student/add";
    public static final String GET_STUDENT = "/student/get/{id}";
    public static final String UPDATE_STUDENT = "/student/update/{id}";
    public static final String GET_ALL_STUDENTS = "/student/all";
    public static final String DELETE_STUDENT = "/student/delete/{id}";

    /**
     * Books endpoints
     */
    public static final String GET_ALL_BOOKS = "/book/all";
    public static final String GET_BOOK = "/book/{id}";
    public static final String GET_BOOKS_COUNT = "/book/count/{id}";
    public static final String GET_AVAILABLE_BOOKS = "/book/available";
    public static final String GET_BORROWED_BOOKS = "/book/borrowed";
    public static final String GET_RESERVED_BOOKS = "/book/reserved";
    public static final String GET_RETURNED_BOOKS = "/book/returned";
    public static final String GET_MY_BOOKS = "/book/{studentId}/{bookId}";
    public static final String GET_BOOKS_BY_ID = "/book/id/{id}";
    public static final String GET_BOOKS_BY_TITLE = "/book/title/{title}";
    public static final String GET_BOOK_BY_AUTHOR = "/book/author/{author}";
    public static final String GET_BOOKS_BY_CHARACTER = "/book/character/{character}";
    public static final String GET_BY_CATEGORY = "/book/category/{category}";
    public static final String ADD_BOOK = "/book/add";
    public static final String UPDATE_BOOK = "/book/update/{id}";
    public static final String DELETE_BOOK = "/book/delete/{id}";

}
