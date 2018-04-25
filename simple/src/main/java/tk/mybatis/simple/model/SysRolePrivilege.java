package tk.mybatis.simple.model;

/**
 * 角色权限关联表
 */
public class SysRolePrivilege {
    private Long roleId;//角色ID
    private Long privilegeId;//权限ID

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }
}
