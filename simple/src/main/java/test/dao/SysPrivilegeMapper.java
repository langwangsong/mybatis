package test.dao;

import java.util.List;
import test.model.SysPrivilege;

public interface SysPrivilegeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated Thu Apr 26 19:58:25 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated Thu Apr 26 19:58:25 CST 2018
     */
    int insert(SysPrivilege record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated Thu Apr 26 19:58:25 CST 2018
     */
    SysPrivilege selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated Thu Apr 26 19:58:25 CST 2018
     */
    List<SysPrivilege> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_privilege
     *
     * @mbggenerated Thu Apr 26 19:58:25 CST 2018
     */
    int updateByPrimaryKey(SysPrivilege record);
}