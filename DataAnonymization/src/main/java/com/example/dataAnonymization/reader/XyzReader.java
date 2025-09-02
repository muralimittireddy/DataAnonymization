package com.example.dataAnonymization.reader;

import com.example.dataAnonymization.dto.Report_Dto;
import com.example.dataAnonymization.dto.XyzDto;
import com.example.dataAnonymization.enums.SqlEnum;
import com.example.dataAnonymization.mapper.Report_Mapper;
import com.example.dataAnonymization.mapper.XyzMapper;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
@StepScope
public class XyzReader extends JdbcPagingItemReader<XyzDto>{

    SqlEnum query = SqlEnum.XYZ_READER;
    public XyzReader(DataSource dataSource
//            ,
//                     @Value("#{stepExecutionContext['minValue']}") Long minValue,
//                     @Value("#{stepExecutionContext['maxValue']}") Long maxValue
    ) {

        setDataSource(dataSource);
        setPageSize(100); // batch size per fetch
        setRowMapper(new XyzMapper());

        // Query provider for paging (thread-safe)
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause(query.getSelectClause());
        queryProvider.setFromClause("FROM " + query.getFromClause());
        queryProvider.setWhereClause("WHERE " + query.getWhereClause());
        queryProvider.setSortKey("external_number");

        try {
            setQueryProvider(queryProvider.getObject());
        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize query provider", e);
        }

//        Map<String, Object> params = new HashMap<>();
//        params.put("min", minValue);
//        params.put("max", maxValue);
//        setParameterValues(params);
    }
}
