package ru.levelup.yulia.zaykova.qa.homework_6.task_1.entities;

public class User {

    private String username;
    private String password;
    private String realname;
    private String email;
    private String verifyPassword;
    private String accessLevel;
    private boolean userEnabled;
    private boolean userProtected;

    private User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealname() {
        return realname;
    }

    public String getEmail() {
        return email;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public boolean isUserEnabled() {
        return userEnabled;
    }

    public boolean isUserProtected() {
        return userProtected;
    }

    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private String realname;
        private String email;
        private String verifyPassword;
        private String accessLevel;
        private boolean userEnabled;
        private boolean userProtected;

        private UserBuilder() {
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setRealname(String realname) {
            this.realname = realname;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setVerifyPassword(String verifyPassword) {
            this.verifyPassword = verifyPassword;
            return this;
        }

        public UserBuilder setAccessLevel(String accessLevel) {
            this.accessLevel = accessLevel;
            return this;
        }

        public UserBuilder setUserEnabled(boolean userEnabled) {
            this.userEnabled = userEnabled;
            return this;
        }

        public UserBuilder setUserProtected(boolean userProtected) {
            this.userProtected = userProtected;
            return this;
        }

        public User build() {
            User user = new User();
            user.username = this.username;
            user.password = this.password;
            user.realname = this.realname;
            user.email = this.email;
            user.verifyPassword = this.verifyPassword;
            user.accessLevel = this.accessLevel;
            user.userEnabled = this.userEnabled;
            user.userProtected = this.userProtected;
            return user;
        }
    }

}
