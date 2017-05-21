package com.example.xiaomage.xingvoices.utils;

/**
 * Class to place the static value .
 * <p>
 */

public class Constants {

    public static String BASE_URL = "https://www.starsound.xyz/yuliao/index.php/api/";
    public static String WEXIN_BASE_URL = "https://api.weixin.qq.com/sns/";
    public static String WEXIN_HEAD_PIC = "http://wx.qlogo.cn/mmopen/";
    public static String PIC_API;

    public interface WxParamValue {
        String APP_ID = "wxc988255a4b16c8cf";
        String APP_SECERT = "16670d3a33f0a7c6b64a8d20a33e2204";
        String NULL_RESP = "null resp";
        String GRANT_TYPE = "authorization_code";
    }

    public interface WxApi {
        String ACCESS_TOKEN = "oauth2/access_token";
        String USER_INFO = "userinfo";
        String PIC_API = "";
    }

    public interface WxRequestParam {
        String APP_ID = "appid";
        String APP_SECRET = "secret";
        String CODE = "code";
        String GRANT_TYPE = "grant_type";
        String ACCESS_TOKEN = "access_token";
        String OPEN_ID = "openid";
    }

    public interface XingVoicesApi {
        String LOGIN = "login";
        String GET_USER = "getUserInfo";
        String ALL_VOICE = "allVoice";
        String MY_FOLLOW = "myFocus";
        String MY_COLLECTION = "myFavorites";
        String VOICE_TO_COMMENTS = "voiceToComments";
    }

    public interface XingVoicesRequestParam {
        String CHANNEL = "channel";
        String OPEN_ID = "openid";
        String NICKNAME = "nickname";
        String SEX = "sex";
        String HEAD_PIC = "headpic";
        String UID = "uid";
        String CID = "cid";
        String VID = "vid";
        String TYPE = "type";
    }

    public interface XingVoicesParamValue {
        String CHANNEL = "APP";
        int MAN = 1;
        int WOMEN = 0;
    }

    public interface VoiceType {
        String POPULAR = "popular";
        String FOLLOW = "follow";
        String COLLECTION = "collection";
    }

    public interface CommentType {
        int VOICE = 1;
        int TEXT = 0;
    }

    public interface CommentItemType {
        int SIMPLE = 0;
        int TOTAL = 1;
    }

    public interface FollowType {
        int UnFollow = 0;
        int Followed = 1;
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

    public interface RecordState {
        int PREPARE_RECORD = 0;
        int IS_RECORDING = 1;
        int PREPARE_AUDITION = 2;
        int IS_AUDITION = 3;
    }

    public interface FileName {
        String HEAD_PIC = "headpic";
    }

    public interface BottomMenuItem {
        int COMMENT = 0;
        int COLLECTION = 1;
        int SHARE = 2;
        int LOOK_UP_PIC = 3;
        int ADD_TO_BLACK_LIST = 4;
    }

    /**
     * BottomMenu的点击事件有可能需要再往外传递给PopularView，RecyclerView的Item点击事件也需要传递
     * 给PopularView，可能发生冲突，所以这里从10开始赋值
     */
    public interface MainPopularItem {
        int FOLLOW = 10;
    }
}
