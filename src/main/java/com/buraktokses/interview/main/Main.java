package com.buraktokses.interview.main;

import com.buraktokses.interview.data.DataManager;
import com.buraktokses.interview.groups.Groups;

public class Main {
    public static DataManager dataManager;
    public static void main(String[] args) {
        dataManager =  new DataManager();
        dataManager.dataRead();
        System.out.println("============Groups============");
        dataManager.grouping();
        System.out.println("============Proportion============");
        Groups groups;
        dataManager.setCustomerPDAVG(dataManager.customerPDAVG());
        for (int i = 1; i <= 10; i++) {
            groups = dataManager.getGroup(i);
            System.out.println("Group " + i + " - AVG: "+dataManager.getGroupAVG(groups));
            dataManager.groupAVGProportioning(groups);
        }
        dataManager.addGroupList();
        dataManager.checkGroups();
    }
}
