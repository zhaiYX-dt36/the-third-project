package cn.java.entity;

public class Member {
    private Long mid;

    private String mname;

    private Integer mgender;

    private Integer mage;

    private String address;

    private String memail;

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Integer getMgender() {
        return mgender;
    }

    public void setMgender(Integer mgender) {
        this.mgender = mgender;
    }

    public Integer getMage() {
        return mage;
    }

    public void setMage(Integer mage) {
        this.mage = mage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

    @Override
    public String toString() {
        return "Member [mid=" + mid + ", mname=" + mname + ", mgender=" + mgender + ", mage=" + mage + ", address="
                + address + ", memail=" + memail + "]";
    }

}