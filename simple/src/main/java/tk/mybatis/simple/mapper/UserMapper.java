package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

public interface UserMapper {
    /**
     * 通过ID查询用户
     */
    SysUser selectById(Long id);
    /**
     * 查询全部用户
     */
    List<SysUser> selectAll();
    /**
     * 根据用户的ID获取角色信息
     */
    List<SysRole> selectRolesByUserId(Long userId);
    /**
     * 插入用户
     */
    int insert(SysUser sysUser);
}
