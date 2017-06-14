package com.example.xiaomage.xingvoices.model.bean.Resp.uploadResp;

/**
 * Created by xiaomage on 2017/5/24.
 */

public class UploadResp {
    /**
     * status : 1
     * info : 上传成功
     * data : {"aFile":"http://app.starsound.xyz/user/TzFiVA7MIl906N/file/20170524/81yxuaf3ivgl4kuzgs9c.mp3",
     *          "bFile":"http://app.starsound.xyz/user/TzFiVA7MIl906N/file/20170524/j5i1kl4wer1bdlrozgxn.jpg",
     *          "cFile":"http://app.starsound.xyz/user/TzFiVA7MIl906N/file/20170524/robpkrthov58xe0o9i23.png"
     *          }
     */

    private int status;
    private String info;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * aFile : http://app.starsound.xyz/user/TzFiVA7MIl906N/file/20170524/81yxuaf3ivgl4kuzgs9c.mp3
         * bFile : http://app.starsound.xyz/user/TzFiVA7MIl906N/file/20170524/j5i1kl4wer1bdlrozgxn.jpg
         * cFile : http://app.starsound.xyz/user/TzFiVA7MIl906N/file/20170524/robpkrthov58xe0o9i23.png
         */

        private String aFile;
        private String bFile;
        private String cFile;

        public String getAFile() {
            return aFile;
        }

        public void setAFile(String aFile) {
            this.aFile = aFile;
        }

        public String getBFile() {
            return bFile;
        }

        public void setBFile(String bFile) {
            this.bFile = bFile;
        }

        public String getCFile() {
            return cFile;
        }

        public void setCFile(String cFile) {
            this.cFile = cFile;
        }
    }
}
