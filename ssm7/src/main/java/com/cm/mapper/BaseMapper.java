package com.cm.mapper;

import java.util.List;

/**
 * Created by Lenovo on 2020/5/7.
 */
public interface BaseMapper<T> {

    <K> int  deleteByPrimaryKey(K id);

    int insert(T record);

    int insertSelective(T record);

    <K> T selectByPrimaryKey(K id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> selectPage(T record);
}
