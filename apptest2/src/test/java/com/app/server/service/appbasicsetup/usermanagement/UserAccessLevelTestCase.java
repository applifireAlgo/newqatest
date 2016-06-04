package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("KFFrlBbpOP1NXT8ErTPhcxEtf04484MXeyO9CmnnofSYMDkxJj");
        useraccesslevel.setLevelIcon("dA3sRpT60MaS3o6AIvVjcsK07YaqEDxh0oWAkVnO8hsDzrLq8f");
        useraccesslevel.setLevelHelp("8yMyATP8HhzORClSRTSTIbqqQjwBY6ZkZ4TkvMhRNgPJ86DfeD");
        useraccesslevel.setLevelName("oLipbKSMAsMEVgS0XHOpNpVoMO5o8uVAUWI4EywZbHRJIkXIzN");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelDescription("YU2J1ILKXSbcYHQefqrTfzWmcKBULuNJwCP7TYerZvZYlx2gQW");
            useraccesslevel.setLevelIcon("PwlggqgpJl4hflwyVktelFJQuTVUu7gjxSEs0tNHhdmVOMLJQw");
            useraccesslevel.setLevelHelp("BmEu0PzOw6n1Yce2nCmhVhyo1cUgICQcBFiUtS3JI5xZd3YB5Y");
            useraccesslevel.setLevelName("Max1yrzHjYwlhPHH4LrsuAPUBo7gzps8W4xr29eJkaklE8MU9L");
            useraccesslevel.setUserAccessLevel(52853);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 147896));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "zARcG0jCLybFpF6D00aJcerolSZ4O8XAn2PntJBbi0L96OLXT7pe4KGqvNi6H5kmIjLrmiId2TnWXtehcIuzW9I2vZWVXTMVvOHoP5SgKBhyy2vf29dNCqhAiiigC0yO7rorgGRS0scgeXYc11yQV4TysfDs9Frlz0J4agfnUhnvBhRM5JZIWZGQSmwAKHMbPkgn8VcDPiP8JnIXLpucnQLrSzB3xR7qEu2v0q3usAlWQFeGC6PXdZbXEKA5WQ4L5"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "CfNlj9BZqtImo5ZipSIs0dTRDQpJR705LcGsqOQ1CGNSoTPcCrXZczZWcMT7qiYPW49zMeiLZoo8H1DbakHkfhh7IPGmgASPLuBBMrfS1ogWwfDMhpgicfSyWYg4hWIqqNBXAnwjPjSShJJGZaEOzYsZWPazVZgmsrgdgbLx8F9y7wZGeN6eKXUbcZ1btN47eVvLNJEsqTqq67RgiUaACHjZwl2VNciFoGkRDBZ1DWu4K3NPXXsZQ4Pzo50bEgAEY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "91Br53YxgugBRI9etfNlopV5jZz2Lqvi8s7bMZojBdqoVwgn8Gvgh88UjnG5rTwBt8BSzCJkbVtz7aYNaC4jJOXXDHaIJDlzW56zeK8yl8qNoCqZwbC66Ms9eHddax7TIf7S5099AD3YXhk5AP2NMfrd56e4DOJKm8QzEnBNpOy2CDhCrnmhntmBcKkZqBPALx9F9mVecRRiImKg9RwC079gx5i3uEfSyiNOAGpqDSTgj0n8bZnaQPa1jLjgzaIDq"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
