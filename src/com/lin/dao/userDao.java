package com.lin.dao;

import com.lin.DButil.DbAccess;
import com.lin.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class userDao {

    public List<User> getUser(){
    List<User> users=new ArrayList<>();
        DbAccess dbAccess=new DbAccess();
        SqlSession sqlSession=null;
        try {
            sqlSession =dbAccess.getSqlSession();
            //sqlSession执行操作
            users=sqlSession.selectList("User.queryUserList");
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
        return users;
    }

    public List<User> selectUser(String name,String password){
        List<User> users=new ArrayList<>();
        DbAccess dbAccess=new DbAccess();
        SqlSession sqlSession=null;
        try {
            sqlSession =dbAccess.getSqlSession();
            //sqlSession执行操作
            User u=new User();
            u.setUserName(name);
            u.setPassword(password);
            users=sqlSession.selectList("User.queryUsers",u);
            sqlSession.commit();
            System.out.println("查询数量"+users.size());
            if(users.size()>0){
                for(int i=0;i<users.size();i++){
                    System.out.println(users.get(i).getId()+" "+users.get(i).getUserName()+" "+users.get(i).getPassword());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession!=null)
                sqlSession.close();
        }
        return users;
    }


    public void delete(int id){  //单条删除

        DbAccess dbAccess=new DbAccess();
        SqlSession sqlSession=null;
        try{
            sqlSession=dbAccess.getSqlSession();
            int a=sqlSession.delete("User.deleteOne",id);
            sqlSession.commit();
            System.out.println("删除情况："+a);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(sqlSession!=null)
            sqlSession.close();
        }
    }

    public void deleteBatch(List<String> list){ //批量删除
        DbAccess dbAccess=new DbAccess();
        SqlSession sqlSession=null;
        try{
            sqlSession=dbAccess.getSqlSession();
            int a=sqlSession.delete("User.deleteBatch",list);
            System.out.println("批量删除："+a);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }

    }

    public void insertUser(User user){
        DbAccess dbAccess=new DbAccess();
        SqlSession sqlSession=null;
        try{
            sqlSession=dbAccess.getSqlSession();
            int a=sqlSession.insert("User.insertUser",user);
            System.out.println("插入user："+a);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    public void updateUser(User user){
        DbAccess dbAccess=new DbAccess();
        SqlSession sqlSession=null;
        try{
            sqlSession=dbAccess.getSqlSession();
            int a=sqlSession.update("User.updateUser",user);
            System.out.println("修改人数："+a+"个");
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUser(){

        List<User> users=getUser();
        System.out.println("人数"+users.size());
        for(User u:users){
            System.out.println(u.getUserName());
        }
    }

    @Test
    public void testSelectUser()
    {
        selectUser("c","12");

    }

    @Test
    public void testDeleteOne(){
        //delete(4);
        List<String> list=new ArrayList<>();
        list.add("3");
        list.add("4");
        list.add("5");
        deleteBatch(list);
    }

    @Test
    public void testInsert()
    {
        User user=new User();
       // user.setId(7);
        user.setUserName("gcsscu");
        user.setPassword("123456");
        insertUser(user);
    }

    @Test
    public void testUpdate()
    {

        User u=selectUser("v","38").get(0);
        System.out.println("修改用户"+u.getId());
        u.setUserName("修改");
        updateUser(u);
    }

}
