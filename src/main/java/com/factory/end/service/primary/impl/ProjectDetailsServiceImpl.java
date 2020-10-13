package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.ProjectDetailsMapper;
import com.factory.end.model.primary.ProjectDetails;
import com.factory.end.service.primary.ProductService;
import com.factory.end.service.primary.ProjectDetailsService;
import com.factory.end.util.ProjectStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/9/15 13:20
 * @Version 1.0
 */
@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {
    Logger logger = LoggerFactory.getLogger(ProjectDetailsServiceImpl.class);

    @Autowired
    private ProjectDetailsMapper projectDetailsMapper;

    /**
     * 获取项目编号
     *  加锁防止获取到相同的生产号
     * @return
     * @throws InterruptedException
     */
    public synchronized String getProjectNoString() throws InterruptedException {
        //延迟1毫秒，防止获取到相同的时间戳
        Thread.sleep(1);
        //Product首字母字母+时间戳
        return "P"+System.currentTimeMillis();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProjectDetails save(ProjectDetails projectDetails) {
        //新增项目时,设置初始状态为1
        projectDetails.setProjectStatus(ProjectStatus.TOBEPRODUCTED);
        try {
            projectDetails.setProjectNo(getProjectNoString());
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("获取项目编号出错");
        }
        ProjectDetails save = projectDetailsMapper.save(projectDetails);
        logger.info(save.toString());
        return save;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProjectDetails update(ProjectDetails projectDetails) {
        ProjectDetails details = projectDetailsMapper.save(projectDetails);
        logger.info("修改成功:"+details.toString());
        return details;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateProjectStatusByProjectNo(String projectNo, String projectStatus) {
        projectDetailsMapper.updateProjectStatusByProjectNo(projectNo,projectStatus);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(ProjectDetails projectDetails) {
        projectDetailsMapper.delete(projectDetails);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByProjectNo(String projectNo) {
        projectDetailsMapper.deleteByProjectNo(projectNo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        projectDetailsMapper.deleteById(id);
    }

    @Override
    public List<ProjectDetails> findAll() {
        List<ProjectDetails> detailsList = (List<ProjectDetails>) projectDetailsMapper.findAll();
        logger.info(detailsList.toString());
        return detailsList;
    }

    @Override
    public Page<ProjectDetails> findByPage(ProjectDetails projectDetails, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage,pageSize);
        Specification<ProjectDetails> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if(projectDetails.getCompany() != null && !"".equals(projectDetails.getCompany())){
                Predicate predicate = criteriaBuilder.equal(root.get("company").as(String.class),projectDetails.getCompany());
                list.add(predicate);
            }
            if(projectDetails.getProjectNo() != null && !"".equals(projectDetails.getProjectNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("projectNo").as(String.class),projectDetails.getProjectNo());
                list.add(predicate);
            }
            if(projectDetails.getProjectName() != null && !"".equals(projectDetails.getProjectName())){
                Predicate predicate = criteriaBuilder.equal(root.get("projectName").as(String.class),projectDetails.getProjectName());
                list.add(predicate);
            }
            if(projectDetails.getCustomerName() != null && !"".equals(projectDetails.getCustomerName())){
                Predicate predicate = criteriaBuilder.equal(root.get("customerName").as(String.class),projectDetails.getCustomerName());
                list.add(predicate);
            }

//            if(projectDetails.getApplyDate() != null && !"".equals(projectDetails.getApplyDate())){
//                Predicate predicate = criteriaBuilder.equal(root.get("applyDate").as(String.class),projectDetails.getApplyDate());
//                list.add(predicate);
//            }

            if(projectDetails.getHandler() != null && !"".equals(projectDetails.getHandler())){
                Predicate predicate = criteriaBuilder.equal(root.get("handler").as(String.class),projectDetails.getHandler());
                list.add(predicate);
            }
            if(projectDetails.getRequestDeliveryDate() != null && !"".equals(projectDetails.getRequestDeliveryDate())){
                Predicate predicate = criteriaBuilder.equal(root.get("requestDeliveryData").as(String.class),projectDetails.getRequestDeliveryDate());
                list.add(predicate);
            }
            if(projectDetails.getWarehouse() != null && !"".equals(projectDetails.getWarehouse())){
                Predicate predicate = criteriaBuilder.equal(root.get("warehouse").as(String.class),projectDetails.getWarehouse());
                list.add(predicate);
            }
            if(projectDetails.getTotal() != null && !"".equals(projectDetails.getTotal())){
                Predicate predicate = criteriaBuilder.equal(root.get("total").as(String.class),projectDetails.getTotal());
                list.add(predicate);
            }
            if(projectDetails.getPayType() != null && !"".equals(projectDetails.getPayType())){
                Predicate predicate = criteriaBuilder.equal(root.get("payType").as(String.class),projectDetails.getPayType());
                list.add(predicate);
            }
            if(projectDetails.getNotes() != null && !"".equals(projectDetails.getNotes())){
                Predicate predicate = criteriaBuilder.equal(root.get("notes").as(String.class),projectDetails.getNotes());
                list.add(predicate);
            }
            if(projectDetails.getReceiptType() != null && !"".equals(projectDetails.getReceiptType())){
                Predicate predicate = criteriaBuilder.equal(root.get("receiptType").as(String.class),projectDetails.getReceiptType());
                list.add(predicate);
            }
            if(projectDetails.getCreateName() != null && !"".equals(projectDetails.getCreateName())){
                Predicate predicate = criteriaBuilder.equal(root.get("createName").as(String.class),projectDetails.getCreateName());
                list.add(predicate);
            }
            if(projectDetails.getProjectStatus() != null && !"".equals(projectDetails.getProjectStatus())){
                Predicate predicate = criteriaBuilder.equal(root.get("projectStatus").as(Integer.class), projectDetails.getProjectStatus());
                list.add(predicate);
            }
//            if(projectDetails.getCreateDate() != null && !"".equals(projectDetails.getCreateDate())){
//                Predicate predicate = criteriaBuilder.equal(root.get("createDate").as(String.class),projectDetails.getCreateDate());
//                list.add(predicate);
//            }
            if(projectDetails.getReceiptType() != null && !"".equals(projectDetails.getReceiptType())){
                Predicate predicate = criteriaBuilder.equal(root.get("receiptType").as(String.class),projectDetails.getReceiptType());
                list.add(predicate);
            }
            if(projectDetails.getBillStatus() != null && !"".equals(projectDetails.getBillStatus())){
                Predicate predicate = criteriaBuilder.equal(root.get("billStatus").as(String.class),projectDetails.getBillStatus());
                list.add(predicate);
            }
            if(projectDetails.getCheckerName() != null && !"".equals(projectDetails.getCheckerName())){
                Predicate predicate = criteriaBuilder.equal(root.get("checkerName").as(String.class),projectDetails.getCheckerName());
                list.add(predicate);
            }
//            if(projectDetails.getCheckerDate() != null && !"".equals(projectDetails.getCheckerDate())){
//                Predicate predicate = criteriaBuilder.equal(root.get("checkerDate").as(String.class),projectDetails.getCheckerDate());
//                list.add(predicate);
//            }
            if(projectDetails.getCheckerNotes() != null && !"".equals(projectDetails.getCheckerNotes())){
                Predicate predicate = criteriaBuilder.equal(root.get("checkerNotes").as(String.class),projectDetails.getCheckerNotes());
                list.add(predicate);
            }

            //new一个数组作为最终返回值的条件
            Predicate[] predicates = new Predicate[list.size()];
            //将list直接转换成数组
            list.toArray(predicates);
            return criteriaBuilder.and(list.toArray(predicates));
        };
        //复杂条件查询
        return projectDetailsMapper.findAll(specification,pageable);
    }

}
