package org.jit.dcg.dto;

public class FileDto {

    private String filenewname;         //文件上传到服务器后的新名字

    private String fileowner;           //文件的归属人

    private String fileurl;             //文件上传到服务器上的路径

    private String filetime;            //文件上传的时间

    private String fileoriginalname;    //文件原来的名字

    private String fileStatus;          //文件的状态

    public String getFilestatus() {
        return fileStatus;
    }

    public void setFilestatus(String filestatus) {
        this.fileStatus = filestatus == null ? null : filestatus.trim();
    }

    public String getFilenewname() {
        return filenewname;
    }

    public void setFilenewname(String filenewname) {
        this.filenewname = filenewname == null ? null : filenewname.trim();
    }

    public String getFileowner() {
        return fileowner;
    }

    public void setFileowner(String fileowner) {
        this.fileowner = fileowner == null ? null : fileowner.trim();
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl == null ? null : fileurl.trim();
    }

    public String getFiletime() {
        return filetime;
    }

    public void setFiletime(String filetime) {
        this.filetime = filetime == null ? null : filetime.trim();
    }

    public String getFileoriginalname() {
        return fileoriginalname;
    }

    public void setFileoriginalname(String fileoriginalname) {
        this.fileoriginalname = fileoriginalname == null ? null : fileoriginalname.trim();
    }

    public FileDto(String filenewname, String fileowner, String fileurl, String filetime, String fileoriginalname, String fileStatus) {
        this.filenewname = filenewname;
        this.fileowner = fileowner;
        this.fileurl = fileurl;
        this.filetime = filetime;
        this.fileoriginalname = fileoriginalname;
        this.fileStatus = fileStatus;
    }

    public FileDto() {
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "filenewname='" + filenewname + '\'' +
                ", fileowner='" + fileowner + '\'' +
                ", fileurl='" + fileurl + '\'' +
                ", filetime='" + filetime + '\'' +
                ", fileoriginalname='" + fileoriginalname + '\'' +
                '}';
    }
}