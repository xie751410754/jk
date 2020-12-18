package com.xzq.module_base.bean;

import java.util.List;

public class NoticeListDto {

    /**
     * type : STAFF
     * details : [{"companyName":"云南国有金融资本投资管理有限公司","addressBook":[{"dept":"计划财务部","staff":[{"name":"刘文君","mobile":null},{"name":"杨晓涓","mobile":null}]},{"dept":"综合事务部","staff":[{"name":"李晟捷","mobile":null},{"name":"高迪","mobile":null}]},{"dept":"审计风控部","staff":[{"name":"樊星","mobile":null},{"name":"韩瑜","mobile":null}]},{"dept":"经营管理部","staff":[{"name":"任伟峰","mobile":null},{"name":"唐源","mobile":null},{"name":"刘熙蕾","mobile":null}]}]}]
     */

    private String type;
    private List<DetailsBean> details;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean {
        /**
         * companyName : 云南国有金融资本投资管理有限公司
         * addressBook : [{"dept":"计划财务部","staff":[{"name":"刘文君","mobile":null},{"name":"杨晓涓","mobile":null}]},{"dept":"综合事务部","staff":[{"name":"李晟捷","mobile":null},{"name":"高迪","mobile":null}]},{"dept":"审计风控部","staff":[{"name":"樊星","mobile":null},{"name":"韩瑜","mobile":null}]},{"dept":"经营管理部","staff":[{"name":"任伟峰","mobile":null},{"name":"唐源","mobile":null},{"name":"刘熙蕾","mobile":null}]}]
         */

        private String companyName;
        private List<NoticeDto> addressBook;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public List<NoticeDto> getAddressBook() {
            return addressBook;
        }

        public void setAddressBook(List<NoticeDto> addressBook) {
            this.addressBook = addressBook;
        }

    }
}
