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
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCapitalLatitude(11);
        state.setStateCapitalLongitude(9);
        Country country = new Country();
        country.setCountryCode1("aqV");
        country.setIsoNumeric(359);
        country.setCountryFlag("Urx8iK2fryKgxLTzok5IL1o6jzNHSJqexyQDgD9deMdlQoTI2e");
        country.setCapital("zQt7anGlwPvNy68V1I6MBOSKJBnAYGvm");
        country.setCurrencyName("tnPbTYnyXpTonBovAeN1cIGxc2QtqDfQrZlvpqdhzagmpEqbJk");
        country.setCountryCode2("xt4");
        country.setCurrencyCode("2VD");
        country.setCurrencySymbol("Yuhb2Yb16Ofp05z8ZXNz5I9ul4UmxlFA");
        country.setCapitalLatitude(10);
        country.setCountryName("GBqgzeQILjLzcqYF8ECoJVjo2p3s57xx9iirVYskBWoDFs0mN2");
        country.setCapitalLongitude(6);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCapitalLatitude(2);
        state.setStateCapitalLongitude(3);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("Gr164tItkqC4LJQTFDuf5zerKQzUhgTe");
        state.setStateFlag("uYBCPp3K4iCImYxWrpwQyofGkjZnRadMn2t0RVfIJPTsSDbjph");
        state.setStateCode(2);
        state.setStateName("DkIwofvRz161AwQnV5u9PhBDv3kLB08oHZ0TrHwI01SZVfszhy");
        state.setStateCapital("oVXuh03ffJYOi7uC20d6QMuUqfP8FSJa4oBVC1deh9OMrMEaaY");
        state.setStateDescription("k6sNRnAWZjZ9sN3z2uofgdDWJAHl9eh9cLmii9ZUHPfam2pVh7");
        state.setStateCodeChar3("n1vSZPUP7hILEyEZwqLbYa6o2XSunLBM");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("Q0MI84cYo1XnHaNpb2jDfnAg6b4ICzmc1LYY0qKtitxPEJUbY8");
        addresstype.setAddressTypeIcon("NKa3Go9SIvK6FZsX4ohUD1ZXMWny4jNGVjC3OEfZFh7bMt2EIH");
        addresstype.setAddressType("F33A2tJhgicBOYZ1WIDmlcphmAZAicuISFgxkRS3r0DPxz7rkM");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityName("JgiAUQDtcJGPTGfmBDrFMUpe7XsFYbu7dXHJBwKSUHD0g0Lqsq");
        city.setCityLongitude(3);
        city.setCityFlag("dXQbGTfNyowxlHEZ5bswUr2mEia9AypYNPs2x54rkVyGf7sBJA");
        city.setCityLatitude(4);
        city.setCityDescription("wgPa9tiQ8fWoX4hNEu42Vi6Bt5sLpEQYLs5xaXuVWgsQkRi8Cj");
        city.setCityCode(2);
        city.setCityName("HcyTtK0OrBHdkJUroGlKILrd5efQtvNeSaCDUC6efyQ2UDzVez");
        city.setCityLongitude(7);
        city.setCityFlag("frPpMQd1KI8ELCiKfR7kIOG0RUNgBOlJKxd80jckl0TQatwiv1");
        city.setCityLatitude(8);
        city.setCityDescription("ODsK0WmY1qXRhredk6gSgRUwz4fSc8lEkuNMXisWy3lMwzgsKQ");
        city.setCityCode(2);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("dLg6arFwDpcZfVuK1d821iLjs3Xt0HbB");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setAddress1("Bwk2PIGgyuZZDUvmhTWywhPIWSvWqTxi1e3weiSJIXBrMxrh3i");
        address.setAddressLabel("7hwTFqQkEYJ");
        address.setAddress2("QBxTyxQF5SICS2Jj8uyBSWqVTgQFspKLJg8FATh4qBI1vzdQm5");
        address.setZipcode("k6QSd8");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("0ycnV2mDLEGUEOucDrZJnQr8bb7zbVVSeSuJmLXNS088UQP9ZJ");
        address.setAddress3("B5sdrJlP9YTAoXmTdxYx2IosRzADbRpBEeAJfkNs1hLbPaDAi3");
        address.setCityId((java.lang.String) CityTest._getPrimarykey());
        address.setLatitude("ORRLBY8pOxnVYpad99qRStFkw8KPrQh3G6vGce2gsOd8AV3VpQ");
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress1("H3fDgNjJtfFC3xCeoeu4Z7UO0PHie3qAck8rEQAmBWwW0kudKF");
            address.setAddressLabel("viatKwY0FIZ");
            address.setAddress2("jjNj04MO7cn8mKTvmndWAkhajaUdbWUaZ3nQnpSb9qnOvtcQg1");
            address.setVersionId(1);
            address.setZipcode("jnf42d");
            address.setLongitude("MhQN1Ohirntgkuwkd56fADd1P5jpOPcfdNCkCpQy62pDKrsbsa");
            address.setAddress3("0pZ5NqqzewCVm98tIWaWCGFpLkE6aeCGLaZzvwFc9Vjg7AR9uD");
            address.setLatitude("GlVEPFJu35RsswhHkKOWrkX9N4txLCM923o3oPPcig4qfXteya");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "XrdQBqa4nofS"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "BAuyaMzES3Qsbrl67LYD87xidqYd4TWI9l6CaJTdiembAJ9UVBtWKmU1a"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "IGfCnnLegZgpRog1sWwPAU79qKTK2Emy43IqvoKK50ewlfKnO8dWmXCdi"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "EHfpGcHyXOWtBSzrQJLjdhoL3czSm988UrKpAssFie7VpOUzJ6nW8W4WF"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "0zljbsT"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "zHc1wfTcsGdrLhNp9VJsZVLtwFUOfEB1fzvESQdHT8czS667aeCZ5DSoPezcfRxOg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "zEAbedqmBVBby9PzFvLW1zzs05nMvYntiOpwO9oVv2Y4hl4nkAITCulvPNptxV7WD"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
