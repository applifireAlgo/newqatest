package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setLanguageIcon("YZ9sc8N5e1w6Lbx0pWGCalNw5GNYuPjOcSpsRNKlc1Jx29FsGe");
        language.setLanguage("SlhDbCoapKLHt22XVq1pULv47WpKsSZEYW0CJKsjGsfttVrrUZ");
        language.setAlpha2("BO");
        language.setAlpha4("lrFh");
        language.setAlpha4parentid(7);
        language.setLanguageType("HSqDKNOitWJ3o8ZXZ5Omz6N1iIKlvpYo");
        language.setLanguageDescription("2h4U1552FH0tori4jUidADm9LiqGge3RwtokpWIZ2ZzLptFGXr");
        language.setAlpha3("nFI");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(11);
        timezone.setTimeZoneLabel("qbfRbuw4Oj6DzTY4ow0puRnDllX4IjHc9yvkm6Z3zbbizBxmfV");
        timezone.setCountry("tngwhyB1qZ0sM4AU1J6uoQ4RO0xc4RRk2xWhQqai11bsdzrSFO");
        timezone.setGmtLabel("gQed99WX0SMirS3CDMVqReMAWQTIajfKVqXESAoR4bp0RNhMSr");
        timezone.setCities("CxfjdNlWANmdSrDbGXQvRqZQ32kro82DLP6ITkOlo3hxwP61dC");
        Title title = new Title();
        title.setTitles("xwwE2XccFF2FmkR1ntAqm3VrfAXgTSLeyg4ErGMBi4wEkW0qai");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("SpSSnSqSuAOesnM4phYLJlH9CECeJmru6AeTqhZ8k1la86HnMb");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeTitle("0FMVFAG4h6aUR2ZyBMKXMh4L8IjASBv4QwVKvPx5QvujeP05kf");
        corecontacts.setEmailId("DAWmf1x0pOqA41OiSB7hl27HXAxkqc63Znrgidxl6eSQJbylSW");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465040620764l));
        corecontacts.setFirstName("mYzapLMVGsAYQCLasQccsvkehkXA66JrGKeA7SvyyU5kZByviD");
        corecontacts.setPhoneNumber("fwWSC3NiXhNpnjyUw5DX");
        corecontacts.setNativeFirstName("qRE3g8EbY7pKIsjn1Q3BmygIkuuvCufTvsoPR2Mj2nMAp5CrEf");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465040620764l));
        corecontacts.setAge(84);
        corecontacts.setNativeMiddleName("hRcfRi9UCDYN86YOd0O0eWbTLPf8CRcUW5HxSqUeraLLXFmC99");
        corecontacts.setMiddleName("RZps1xM1U55bR2UDPSdbFxmOfxlb5IaOh9vk1ZYDrVAul3SHBA");
        corecontacts.setLastName("P0FWf9vqsS1BVDEJ0LHhmp6hb0tvNbu09TKwd7VAu9TNTnp0R5");
        corecontacts.setNativeLastName("afwxoE0q42IJAwSy6fGImECXVhaqMzCfGTgmetmjLwqeM4UmNe");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddress1("C35QDEyXMi4fWAug6oGiKWJ4MUstrADByEossLqyfVJmCqoyrH");
        address.setAddressLabel("zc1mx4xily2");
        address.setAddress2("NfoknboRpKbYVQVkyz7UmizlJ6kCAfBtobtzryvCeOaUV4Z9zL");
        address.setZipcode("arHzrw");
        State state = new State();
        state.setStateCapitalLatitude(1);
        state.setStateCapitalLongitude(11);
        Country country = new Country();
        country.setCountryCode1("7Cm");
        country.setIsoNumeric(549);
        country.setCountryFlag("PvXziMgbnhGCaPpYmMe6UpeLbx8KI337vjDbUUdDHyIguJPNV9");
        country.setCapital("IKfhcMlGN5NEISa4WYy2mOphDKkZ6uGH");
        country.setCurrencyName("IMvBqRaK2zZK8IzJZYKJVZHyj6SX8MaZmqm9go6YUrv22D4tVb");
        country.setCountryCode2("dHD");
        country.setCurrencyCode("oUI");
        country.setCurrencySymbol("mQj7uKMrqhXGciTYh03BqHQ0MQ532ULM");
        country.setCapitalLatitude(5);
        country.setCountryName("fDnw1MujUjo4G6ihiEfrdbwRULiDqd3LmFQoRQTqOkJYHzkPwz");
        country.setCapitalLongitude(11);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLatitude(1);
        state.setStateCapitalLongitude(6);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("x5e62wO5Msm3UWnxtAbIy58B4EfNwBMj");
        state.setStateFlag("gArPze58nonQTUdbue8invXGg5hYpHO5ZGMoOxpoGTdoRo2ZBB");
        state.setStateCode(1);
        state.setStateName("zstZNQLtGCBy9UhyufCmXA9hCNrtjQHj79BdAY7M1dOppaEOre");
        state.setStateCapital("71Yj5Q0Y5a4msZBRsDSI3og3JBWuIWE7hQ8X6OGIn3T6WsIpQw");
        state.setStateDescription("r6VZOtLugghOiGMWoMEPhd92WIbqUgB90mLrD3nIVcCJCSlS6P");
        state.setStateCodeChar3("StTpPuG2saO8fp9HAODMgw6nbR8dUHvo");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("UucI2XpMEBT4t8K8gcStoNdnCLRCLUFiSOCAtJoQ4VVAxPZRxb");
        addresstype.setAddressTypeIcon("AUA68hGLHc56IPI3qD429quEp2boYygMU6jeTWhTyhVWLjnDFe");
        addresstype.setAddressType("ObmGJnZ4MbIJb92RT7sLlEYLAWwdbgFgfwcGUPl0XhJnq3jQrR");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("5WPqIwDMFQhy4JsKKkXdUs6gMjDMStBQGHj58RtgTHLzv9eza3");
        city.setCityLongitude(11);
        city.setCityFlag("c2LX8whCrYfUyBtXUOFJATe6EHHYIxvnkIxy32SOF1gF7x6Tri");
        city.setCityLatitude(10);
        city.setCityDescription("kciWFfJN3JE0SFNTBvLll0Sl1nUplN80SQ0NQb1eL6EZSNNl8H");
        city.setCityCode(3);
        city.setCityName("fEROGdQF5ELlOECgAG0BBVfhAY5sLP8qyP0jcBNKGMpbqPCikH");
        city.setCityLongitude(6);
        city.setCityFlag("8lGgqGghcrk9K6V2xgHZ279H3VGBLbm8quO4FJfAyyF59yuK4R");
        city.setCityLatitude(6);
        city.setCityDescription("tgQDDbplFfZIH8VZs7wUhxgkaMNXJEbcZVJXnDVgkY8ArUjeTc");
        city.setCityCode(3);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("EPKcFb8E0fYShMIXtu9cyf3cygeQanrZ");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddress1("MCewEfMvZpq7UX13l91ReTl70LEoOsGCIj6nNJ7nn6AAosZT98");
        address.setAddressLabel("lBwEEJlm5Dn");
        address.setAddress2("vux7sFcFFZ7h3HPgFH7EofdNIWpD9453Oy59hFUYLpyXhxt2Qp");
        address.setZipcode("ohCvQn");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("elCCwLLcTAoB6twVbg1yRypEhXJWOoULNX9EcOiheKbsyRA5jG");
        address.setAddress3("60btdAmZ75giI4J4OYI7B8lVEsy2eFUY9r2810gXnIkNqlQ9ta");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("33JTG5EgrkznoCHlQ0Fr18GTqbfq8f3HeLjWHXGm3F7S7GEDmw");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("tXo3DesjwO71M1HIxTtIDSsMIr4J2Lje7Ivrz3VogzCjgi35Xs");
        communicationgroup.setCommGroupName("V5MpnZOzuxyAAaOClPWezgvNDXeun727J23DqDSQMjwqBBOt4M");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeDescription("LehOp1zg4vU96KDD0yQKxEIDRZSL55lWTbssWMHzVHHinueqo9");
        communicationtype.setCommTypeDescription("WTNPQWcks0ei7cMsnXe1DPy1zOUGrtKiEjiAEDSvuuNCiOvtYa");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("UAimWqARiE1XUOrqfCIj7mM5qYczMXpAx8VOnNLZA0zBd7d5vz");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("BWUV9cvSB7");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeTitle("xQvffHQKyzkQZVjt1Kt33eiXbMxrEWjS8lCt1U42JWpbKArkyI");
            corecontacts.setEmailId("DvVCJuQMSNGjgx7Vl5MktvxpqN7shabEtUaP7qlL3Qzal0DFkp");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1465040621730l));
            corecontacts.setFirstName("rJKqd8bqGj5TjDwgj1M57OMWAXNbPI0TiHdo0qLGBFVoSDBHjO");
            corecontacts.setPhoneNumber("k9oAOJ2wsQ436J5cTPOd");
            corecontacts.setNativeFirstName("BVrO5XZ9B3loJd3C4lSbrPRHReINxrWWHuUmEIedD8MnquNtpT");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1465040621833l));
            corecontacts.setAge(32);
            corecontacts.setNativeMiddleName("Eq1DC7NdOay1PdJTwdsPsUv2EW5rINmCUXx7xCJkxCnxI92arz");
            corecontacts.setMiddleName("sgiMkvRX9KJitMAY4uj9nt9hygc9sZBivTrEykDPh1JFxeyP2y");
            corecontacts.setVersionId(1);
            corecontacts.setLastName("LFXXvrW7BOjSvc6DYblL7gW7O5csuG2ZikT8tDUUJurzKcKFts");
            corecontacts.setNativeLastName("XQ4lz7cv0ietJMJ5rkvFjs3pqFkkv8PWcoL8UVhzRfJNvhMmCt");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "Ow06egmGPJ4WAuxIgb8pSwh91Whl2QSdYdNunILE59u3HTMoTcIMzThXExL3j1lwLE1UNtG6gRw4had8kmC9zfLTNHan8kl28zA7qnhIf7BZEwMASexqUcpDOSiyWuGN1EO80XowUrDTSfNbRIvmfVSwxTFiRGVLQBDViOSMnDFPRa9urIrwbUXQ9Xcs9hqkfKD5kJyAbmy1phmFDnf0e2qUkqBpO8zy3ptosSyCEOZO5TcAbaLSVSMvcWN7IsekE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "vZeS0ZJT6PA4Q1WzRylfNV70p4cM0f0ivwB1JHDAYivffHIUYkO9oRACbvhHbsseRJB21Qm9tT4cQrAYxy7JvAgO8Hdgex508vqMrLdf3fscytSNNQDNXV2DoquzoJSxXmwXV2G8zzMq3pe4qDjzKuVDkj470AZNyhTEZY3GfCC40PFFpOjUVBfenlS7z4swgUZcCmFdLXGlUlA46njsb2e0ljFkP2o8fZZ5vHrjhrQmUzft2rypZVnU10UGtGFtj"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "8Xm5Cx540qcLYcTtbrQjDEMzFZlx414q62Uh7TDUroboSMWHKOpQeN2MISplYxj6rzHkXCf0JdhC5x4evkeFzzjGMbrSUs7jl8croFarQJwhqzsCaT995zbm0QdYCuKrnbAYgmXXBPfYWn0PEUP4BHxY1VbBAx8OOhwcn30DLMWRJ2IHLGDSc7P8SVCmxcyMg5STA1scNGvvkApGNdq240dqzaDCzvGZ9NwQ1umam2V0BhRWoSex6DdePhyw2lCr9"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "celWaNz7u9RAeOJg9S0G4xa7FYrMCIOZVLtlqP59aigN0pNeakmAN268oy854veYY"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "dOqTyigKSIbnSdimthL3mAl2HW6uLV2pc7wB4Ub2Et2Z1rFa0HCoOqFiixl8k61bT0gMo1RMNoeV6aygZdNuswW5XUS5pVSnJ2SqwCTFB8Hti42JACb2SOH0DTPqONX3r21AWIu2JU91qO2GvPmGL9DjMDKrZVuqLT07NLywiHVcLmGfY0P4F1DUTiwsh1fDW8llZG4lHYnrRunDZ0YYYH6MxttHIw5Clm1YgDTJOGXcjSumEOwoCvCMuA1NS1CJ2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "i772gMrnUDLahBu8Veu6iMYn6O9KuLZMUp3iuWVfhRSCOxVw5ut84tQJjU3w19VqyfWbxBQw4L2HPk5g8al4ZqS3mC7SIdbHL9WFCYriyF6m8h0z5derBYc3i2MMrz7RrFFgJQOxRaWoDyTFMM7fbzOSU4CDUA0nemC0iciRc4uYCTvNnbKWqodDHCDdDGDyZK81rXlmXtdxgL6iSdnnu4mGqoGGJhLcgbtdMNDoiYkgyiUOdBWw5W3MwOUVhEO18"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "fxHNVN1oZj8kFXqz39HAjCKIwz2xmy8Uf16uUgNjXWDr0z5wk4D0heQbTFt8viPN9L6vVR87WBJDKQc0yt2ZXql7i3jcQ54suzDv5s2ferJpaVSuqpgl7haNOpKqMApwheFsGrlqnLb52Xn5FBh0B3ZVjUSs4DUgUtatTsDDk3ww1qZ8m2AlZmvE0Xuf70xjta5EKLWLJXYUeaprZxDOXJhC3JkdesoI3bZkNguJ3KHPCswvHpADA1pQSLCgzfVWE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 132));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "HO7m8qeoQUjupjXag81CECAJMBKrQCMqdkG1tV1u9AiQsHIisfguxpKOrhf9m6tFb9aDgDL8W6anXg9n0bpB0jbjbCD2FKu5rPFmePUW2vzxEXv9LzCXJvgndJWFMaJNkxbjpTfi7IpmqNSJ19zt8L2CJawmD9TryTUvm6CCb6qgasa1OzQnflmme7vuRZWSzHPVxZ2GJT2roud1ZKfRyQzBrcVGEzfrYWBkCRfcXBByqMfpEM2OU9EvBYRfp2GBg"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "MvOpTisMhiNrwh5xdLvMp"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
