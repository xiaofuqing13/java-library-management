package com.yx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.dao.ReaderInfoMapper;
import com.yx.po.ReaderInfo;
import com.yx.po.BookInfo;
import com.yx.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderInfoServiceImpl implements ReaderInfoService {

    @Autowired
    private ReaderInfoMapper readerInfoMapper;


    @Override
    public PageInfo<ReaderInfo> queryAllReaderInfo(ReaderInfo readerInfo, Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<ReaderInfo> list = readerInfoMapper.queryAllReaderInfo(readerInfo);
        return new PageInfo<>(list);
    }

    @Override
    public void addReaderInfoSubmit(ReaderInfo readerInfo) {
        readerInfoMapper.insert(readerInfo);
    }

    @Override
    public ReaderInfo queryReaderInfoById(Integer id) {
        return readerInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateReaderInfoSubmit(ReaderInfo readerInfo) {
        readerInfoMapper.updateByPrimaryKeySelective(readerInfo);
    }

    @Override
    public void deleteReaderInfoByIds(List<String> ids) {
        readerInfoMapper.deleteByIds(ids);
    }

    @Override
    public ReaderInfo queryUserInfoByNameAndPassword(String username, String password) {
        return readerInfoMapper.queryUserInfoByNameAndPassword(username, password);
    }

    @Override
    public List<BookInfo> searchBooksByName(String bookName) {
        return readerInfoMapper.searchBooksByName(bookName);
    }

    @Override
    public boolean reserveBook(Integer userId, String bookId) {
        return readerInfoMapper.reserveBook(userId, bookId);
    }
    @Override
    public void updatePersonalInfo(ReaderInfo readerInfo) {
        readerInfoMapper.updateByPrimaryKeySelective(readerInfo);
    }
}
