package com.lin.DButil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by 言溪 on 2017/9/19.
 */
public class DbAccess {
    public SqlSession getSqlSession() throws IOException {
        //通过配置文件得到Reader对象
        Reader reader=Resources.getResourceAsReader("com/lin/config/Configuration.xml");
        //通过reader获取SqlSessionFactory
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
        //通过SqlSessionFactory打开一个数据库会话
        SqlSession sqlSession=factory.openSession();
        return sqlSession;
    }
}
