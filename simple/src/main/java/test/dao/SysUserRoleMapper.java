package test.dao;

import java.util.List;
import test.model.SysUserRole;

public interface SysUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbggenerated Thu Apr 26 19:58:25 CST 2018
     */
    int insert(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbggenerated Thu Apr 26 19:58:25 CST 2018
     */
    List<SysUserRole> selectAll();
}