package org.orcid.core.adapter.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.orcid.core.adapter.v3.JpaJaxbEmailAdapter;
import org.orcid.core.manager.ClientDetailsEntityCacheManager;
import org.orcid.core.manager.ClientDetailsManager;
import org.orcid.core.manager.SourceNameCacheManager;
import org.orcid.core.manager.v3.read_only.RecordNameManagerReadOnly;
import org.orcid.core.adapter.MockSourceNameCache;
import org.orcid.jaxb.model.v3.release.common.Visibility;
import org.orcid.jaxb.model.v3.release.record.Email;
import org.orcid.persistence.dao.RecordNameDao;
import org.orcid.persistence.jpa.entities.ClientDetailsEntity;
import org.orcid.persistence.jpa.entities.EmailEntity;
import org.orcid.persistence.jpa.entities.ProfileEntity;
import org.orcid.test.OrcidJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * 
 * @author Angel Montenegro
 * 
 */
@RunWith(OrcidJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:orcid-core-context.xml" })
public class JpaJaxbEmailAdapterTest extends MockSourceNameCache {

    @Resource(name = "jpaJaxbEmailAdapterV3")
    private JpaJaxbEmailAdapter jpaJaxbEmailAdapter;

    @Resource
    private ClientDetailsEntityCacheManager clientDetailsEntityCacheManager;

    @Resource
    private SourceNameCacheManager sourceNameCacheManager;

    @Mock
    private ClientDetailsManager mockClientDetailsManager;

    @Mock
    private RecordNameDao mockRecordNameDao;

    @Mock
    private RecordNameManagerReadOnly mockRecordNameManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // by default return client details entity with user obo disabled
        Mockito.when(mockClientDetailsManager.findByClientId(Mockito.eq(CLIENT_SOURCE_ID))).thenReturn(new ClientDetailsEntity());
        ReflectionTestUtils.setField(clientDetailsEntityCacheManager, "clientDetailsManager", mockClientDetailsManager);

        Mockito.when(mockRecordNameDao.exists(Mockito.anyString())).thenReturn(true);
        Mockito.when(mockRecordNameManager.fetchDisplayablePublicName(Mockito.anyString())).thenReturn("test");
        ReflectionTestUtils.setField(sourceNameCacheManager, "recordNameDao", mockRecordNameDao);
        ReflectionTestUtils.setField(sourceNameCacheManager, "recordNameManagerReadOnlyV3", mockRecordNameManager);
    }

    @Test
    public void testEmailToEmailEntity() throws JAXBException {
        Email email = getEmail();
        assertNotNull(email);
        EmailEntity entity = jpaJaxbEmailAdapter.toEmailEntity(email);
        assertNotNull(entity);
        assertNotNull(entity.getDateCreated());
        assertNotNull(entity.getLastModified());
        assertEquals("user1@email.com", entity.getEmail());
        assertEquals(org.orcid.jaxb.model.common_v2.Visibility.PUBLIC.name(), entity.getVisibility());

        // Source
        assertNull(entity.getSourceId());
        assertNull(entity.getClientSourceId());
        assertNull(entity.getElementSourceId());
    }

    @Test
    public void testEmailEntityToEmail() {
        EmailEntity entity = getEmailEntity();
        Email email = jpaJaxbEmailAdapter.toEmail(entity);
        assertNotNull(email);
        assertNotNull(email.getLastModifiedDate().getValue());
        assertNotNull(email.getCreatedDate().getValue());
        assertEquals("email@test.orcid.org", email.getEmail());
        assertEquals(Visibility.PRIVATE, email.getVisibility());

        // no user obo
        assertNull(email.getSource().getAssertionOriginOrcid());
    }

    @Test
    public void testEmailEntityToUserOBOEmail() {
        // set client source to user obo enabled client
        ClientDetailsEntity userOBOClient = new ClientDetailsEntity();
        userOBOClient.setUserOBOEnabled(true);
        Mockito.when(mockClientDetailsManager.findByClientId(Mockito.eq(CLIENT_SOURCE_ID))).thenReturn(userOBOClient);

        EmailEntity entity = getEmailEntity();
        Email email = jpaJaxbEmailAdapter.toEmail(entity);
        assertNotNull(email);
        assertNotNull(email.getLastModifiedDate().getValue());
        assertNotNull(email.getCreatedDate().getValue());
        assertEquals("email@test.orcid.org", email.getEmail());
        assertEquals(Visibility.PRIVATE, email.getVisibility());

        // user obo
        assertNotNull(email.getSource().getAssertionOriginOrcid());
    }

    private Email getEmail() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(new Class[] { Email.class });
        Unmarshaller unmarshaller = context.createUnmarshaller();
        String name = "/record_2.0/samples/read_samples/email-2.0.xml";
        InputStream inputStream = getClass().getResourceAsStream(name);
        return (Email) unmarshaller.unmarshal(inputStream);
    }

    private EmailEntity getEmailEntity() {
        EmailEntity result = new EmailEntity();
        result.setEmail("email@test.orcid.org");
        result.setCurrent(true);
        result.setPrimary(true);
        result.setProfile(new ProfileEntity("0000-0000-0000-0000"));
        result.setVerified(true);
        result.setDateCreated(new Date());
        result.setLastModified(new Date());
        result.setVisibility(org.orcid.jaxb.model.common_v2.Visibility.PRIVATE.name());
        result.setClientSourceId(CLIENT_SOURCE_ID);

        return result;
    }
}
