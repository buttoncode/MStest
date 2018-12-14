package com.buttoncode.mstest.admin.web.utils;

import javax.servlet.http.HttpServletRequest;

public class WebInfoUtils {

    private WebInfoUtils()
    {
    }
    public static final String FILE_INFO_INFO = "info";
    public static final String FILE_INFO_SUCCESS = "success";
    public static final String FILE_INFO_WARNING = "warning";
    public static final String FILE_INFO_ERROR = "error";

    public static final String FILE_INFO_INFO_SUCCESS = "Success - Poprawnie zaladowane pliki: ";
    public static final String FILE_INFO_INFO_SUCCESS_INFOSAP = "Zaktualizowano karty stanowiskowe pranownikom (NumerSAP): ";

    public static final String FILE_INFO_ERROR_BAD_FORMAT = "Error 1032 - Bladny format pliku: ";

    public static final String FILE_INFO_SUCCESS_TASKBOOK = "";

    public static final String FILE_FORMAT_PDF = ".pdf";

    public static String getURLWithContextPath(HttpServletRequest request)
    {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    }
}
