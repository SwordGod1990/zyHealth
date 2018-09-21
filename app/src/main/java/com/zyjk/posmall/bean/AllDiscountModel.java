package com.zyjk.posmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */

public class AllDiscountModel {

    /**
     * page : 1
     * areaCode : 110105
     * provinceCode : 110000
     * cityCode : 110100
     * data : [{"acMeQrcode":"qrcode/readQrcode?type=3&activeInfoId=40288ecf6560375701657a266c1f000a","num":1,"price":"0.01","surplusTime":823989346,"activeId":"40288ecf6560375701657a266bfe0009","distributionArea":"北京市北京市延庆区密云区平谷区怀柔区大兴区","pic":"http://zy-business.oss-cn-hangzhou.aliyuncs.com/mall/good/good2.png","commodityName":"醪醴药膳炖鸭料","type":1,"discountPrice2":"00","discountPrice1":"0","activeInfoId":"40288ecf6560375701657a266c1f000a"}]
     * code : 0000
     * pageNum : 1
     */

    private int page;
    private String areaCode;
    private String provinceCode;
    private String cityCode;
    private String code;
    private int pageNum;
    private List<DataBean> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * acMeQrcode : qrcode/readQrcode?type=3&activeInfoId=40288ecf6560375701657a266c1f000a
         * num : 1
         * price : 0.01
         * surplusTime : 823989346
         * activeId : 40288ecf6560375701657a266bfe0009
         * distributionArea : 北京市北京市延庆区密云区平谷区怀柔区大兴区
         * pic : http://zy-business.oss-cn-hangzhou.aliyuncs.com/mall/good/good2.png
         * commodityName : 醪醴药膳炖鸭料
         * type : 1
         * discountPrice2 : 00
         * discountPrice1 : 0
         * activeInfoId : 40288ecf6560375701657a266c1f000a
         */

        private String acMeQrcode;
        private int num;
        private String price;
        private int surplusTime;
        private String activeId;
        private String distributionArea;
        private String pic;
        private String commodityName;
        private int type;
        private String discountPrice2;
        private String discountPrice1;
        private String activeInfoId;

        public String getAcMeQrcode() {
            return acMeQrcode;
        }

        public void setAcMeQrcode(String acMeQrcode) {
            this.acMeQrcode = acMeQrcode;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getSurplusTime() {
            return surplusTime;
        }

        public void setSurplusTime(int surplusTime) {
            this.surplusTime = surplusTime;
        }

        public String getActiveId() {
            return activeId;
        }

        public void setActiveId(String activeId) {
            this.activeId = activeId;
        }

        public String getDistributionArea() {
            return distributionArea;
        }

        public void setDistributionArea(String distributionArea) {
            this.distributionArea = distributionArea;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDiscountPrice2() {
            return discountPrice2;
        }

        public void setDiscountPrice2(String discountPrice2) {
            this.discountPrice2 = discountPrice2;
        }

        public String getDiscountPrice1() {
            return discountPrice1;
        }

        public void setDiscountPrice1(String discountPrice1) {
            this.discountPrice1 = discountPrice1;
        }

        public String getActiveInfoId() {
            return activeInfoId;
        }

        public void setActiveInfoId(String activeInfoId) {
            this.activeInfoId = activeInfoId;
        }
    }
}
