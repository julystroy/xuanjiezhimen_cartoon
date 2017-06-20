package com.jssj.jiangye.data;

/**
 * Created by cc on 17-6-6.
 */
public class SysModule {
    /*
    * businessId: "1",
                      businessName: "老笔斋"
    */

        private String businessId;
        private String businessName;
        private String businessCode;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Override
    public String toString() {
        return "SysModule{" +
                "businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessCode='" + businessCode + '\'' +
                '}';
    }
}


