package com.saltedfish.mapper.app;

import com.saltedfish.entity.app.CommAppVersion;

public interface CommAppVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommAppVersion record);

    int insertSelective(CommAppVersion record);

    CommAppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommAppVersion record);

    int updateByPrimaryKey(CommAppVersion record);
}