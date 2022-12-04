package com.example.ItemCheck.Dto.Union;

import com.google.gson.annotations.SerializedName;

public class UnionResponseDto {
    @SerializedName("success")
    private String success;

    @SerializedName("code")
    private Integer code;

    @SerializedName("result")
    private UnionData result;

    public UnionResponseDto(String success, Integer code, UnionData result) {
        this.success = success;
        this.code = code;
        this.result = result;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public UnionData getResult() {
        return result;
    }

    public void setResult(UnionData result) {
        this.result = result;
    }

    public class UnionData {
        @SerializedName("data")
        private Union unionData;

        public UnionData(Union unionData) {
            this.unionData = unionData;
        }

        public Union getUnionData() {
            return unionData;
        }

        public void setUnionData(Union unionData) {
            this.unionData = unionData;
        }

        public class Union {
            @SerializedName("open")
            private boolean isOpen;

            public Union(boolean isOpen) {
                this.isOpen = isOpen;
            }

            public boolean getIsOpen() {
                return isOpen;
            }

            public void setIsOpen(boolean isOpen) {
                this.isOpen = isOpen;
            }
        }
    }
}
