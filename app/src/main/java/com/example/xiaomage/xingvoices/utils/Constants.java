package com.example.xiaomage.xingvoices.utils;

/**
 * Class to place the static value .
 * <p>
 */

public class Constants {

    public static String BASE_URL = "";


    public interface ContentType {
        String
                JSON = "application/json",
                PNG = "image/png",
                JPEG = "image/jpeg",
                FROM_DATA = "multipart/form-data";
    }

    public interface AppSign {
        String
                K_CONTENT_TYPE = "Content-Type",
                V_CONTENT_TYPE = "application/json";
    }

    public interface ResponseCode {
        int
                SUCCESS = 1;
    }

    public interface ResponseError {
        String
                UNKNOWN = "Unknown Error";
    }

    public interface ResultCode {
        int
                LOCAL = 1024,
                REMOTE = 1025;
    }

    public interface RecordState{
        int PREPARE_RECORD = 0;
        int IS_RECORDING = 1;
        int PREPARE_AUDITION = 2;
        int IS_AUDITION = 3;
    }
}
