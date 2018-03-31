package org.jit.dcg.dto;

public class UserDto {
    private Integer id;

    private String name;            //用户名

    private String pwd;             //用户密码

    private String imagepath;       //用户头像

    private String roleid;          //用户角色

    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public UserDto() {

    }

    public UserDto(Integer id, String name, String pwd, String imagepath, String roleid, String state) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.imagepath = imagepath;
        this.roleid = roleid;
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", imagepath='" + imagepath + '\'' +
                ", roleid='" + roleid + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}