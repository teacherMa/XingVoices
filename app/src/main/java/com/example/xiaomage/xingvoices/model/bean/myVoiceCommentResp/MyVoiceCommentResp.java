package com.example.xiaomage.xingvoices.model.bean.myVoiceCommentResp;

import java.util.List;

/**
 * Created by xiaomage on 2017/5/26.
 */

public class MyVoiceCommentResp {
    /**
     * vid : PGAC0rVDo4E
     * title : hhh
     * comments : [{"user":"柏武","headpic":"http://wx.qlogo.cn/mmopen/AAl6RUmA2UZW8icnicJVnLeFLuJ8zmicw1EUSVuGpIgPJicS7JAkjQqkN6sR1Daicclsjbuff0XeAiaM8w27Bl0aSDEHh8omqcljvL/0","content":"还有吗?"},{"user":"卓义茜","headpic":"http://wx.qlogo.cn/mmopen/AAl6RUmA2UZW8icnicJVnLeFLuJ8zmicw1EUSVuGpIgPJicS7JAkjQqkN6sR1Daicclsjbuff0XeAiaM8w27Bl0aSDEHh8omqcljvL/0","content":"声音好sex!\r"},{"user":"费东","headpic":"http://wx.qlogo.cn/mmopen/AAl6RUmA2UZW8icnicJVnLeFLuJ8zmicw1EUSVuGpIgPJicS7JAkjQqkN6sR1Daicclsjbuff0XeAiaM8w27Bl0aSDEHh8omqcljvL/0","content":""},{"user":"胥韦雁","headpic":"http://wx.qlogo.cn/mmopen/AAl6RUmA2UZW8icnicJVnLeFLuJ8zmicw1EUSVuGpIgPJicS7JAkjQqkN6sR1Daicclsjbuff0XeAiaM8w27Bl0aSDEHh8omqcljvL/0","content":"非常动听！\r"},{"user":"龚竹朋","headpic":"http://wx.qlogo.cn/mmopen/AAl6RUmA2UZW8icnicJVnLeFLuJ8zmicw1EUSVuGpIgPJicS7JAkjQqkN6sR1Daicclsjbuff0XeAiaM8w27Bl0aSDEHh8omqcljvL/0","content":""},{"user":"陶茂","headpic":"http://wx.qlogo.cn/mmopen/dUyqaiaxg9oNia7YOfjG68LgnAqsna5bicmCIEBia3nKyf4vk3aTHw3zh88icLMyhk3HwUbicDwPMdvgFAoOo2uLEYW3EYDT9LTCNy/0","content":""},{"user":"公孙玛蓉","headpic":"http://wx.qlogo.cn/mmopen/AAl6RUmA2UZW8icnicJVnLeFLuJ8zmicw1EUSVuGpIgPJicS7JAkjQqkN6sR1Daicclsjbuff0XeAiaM8w27Bl0aSDEHh8omqcljvL/0","content":""},{"user":"云澜俊","headpic":"http://wx.qlogo.cn/mmopen/AAl6RUmA2UZW8icnicJVnLeFLuJ8zmicw1EUSVuGpIgPJicS7JAkjQqkN6sR1Daicclsjbuff0XeAiaM8w27Bl0aSDEHh8omqcljvL/0","content":"很好！！！\r"},{"user":"蔡顺","headpic":"http://wx.qlogo.cn/mmopen/dUyqaiaxg9oNia7YOfjG68LgnAqsna5bicmCIEBia3nKyf4vk3aTHw3zh88icLMyhk3HwUbicDwPMdvgFAoOo2uLEYW3EYDT9LTCNy/0","content":"声音好棒！\r"}]
     */

    private String vid;
    private String title;
    private List<CommentsBean> comments;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

}
