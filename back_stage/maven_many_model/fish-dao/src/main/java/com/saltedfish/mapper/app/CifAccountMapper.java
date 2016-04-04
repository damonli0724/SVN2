package com.saltedfish.mapper.app;

import com.saltedfish.entity.app.CifAccount;

public interface CifAccountMapper {
    int deleteByPrimaryKey(Integer accountId);

    int insert(CifAccount record);

    int insertSelective(CifAccount record);

    CifAccount selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(CifAccount record);

    int updateByPrimaryKey(CifAccount record);
}