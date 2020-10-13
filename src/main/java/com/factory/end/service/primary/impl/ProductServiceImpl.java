package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.OrderMapper;
import com.factory.end.mapper.primary.ProductMapper;
import com.factory.end.mapper.primary.ProjectDetailsMapper;
import com.factory.end.mapper.primary.SchedulingMapper;
import com.factory.end.model.primary.Product;
import com.factory.end.model.primary.Scheduling;
import com.factory.end.service.primary.ProductService;
import com.factory.end.util.OrderStatus;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 14:01
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper iProductMapper;

    @Autowired
    private SchedulingMapper schedulingMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProjectDetailsMapper projectDetailsMapper;

    /**
     * 获取生产号no
     *  加锁防止获取到相同的生产号
     * @return
     * @throws InterruptedException
     */
    public synchronized String getProductNoString() throws InterruptedException {
        //延迟1毫秒，防止获取到相同的时间戳
        Thread.sleep(1);
        //Product首字母字母+时间戳
        return "P"+System.currentTimeMillis();
    }

    @Override
    public List<Product> findAll() {
        Iterable<Product> colors = iProductMapper.findAll();
        logger.info(colors.toString());
        return (List<Product>)colors;
    }

    @Override
    public List<Product> findAllByOrderNo(String orderNo) {
        List<Product> allByOrderNo = iProductMapper.findAllByOrderNo(orderNo);
        logger.info(allByOrderNo.toString());
        return allByOrderNo;
    }

    @Override
    public List<Product> findAllByProductNo(String productNo) {
        List<Product> allByProductNo = iProductMapper.findAllByProductNo(productNo);
        logger.info(allByProductNo.toString());
        return allByProductNo;
    }

    @Override
    public List<Product> findAllByEquipmentNo(String equNo) {
        List<Product> allByEquipmentNo = iProductMapper.findAllByEquipmentNo(equNo);
        logger.info(allByEquipmentNo.toString());
        return allByEquipmentNo;
    }

    @Override
    public List<Product> findAllByStartDateBetween(String startDate,String endDate) {
        List<Product> allByStartDate = iProductMapper.findAllByStartDateBetween(startDate,endDate);
        logger.info(allByStartDate.toString());
        return allByStartDate;
    }

    @Override
    public List<Product> findAllByOrderStatus(Integer orderStatus) {
        List<Product> allByCoAndCompProductFlg = iProductMapper.findAllByOrderStatus(orderStatus);
        logger.info(allByCoAndCompProductFlg.toString());
        return allByCoAndCompProductFlg;
    }


    @Override
    @Transactional
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product saveProductBySchdulingId(Integer id,Integer lastSchedulingOfOrder) {
        logger.info("1):根据表id从scheduling表中查询出此条记录返回Scheduling类");
        Scheduling scheduling = schedulingMapper.findById(id).get();
        logger.info("2):根据表id从scheduling表中删除此条数据");
        schedulingMapper.deleteById(id);
        logger.info("3):将查询出的Schduling类相应的字段填充到一个Product对象中");
        Product product = SchedulingToProduct(scheduling);
        logger.info("设置是否是最后一条数据");
        product.setLastSchedulingOfOrder(lastSchedulingOfOrder);
        logger.info("4):将Product对象插入到Product表中");
        logger.info(product.toString());
        Product saveProduct = iProductMapper.save(product);
        logger.info("5):修改order表中的Order_Status为[生产中]状态");
        int orderStatus = OrderStatus.WAITPRODUCE;
        orderMapper.updateOrderStatusByOrderNo(scheduling.getOrderNo(),orderStatus);
        logger.info("6):按照scheduling表中的order_no在order表中查询出project_no");
        String projectNo = orderMapper.findOrderByOrderNo(scheduling.getOrderNo()).getProjectNo();
        if(projectNo == null || "".equals(projectNo)){
            throw new RuntimeException("此订单没有关联到项目号");
        }
        logger.info("projectNo:"+projectNo);
        logger.info("7):修改pro_Project_Details表中project_status为[生产中状态]");
        Integer projectStatus = ProjectStatus.INPRODUCT;
        projectDetailsMapper.updateProjectStatusByProjectNo(projectNo,projectStatus.toString());
        return saveProduct;
    }

    /**
     * 将Schduling类的相应字段填充到Product类中
     * @return
     */
    private Product SchedulingToProduct(Scheduling scheduling){
        Product product = new Product();
        try {
            product.setProductNo(getProductNoString());
        } catch (InterruptedException e) {
            logger.error("Schduling类的相应字段填充到Product类中时设值productNo出错");
            e.printStackTrace();
        }
        product.setOrderNo(scheduling.getOrderNo());
        product.setEquipmentNo(scheduling.getEquipmentNo());
        product.setTargetValue(scheduling.getTargetValue());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        product.setStartDate(time);
        //默认初始值为0
        product.setCollectValue(0);
        product.setOkValue(0);
        product.setNgValue(0);
        product.setCompProductProgress("0");
        //先设置预期完成时间为2个小时后
        //product.setCompExpectDate(simpleDateFormat.format(new Date(System.currentTimeMillis()+7200)));
        //设置预期完成时间为实际生产数的所消耗的时间
        product.setCompExpectDate(getCompExceptDate(scheduling.getTargetValue()));
        //生产完成时间不用此时不用设值
        //设值更新日期时间为当前时间
        product.setLastUpdate(time);
        //设置订单状态
        product.setOrderStatus(OrderStatus.PRODUCING);
        //设置用户名
        product.setUserName(scheduling.getUserName());
        return product;
    }

    /**
     * 获取系统预期完成时间
     * @param targetValue 目标产量
     * @return
     */
    public synchronized String getCompExceptDate(Integer targetValue){
        long timeMillis = System.currentTimeMillis();
        long compTimeMillis = timeMillis + 5 * 1000 * targetValue;
        Date date = new Date(compTimeMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String compExceptionDateStr = simpleDateFormat.format(date);
        logger.info("获取系统预期完成时间:"+compExceptionDateStr);
        return compExceptionDateStr;
    }

    @Override
    @Transactional
    public List<Product> saveProducts(List<Product> productList) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Product> saveProductsByOrderNoList(List<String> orderNoList) {
        //1:根据订单号从scheduling表中查询出此条记录返回Scheduling类
        List<Scheduling> schedulingList = new ArrayList<Scheduling>();
        for(String orderNo:orderNoList){
            Scheduling scheduling = schedulingMapper.findByOrderNo(orderNo);
            schedulingList.add(scheduling);
        }
        //2:根据订单号从scheduling表中删除此条数据
        schedulingMapper.deleteAll(schedulingList);
        //3:将查询出的Schduling类相应的字段填充到一个Product对象中
        List<Product> productList = new ArrayList<>();
        for(Scheduling scheduling:schedulingList){
            Product product = SchedulingToProduct(scheduling);
            productList.add(product);
        }
        //4:将Product对象插入到Product表中
        iProductMapper.saveAll(productList);
        //5:修改order表中的Order_Status为[生产中]状态
        int orderStatus = OrderStatus.WAITPRODUCE;
        for(String orderNo:orderNoList){
            orderMapper.updateOrderStatusByOrderNo(orderNo,orderStatus);
        }
        return productList;
    }

    @Override
    public boolean existsById(Integer id) {
        boolean exists = iProductMapper.existsById(id);
        return exists;
    }

    @Override
    public boolean existsByOrderNo(String orderNo) {
        boolean exists = iProductMapper.existsByOrderNo(orderNo);
        return exists;
    }

    @Override
    public boolean existsByProductNo(String productNo) {
        boolean exists = iProductMapper.existsByProductNo(productNo);
        return exists;
    }

    @Override
    public Integer deleteByOrderNo(String orderNo) {
        Integer i = iProductMapper.deleteByOrderNo(orderNo);
        return i;
    }

    @Override
    public Page<Product> findByPage(Product product, Integer currentPage, Integer pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Specification<Product> specification = (root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();
            if(product.getOrderNo() != null && !"".equals(product.getOrderNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("orderNo").as(String.class), product.getOrderNo());
                list.add(predicate);
            }
            if(product.getProductNo() != null && !"".equals(product.getProductNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("productNo").as(String.class), product.getProductNo());
                list.add(predicate);
            }
            if(product.getEquipmentNo() != null && !"".equals(product.getEquipmentNo())){
                Predicate predicate = criteriaBuilder.equal(root.get("equipmentNo").as(String.class), product.getEquipmentNo());
                list.add(predicate);
            }
            if (product.getUserName() != null && !"".equals(product.getUserName())){
                Predicate predicate = criteriaBuilder.equal(root.get("userName").as(String.class), product.getUserName());
                list.add(predicate);
            }
            //new一个数组作为最终返回值的条件
            Predicate[] predicates = new Predicate[list.size()];
            //将list直接转换成数组
            list.toArray(predicates);
            return criteriaBuilder.and(list.toArray(predicates));
            //return criteriaBuilder.and(list.toArray(new javax.persistence.criteria.Predicate[0]));
        };
        //复杂条件查询
        return iProductMapper.findAll(specification,pageable);
    }

    @Override
    public void updateCollectValueByProductNo(String productNo,Integer collectValue) {
        iProductMapper.updateCollectValueByProductNo(productNo,collectValue);
    }

    @Override
    public void updateOrderStatusByProductNo(String productNo, Integer orderStatus) {
        iProductMapper.updateOrderStatusByProductNo(productNo,orderStatus);
    }

    @Override
    public void updateCompProductDateByProductNo(String productNo, String compProductDate) {
        iProductMapper.updateCompProductDateByProductNo(productNo,compProductDate);
    }

    @Override
    public Integer findCountByOrderNo(String orderNo) {
        Integer countByOrderNo = iProductMapper.findCountByOrderNo(orderNo);
        return countByOrderNo;
    }
}
