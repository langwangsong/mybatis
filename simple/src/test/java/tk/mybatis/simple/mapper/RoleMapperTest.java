package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.type.Enabled;

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
    public void testUpdateById(){
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //先查询出角色，然后修改角色的enabled的值为disabled
            SysRole sysRole = roleMapper.selectById(2L);
            Assert.assertEquals(Enabled.enabled,sysRole.getEnabled());
            sysRole.setEnabled(Enabled.disabled);
            roleMapper.updateById(sysRole);

        }finally {
            sqlSession.rollback();
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
    @Test
    public void testSelectRoleByUserIdChoose(){
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //由于数据库中数据enabled都为1，所以给其中一个角色的enabled赋值为0
            SysRole role = roleMapper.selectById(2L);
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
            //获取用户1的角色
            List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
            for (SysRole r: roleList
                 ) {
                System.out.println("角色名："+r.getRoleName());
                if(r.getId().equals(1L)){
                    //第一个角色存在权限信息
                    Assert.assertNotNull(r.getPrivilegeList());
                }else if(r.getId().equals(2L)){
                    //第二个角色的权限为null
                    Assert.assertNull(r.getPrivilegeList());
                    continue;
                }
                for (SysPrivilege privilege:r.getPrivilegeList()
                     ) {
                    System.out.println("权限名："+privilege.getPrivilegeName());
                }
            }
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
