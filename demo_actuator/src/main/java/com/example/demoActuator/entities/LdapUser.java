package com.example.demoActuator.entities;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;


public class LdapUser {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String type;
    private boolean resetPwd;
    private boolean unlockAccount;

    public LdapUser(Builder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.type = builder.type;
        this.resetPwd = builder.resetPwd;
        this.unlockAccount = builder.unlockAccount;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getType() {
        return type;
    }

    public boolean isResetPwd() {
        return resetPwd;
    }

    public boolean isUnlockAccount() {
        return unlockAccount;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        LdapUser other = (LdapUser) obj;
        return Objects.equal(this.userName, other.userName) && Objects.equal(this.password, other.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getClass(), userName, password);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("username", userName).add("password", password)
                .add("firstName", firstName).add("lastName", lastName).add("type", type).add("resetPwd", resetPwd)
                .add("unlockAccount", unlockAccount).toString();
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder{
        private String userName;
        private String password;
        private String firstName;
        private String lastName;
        private String type;
        private boolean resetPwd;
        private boolean unlockAccount;

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder resetPwd(boolean resetPwd) {
            this.resetPwd = resetPwd;
            return this;
        }

        public Builder unlockAccount(boolean unlockAccount) {
            this.unlockAccount = unlockAccount;
            return this;
        }

        public LdapUser build() {
            return new LdapUser(this);
        }
    }
}
