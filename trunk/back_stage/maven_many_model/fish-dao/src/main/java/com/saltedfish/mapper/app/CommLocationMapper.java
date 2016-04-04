package com.saltedfish.mapper.app;

import com.saltedfish.entity.app.CommLocation;

public interface CommLocationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommLocation record);

    int insertSelective(CommLocation record);

    CommLocation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommLocation record);

    int updateByPrimaryKey(CommLocation record);
}