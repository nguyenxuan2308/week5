package com.xuan.diary.common.response;

public enum ResponseCode {

    /*
     * =======================================
     * ===== 2xx - SUCCESS =====
     * =======================================
     */

    /**
     * {@code 200 OK}.
     *  @see <a href="http://tools.ietf.org/html/rfc7231#section-6.2.1">HTTP/1.1:
     */
    R_200,

    /**
     * {@code 201 Created}.
     */
    R_201,

    /*
     * =======================================
     * ===== 4xx - CLIENT REQUEST ERROR =====
     * =======================================
     */
    /**
     * {@code 304 Not Modified}.
     */
    R_304,

    /**
     * {@code 400 Bad Request}.
     */
    R_400,

    /**
     * {@code 401 Unauthorized}.
     */
    R_401,

    /**
     * {@code 402 Payment Required}.>
     */
    R_402,

    /**
     * {@code 403 Forbidden}.
     */
    R_403,

    /**
     * {@code 404 Not Found}.
     */
    R_404,

    /**
     * {@code 405 Method Not Allowed}.
     */
    R_405,

    /*
     * =======================================
     * ===== 5xx - SERVER ERROR =====
     * =======================================
     */

    /**
     * {@code 500 Internal Server Error}.
     */
    R_500
}
