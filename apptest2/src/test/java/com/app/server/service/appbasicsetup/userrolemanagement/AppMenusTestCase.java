package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setMenuCommands("bpAhBMj0UOdQQc6Ny1OEKd9uyRUZa0AuLRqegC6bGYRXhrD1FD");
        appmenus.setMenuAction("cxdWtuEXaSVjvyQGg2TesqNNdYu3ExQyw4f1YFfJB60oEVjCMm");
        appmenus.setUiType("4v9");
        appmenus.setMenuLabel("e1FUxu1sY4ejGidxtYdChwMdpm53aljkUju2e0d8D8VVA61vqI");
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        appmenus.setRefObjectId("RGSiWz9URaAMHiijtRa6CKyR6qQD2lADs7FAPRMH0z0b8jvaYt");
        appmenus.setAppType(2);
        appmenus.setMenuTreeId("NV1DwArHuyqQJ5ghJ3BhYdnCeoLLsszs1JpAkn7VF6UD1tLzCn");
        appmenus.setMenuIcon("PS3nuM5DrB9mS2fVVDWr9W9VIOVtRpGlt8uXbNeqewNvjQFfkU");
        appmenus.setAppId("vSHsNoi2MnisvlLtJZcnTd3SNEA8MAgC5DX4J6I8egV5hw91B8");
        appmenus.setMenuAccessRights(5);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuCommands("DvCPahveUksGkAtCM0BTgckqmzF8aSibnex2DQAOFoxVNsvKCc");
            appmenus.setMenuAction("smvsA6Nbhjsk8eO80N22rl51aB15LhjQanz9XXIOKNLGKiCFcT");
            appmenus.setUiType("L8B");
            appmenus.setMenuLabel("Hk0dmuyNJLYtiJ8IKdJSq2X3HP8JOF8IvoJWo3jslyAZbewTzz");
            appmenus.setRefObjectId("LZYI8NC1BcfYgkF6vLfOuXNOw4P2xN8W7qxBbDauO3v75Nm2aQ");
            appmenus.setAppType(2);
            appmenus.setVersionId(1);
            appmenus.setMenuTreeId("Rc4DLleb207J2zdh1Xs2Kj2oInY4NIqWAYyc6Ygppq1AGzW2Mu");
            appmenus.setMenuIcon("nMmMcOOVK7aueY08aL1z8K26Wvk59HMX1ktxM3J0vS8CGhTjIL");
            appmenus.setAppId("4cActLKO2sLv6ZFPCckNtuzIbLdB8HnUlMRlfwjT9ysOKieQgj");
            appmenus.setMenuAccessRights(1);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "lMbENsx3SW3qUiVwll8BcjGEg47CYUTlBwoLsXFINAXhXKkicT7PYlIotaRubDnwNgqJykSc8z81Hmeh1DaPtgF6JSlwxDTAQ90fTn9DTpV8BgiBF5h6WDMvxyPNuLjzXXlbA1ZwPyWFX4oTqoE34C7P3HcSsAhlrarkE0Vo81enQIFDcCKfte99yn3rQFzndGP0XLG722IHOt5wtVJwv7c4gwObnjznmB1FA3ttfdAojzd1UCy7jevd11Vxfye7T"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "IvEKoBSnH8tv49yMIyne6kZmS8gA2MVh62hhp3x48qA2rg9pgVlGxJ2LFXn7kfhSoIPJXkiBekEWtFp7qLewdzT9quOjE8GrqWGdlCIeMucQyzU13s0VEPet01uR2tKMTiqCtS5XUSbJNfjfniO3YaVJDIb50Nr92EWaSExNodCP5KHB7Li82X6t75JNTgXJbm0shOPyHIHsc9bnB1chTCZBXdpUMPCxoi1PYZg4tTb0nPglD6Uh2fzkL1wNQe0vS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "Ar9kz85U1xDTpCN2UoInjK861Wmbq3fx918026vbNEbppEQSFdrZfA8QiG1UeqgrjQleDoW4bp5gG8CBfVTdUlH52Bled546DuzLxJWJKvieAs9JZz8GKlW7F6HT4Qd6LSSsfNuiyf8xkeHdd4mOQ92KFojWSKC6PRcWcZf25dvGY2dOA6g7wzcplw8UGFMtYeY5DcElpN6ItHInISnFnHkKPo9zLdRskSthxEyfOlWCH5oKwpVg1lU8e1aOSG3l8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "kWUpd09wYOvOoQGSMGx5ltjcoqqdajKDpLXQ2Gj90q7nwmGM4jVCuUhrGpLaD5nxF"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "uwWfFZTcU9ElEWOUf41s9hpA9syZDV4XeRCcP4yb9yrstGeXNvbHqfVwYihTUBAyehgPMAkmKYtZzKIHxErJ3lX9Yct5r8x8fNZIV95MxH5q946F54AW3riudJMn4YbOTshDC67j9Mvj11gaYoFTj0o0KvFLjLcS0mbq8NIQ0nTojBakNzVd7apw4eQnPDOcPyYifYF1GKqWc52wbghqrDZkj9B4CVLLzb3xzfQ6F7WHx9W4qFmFIYjR9bB8Tn0A2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "b3nO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "9MNZS9RuBwnOW8j6Z5WDFJdLdM5ctbgaLclI7IKL2qXza2mr4QCqROP409YMa4WgE6YZUbi5I56Irk5JMb8rSzM2ksbvN56VEPOtpxjwHR30tV7ONyZQlx5znLZVIxmepOzj2wyz1pOc45oe1RdNu58iWdBTPROdDfxzVQHybaXrXXCzZYvNyeizfaFI3WYpNaPhWUMjLyTprW3RdHUtglAsSnRC0LxfarJSsKTjT0jghTLCBs9hx4BMI8l32v5gJ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 17));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "uuEzUkjWb7AZdxG9VrUBg9UrnOYXW3B2fDuxDUZOj5YGrHXkkg2vvlVLit6M9qhBCj5RAJz5KaPXKdsc3vSTEbsSluj8CUToollWyDUue06exzxxzTdaZrvU0HuGGrcjoNF8SIWXpJigdGauPlJBPJg41REkfSt72qjUYeSWpLp6PAyPXY0QpCJz8axdSTiijcPLov2gSFxEOSU7qPhNUOMdjlfGC4eaQx80rgUvh9SHQbaepOHSiQ6we3b0w5dC5"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
