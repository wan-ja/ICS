package com.example.ItemCheck.Dto.User;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserLoginResponseDto {

    @SerializedName("success")
    private String success;

    @SerializedName("code")
    private String code;

    @SerializedName("result")
    private UserData userData;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public class UserData {
        @SerializedName("data")
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public UserData(User user) {
            this.user = user;
        }

        public class User {
            @SerializedName("studentId")
            private String studentId;

            @SerializedName("password")
            private String password;

            @SerializedName("userRole")
            private String userRole;

            public User(String studentId, String password) {
                this.studentId = studentId;
                this.password = password;
            }

            public String getStudentId() {
                return studentId;
            }

            public void setStudentId(String studentId) {
                this.studentId = studentId;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getUserRole() {
                return userRole;
            }

            public void setUserRole(String userRole) {
                this.userRole = userRole;
            }
        }
    }
}