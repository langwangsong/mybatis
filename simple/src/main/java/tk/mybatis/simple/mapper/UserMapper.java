package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
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
    /**
     * 插入用户，使用userGenerateKeys方式
     */
    int insert2(SysUser sysUser);
    /**
     * 插入用户，使用selectKey方式
     */
    int insert3(SysUser sysUser);
    /**
     * 根据主键更新
     */
    int updateById(SysUser sysUser);
    /**
     * 通过主键删除
     */
    int deleteById(Long id);
    /**
     * 通过主键删除，参数使用sysuser对象也是可以的，
     * 对应的xml方法不做任何修改
     */
    int deleteById(SysUser sysUser);
    /**
     * 根据用户id和角色的enabled状态来获取用户的角色
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(
            @Param("userId") Long userId,
            @Param("enabled") Integer enabled);
}
