package net.cowfish.dao;

import net.cowfish.entity.ParamModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ParamMapper {
    @Select("select param_value paramValue from param")
    ParamModel queryParam();
}
