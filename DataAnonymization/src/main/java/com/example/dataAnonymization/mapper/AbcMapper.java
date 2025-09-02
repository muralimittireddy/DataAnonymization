package com.example.dataAnonymization.mapper;

import com.example.dataAnonymization.dto.AbcDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AbcMapper implements RowMapper<AbcDto> {
    @Override
    public AbcDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        AbcDto record = new AbcDto();

        record.setExternalNumber(rs.getInt("external_number"));
        record.setInternalNumber(rs.getInt("internal_number"));
        record.setIsAbc(rs.getString("isabc"));
        record.setStatus(rs.getString("status"));
        return record;
    }
}
