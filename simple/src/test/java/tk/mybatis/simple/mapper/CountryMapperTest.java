package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.CountryExample;

import java.util.List;

public class CountryMapperTest extends BaseMapperTest {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try {
            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
            for (Country country:countryList
                 ) {
                System.out.printf("%-4d%4s%4s\n",country.getId(),country.getCountryname(),country.getCountrycode());
            }
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testExample(){
        //获取SqlSession
        SqlSession sqlSession = getSqlSession();
        try{
            //获取CountryMaper接口
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //创建Example对象
            CountryExample example = new CountryExample();
            //设置排序规则
            example.setOrderByClause("id desc, countryname asc");
            //设置是否distinct去重
            example.setDistinct(true);
            //创建条件
            CountryExample.Criteria criteria = example.createCriteria();
            //id>=1
            criteria.andIdGreaterThanOrEqualTo(1);
            //id<4
            criteria.andIdLessThan(4);
            //Countrycode like '%U%'
            //最容易出错的地方，注意like 必须自己协商通配符的位置
            criteria.andCountrycodeLike("%U%");
            //or情况
            CountryExample.Criteria or = example.or();
            //countryname=中国
            or.andCountrynameLike("中国");
            //执行查询
            List<Country> countryList = countryMapper.selectByExample(example);
            for (Country c: countryList
                 ) {
                System.out.println(c);
            }
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testUpdateExampleSelective(){
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            //创建条件，只能有一个createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //更新id>2的国家
            criteria.andIdGreaterThan(2);
            //创建一个要更新的对象
            Country country = new Country();
            //将国家设置为China
            country.setCountryname("china");
            //执行查询
            countryMapper.updateByExampleSelective(country,example);
            //把符合条件的节骨输出查看
            List<Country> countryList = countryMapper.selectByExample(example);
            for (Country c: countryList
                 ) {
                System.out.println(c);
            }
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
    @Test
    public void testDeleteByExample(){
        SqlSession sqlSession = getSqlSession();
        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            //创建Example对象
            CountryExample example = new CountryExample();
            //创建条件，只能有一个createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //删除所有id>2的国家
            criteria.andIdGreaterThan(2);
            //执行查询
            countryMapper.deleteByExample(example);
            Assert.assertEquals(0,countryMapper.countByExample(example));
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
