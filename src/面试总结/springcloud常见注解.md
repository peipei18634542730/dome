开发中常用注解：

@RabbitListener(消费者监听器)



@EnableConfigServer,@EnableDiscoveryClient（接入配置中心,让客户端可以发现服务端）添加 `@EnableDiscoveryClient` 或 `@EnableEurekaClient` 注解到启动类上，将自己注册到 Erueka Server

@EnableAdminServer 注解开启监控

@EnableHystrixDashboard 添加启用Hystrix Dashboard熔断器

 @EnableTurbine Netflix提供了一个开源项目（Turbine）来提供把多个hystrix.stream的内容聚合为一个数据源供Dashboard展示

@EnableCircuitBreaker 开启断路器 对应的方法上加入@HystrixCommand注解实现断路器功能

@EnableTransactionManagement 开启事务管理  在访问数据库的Service方法上添加注解 @Transactional 便可

@EnableCaching注解是spring framework中的注解驱动的缓存管理功能 当你在配置类(@Configuration)上使用@EnableCaching注解时，会触发一个post processor，这会扫描每一个spring bean，查看是否已经存在注解对应的缓存。如果找到了，就会自动创建一个代理拦截方法调用，使用缓存的bean执行处理。

   @Cacheable可以标记在一个方法上，也可以标记在一个类上。当标记在一个方法上时表示该方法是支持缓存的，当标记在一个类上时则表示该类所有的方法都是支持缓存的。

