package com.example.dataAnonymization.writer;

import com.example.dataAnonymization.dto.Report_Dto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class XyzWriter implements ItemWriter<Report_Dto> {
    @Override
    public void write(Chunk<? extends Report_Dto> chunk) throws Exception {
        for (Report_Dto record : chunk.getItems()) {
            System.out.println(record);  // just print
        }
    }
}
