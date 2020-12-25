package com.ggp.noob.util.pki.crl;

import java.util.Date;

/**
 * @Author:ggp
 * @Date:2020-05-26 10:32
 * @Description:
 */
public class CrlEntry {
    /**
     * 被撤销证书sn
     */
    private String sn;
    /**
     * 被撤销证书的撤销时间
     */
    private Date date;
    /**
     * 被撤销证书的撤销原因
     */
    private int reason;

    public CrlEntry(String sn, Date date, int reason) {
        this.sn = sn;
        this.date = date;
        this.reason = reason;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }
}
