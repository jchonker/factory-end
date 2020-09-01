package com.factory.end.service.primary.impl;

import com.factory.end.mapper.primary.IProductMapper;
import com.factory.end.mapper.primary.ISchedulingMapper;
import com.factory.end.model.primary.Color;
import com.factory.end.model.primary.Product;
import com.factory.end.service.primary.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/28 14:01
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private IProductMapper iProductMapper;

    @Autowired
    private ISchedulingMapper schedulingMapper;

    @Override
    public List<Product> findAll() {
        Iterable<Product> colors = iProductMapper.findAll();
        System.out.println("colors:"+colors);
        return (List<Product>)colors;
    }

    @Override
    public List<Product> findAllByOrderNo(Integer orderNo) {
        List<Product> allByOrderNo = iProductMapper.findAllByOrderNo(orderNo);
        System.out.println("allByOrderNo:"+allByOrderNo);
        return allByOrderNo;
    }

    @Override
    public List<Product> findAllByProductNo(Integer productNo) {
        List<Product> allByProductNo = iProductMapper.findAllByProductNo(productNo);
        System.out.println("allByProductNo:"+allByProductNo);
        return allByProductNo;
    }

    @Override
    public List<Product> findAllByEquipmentNo(Integer equNo) {
        List<Product> allByEquipmentNo = iProductMapper.findAllByEquipmentNo(equNo);
        System.out.println("allByEquipmentNo:"+allByEquipmentNo);
        return allByEquipmentNo;
    }

    @Override
    public List<Product> findAllByStartDateBetween(String startDate,String endDate) {
        List<Product> allByStartDate = iProductMapper.findAllByStartDateBetween(startDate,endDate);
        System.out.println("allByStartDate:"+allByStartDate);
        return allByStartDate;
    }

    @Override
    public List<Product> findAllByCompProductFlg(Integer compProductFlg) {
        List<Product> allByCoAndCompProductFlg = iProductMapper.findAllByCompProductFlg(compProductFlg);
        System.out.println("findAllByCoAndCompProductFlg:"+allByCoAndCompProductFlg);
        return allByCoAndCompProductFlg;
    }


    @Override
    @Transactional
    public Product saveProduct(Product product) {
        //先根据订单号从scheduling表中删除此条数据
        schedulingMapper.deleteByOrderNo(product.getOrderNo());
        System.out.println("从scheduling表中删除订单号为"+product.getOrderNo()+"的数据");
        Product saveProduct = iProductMapper.save(product);
        System.out.println("插入product表中订单号为:"+saveProduct.getOrderNo()+"的数据");
        return saveProduct;
    }

    @Override
    @Transactional
    public List<Product> saveProducts(List<Product> productList) {
        //先根据订单号从scheduling表中删除此条数据
        for(Product product:productList){
            schedulingMapper.deleteByOrderNo(product.getOrderNo());
            System.out.println("从scheduling表中删除订单号为"+product.getOrderNo()+"的数据");
        }
        Iterable<Product> products = iProductMapper.saveAll(productList);
        for(Product product:products){
            System.out.println("插入product表中成功,订单号:"+product.getOrderNo());
        }
        return (List<Product>)products;
    }


    @Override
    public boolean existsById(Integer id) {
        boolean exists = iProductMapper.existsById(id);
        return exists;
    }

    @Override
    public boolean existsByOrderNo(Integer orderNo) {
        boolean exists = iProductMapper.existsByOrderNo(orderNo);
        return exists;
    }

    @Override
    public boolean deleteByOrderNo(Integer orderNo) {
        boolean delete = iProductMapper.deleteByOrderNo(orderNo);
        return delete;
    }
}
