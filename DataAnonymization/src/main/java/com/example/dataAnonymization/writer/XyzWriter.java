package com.example.dataAnonymization.writer;

import com.example.dataAnonymization.dto.Report_Dto;
import com.example.dataAnonymization.dto.XyzDto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class XyzWriter implements ItemWriter<XyzDto> {
    @Override
    public void write(Chunk<? extends XyzDto> chunk) throws Exception {
        for (XyzDto record : chunk.getItems()) {
            System.out.println(record);  // just print
        }
    }
}
