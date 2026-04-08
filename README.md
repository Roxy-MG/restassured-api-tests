![Java](https://img.shields.io/badge/Java-17-blue)
![RestAssured](https://img.shields.io/badge/RestAssured-5.3.0-green)
![Allure](https://img.shields.io/badge/Allure-2.25.0-purple)
![CI](https://github.com/Roxy-MG/restassured-api-tests/actions/workflows/test.yml/badge.svg)
# RestAssured + JUnit5 接口自动化测试框架

## 项目简介

基于 RestAssured 和 JUnit5 的接口自动化测试项目，用于 ReqRes 示例接口的测试。

## 技术栈

- Java 17
- RestAssured 5.3.0
- JUnit 5
- Allure
- Maven
- Lombok

## 项目结构
src/test/java/  
├── config/ # 配置层（请求/响应规格）  
├── models/ # 数据模型层  
├── services/ # 业务层（API封装）  
└── tests/ # 测试用例层  

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+

### 运行测试
```bash
mvn clean test allure:serve
```

### 报告位置
- target/allure-results/
