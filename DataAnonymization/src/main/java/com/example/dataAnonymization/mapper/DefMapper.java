package com.example.dataAnonymization.mapper;

import com.example.dataAnonymization.dto.AbcDto;
import com.example.dataAnonymization.dto.DefDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefMapper implements RowMapper<DefDto> {
    @Override
    public DefDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        DefDto record = new DefDto();

        record.setExternalNumber(rs.getInt("external_number"));
        record.setInternalNumber(rs.getInt("internal_number"));
        record.setIsDef(rs.getString("isdef"));
        record.setStatus(rs.getString("status"));
        return record;
    }
}
