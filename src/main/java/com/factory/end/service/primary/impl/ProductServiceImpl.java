package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.IOrderMapper;
import com.factory.end.mapper.primary.IProductMapper;
import com.factory.end.mapper.primary.ISchedulingMapper;
import com.factory.end.model.primary.Product;
import com.factory.end.model.primary.Scheduling;
import com.factory.end.service.primary.ProductService;
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
    private IProductMapper iProductMapper;

    @Autowired
    private ISchedulingMapper schedulingMapper;

    @Autowired
    private IOrderMapper orderMapper;

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
        return "F"+System.currentTimeMillis();
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
    @Transactional
    public Product saveProductByOrderNo(String orderNo) {
        //1:根据订单号从scheduling表中查询出此条记录返回Scheduling类
        Scheduling scheduling = schedulingMapper.findByOrderNo(orderNo);
        //2:根据订单号从scheduling表中删除此条数据
        schedulingMapper.deleteByOrderNo(orderNo);
        //3:将查询出的Schduling类相应的字段填充到一个Product对象中
        Product product = SchedulingToProduct(scheduling);
        //4:将Product对象插入到Product表中
        Product saveProduct = iProductMapper.save(product);
        //5:修改order表中的Order_Status为[生产中]状态
        orderMapper.updateOrderStatusByOrderNo(orderNo);
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
        product.setCompProductProgress("0%");
        //先设置预期完成时间为2个小时后
        product.setCompExpectDate(simpleDateFormat.format(new Date(System.currentTimeMillis()+7200)));
        //生产完成时间不用此时不用设值
        //设值更新日期时间为当前时间
        product.setLastUpdate(time);
        return product;
    }

    @Override
    @Transactional
    public List<Product> saveProducts(List<Product> productList) {
        return null;
    }

    @Override
    @Transactional
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
        for(String orderNo:orderNoList){
            orderMapper.updateOrderStatusByOrderNo(orderNo);
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
}
