package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;

public class PrivilegeMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try {
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            //调用selectById方法，查询id=1的权限
            SysPrivilege sysPrivilege = privilegeMapper.selectById(1L);
            //privilege不为空
            Assert.assertNotNull(sysPrivilege);
            //privilegeName=用户管理
            Assert.assertEquals("用户管理",sysPrivilege.getPrivilegeName());
        }finally {
            sqlSession.close();
        }
    }
}
