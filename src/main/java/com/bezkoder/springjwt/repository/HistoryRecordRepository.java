package com.bezkoder.springjwt.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.GroupEquipmentJoin;
import com.bezkoder.springjwt.models.HistoryRecord;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;

@Repository
public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long> {

    @Query("select h from HistoryRecord h order by h.id desc")
    List<HistoryRecord> getHistoryRecord();
    
    @Query("select u from User u")
    List<User> getUserHistory();
    
    @Query("select h from HistoryRecord h where h.userName in (:user) and h.actionType in (:action) order by h.id desc")
    List<HistoryRecord> getSelectHistory(@Param("user")String[] user,@Param("action")String[] action);
    

}