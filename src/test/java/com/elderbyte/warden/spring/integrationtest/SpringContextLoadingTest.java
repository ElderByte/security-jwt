package com.elderbyte.warden.spring.integrationtest;

import com.elderbyte.warden.spring.WardenSpringSecurityJwtSettings;
import com.elderbyte.warden.spring.local.auth.LocalAuthService;
import com.elderbyte.warden.spring.local.auth.SecurityUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationTestApp.class)
@TestPropertySource(properties = {
        "warden.client.enableMock=false"
})
public class SpringContextLoadingTest {

    @Autowired
    private LocalAuthService authService;

    @Autowired
    private WardenSpringSecurityJwtSettings settings;

    @Test
    public void contextLoads(){
        Assert.assertTrue(authService != null);
    }


    @Test
    public void ensureMockNotEnabled(){
        Assert.assertFalse("Mock should be disabled by default!",settings.isEnableMock());
    }

    @Test
    public void ensureMockNotLoaded(){
        Assert.assertTrue("There should be no authentication context when mock is disabled!",SecurityUtils.getAuthentication() == null);
    }
}
