package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectById方法，查询用户为1的用户
            SysUser user = userMapper.selectById(1l);
            //user不为空
            Assert.assertNotNull(user);
            //userName=admin
            Assert.assertEquals("admin",user.getUserName());
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用selectAll方法查询所有用户
            List<SysUser> sysUsers = userMapper.selectAll();
            //结果不为空
            Assert.assertNotNull(sysUsers);
            //用户数量大于0个
            Assert.assertTrue(sysUsers.size()>0);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectRolesByUserId(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles = userMapper.selectRolesByUserId(1l);
            for (SysRole role:roles
                 ) {
                System.out.println(role.getRoleName());
            }
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size()>0);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个User对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test1@qq.com");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片存放到byte数组中
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            //将新对象插入数据库的时候，特别注意这里的返回值result实质性的SQL影响的行数
            int result = userMapper.insert(user);
            //只插入一条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配置回写的id的值
            Assert.assertNull(user.getId());
        }finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的sqlSessionFactory.openSession()不是自动提交的
            //因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
