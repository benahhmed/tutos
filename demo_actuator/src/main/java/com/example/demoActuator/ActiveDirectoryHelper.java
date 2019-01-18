package com.example.demoActuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Component;

import javax.naming.directory.DirContext;

//@Component
public class ActiveDirectoryHelper {

    private final Logger logger = LoggerFactory.getLogger(ActiveDirectoryHelper.class.getName());


    @Autowired
    private LdapContextSource contextSource;

    public ActiveDirectoryHelper() {
    }

    public boolean authenticate(String userDn, String credentials) {
        DirContext ctx = null;
        try {
            ctx = contextSource.getContext(userDn, credentials);
            return true;
        } catch (Exception e) {
            // Context creation failed - authentication did not succeed
            logger.error("login failed");
            return false;
        } finally {
            // It is imperative that the created DirContext instance is always
            // closed
            LdapUtils.closeContext(ctx);
        }
    }
}
