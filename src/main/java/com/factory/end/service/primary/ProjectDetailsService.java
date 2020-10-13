package com.factory.end.service.primary;

import com.factory.end.model.primary.ProjectDetails;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author jchonker
 * @Date 2020/9/15 13:19
 * @Version 1.0
 */
@Service
public interface ProjectDetailsService {

    /**
     * 保存
     * @param projectDetails
     * @return
     */
    ProjectDetails save(ProjectDetails projectDetails);

    /**
     * 修改
     * @param projectDetails
     * @return
     */
    ProjectDetails update(ProjectDetails projectDetails);

    /**
     * 根据项目编号修改项目状态
     * @param projectNo
     */
    void updateProjectStatusByProjectNo(String projectNo,String projectStatus);

    /**
     * 根据实体类删除
     * @param projectDetails
     */
    void delete(ProjectDetails projectDetails);

    /**
     * 根据项目编号删除
     * @param projectNo
     */
    void deleteByProjectNo(String projectNo);

    /**
     * 根据id查询
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<ProjectDetails> findAll();

    /**
     * 复杂分页查询
     * @param projectDetails
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<ProjectDetails> findByPage(ProjectDetails projectDetails,Integer currentPage, Integer pageSize);
}
