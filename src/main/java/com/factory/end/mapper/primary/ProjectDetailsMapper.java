package com.factory.end.mapper.primary;

import com.factory.end.model.primary.ProjectDetails;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author jchonker
 * @Date 2020/9/15 11:32
 * @Version 1.0
 */
public interface ProjectDetailsMapper extends CrudRepository<ProjectDetails,Integer>, JpaSpecificationExecutor<ProjectDetails> {

    /**
     * 根据项目编号删除
     * @param projectNo 项目编号
     */
    void deleteByProjectNo(String projectNo);

    /**
     * 根据项目编号修改项目状态
     * @param projectNo
     * @param projectStatus
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query(value = "update pro_Project_Details set Project_Status = :projectStatus where Project_No = :projectNo",nativeQuery = true)
    void updateProjectStatusByProjectNo(@Param("projectNo") String projectNo,@Param("projectStatus") String projectStatus);

}
