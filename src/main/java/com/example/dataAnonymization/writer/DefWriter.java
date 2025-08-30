package com.example.dataAnonymization.writer;

import com.example.dataAnonymization.dto.Report_Dto;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class DefWriter implements ItemWriter<Report_Dto> {
    @Override
    public void write(Chunk<? extends Report_Dto> chunk) throws Exception {
        for (Report_Dto record : chunk.getItems()) {
            System.out.println(record);  // just print
        }
    }
}
