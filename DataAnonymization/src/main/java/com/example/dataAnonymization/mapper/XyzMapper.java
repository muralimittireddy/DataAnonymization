package com.example.dataAnonymization.mapper;

import com.example.dataAnonymization.dto.AbcDto;
import com.example.dataAnonymization.dto.XyzDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class XyzMapper implements RowMapper<XyzDto> {
    @Override
    public XyzDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        XyzDto record = new XyzDto();

        record.setExternalNumber(rs.getInt("external_number"));
        record.setInternalNumber(rs.getInt("internal_number"));
        record.setIsXyz(rs.getString("isxyz"));
        record.setStatus(rs.getString("status"));
        return record;
    }
}
