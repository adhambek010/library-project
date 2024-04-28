package com.example.library.weblibrary.book.enums;

public enum BookStatus {
    AVAILABLE,
    ON_LOAN,
    RESERVED,
    LOST,
    DAMAGED,
    IN_PROCESS, // Being processed for addition or maintenance
    DISCARDED, // Book is no longer in the library's collection
    ARCHIVED,  // Book is in the library's archives
    RESTRICTED; // Book has restricted access
}
