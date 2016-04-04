package com.saltedfish.mapper.app;

import com.saltedfish.entity.app.CifLogin;

public interface CifLoginMapper {
    int deleteByPrimaryKey(Integer loginId);

    int insert(CifLogin record);

    int insertSelective(CifLogin record);

    CifLogin selectByPrimaryKey(Integer loginId);

    int updateByPrimaryKeySelective(CifLogin record);

    int updateByPrimaryKey(CifLogin record);
}