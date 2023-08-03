package com.care.library.cultural;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CulturalMapper {
    void insertCultural(CulturalDTO culturalDTO);
}
