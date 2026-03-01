package com.yx.dao;

import com.yx.po.ReaderInfo;
import com.yx.po.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReaderInfoMapper {

    /**
     * 删除读者信息
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入读者信息
     */
    int insert(ReaderInfo record);

    /**
     * 选择性插入读者信息
     */
    int insertSelective(ReaderInfo record);

    /**
     * 根据主键查询读者信息
     */
    ReaderInfo selectByPrimaryKey(Integer id);

    /**
     * 选择性更新读者信息
     */
    int updateByPrimaryKeySelective(ReaderInfo record);

    /**
     * 更新读者信息
     */
    int updateByPrimaryKey(ReaderInfo record);

    /**
     * 查询所有记录信息
     */
    List<ReaderInfo> queryAllReaderInfo(@Param("readerInfo") ReaderInfo readerInfo);

    /**
     * 根据用户名和密码查询用户信息
     */
    ReaderInfo queryUserInfoByNameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据ID集合删除读者信息
     */
    int deleteByIds(@Param("ids") List<String> ids);
    // 根据图书名称查询图书
    List<BookInfo> searchBooksByName(@Param("bookName") String bookName);

    // 处理图书预约
    boolean reserveBook(@Param("userId") Integer userId, @Param("bookId") String bookId);
}
