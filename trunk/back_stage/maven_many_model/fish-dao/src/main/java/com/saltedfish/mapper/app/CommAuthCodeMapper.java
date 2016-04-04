package com.saltedfish.mapper.app;

import com.saltedfish.entity.app.CommAuthCode;

public interface CommAuthCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommAuthCode record);

    int insertSelective(CommAuthCode record);

    CommAuthCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommAuthCode record);

    int updateByPrimaryKey(CommAuthCode record);
}