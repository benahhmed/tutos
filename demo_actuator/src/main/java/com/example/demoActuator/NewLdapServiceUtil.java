package com.example.demoActuator;

import com.example.demoActuator.entities.LdapUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.BasicControl;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewLdapServiceUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(NewLdapServiceUtil.class.getName());

    private static final Integer THREE_SECONDS = 3000;
    public static final String LDAP_KEY_UID = "uid";
    public static final String LDAP_KEY_PWD = "userPassword";
    public static final String LDAP_KEY_CN = "cn";


    public static final String LDAP_KEY_CLASS = "objectclass";
    public static final String LDAP_KEY_PERSON = "person";
    public static final String LDAP_KEY_TOP = "top";
    public static final String LDAP_KEY_INET_ORG_PERSON = "inetOrgPerson";
    public static final String LDAP_KEY_SN = "sn";


    private static Control[] passwordControl = new Control[]{new BasicControl("1.3.6.1.4.1.42.2.27.8.5.1")};


    @Autowired
    private LdapTemplate ldapTemplate;

  //  @Autowired
  //  private ActiveDirectoryHelper activeDirectoryHelper;

    public void create(LdapUser user) {
        LOGGER.info("Creating new LDAP entry for user {}", user.getUserName());
        Name dn = buildDn(user.getUserName());
        try {
            DirContextAdapter context = new DirContextAdapter(dn);
            mapToContext(context, user);
            ldapTemplate.executeReadWrite(ctx -> doCreate(dn, context, ctx));
//        }catch (ApplicationException e){
//            throw e;
        } catch (Exception e) {
            LOGGER.error("Error when creating ldap entry {}", dn, e);
            throw LdapUtils.convertLdapException(new NamingException(e.getMessage()));
        }
    }

    private Object doCreate(Name dn, DirContextAdapter contextAdapter, DirContext ctx) throws NamingException {
        InitialLdapContext initialLdapContext = (InitialLdapContext) ctx;
        try {

            initialLdapContext.setRequestControls(passwordControl);
            initialLdapContext.bind(dn, contextAdapter, null);
            return null;
        } catch (NamingException e) {
            LOGGER.error("Error when creating ldap entry {}", dn, e);
            throw LdapUtils.convertLdapException(new NamingException(e.getMessage()));
        }
    }

    public LdapUser bindUser(String customerNumber, String password) {
        try {
            LOGGER.info("Getting LDAP entry for user {}", customerNumber);
            LdapQuery query = LdapQueryBuilder.query()
                    .where(LDAP_KEY_UID).is(customerNumber);
            return (LdapUser) ldapTemplate.searchForObject(query, new PersonContextMapper());
        } catch (Exception e) {
            LOGGER.error("Error when authenticating with credentials {}/{} : {}", customerNumber, password, e);
            throw LdapUtils.convertLdapException(new NamingException(e.getMessage()));
        }
    }


    public boolean authenticateUser(String customerNumber, String password) {
        try {
            LOGGER.info("Getting LDAP entry for user {}", customerNumber);
            LdapQuery query = LdapQueryBuilder.query()
                    .where(LDAP_KEY_UID).is(customerNumber);
            ldapTemplate.authenticate(query, password);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error when authenticating with credentials {}/{} : {}", customerNumber, password, e);
            throw LdapUtils.convertLdapException(new NamingException(e.getMessage()));
        }
    }

    public void updatePassword(LdapUser user) {
        Name dn = buildDn(user.getUserName());
        try {
            LOGGER.debug("Updating LDAP entry for user {}", user.getUserName());
            DirContextOperations context = ldapTemplate.lookupContext(dn);
            mapToContext(context, user);
            ldapTemplate.executeReadWrite(ctx -> doUpdate(dn, user, ctx));
        } catch (Exception e) {
            LOGGER.error("Entry with dn {} not found in LDAP", dn.toString());
            throw LdapUtils.convertLdapException(new NamingException(e.getMessage()));
        }
    }

    private Object doUpdate(Name dn, LdapUser user, DirContext ctx) {
        InitialLdapContext initialLdapContext = (InitialLdapContext) ctx;
        try {
            List<ModificationItem> items = new ArrayList<>();

            items.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(LDAP_KEY_PWD, user.getPassword())));

            initialLdapContext.setRequestControls(passwordControl);

            ModificationItem[] modificationItems = items.toArray(new ModificationItem[items.size()]);
            initialLdapContext.modifyAttributes(dn, modificationItems);
            return null;
        } catch (NamingException e) {
            LOGGER.error("Error when updating ldap entry {} : {}", dn, e);
            throw LdapUtils.convertLdapException(new NamingException(e.getExplanation()));
        }
    }

    //FIXME
    //for Manager to seek Users:
    public LdapUser find(String username) {
        try {
            LOGGER.info("Getting LDAP entry for user {}", username);
            LdapQuery query = LdapQueryBuilder.query()
                    .where(LDAP_KEY_UID).is(username);
            return (LdapUser) ldapTemplate.searchForObject(query, new PersonContextMapper());
        } catch (Exception e) {
            if (e instanceof EmptyResultDataAccessException) {
                LOGGER.error("Customer with username {}  not found in LDAP ", username);
                //     throw new ApplicationException(ReturnCodes.E201.getCode(), ReturnCodes.E201.getMessage());
                throw e;
            } else {
                LOGGER.error("Error when trying to find customer in LDAP with username {} : {}", username, e);
                throw LdapUtils.convertLdapException(new NamingException(e.getMessage()));
            }
        }
    }

//    /**
//     * Custom person attributes mapper, maps the attributes to the person POJO
//     */
//    public class PersonAttributesMapper implements AttributesMapper<Person> {
//        public Person mapFromAttributes(Attributes attrs) throws NamingException {
//            Person person = new Person();
//            person.setCn((String)attrs.get("cn").get());
//            return person;
//        }
//    }

    private static class PersonContextMapper extends AbstractContextMapper {
        public Object doMapFromContext(DirContextOperations context) {
            return LdapUser.newBuilder()
                    .userName(context.getStringAttribute(LDAP_KEY_UID))
                    .build();
        }
    }

    private Name buildDn(String uid) {
        return LdapNameBuilder.newInstance()
                .add(LDAP_KEY_UID, uid)
                .build();
    }

    private void mapToContext(DirContextOperations context, LdapUser user) {
        context.setAttributeValues(LDAP_KEY_CLASS, new String[]{LDAP_KEY_TOP, LDAP_KEY_TOP, LDAP_KEY_INET_ORG_PERSON});
        context.setAttributeValue(LDAP_KEY_CN, user.getFirstName());
        context.setAttributeValue(LDAP_KEY_SN, user.getLastName());
        context.setAttributeValue(LDAP_KEY_PWD, user.getPassword());
//        context.setAttributeValue(LDAP_KEY_EMP_TYPE, user.getType());
    }
}
