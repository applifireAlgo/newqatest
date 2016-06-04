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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("3Ng0H0bWyRQmtgFM8ByqXqPLz4VmhgjKyBkgFR89rRIvchRdv4");
        useraccessdomain.setDomainName("PjMEPJJexGe7HXmPQlA6uECYKMBrfY5gSkWpeX00rdI0qMKXhk");
        useraccessdomain.setDomainHelp("AhM6lrxagHQpZIndrNHEyJBUnF9lfazaFSdntG6w7b0DGJti8a");
        useraccessdomain.setDomainIcon("fjUu7l2fX61eqdtz3kFqMrLXNdlLHJLMnuTWiOLBpqp2SVjOrt");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainDescription("TTjuIzt5CeT4UTy7bowsUHkEsuWv0DIV48I1Gwn8IloREOJsKR");
            useraccessdomain.setDomainName("1Kt0PmQ9uDuaPxrwbTsomREB4FvKhxH2fQ8GGUgynv15xN0OWy");
            useraccessdomain.setDomainHelp("WWOaKXIWTj2a26QssSOnxgWtN4vIwbFaOA5jFVvwgiIqFTkvca");
            useraccessdomain.setDomainIcon("mKF42XTMY2sajyyTWJkQOBV4z5XqJFMhHghBsnTbOL6myzzPdr");
            useraccessdomain.setUserAccessDomain(88318);
            useraccessdomain.setVersionId(1);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 103617));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "LgzOYDsmb06kybhPISs1n2MQvS1z3ADpha7ijkZ6nox7AGBbYaRV6xdOI7lSgw1OzA67xvfDIECvY8rTAKRDmFZIIKN5pDPO3Lwo6cjCbtIc7LvG1Ec3byRA5EeW2jcDKOrb8RkTadlCnpte7Yq35028qHiD52PlbGzQA2RLolOaYIG5TleYxRZ5826B5oVOjOPesp1PeSMeTwKnQFo49OpWD7qv2TR24Vf72r0dXJP5IJSiek11HM9Oo3s1G1wFh"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "qXW5zL9kTp38tW4W2iOG0MR1Krxsgn3dKJZtEJOr5nEXVosPA2IaXUxFHtc49eao4DesLANKg71lj81Eyo3rXixZUrKuOohk1M9jyXCiOj6J61NoKgXiGppInY1SA0aEo4SwXCYU6HyiGlUbRVAAe6EVZcEhFUimtRAzonZt8j4ru9KJ0dbSSaV899UmupK0XNWzVFimBpexisLfSKj2Hlcw6uzgZ5aaa0n4NXmTPhMb5DpIsbuBpkAtnhtrdk4Ej"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "7dO4UWXgbuRbr8Aofe8sP8bPpEnxyUAYG6CBMwugSGanZd5OGb0BgRYID1hdhKwvnZ16AAjjmNHvWNYC1yyO0gWdF85seaiUhSEOddDeGMsufzfYnT0XHRPc0YnQUoVN67s8F4b73HthCRnkIO2GzM489C3PbJMtYRT9TRu1mlG1lcAbbGsgDGFpUlvm2m4oS4qv8SJOUkVLJ529Gpyi01zHVqZ86tgpa8aPEOT7EYe9hlYOXeOu0pcetguLPTZOM"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
