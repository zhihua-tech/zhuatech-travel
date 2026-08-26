# ZhuaTech Travel｜知华科技企业商旅管理系统

        > 从出差申请到行程、费用和结算全程受控。

        [![Java 21](https://img.shields.io/badge/Java-21-315a70)](backend/pom.xml) [![Vue 3](https://img.shields.io/badge/Vue-3-42b883)](frontend/package.json) [![MySQL 8](https://img.shields.io/badge/MySQL-8-4479a1)](compose.yaml) [![个人非商用](https://img.shields.io/badge/license-personal%20non--commercial-b47b3a)](LICENSE)

        ZhuaTech Travel 是知华科技（上海如静知华信息科技有限公司）发布的前后端分离企业应用社区源码版，面向出差申请、预算审批、行程预订、差旅费用和报销结算管理。官网：[https://www.zhuatech.cn/](https://www.zhuatech.cn/)。

        ## 企业版 V2.0

        在原有四大业务模块基础上，新增企业控制中心：支持组织与账期维度、幂等防重、经办/管理员职责分离、审批闭环、附件 SHA-256 元数据、办结凭证门槛、外部适配器回执、乐观锁和全程审计；并实现费用逐项差标、票据与公务属性校验、公司支付、预借款冲销。详见[企业版能力说明](docs/ENTERPRISE.md)。

        ## 业务闭环

        ```text
        提交申请 → 审批通过 → 完成预订 → 开始行程 → 完成结算
        ```

        ## 主要模块

        | 模块 | 已实现能力 |
        | --- | --- |
        | 出差申请 | 登记目的地、事由、人员、时间与预算 |
| 行程与预订 | 维护交通、酒店和行程变更记录 |
| 差旅费用 | 归集票据、费用明细与差标校验 |
| 报销结算 | 完成报销审批、借款冲销和财务付款 |
        | 运营总览 | 状态结构、模块负荷、金额指标、风险关注和最近业务 |
        | 领域计算 | 差旅政策与预算校验，提供可解释计算结果和处理建议 |
| 操作审计 | 创建、修改、删除、流程动作和设置变更均保留操作人及时间 |
        | 系统设置 | 核心业务参数持久化，管理员权限隔离 |

        管理端支持业务记录查询、新增、修改、删除、状态流转、越级操作拦截和审计追踪；响应式界面可在电脑和移动浏览器使用。演示数据全部为虚构数据。

        ## 技术架构

        - 后端：Java 21、Spring Boot 4、Spring Security、Spring Data JPA、MySQL 8
        - 前端：Vue 3、Vite，管理端与业务工作台响应式布局
        - 测试：H2 隔离数据库、MockMvc 接口与权限集成测试
        - 部署：Docker Compose、Nginx 反向代理、健康检查和环境变量
        - Java 工程包：`cn.zhuatech.travel`

        ## 快速启动

        ```bash
        cp .env.example .env
        docker compose up --build
        ```

        浏览器打开 `http://localhost:8103`。演示账号：`admin / admin123`、`operator / operator123`。默认密码只能用于本地演示，上线前必须修改。

        本地开发：

        ```bash
        cd backend && mvn test
        cd ../frontend && npm install && npm run build
        ```

        更多资料参见 [API 文档](docs/API.md)、[架构说明](docs/ARCHITECTURE.md)、[安全政策](SECURITY.md)和[贡献指南](CONTRIBUTING.md)。

        ## 使用范围

        本工程仅允许个人非商业性的学习、研究和技术交流，**不得商用**。商用、二次销售、SaaS 服务、企业部署及深度定制须取得上海如静知华信息科技有限公司书面授权。

        商业授权、企业信息化、AI 转型、软件外包、项目实施和深度定制请访问[知华科技官网](https://www.zhuatech.cn/)，或扫描微信二维码咨询。

        <p align="center"><img src="docs/images/zhuatech-wechat-consulting.png" alt="知华科技微信咨询二维码一" width="230"><img src="docs/images/zhuatech-wechat-consulting-2.png" alt="知华科技微信咨询二维码二" width="230"></p>

        SEO 关键词：商旅管理、出差申请、差旅预订、差旅费报销、企业差旅系统、知华科技、上海软件开发、企业信息化、软件项目外包。
