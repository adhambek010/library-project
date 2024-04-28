package com.example.library.weblibrary.user.endpoints;

import lombok.Data;

@Data
public class Endpoints {
    /**
     * Admin endpoints
     */
    public static final String LOGIN_USER = "/user/login";
    public static final String LOGOUT_USER = "/user/logout";
    public static final String REGISTER_USER = "/user/register";


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
    public static final String GET_LOANED_BOOKS = "/book/loaned";
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
