package com.aiwacs.spring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aiwacs.spring.models.DiagramGroup;

@Repository
public interface DiagramGroupRepository extends JpaRepository<DiagramGroup, Integer> {

    @Query("select d from DiagramGroup d order by d.updatedAt desc nulls last")
    List<DiagramGroup> getDiagramGroup();
    
    @Query("select d from DiagramGroup d where d.id =:id")
    DiagramGroup findOne(Integer id);
    
    @Query("SELECT a FROM DiagramGroup a WHERE a.id = ?1")
    List<DiagramGroup> getByNoDiagramGroup(Integer diagramId);
    
    @Modifying(clearAutomatically=true)
    @Query("delete from DiagramGroup d where d.id in (?1) ")
    Integer deleteDiagramGroup(int[] groupId);
    
    @Modifying(clearAutomatically=true)
    @Query("update DiagramGroup d set d.endCreatedName = ?1 , d.updatedAt = ?2 where id = ?3 ")
    Integer updateDiagramGroup(String name, LocalDateTime date, Integer id);
    
    
    
    
//    @Modifying(clearAutomatically=true)
//    @Query("delete from TopologyLink l where l.id = ?1 ")
//    Integer deleteTopogolyLink(int id);
}
