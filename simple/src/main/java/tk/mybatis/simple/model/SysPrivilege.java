package tk.mybatis.simple.model;

/**
 * 权限表
 */
public class SysPrivilege {
    private Long id;//权限id
    private String privilegeName;//权限名称
    private String privilegeUrl;//权限URL

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeUrl() {
        return privilegeUrl;
    }

    public void setPrivilegeUrl(String privilegeUrl) {
        this.privilegeUrl = privilegeUrl;
    }
}
