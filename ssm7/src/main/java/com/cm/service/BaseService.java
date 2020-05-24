package com.cm.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by Lenovo on 2020/5/8.
 */
public interface BaseService<T> {

    <K> int  deleteByPrimaryKey(K id);

    int insert(T record);

    int insertSelective(T record);

    <K> T selectByPrimaryKey(K id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    //分页
    public PageInfo<T> selectPage(T entity, Integer pageIndex, Integer pageSize);

    //加载系统参数方法
    public Map<String, Object> loadSysParam();
}
