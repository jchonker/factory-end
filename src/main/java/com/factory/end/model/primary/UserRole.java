//package com.factory.end.model.primary;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
///**
// * @Author jchonker
// * @Date 2020/10/30 10:56
// * @Version 1.0
// */
//@Data
//@Table(name = "pro_User_Role")
//@Entity
//public class UserRole {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
////    @ManyToOne
////    @JoinColumn(name = "uid")
////    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "rid")
//    private Role role;
//
//    @Column(name = "uid")
//    private Long uid;
//
//    @Column(name = "rid",insertable=false,updatable=false)
//    private Long rid;
//}
