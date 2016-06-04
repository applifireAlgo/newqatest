package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryCode1("CN8");
        country.setIsoNumeric(386);
        country.setCountryFlag("OQINlZrGohuZFq9wUmhswgoTljbjGGAIwMx57mISLHqljYJNro");
        country.setCapital("dizYpyVGXXACtFe4gnFMYgkQtqKp640X");
        country.setCurrencyName("Fg690m6BhCK2liDZlmEz3AfK3Wd6o8NZkUuOamI9LALz3TYugi");
        country.setCountryCode2("NQj");
        country.setCurrencyCode("xaD");
        country.setCurrencySymbol("udaddIZXywy30nrIZ76YHzlrCLZsAx3H");
        country.setCapitalLatitude(2);
        country.setCountryName("H2gU43h7DMvrOkaEcxw7KVawvbIe7hm64HpUN0eLp1WZoMH6OK");
        country.setCapitalLongitude(1);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCapitalLatitude(2);
        state.setStateCapitalLongitude(7);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCodeChar2("8stQeyOU788UQGFltIgUy8K1MFjqkzlX");
        state.setStateFlag("gokO9j3BFMLH5gYejRv6plPFzq43dNMgRCIbPCQl5hh3RMeLUT");
        state.setStateCode(1);
        state.setStateName("kqtSAjKgmBS2JMOE8eixpWcM4tvahc2AH5eBX5sYkFPzqgQ7eo");
        state.setStateCapital("04CZgpwCKbfKOTGrIJnTUD4TQgUWK6le3EkCOCv9Gm1qz3nXhk");
        state.setStateDescription("4U7v7asZ1PzLeNVGTY9bgzSDn0OGEFthVUlSncddNc3OtDx6ix");
        state.setStateCodeChar3("wuAXWqapifDuz6oIdj9srNHdpc1YToHS");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCapitalLatitude(4);
            state.setStateCapitalLongitude(4);
            state.setStateCodeChar2("6VoqpzaiG1aLFNhrSDqVr1JAOO1TvNwN");
            state.setVersionId(1);
            state.setStateFlag("fZ8VnRbLA5xLhDqJ3mguwbNDAs2TUTKkyXJh1zBC7c7N4XbUjd");
            state.setStateCode(1);
            state.setStateName("p4EkFwhzy7PjDvUVOYMNUo65fw4R9GYPTgYSWXedTVcR7ymF7F");
            state.setStateCapital("IfdKErfhrHZUzEarAxkmLEWiuPOHzdbcVQhCUz8BNwF5HQv2f4");
            state.setStateDescription("rcLefUbGn2NHZMrpmwqTUJS2KOpmXMdewaX50ItF4l9vNlMoI4");
            state.setStateCodeChar3("x1MacGJgiLXAZH76In9st2ZuUuo2dF59");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "Ub3pDAn5GRk3S1sbDQYJF9ZDLJNAGoXvo4olqGQZIXGrqlWduIvQFjSWNEeMxGzfOlyLBBqYsVzR9R1mMNEifU8xRhqBCOhfpNzXG549g29NoW1T84ZeJ4qtX3eqowQc7UBynlYMSzwMg3DgWnSPtsO68LCkTaIUpcNKKayseKcKxOlQNPRCl9mZtaLqFxTHWlOGOQYn1e5gifVLfBumCL7yoEy9sVFt5PL2914wlXI4k2gtyrriGuDGvznUoMUO3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "Ol3P9fvdHL55agarRYiJVA5FA1wk9nyAl"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "eo4ru66hsrm1TCjUpn8MjdGMRjV1DZxoz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "cdHvByCEDRxiVsXlbXDgwrbowbAA37yqGepwi4aa2xCnNw84HxN9gVt6TvqI2JyKti1XxwuEx6KYEXg0n1JaT95IpisdQeiG2kHW8sJLR8ThsO6ugXVrrCMDGchBNaZ64qfUlFFO7MNEqSINCHdESGndjGhDMeLyOywwIihqKqXKknCr0AE3y8iV3g8vxAZQRrU45nZEGosCd9ztGEQVP7FW5RbuVCc8EHbPWhT64Q9kzTk6t2MDxxDi7YqY8YtBX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "cemNzHli65ol9xVu1lqRDbSz67nRXJa9uM2aq5FdGLKD6zZWwmD0rLZaNpU6FOAGFPXABNtBm5AiZEoAaOvgOfZNGA7TMci5TrBB9IMvPIxPuvwXJ8B4cVFjxSMgYzYeB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "m4Ucr4vTmEYureY4rAmJjOpOfdWNEQqN1nG5huWE3tB8yyQFIrMyXjxlzxemLca11WrVcx6Pi021ZEEkeucNE6TOwGkB3Y9VmV8Veyigid3aKMuBdLP5hkcaBS4pLeuto"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 14));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 22));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
