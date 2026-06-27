package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController  // ① 标记为控制器，且所有方法返回 JSON（而不是页面）
@RequestMapping("/api/v1/employees")  // ② 接口的父路径
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping  // ③ 只处理 POST 请求，完整路径是 /api/v1/employees
    public Map<String, Object> addEmployee(@RequestBody Employee employee) {
        // ④ 统一响应格式：最外层用 Map 装 code, message, data

        // --- 第一步：参数校验（简单校验）---
        // 如果前端没传名字，或者名字长度小于2，返回错误
        if (employee.getName() == null || employee.getName().length() < 2) {
            return errorResponse("姓名不能为空且长度至少2个字符");
        }
        // 简单校验邮箱是否为空（格式校验后续可加正则，这里先简化）
        if (employee.getEmail() == null || employee.getEmail().isEmpty()) {
            return errorResponse("邮箱不能为空");
        }
        // 校验工资必须大于0
        if (employee.getSalary() == null || employee.getSalary().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return errorResponse("工资必须大于0");
        }

        // --- 第二步：业务校验（调用 Service）---
        // 检查邮箱是否已被占用
        if (employeeService.isEmailExists(employee.getEmail())) {
            return errorResponse("该邮箱已被其他员工注册，请更换");
        }

        // --- 第三步：调用 Service 执行插入 ---
        try {
            Long newId = employeeService.addEmployee(employee);
            // 组装成功响应
            Map<String, Object> result = new HashMap<>();
            result.put("code", 200);
            result.put("message", "操作成功");
            Map<String, Object> data = new HashMap<>();
            data.put("employeeId", newId);
            result.put("data", data);
            return result;
        } catch (Exception e) {
            // 如果 Service 层抛了异常，这里捕获并返回错误
            return errorResponse("系统内部错误：" + e.getMessage());
        }
    }

    // ⑤ 小工具方法：统一封装错误响应（避免重复写一堆 put 代码）
    private Map<String, Object> errorResponse(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", msg);
        result.put("data", null);
        return result;
    }
}