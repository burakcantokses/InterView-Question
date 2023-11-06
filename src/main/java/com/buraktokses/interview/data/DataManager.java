package com.buraktokses.interview.data;

import com.buraktokses.interview.groups.Groups;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    public List<Data> data;
    public List<Double> groupList;
    public double customerPDAVG;

    public DataManager() {
        data = new ArrayList<>();
        groupList = new ArrayList<>();
        customerPDAVG = 0;
    }

    public void addData(int customerID, double customerPD, Groups customerGroup) {
        if (!data.contains(new Data(customerID, customerPD, customerGroup))) {
            data.add(new Data(customerID, customerPD, customerGroup));
        } else {
            System.out.println("Böyle bir veri bulunuyor.");
            System.exit(0);
        }
    }

    public void grouping() {
        Groups groups;
        int j = 0;
        for (int i = 1; i <= 10; i++) {
            groups = getGroup(i);
            System.out.println(groups);
            while (getGroupMemberSize(groups) < 7519) {
                addData(getData().get(j).getCustomerID(), getData().get(j).getCustomerPD(), groups);
                j++;
            }
        }
    }

    public void groupAVGProportioning(Groups groups) {
        Groups maxGroupAVG = getMaxGroupAVG();
        double standardDeviation = standardDeviation();
        while ((int) getGroupAVG(groups) < (int) standardDeviation) {
            setGroup(getMaxCustomerIDGroup(maxGroupAVG), groups);
            setGroup(getMinCustomerIDGroup(groups), maxGroupAVG);
            maxGroupAVG = getMaxGroupAVG();
        }
    }

    public void addGroupList() {
        Groups groups;
        double avg;
        for (int i = 1; i <= 10; i++) {
            groups = getGroup(i);
            System.out.println(new DecimalFormat("#.###").format(getGroupAVG(groups) / 100) + " - " + getGroupMemberSize(groups));
            avg = Double.parseDouble((new DecimalFormat("#.##").format(getGroupAVG(groups) / 100)).replace(",", "."));
            getGroupList().add(avg);
        }
    }

    public void checkGroups() {
        double[] ratio = new double[getGroupList().size()];
        System.out.println("============Ratios============");
        for (int i = 1; i < getGroupList().size(); i++) {
            ratio[i - 1] = getGroupList().get(i) / getGroupList().get(i - 1);
            System.out.println((i + 1) + "/" + (i) + " " + ratio[i - 1]);
        }
    }

    public double standardDeviation() {
        List<Double> numbers = new ArrayList<>();
        for (int i = 0; i < getData().size(); i++) {
            numbers.add(getData().get(i).getCustomerPD());
        }
        double total = 0.0, standardD = 0.0;
        int length = numbers.size();
        for (double number : numbers) {
            total += number;
        }
        double avg = total / length;
        for (double number : numbers) {
            standardD += Math.pow(number - avg, 2);
        }
        return Math.sqrt(standardD / length);
    }

    public double customerPDAVG() {
        double total = 0;
        for (int i = 0; i < getData().size(); i++) {
            total += getData().get(i).getCustomerPD();
        }
        return total / getData().size();
    }

    public double getGroupAVG(Groups groups) {
        double total = 0;
        int j = 0;
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).getCustomerGroup() == groups) {
                total += getData().get(i).getCustomerPD();
                j++;
            }
        }
        if (total != 0) {
            return total / j;
        } else {
            return total;
        }
    }

    public void setCustomerPDAVG(double customerPDAVG) {
        this.customerPDAVG = customerPDAVG;
    }

    public Groups getMaxGroupAVG() {
        Groups groups;
        double max = 0;
        int id = 0;
        for (int i = 1; i <= 10; i++) {
            groups = getGroup(i);
            if (getGroupAVG(groups) > max) {
                max = getGroupAVG(groups);
                id = i;
            }
            getGroupAVG(groups);
        }
        return getGroup(id);
    }

    public int getGroupMemberSize(Groups groups) {
        int counter = 0;
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).getCustomerGroup() == groups) {
                counter++;
            }
        }
        return counter;
    }


    public Groups getGroup(int id) {
        switch (id) {
            case 1:
                return Groups.Group1;
            case 2:
                return Groups.Group2;
            case 3:
                return Groups.Group3;
            case 4:
                return Groups.Group4;
            case 5:
                return Groups.Group5;
            case 6:
                return Groups.Group6;
            case 7:
                return Groups.Group7;
            case 8:
                return Groups.Group8;
            case 9:
                return Groups.Group9;
            case 10:
                return Groups.Group10;
            default:
                return Groups.NULL;
        }
    }

    public void setGroup(int customerID, Groups groups) {
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).getCustomerID() == customerID) {
                getData().get(i).setCustomerGroup(groups);
            }
        }
    }

    public int getMaxCustomerIDGroup(Groups groups) {
        double max = 0;
        int id = 0;
        for (int i = 0; i < getData().size(); i++) {
            if (getData().get(i).getCustomerPD() > max && getData().get(i).getCustomerGroup() == groups) {
                max = getData().get(i).getCustomerPD();
                id = getData().get(i).getCustomerID();
            }
        }
        return id;
    }

    public int getMinCustomerIDGroup(Groups groups) {
        double min = 100;
        int id = 0;
        for (int i = 0; i < getData().size(); i++) {
            if (min > getData().get(i).getCustomerPD() && getData().get(i).getCustomerGroup() == groups) {
                min = getData().get(i).getCustomerPD();
                id = getData().get(i).getCustomerID();
            }
        }
        return id;
    }

    public void dataRead() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/CUSTOMER_PD_LIST.txt");

            if (inputStream == null) {
                System.out.println("Dosya bulunamadı.");
                System.exit(0);
            }

            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                String[] data = nextLine.split("\t");
                if (!data[0].equals("BD_MUST_NO")) {
                    int customerID = Integer.parseInt(data[0]);
                    double customerPD = Double.parseDouble(data[1].replace(",", ".")) * 100;
                    addData(customerID, customerPD, Groups.NULL);
                }
            }
        } catch (Exception exception) {
            System.out.println("Dosya okuma hatası: " + exception.getMessage());
            System.exit(0);
        }
    }

    public List<Data> getData() {
        return data;
    }

    public List<Double> getGroupList() {
        return groupList;
    }
}

