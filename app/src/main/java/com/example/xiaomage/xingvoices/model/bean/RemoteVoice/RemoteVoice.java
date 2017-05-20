package com.example.xiaomage.xingvoices.model.bean.RemoteVoice;

import java.util.List;

/**
 * Created by xiaomage on 2017/5/2.
 * 这里remoteVoice没有包含评论，是因为评论可能获取不到，改为在每一个item里获取
 */

public class RemoteVoice {
    /**
     * vid : I2QNJbVE98NawKq
     * is_hot : 1
     * title : 拥有不平凡
     * reurl : http://app.starsound.xyz/user/qnskIbhlcB38/file/20170417/h4ie112hv14odauk67ki.mp3
     * length : 60
     * play_num : 65
     * fav_num : 0
     * allbackgrund : http://app.starsound.xyz/user/qnskIbhlcB38/image/20170417/g0ppp2m4fzb39uqrzh4x.jpeg
     * backgrund : http://app.starsound.xyz/user/qnskIbhlcB38/image/20170417/0uj7gs5s2riw3phznobh.jpeg
     * addtime : 2017-04-17 15:46:48
     * comment_c : [{"cid":"d1jDH7Ouzop","user":"Elsa","uid":"qnskIbhlcB38","headpic":"http://wx.qlogo.cn/mmopen/dUyqaiaxg9oOdp4Zbql8P2Gicf6UaVZHf0bk27VbXDvvwm4BlibDnIcPyPKXsCMvW2B1Nzn7srvZP32jACReBcyDoib2EEn2YFIk/0","content":"http://app.starsound.xyz/user/qnskIbhlcB38/file/20170417/fhecqq9g3b1i4xvi0tll.mp3","zan":1,"clength":5,"is_zan":0}]
     * comment_t : [{"cid":"i3uKJXzoHERg","uid":"NLWifdJubeRhSM","user":"贺娟勇","headpic":"http://wx.qlogo.cn/mmopen/6FHibL3EiaibQFp7Ihyd7cYc5ozfDt0fWehT3URP08rgTicTj4gy87yXiab7bZdrlvIRK0rsw9KMClAIyTGFCchXJHicL4ggbfvCf0/0","content":"声音好棒！\r","zan":1,"clength":0,"is_zan":0},{"cid":"Um1AkSDjxlIZU8","uid":"qnskIbhlcB38","user":"Elsa","headpic":"http://wx.qlogo.cn/mmopen/dUyqaiaxg9oOdp4Zbql8P2Gicf6UaVZHf0bk27VbXDvvwm4BlibDnIcPyPKXsCMvW2B1Nzn7srvZP32jACReBcyDoib2EEn2YFIk/0","content":"喜欢我们可以关注一下。之后会和大家分享一些穿搭经验","zan":1,"clength":0,"is_zan":0},{"cid":"5r44I7ZQsZehIkR","uid":"gcWrNwFGgxcVdC","user":"陆钧霭","headpic":"http://wx.qlogo.cn/mmopen/6FHibL3EiaibQFp7Ihyd7cYc5ozfDt0fWehT3URP08rgTicTj4gy87yXiab7bZdrlvIRK0rsw9KMClAIyTGFCchXJHicL4ggbfvCf0/0","content":"声音好sex!\r","zan":0,"clength":0,"is_zan":0},{"cid":"PtWrFhGnhVSQ","uid":"rHFfimrMkgEl","user":"熊丽儿","headpic":"http://wx.qlogo.cn/mmopen/6FHibL3EiaibQFp7Ihyd7cYc5ozfDt0fWehT3URP08rgTicTj4gy87yXiab7bZdrlvIRK0rsw9KMClAIyTGFCchXJHicL4ggbfvCf0/0","content":"还有吗?","zan":0,"clength":0,"is_zan":0},{"cid":"vv2TVLCsewXKP1","uid":"wvSrjKcDeauyJ","user":"缪凤航","headpic":"http://wx.qlogo.cn/mmopen/AAl6RUmA2UZW8icnicJVnLeFLuJ8zmicw1EUSVuGpIgPJicS7JAkjQqkN6sR1Daicclsjbuff0XeAiaM8w27Bl0aSDEHh8omqcljvL/0","content":"非常动听！\r","zan":0,"clength":0,"is_zan":0},{"cid":"OYG759MzWuLCYgQ","uid":"zpKhYZMPiJ","user":"扶琦","headpic":"http://wx.qlogo.cn/mmopen/6FHibL3EiaibQFp7Ihyd7cYc5ozfDt0fWehT3URP08rgTicTj4gy87yXiab7bZdrlvIRK0rsw9KMClAIyTGFCchXJHicL4ggbfvCf0/0","content":"很好！！！\r","zan":0,"clength":0,"is_zan":0}]
     * type : {"tid":"7182896105","title":"个人"}
     * is_focus : 0
     * user : {"uid":"qnskIbhlcB38","nickname":"Elsa","headpic":"http://wx.qlogo.cn/mmopen/dUyqaiaxg9oOdp4Zbql8P2Gicf6UaVZHf0bk27VbXDvvwm4BlibDnIcPyPKXsCMvW2B1Nzn7srvZP32jACReBcyDoib2EEn2YFIk/0"}
     */

    private String vid;
    private int is_hot;
    private String title;
    private String reurl;
    private int length;
    private int play_num;
    private int fav_num;
    private String allbackgrund;
    private String backgrund;
    private String addtime;
    private TypeBean type;
    private int is_focus;
    private UserBean user;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReurl() {
        return reurl;
    }

    public void setReurl(String reurl) {
        this.reurl = reurl;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPlay_num() {
        return play_num;
    }

    public void setPlay_num(int play_num) {
        this.play_num = play_num;
    }

    public int getFav_num() {
        return fav_num;
    }

    public void setFav_num(int fav_num) {
        this.fav_num = fav_num;
    }

    public String getAllbackgrund() {
        return allbackgrund;
    }

    public void setAllbackgrund(String allbackgrund) {
        this.allbackgrund = allbackgrund;
    }

    public String getBackgrund() {
        return backgrund;
    }

    public void setBackgrund(String backgrund) {
        this.backgrund = backgrund;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public TypeBean getType() {
        return type;
    }

    public void setType(TypeBean type) {
        this.type = type;
    }

    public int getIs_focus() {
        return is_focus;
    }

    public void setIs_focus(int is_focus) {
        this.is_focus = is_focus;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class TypeBean {
        /**
         * tid : 7182896105
         * title : 个人
         */

        private String tid;
        private String title;

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class UserBean {
        /**
         * uid : qnskIbhlcB38
         * nickname : Elsa
         * headpic : http://wx.qlogo.cn/mmopen/dUyqaiaxg9oOdp4Zbql8P2Gicf6UaVZHf0bk27VbXDvvwm4BlibDnIcPyPKXsCMvW2B1Nzn7srvZP32jACReBcyDoib2EEn2YFIk/0
         */

        private String uid;
        private String nickname;
        private String headpic;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }
    }
}
