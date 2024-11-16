package com.incetutku.ordermanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class OrderManagementSystemApplicationTests {

    @Test
    void contextLoads() {
        ApplicationModules.of(OrderManagementSystemApplication.class).verify();
    }

}
