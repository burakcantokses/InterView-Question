package com.buraktokses.interview.data;

import com.buraktokses.interview.groups.Groups;

public class Data {
    public int customerID;
    public double customerPD;
    public Groups customerGroup;

    public Data(int customerID, double customerPD, Groups customerGroup) {
        this.customerID = customerID;
        this.customerPD = customerPD;
        this.customerGroup = customerGroup;
    }

    public double getCustomerPD() {
        return customerPD;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerGroup(Groups customerGroup) {
        this.customerGroup = customerGroup;
    }

    public Groups getCustomerGroup() {
        return customerGroup;
    }

    @Override
    public String toString() {
        return "Data{" +
                "customerID=" + customerID +
                ", customerPD=" + customerPD +
                ", customerGroup='" + customerGroup + '\'' +
                '}';
    }
}
