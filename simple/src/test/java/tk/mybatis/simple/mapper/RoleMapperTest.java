package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

public class RoleMapperTest extends BaseMapperTest{
    @Test
    public void testSelectById(){
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用selectById方法，查询Id=1的角色
            SysRole sysRole = roleMapper.selectById(1L);
            //role不能为空
            Assert.assertNotNull(sysRole);

            //roleName=管理员
            Assert.assertEquals("管理员",sysRole.getRoleName());
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectAllRoleAndPrivileges(){
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
            for (SysRole role: roleList
                 ) {
                System.out.println(role.getRoleName());
                for (SysPrivilege privilege:role.getPrivilegeList()
                     ) {
                    System.out.println(privilege.getPrivilegeName());
                }
            }
        }finally {
            sqlSession.close();
        }
    }

}
