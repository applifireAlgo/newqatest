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
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.Question;
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
public class QuestionTestCase extends EntityTestCriteria {

    @Autowired
    private QuestionRepository<Question> questionRepository;

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

    private Question createQuestion(Boolean isSave) throws Exception {
        Question question = new Question();
        question.setLevelid(2);
        question.setQuestionIcon("JZeiuv0a0kyQSUQPEsuTTafUvm59u12G3JObCQ9BUZV1B4oK2r");
        question.setQuestionDetails("1bSKdnIfdD");
        question.setQuestion("fAhjVnyEuFe4QMKHfhgEZZ0LVVBdRI0LJO58k3pwe1Pc9OgX5y");
        question.setEntityValidator(entityValidator);
        return question;
    }

    @Test
    public void test1Save() {
        try {
            Question question = createQuestion(true);
            question.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            question.isValid();
            questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("QuestionPrimaryKey"));
            Question question = questionRepository.findById((java.lang.String) map.get("QuestionPrimaryKey"));
            question.setLevelid(6);
            question.setQuestionIcon("OwDrxGmF2EW0BK85siSIEBvODBBrfQwIs2Ib2sKmqRZTwcmM5d");
            question.setQuestionDetails("HOYvfNa0Js");
            question.setQuestion("sPLlrdFSUXP05xD2esaVSrG9BdwipNoxPMyxYpfCaAkY94OIWo");
            question.setVersionId(1);
            question.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            questionRepository.update(question);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("QuestionPrimaryKey"));
            questionRepository.findById((java.lang.String) map.get("QuestionPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("QuestionPrimaryKey"));
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateQuestion(EntityTestCriteria contraints, Question question) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            question.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            question.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            question.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            questionRepository.save(question);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "levelid", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "levelid", 17));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "question", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "question", "O6weP5C7RH8wyKFrU5mRCBUo5ztD1NdxOcn2BSEvohFTujJNTLtt8FXQfbibZSYZSQLKDIhUsyWa64iBdaZ2vgPE3GvWTavNUC4srRH3aBv1eVcBsuEgQmJLv5QQZ7Ip42O16fr29XZ2IQWLStN1sOO8rlywWRK0Qjor4khRg7qxwv3O0eh5t19iYygxuGukXteY3l1btuJA325jkUbv3ry7ySytXznuX8PRI8nsdByFPQTjINBfIYmjNA2WVyIbM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "questionDetails", ""));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "questionIcon", "1LWwyzwIZfT1B4O41DgJpaB1yKMLZ3SYR2ozGBg0HZCCkT9yxZIVfMyQQ2UnKG8LK"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Question question = createQuestion(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = question.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(question, null);
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 2:
                        question.setLevelid(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(question, null);
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 4:
                        question.setQuestion(contraints.getNegativeValue().toString());
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 5:
                        question.setQuestionDetails(contraints.getNegativeValue().toString());
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 6:
                        question.setQuestionIcon(contraints.getNegativeValue().toString());
                        validateQuestion(contraints, question);
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
