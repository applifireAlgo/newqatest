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
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setIsLocked(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("iEnwmERfLSczlLfEqjsBMSrsAupShOakaNSd3ymPAQ3vjc4erK");
        useraccessdomain.setDomainName("uZk5nR6FrBnKgfO2cOTBLkfsUo58fRdJ1ntPFMXuJMnxJmyOsF");
        useraccessdomain.setDomainHelp("wp2df3liprUaskVFSdhotELs44u9Ie17Go2DuyhaSANOpS4AX7");
        useraccessdomain.setDomainIcon("bfU3YbxEDI66B7PmzVH80UNGvG6jhrtGvkqPUudnG1H1PXjKWy");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("AFb56k2CV1hZfZ4QONGAV4lWRwPdpFq5YuVaZ8DHqVWkLLxgUR");
        useraccesslevel.setLevelIcon("ctYHI5Rb88JUo6egfaM2zGxbt139cflxi3MkaQkk2C8WCgO7aK");
        useraccesslevel.setLevelHelp("d7uqPbMJU65Y7xW7gDHNahecHZRYRq0ZL6bqeLmVdgVTePa8aF");
        useraccesslevel.setLevelName("zggGnrSul1I0sHPWK0SPqffgyymPsingsyV1WVcWSWrjhsV06k");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setIsLocked(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465040639066l));
        user.setAllowMultipleLogin(1);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465040639066l));
        user.setPasswordAlgo("2midHbV3nTu8l8N344qtMZkrdDEvtWhpyNxdLKQnc4shYjXD8P");
        user.setIsDeleted(1);
        user.setSessionTimeout(2381);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setGenTempOneTimePassword(1);
        user.setUserAccessCode(46686);
        user.setChangePasswordNextLogin(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("BKlV8Um2xFOSUFoTZhB8XxKWwVwDmNbGzZaw3pfKUfufF7Qhdh");
        Question question = new Question();
        question.setLevelid(11);
        question.setQuestionIcon("WUTu712u5FWcRAqHYC9XU4ofSHb3w0U7JtXJrndldrsX2kZpVv");
        question.setQuestionDetails("XbtMPZqujc");
        question.setQuestion("TEj9vi3DoB2PMAey7pOAxyTsC3Y02Nb7758NI7J3UImsrZOZs2");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("PkmDZq31u3ra4nXeULD11s0vVnthgGArLfCHrMMqNUYi4KicUe");
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("mmqWaSNpLiFpEdC3W9ZJhvMeVYqrjGsh");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465040639569l));
        userdata.setOneTimePassword("yTvPdYiBQqwYIpjWdSpVuFnI4yGnNrSD");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465040639616l));
        userdata.setUser(user);
        userdata.setOneTimePasswordExpiry(1);
        userdata.setLast5Passwords("faNosZKna7Wb4ntmoXwDyux4Yj0lEXBgMgHVyV8KFjdW69y5Zc");
        userdata.setPassword("XgZYinB6VRkNNcsokjv5rOSwZN5O2J6DrUTS8FWvzkRvRh4IE9");
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        Language language = new Language();
        language.setLanguageIcon("FsdVzQMWvfbGRN2YU76FAlpkfOXDAXJOa0GRXFBuHIeJRjsRw4");
        language.setLanguage("sF0aclQSXgBK5a3iYsFNsou408VJjGju36nIwvDXjSUJXEMuKX");
        language.setAlpha2("Yy");
        language.setAlpha4("PqMA");
        language.setAlpha4parentid(2);
        language.setLanguageType("oPDwKep3AF8DLhg5Q4JbN8XIMEVzgy3g");
        language.setLanguageDescription("gBamVI9jMtIcqgwyPxgHKCOERJgQXfkVH10RVy3Ckc8qovVj91");
        language.setAlpha3("Axo");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(7);
        timezone.setTimeZoneLabel("UCVmrN7Eqe9AqxXCTjkfbR2XRx2dEdYJIh8JhrVXDH6N3QutjC");
        timezone.setCountry("c3jLAvrCxG8pgXF4RFzvQUTvmuwbpHyQHAg9I7XVCrwXg896P3");
        timezone.setGmtLabel("77XW5jdp5UAY6gBOAO8v5ksXdTvBoqCAVrtcZ3VgkWuzLfB0Mv");
        timezone.setCities("K2kjzDeStBaIuM3vN5AUtEnbBf8a1xkUsHpObnaSYSQvQYa0R2");
        Title title = new Title();
        title.setTitles("srBlwZan8h0DGrGRliXM2Hy9bM5AjnR1jUwrduVMBXUmxWsZTP");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("E4Xq100jEpott3R362z7KIyFYkBbfvRPWHb7dRNJGt6ceX4Q6k");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeTitle("Hnv8mF94lDtcPjYcaHrdMA4qFvKfuAW34QHE1n1hmWzMc6hev6");
        corecontacts.setEmailId("jL6YXBILqnZ7SZMY1sUL6QGq5gRK4iV2OdHxIroHOOTos3zrxH");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465040640260l));
        corecontacts.setFirstName("J2hoo7qjClYWLRtzvu06yp3p9L1JWVuBcDzaFEQCHBbt92sXXs");
        corecontacts.setPhoneNumber("G7txWqZrY0b31W1xCsXP");
        corecontacts.setNativeFirstName("NlNKZvapWB7dDp0YbUtM2YvzU1xOjeBO9e1CCnbE6JaDgU11jv");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465040640260l));
        corecontacts.setAge(92);
        corecontacts.setNativeMiddleName("XCrJuCeQ8OCS68IY05HflG8XPCWXNaBYIT1D52CLTO9L0Odj6b");
        corecontacts.setMiddleName("Awk8P1QhXVdS8UkH5OaOhkqA3lVUP8tnqzyYF2PcCSv2yv5zvt");
        corecontacts.setLastName("27WmfIkFE9R2T8rR1vF637lfP6ka4seymWILLtj8kwYnsXr9La");
        corecontacts.setNativeLastName("dJgxe1CjzKIsmS8vg5SnT4WKkjzUSQoUO1WG8B9WANby7WG4oQ");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress1("z2eicXgTyB49TxBCYP9UtSskdRllD3kaLsb3Y6GO1YFI8JhYvm");
        address.setAddressLabel("QHE5ulrecYw");
        address.setAddress2("2zn22bujCxCdRduKD7lD1x9K1CLZp8d90nkQb80ANh6RrUkkne");
        address.setZipcode("GmEUkO");
        State state = new State();
        state.setStateCapitalLatitude(1);
        state.setStateCapitalLongitude(8);
        Country country = new Country();
        country.setCountryCode1("X4J");
        country.setIsoNumeric(614);
        country.setCountryFlag("NaO3DzV33Ga6OMdm1U8XhGGKe82HPjieh9WSeGWxpOSH4vqlyb");
        country.setCapital("jWLo1W3ZN8itT6mcDRp5L6EvkPEwPiH6");
        country.setCurrencyName("rrhNtH6qbKcRkTP49fvUNbNqwz0YxGpC4fbdM4uKOznuTSZhjQ");
        country.setCountryCode2("gbh");
        country.setCurrencyCode("d6v");
        country.setCurrencySymbol("vNTAAQqYQwCbViK4zhA9de4EbmyZjaCz");
        country.setCapitalLatitude(4);
        country.setCountryName("dv1y0USvw2B7JrONepXEPb9XGARq96AavkSB2GauIGIxTacozb");
        country.setCapitalLongitude(8);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLatitude(4);
        state.setStateCapitalLongitude(7);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("htqArBZiUThYQn4L38STlgIAKUWurswU");
        state.setStateFlag("wk3R9d6szriHgfxcEDrewUyoauM55NnDU14H9695ek5LFfE736");
        state.setStateCode(2);
        state.setStateName("uziIGgNgWrYtN1zy8JbD5d8HGNNZaXO2d279DoTmDl8bebltWP");
        state.setStateCapital("z7qLbdVOSltE4yr0Ajv6MGc2khUURm5vmd5k5Y52AeLGcgvqgb");
        state.setStateDescription("8PUaoq9KXnRd48ykW0LOInbVwJlYoN3bEXG0lwqHdPQJpd3zdB");
        state.setStateCodeChar3("ZLeKVmBJrWZrI03q9uOlHy71mBpgIVuP");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("ZrLI8HcdzXnekrD2OQ2t7Gd1N9DsIn6HePqpOFgAkPpTdv6YrW");
        addresstype.setAddressTypeIcon("cwpX3Sqe6Q00GQ6MbkrJ77wrnPhwPxljzxPmd4PmBsyDqqVO9I");
        addresstype.setAddressType("VupNzFSttSQz9LizSgRxMkEgm2ElNi5jk4Bo3KnNHuhdDzTGDs");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("m8Nhhso32edLluciQIkZxBaO3cHvv5m8GC9doC0hIF5tRVPKBr");
        city.setCityLongitude(8);
        city.setCityFlag("elJEIzKRSN4JqhyGaF065ZGOH6B19d5flZi9YhAsZNxVX7U3Fu");
        city.setCityLatitude(9);
        city.setCityDescription("jIiUqyeDuwFpLVmE6LQz6U8PAi7KR7S94NBw3OXBOM7gLj6PGA");
        city.setCityCode(1);
        city.setCityName("4ENsAvdRTweg49POGsNAhV9V5yG2LWCWFsuJIAUyeh9bOxiPEe");
        city.setCityLongitude(10);
        city.setCityFlag("BqkwdDV3po2C0IhFqB143HlOLJLQ7IxP2pfpq9A9heCWUnQcXM");
        city.setCityLatitude(2);
        city.setCityDescription("B27NqRyt5nkUNVhmzZfZwhdFMRCrww0bPBYwl7Wzxx43eJvZMU");
        city.setCityCode(1);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("cvnFhPHdLsFLWbKCwmzaJ3gPbR5NYEZw");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress1("KWOGYg9zoSzDqCWoSDlTt2SOrTHGLdLNvyCC7wpLAaQuBn1vp7");
        address.setAddressLabel("vpJELBZlIEf");
        address.setAddress2("mtleKi3H7czhC3xd9bLNUpqLEdxOpixYd2gpH22I7VfH2iiT2x");
        address.setZipcode("fYsMsF");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("5glCehEGmEl3ULKQxAAiRjwXV30tlozDW01l9wRz05DXCPtaYa");
        address.setAddress3("hTrSy42HHUrIs2IjYvPHVUFufvmUzvwAzHTkRemargYNKglNcY");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("tHqkwX389qwP9IMcjMY9vI25lxXVj91y66x81J4rqosqAeAsFD");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("0WLxWKOfdRV4owTNOIrjB5GfBnFsznTXGUfMzWKtRfD5neTiyQ");
        communicationgroup.setCommGroupName("ZAz35VhUz3WgRIgOGJnGlXxOUfvr2s7th9zGgygasvdyRru2N7");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("0PhK258ob9ArUldQ1CwQl5grb1Y6yANHRXX5DIIhhrIUYaUvXI");
        communicationtype.setCommTypeDescription("GsDh2mCqxJvGlq9Ll89cX8XCedw4LqlRd652icTWxLkjJnn21R");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("Hv8S4MI2NXLypNKaK93sefsw0Jv1VaBWe51cI5pNZcQxGh9qpY");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("3CQf7u2v61");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        Login login = new Login();
        user.setUserId(null);
        login.setUser(user);
        login.setFailedLoginAttempts(4);
        login.setServerAuthImage("nfIsMBo7bJduNBwmsa5ir6EPwSYrI8A4");
        login.setIsAuthenticated(true);
        login.setServerAuthText("HLUIp1FOzuzG1fbX");
        login.setLoginId("4ywuO6GHpcsGJhwZD6MoKVQQuBBHMqxwCaRFcGoq8bXcsMLBuC");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setFailedLoginAttempts(9);
            login.setServerAuthImage("M9ayy4rJCVrN8QjSkou0HydCcKewc0dT");
            login.setServerAuthText("3O1PR7GiP6XS5K25");
            login.setLoginId("7vIwz50jHDnqpaRhjYvEpZsX0JNTr4coTtQp9asAjV2Tw9llv4");
            login.setVersionId(1);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "ebNkeSWnrUobACVFCjhTwwwW2I48iln5igp6JZEj9CVCM4QLLVWKRtaLFRaH9mWExB3DzCiucGHnN4ES3T1t0t9nUb9HddeCw4wnJrTqqGUphnwJBnJgadJnhAFWXUIyn5WAMeeOBuWIYnxt5oVLxEkQzC2HLh3iMc8hrSDWYsZdFMXp3BcRccWN0YqQgJmPodeqjWld5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "LLAvEJ6Gojg45RPLcOwe3KDYSA4f6ht2C"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "FyohyDdngtiUEhNV3"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
