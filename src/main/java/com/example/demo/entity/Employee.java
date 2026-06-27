package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user1")   // 注意：这里指向你改后的表名
public class Employee {

    @TableId(type = IdType.AUTO)
    private Long id;          // ① 对应数据库 id

    private String name;      // ② 对应 name（String 类型）
    
    private String email;     // ③ 对应 email（String 类型）
    
    private Long departmentId;  // ④ 对应 department_id（注意驼峰 + Long 类型）
    
    private BigDecimal salary;   // ⑤ 对应 salary（注意用 BigDecimal，不是 Double）
    
    private LocalDateTime createTime;  // ⑥ 对应 create_time（注意用 LocalDateTime）
    
    private LocalDateTime updateTime;  // ⑦ 对应 update_time（注意用 LocalDateTime）
}
