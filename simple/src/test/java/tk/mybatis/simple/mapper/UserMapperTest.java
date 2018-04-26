package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.*;

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
    @Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个User对象
            SysUser user = new SysUser();
            user.setUserName("test2");
            user.setUserPassword("123456");
            user.setUserEmail("test1@qq.com");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片存放到byte数组中
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            //将新对象插入数据库的时候，特别注意这里的返回值result实质性的SQL影响的行数
            int result = userMapper.insert2(user);
            //只插入一条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配置回写的id的值
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
        }finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的sqlSessionFactory.openSession()不是自动提交的
            //因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    @Test
    public void testInsert3(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个User对象
            SysUser user = new SysUser();
            user.setUserName("test3");
            user.setUserPassword("123456");
            user.setUserEmail("test1@qq.com");
            user.setUserInfo("test info");
            //正常情况下应该读入一张图片存放到byte数组中
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            //将新对象插入数据库的时候，特别注意这里的返回值result实质性的SQL影响的行数
            int result = userMapper.insert3(user);
            //只插入一条数据
            Assert.assertEquals(1,result);
            //id为null，没有给id赋值，并且没有配置回写的id的值
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
        }finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的sqlSessionFactory.openSession()不是自动提交的
            //因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询一个User对象
            SysUser user = userMapper.selectById(1L);
            //当前userName为admin
            Assert.assertEquals("admin",user.getUserName());
            //修改用户名
            user.setUserName("admin_test");
            //修改邮箱
            user.setUserEmail("testUpdate@qq.com");
            //更新数据，特别注意，这里的返回值result 是执行的SQL影响行数
            int result = userMapper.updateById(user);
            //只更新一条数据
            Assert.assertEquals(1,result);
            //根据当前id查询修改后的数据
            user = userMapper.selectById(1L);
            //修改后的名字是admin_test
            Assert.assertEquals("admin_test",user.getUserName());
        }finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的sqlSessionFactory.openSession()不是自动提交的
            //因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询一个User对象，根据id=1查询
            SysUser user = userMapper.selectById(1L);
            //现在还能查出
            Assert.assertNotNull(user);
            //调用方法删除
            Assert.assertEquals(1,userMapper.deleteById(user.getId()));
            //再次查询，这时应该没有值，为null
            Assert.assertNull(userMapper.selectById(1L));
            //使用SysUser参数再次进行一次测试，根据id=1001查询
            SysUser user2 = userMapper.selectById(1001L);
            //现在还能查询出user对象
            Assert.assertNotNull(user2);
            //调用方法删除，注意这次的参数为user2
            Assert.assertEquals(1,userMapper.deleteById(user2));
            //再次查询，应该没有值，为null
            Assert.assertNull(userMapper.selectById(1001L));
        }finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的sqlSessionFactory.openSession()不是自动提交的
            //因此不手动执行commit也不会提交到数据库
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用testSelectRolesByUserIdAndRoleEnabled方法查询用户角色
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1L,1);
            //结果不为空
            Assert.assertNotNull(roleList);
            //角色个数大于0
            Assert.assertTrue(roleList.size()>0);
            for (SysRole role:roleList
                 ) {
                System.out.println(role.getRoleName());
            }
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectByUser(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size()>0);
            //只查询用户邮箱
            query = new SysUser();
            query.setUserEmail("test@qq.com");
            userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size()>0);
            //当同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@qq.com");
            userList = userMapper.selectByUser(query);
            //由于没有同时满足两个条件的用户，因此查询的结果为0
            Assert.assertTrue(userList.size()==0);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            SysUser user = new SysUser();
            //更新 id=1 的用户
            user.setId(1L);
            //修改邮箱
            user.setUserEmail("test@qq.com");
            //更新邮箱，特别注意，这里的返回值是影响的行数
            int result = userMapper.updateByIdSelective(user);
            //只更新一条数据
            Assert.assertEquals(1,result);
            //根据当前的id查询被修改的数据
            user = userMapper.selectById(1L);
            //修改后的名字保持不变，但是邮箱变成了新的
            Assert.assertEquals("admin",user.getUserName());
            Assert.assertEquals("test@qq.com",user.getUserEmail());
        }finally {
            //为了不影响其他测试，这里选择回滚
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    @Test
    public void testInsert2Selective(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setCreateTime(new Date());
            //插入数据库
            userMapper.insert2(user);
            //获得插入的这条数据
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@qq.com",user.getUserEmail());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名时
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            //当没有id时
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            //当id 和 userName都为空时
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            //业务逻辑中必须校验idList.size()>0
            List<SysUser> userList = userMapper.selectByIdList(idList);
            Assert.assertEquals(2,userList.size());

        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testInsertList(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个user对象
            List<SysUser> userList = new ArrayList<SysUser>();
            for(int i=0;i<2;i++){
                SysUser user = new SysUser();
                user.setUserName("test"+i+1);
                user.setUserPassword("123456");
                user.setUserEmail("test@qq.com");
                userList.add(user);
            }
            //将新建的对象批量插入到数据库中
            //特别注意：这里的返回值result是执行SQL的影响行数
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2,result);
            for (SysUser u: userList
                 ) {
                System.out.println(u);
            }
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map = new HashMap<String,Object>();
            //查询条件，同样也是更新字段，必须保证该值存在
            map.put("id",1L);
            //要更新的其他字段
            map.put("user_email","test@qq.com");
            map.put("user_password","654321");
            //更新数据
            userMapper.updateByMap(map);
            //根据当前的id查询修改后的数据
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("test@qq.com",user.getUserEmail());

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
