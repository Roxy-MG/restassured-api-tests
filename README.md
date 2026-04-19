![Java](https://img.shields.io/badge/Java-17-blue)
![Junit5](https://img.shields.io/badge/JUnit5-5.10.2-yellow)
![RestAssured](https://img.shields.io/badge/RestAssured-5.3.0-green)
![Allure](https://img.shields.io/badge/Allure-2.25.0-purple)

# RestAssured API 自动化测试框架

## 🏗️ 技术栈
Java 17 | RestAssured | JUnit 5 | Allure Report | Lombok | Maven

## 📁 项目结构
src/test/java/  
├── config/ # 配置层（请求/响应规格）  
├── models/ # 数据模型层  
├── services/ # 业务层（API封装）  
└── tests/ # 测试用例层  

src/test/resources/  
├── data/ # 测试数据  
└── schema/ # JSON Schema 文件  

## ✨ 框架亮点

### 🔀 CI/CD 自动化
测试已集成 GitHub Actions  

[![API_Tests](https://github.com/Roxy-MG/restassured-api-tests/actions/workflows/test.yml/badge.svg)](https://github.com/Roxy-MG/restassured-api-tests/actions/workflows/test.yml)

### 📊 数据驱动测试
支持 `@CsvSource` 和 `@CsvFileSource` 两种数据驱动方式。

### 📋 Allure 测试报告
集成 Allure Report，测试结果可视化展示。

### ⚡ 支持并发
junit-platform.properties 配置全局并发

### 🛡️ 响应校验
支持 JSON Schema 结构校验 + 字段精确断言。

## ▶️ 如何运行
```bash
mvn clean test allure:serve
```

## 📂 报告位置
target/allure-results/
