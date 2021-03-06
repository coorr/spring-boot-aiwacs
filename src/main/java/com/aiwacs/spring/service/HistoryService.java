package com.aiwacs.spring.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.aiwacs.spring.models.HistoryRecord;
import com.aiwacs.spring.models.User;

public interface HistoryService {

    public List<HistoryRecord> getHistoryRecord();
    public List<User> getHistoryUser();
    public List<HistoryRecord> getSelectHistory(int size,String user,String action,String firstDate,String secondDate);
    public ByteArrayInputStream  historyDownloadExcel(String user,String firstDate,String outDate);
    
}
