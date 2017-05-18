package com.example.xiaomage.xingvoices.utils;

/**
 * Class to place the static value .
 * <p>
 */

public class Constants {

    public static String BASE_URL = "https://www.starsound.xyz/yuliao/index.php/api/";
    public static String WEXIN_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/";
    public static String WEXIN_USER_URL = "https://api.weixin.qq.com/sns/";

    public interface WxLogin{
        String APP_ID = "wxc988255a4b16c8cf";
        String APP_SECERT = "16670d3a33f0a7c6b64a8d20a33e2204";
        String NULL_RESP = "null resp";
        String GRANT_TYPE = "authorization_code";
    }

    public interface WxApi{
        String ACCESS_TOKEN = "access_token";
        String USER_INFO = "userinfo";
    }

    public interface RequestParam{
        String APP_ID = "appid";
        String APP_SECRET = "secret";
        String CODE = "code";
        String GRANT_TYPE = "grant_type";
        String ACCESS_TOKEN = "access_token";
        String OPEN_ID = "openid";
    }

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
                DATA_EMPTY = "Data Null",
                SERVER_ERROR = "Server Error",
                NO_MORE = "No More Data",
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

    public interface BottomMenuItem{
        int COMMENT = 0;
        int COLLECTION = 1;
        int SHARE = 2;
        int LOOK_UP_PIC = 3;
        int ADD_TO_BLACK_LIST = 4;
    }

    /**
     *BottomMenu的点击事件有可能需要再往外传递给PopularView，RecyclerView的Item点击事件也需要传递
     * 给PopularView，可能发生冲突，所以这里从10开始赋值
     *
     * */
    public interface MainPopularItem{
        int FOLLOW = 10;
    }
}
