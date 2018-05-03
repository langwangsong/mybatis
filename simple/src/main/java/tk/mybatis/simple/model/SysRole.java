package tk.mybatis.simple.model;

import tk.mybatis.simple.type.Enabled;

import java.util.Date;
import java.util.List;

/**
 * 角色表
 */
public class SysRole {
    private Long id;//角色ID
    private String roleName;//角色名
    //private  Integer enabled;//有效状态
    private  Long createBy;//创建人
    private Date createTime;//创建时间
    //有效标志
    private Enabled enabled;

    public void setEnabled(Enabled enabled) {
        this.enabled = enabled;
    }

    public Enabled getEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

//    public Integer getEnabled() {
//        return enabled;
//    }

//    public void setEnabled(Integer enabled) {
//        this.enabled = enabled;
//    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 角色包含的权限列表
     */
    private List<SysPrivilege> privilegeList;

    public List<SysPrivilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<SysPrivilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    /**
     * 创建信息
     */
    private CreateInfo createInfo;

    public CreateInfo getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(CreateInfo createInfo) {
        this.createInfo = createInfo;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", enabled=" + enabled +
                ", privilegeList=" + privilegeList +
                ", createInfo=" + createInfo +
                '}';
    }
}
