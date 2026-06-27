package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service  // ① 标记这个类是 Spring 的"业务层组件"，交给容器管理
public class EmployeeService {

    @Autowired  // ② 自动注入 Mapper，相当于把数据库操作的工具拿过来
    private EmployeeMapper employeeMapper;

    /**
     * 新增员工
     * @param employee 前端传过来的员工信息（不含 id，因为 id 是数据库自增的）
     * @return 数据库生成的主键 id
     */
    public Long addEmployee(Employee employee) {
        // ③ 补全时间字段（数据库虽然设了默认值，但 Java 代码里手动设一下更稳妥）
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        // ④ 调用 MyBatis-Plus 的 insert 方法，返回受影响的行数（1 表示成功）
        int rows = employeeMapper.insert(employee);

        // ⑤ 如果插入成功，employee 对象里会被自动回填 id（因为我们在实体类加了 @TableId）
        if (rows > 0) {
            return employee.getId();  // 返回自增后的 ID
        } else {
            throw new RuntimeException("新增员工失败，数据库无影响");
        }
    }

    /**
     * 校验邮箱是否已存在（为 Controller 层提供复用）
     */
    public boolean isEmailExists(String email) {
        // QueryWrapper 是 MyBatis-Plus 提供的"条件构造器"，相当于 where 条件
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);  // 相当于 where email = ?
        Long count = employeeMapper.selectCount(wrapper);
        return count > 0;
    }
}