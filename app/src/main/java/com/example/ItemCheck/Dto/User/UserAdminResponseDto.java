package com.example.ItemCheck.Dto.User;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserAdminResponseDto {
    @SerializedName("success")
    private String success;

    @SerializedName("code")
    private String code;

    @SerializedName("result")
    private UserAdminResponseDto.UserData userData;

    public UserAdminResponseDto(UserAdminResponseDto.UserData userData) {
        this.userData = userData;
    }

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

    public UserAdminResponseDto.UserData getUserData() {
        return userData;
    }

    public void setUserDataList(UserAdminResponseDto.UserData userData) {
        this.userData = userData;
    }

    public class UserData {
        @SerializedName("data")
        private List<User> user;

        public void setUser(List<User> user) {
            this.user = user;
        }

        public List<User> getUser() {
            return user;
        }

        public class User {
            @SerializedName("studentId")
            private String studentId;

            @SerializedName("password")
            private String password;

            @SerializedName("name")
            private String name;

            public User(String studentId, String password, String name) {
                this.studentId = studentId;
                this.password = password;
                this.name = name;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
