package com.saltedfish.mapper.app;

import com.saltedfish.entity.app.CommMessageTemplate;

public interface CommMessageTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommMessageTemplate record);

    int insertSelective(CommMessageTemplate record);

    CommMessageTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommMessageTemplate record);

    int updateByPrimaryKey(CommMessageTemplate record);
}