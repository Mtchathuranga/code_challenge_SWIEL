/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.FileReader;
import Config.AppParams;
import java.lang.reflect.Array;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author chathuranga
 */
public class ReadFiles {

    public boolean isAvailable = false;
    public boolean isFoundValue = false;
    public boolean isInner = false;

    public JSONObject showKeys(String fileName) {
        ClassLoader classLoader = new ReadFiles().getClass().getClassLoader();
        File file = new File(fileName);
        JSONObject objFirst = null;
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(file.getAbsolutePath());
            Object obj = parser.parse(reader);
            JSONArray objArry = (JSONArray) obj;
            if (!objArry.isEmpty()) {
                objFirst = (JSONObject) objArry.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error--" + e.getMessage());
        }
       
        return objFirst;

    }

    public JSONArray ReadJSON_FILES(String fileName) {
        ClassLoader classLoader = new ReadFiles().getClass().getClassLoader();
        File file = new File(fileName);
        JSONArray objArry = null;
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(file.getAbsolutePath());
            
            Object obj = parser.parse(reader);
            objArry = (JSONArray) obj;

        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Error--" + e.getMessage());
        } finally {
            return objArry;
        }

    }

    public Boolean CheckKey(JSONArray arrayIN, String searchTerm) {

        try {
            isAvailable = false;            
            if (arrayIN.isEmpty()) {
                System.out.println("\t -> 'JSON' File Is Empty.Can not find data");
                return isAvailable;
            } else {
                JSONObject objFirst = null;
                for (int count = 0; count < arrayIN.size(); count++) {
                    objFirst = (JSONObject) arrayIN.get(count);
                    if (objFirst.containsKey(searchTerm)) {
                        isAvailable = true;
                        break;
                    }
                }

                if (!isAvailable) {
                    System.out.println("No Search Item Field Found");
                }
            }

        } catch (Exception e) {
            System.out.println("Error-->" + e.getMessage());
        }

        return isAvailable;

    }

    public void searchUsersInList(JSONArray userList, JSONArray ticketList, JSONArray orgList, String searhField, String searchValue) {

        try {
            isFoundValue = false;
            if (userList.isEmpty()) {
                System.out.println("\t -> 'users.json' File Is Empty.Can not find data");
            } else {

                for (int i = 0; i < userList.size(); i++) {
                    JSONObject tempObject = (JSONObject) userList.get(i);
                    tempObject.keySet().forEach(Key -> {
                        if (Key.equals(searhField)) {
                            isInner = false;
                            if (searhField.equals("tags")) {
                                JSONArray tempTags = (JSONArray) tempObject.get("tags");

                                for (int count = 0; count < tempTags.size(); count++) {
                                    if (tempTags.get(count).toString().equals(searchValue)) {
                                        isInner = true;
                                    }
                                }

                            }
                            if (tempObject.get(searhField).toString().equals(searchValue.toString()) || isInner) {
                                isFoundValue = true;
                                String SubId = tempObject.get("_id").toString();
                                String OrgId = (tempObject.get("organization_id") == null) ? " " : tempObject.get("organization_id").toString();

                                for (int count = 0; count < orgList.size(); count++) {
                                    JSONObject orgFirst = (JSONObject) orgList.get(count);
                                    if (orgFirst.get("_id").toString().equals(OrgId)) {
                                        System.out.println("-------------------------------------------------------------------------------------------------");
                                        System.out.println("  - Organization Name\t\t\t\t : " + orgFirst.get("name").toString());
                                    }
                                }

                                for (int count1 = 0; count1 < ticketList.size(); count1++) {
                                    JSONObject tFirst = (JSONObject) ticketList.get(count1);
                                    if (tFirst.get("submitter_id").toString().equals(SubId)) {
                                        String assignID = (tFirst.get("assignee_id") == null) ? " " : tFirst.get("assignee_id").toString();
                                        System.out.println("  \t- Submitted ticket subject\t\t : " + tFirst.get("subject").toString());

                                        for (int count = 0; count < ticketList.size(); count++) {
                                            JSONObject t1First = (JSONObject) ticketList.get(count);
                                            if (t1First.get("submitter_id").toString().equals(assignID)) {
                                                System.out.println("  \t\t > Assignee ticket subject\t : " + t1First.get("subject").toString());
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    });
                }
                if (!isFoundValue) {
                    System.out.println("No Search Value Found");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error-->" + e.getMessage());

        }

    }

    public void searchTicketsInList(JSONArray userList, JSONArray ticketList, JSONArray orgList, String searhField, String searchValue) {

        try {
            isFoundValue = false;

            if (orgList.isEmpty()) {
                System.out.println("\t -> 'tickets.json' File Is Empty.Can not find data");
            } else {

                for (int i = 0; i < ticketList.size(); i++) {
                    JSONObject tempObject = (JSONObject) ticketList.get(i);
                    tempObject.keySet().forEach(Key -> {
                        if (Key.equals(searhField)) {
                            isInner = false;

                            if (searhField.equals("tags")) {
                                JSONArray tempTags = (JSONArray) tempObject.get("tags");

                                for (int count = 0; count < tempTags.size(); count++) {
                                    if (tempTags.get(count).toString().equals(searchValue)) {
                                        isInner = true;
                                    }
                                }

                            }

                            if (tempObject.get(searhField).toString().equals(searchValue.toString()) || isInner) {
                                isFoundValue = true;

                                String SubId = (tempObject.get("submitter_id") == null) ? " " : tempObject.get("submitter_id").toString();
                                String assId = (tempObject.get("assignee_id") == null) ? " " : tempObject.get("assignee_id").toString();
                                String orgId = (tempObject.get("organization_id") == null) ? " " : tempObject.get("organization_id").toString();

//                                System.out.println("assId-" + assId + "  Sub-" + SubId + "  Orag-" + orgId);
                                for (int count = 0; count < userList.size(); count++) {
                                    JSONObject userFirst = (JSONObject) userList.get(count);
                                    if (userFirst.get("_id") != null) {

                                        if (userFirst.get("_id").toString().equals(SubId)) {
                                            System.out.println("---------------------------------------------------------------------");
                                            System.out.println("\t  - Submitter Name\t : " + userFirst.get("name").toString());

                                            for (int count2 = 0; count2 < userList.size(); count2++) {
                                                JSONObject uFirst = (JSONObject) userList.get(count2);
                                                if (uFirst.get("_id") != null) {
                                                    if (uFirst.get("_id").toString().equals(assId)) {
                                                        System.out.println("\t  - Assigner Name\t : " + uFirst.get("name").toString());
                                                        break;
                                                    }
                                                }

                                            }

                                            for (int count1 = 0; count1 < orgList.size(); count1++) {
                                                JSONObject orgFirst = (JSONObject) orgList.get(count1);
                                                if (orgFirst.get("_id") != null) {
                                                    if (orgFirst.get("_id").toString().equals(orgId)) {
                                                        System.out.println("\t  - Organization Name\t : " + orgFirst.get("name").toString());
                                                        break;
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }

                        }
                    });
                }
                if (!isFoundValue) {
                    System.out.println("No Search Value Found");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error-->" + e.getMessage());
        }

    }

    public void searchOraganizationsInList(JSONArray userList, JSONArray ticketList, JSONArray orgList, String searhField, String searchValue) {

        try {
            isFoundValue = false;
            if (orgList.isEmpty()) {
                System.out.println("\t -> 'organizations.json' File Is Empty.Can not find data");
            } else {

                for (int i = 0; i < orgList.size(); i++) {
                    JSONObject tempObject = (JSONObject) orgList.get(i);
                    tempObject.keySet().forEach(Key -> {
                        if (Key.equals(searhField)) {
                            isInner = false;
                            if (searhField.equals("tags")) {
                                JSONArray tempTags = (JSONArray) tempObject.get("tags");

                                for (int count = 0; count < tempTags.size(); count++) {
                                    if (tempTags.get(count).toString().equals(searchValue)) {
                                        isInner = true;
                                    }
                                }

                            }

                            if (searhField.equals("domain_names")) {
                                JSONArray tempDomain = (JSONArray) tempObject.get("domain_names");

                                for (int count = 0; count < tempDomain.size(); count++) {
                                    if (tempDomain.get(count).toString().equals(searchValue)) {
                                        isInner = true;
                                    }
                                }

                            }

                            if (tempObject.get(searhField).toString().equals(searchValue.toString()) || isInner) {
                                isFoundValue = true;
                                String userId = (tempObject.get("_id") == null) ? " " : tempObject.get("_id").toString();
                                System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                for (int count = 0; count < userList.size(); count++) {
                                    JSONObject orgFirst = (JSONObject) userList.get(count);
                                    if (orgFirst.get("organization_id") != null) {
                                        if (orgFirst.get("organization_id").toString().equals(userId)) {
                                            String SubID = orgFirst.get("_id").toString();
                                            System.out.println("\t---------------------------------------------------------------------");
                                            System.out.println("\t  - User Name\t\t\t : " + orgFirst.get("name").toString());
                                            for (int count1 = 0; count1 < ticketList.size(); count1++) {
                                                JSONObject tFirst = (JSONObject) ticketList.get(count1);
                                                if (tFirst.get("submitter_id") != null) {
                                                    if (tFirst.get("submitter_id").toString().equals(SubID)) {
                                                        System.out.println("  \t\t > Ticket subject\t : " + tFirst.get("subject").toString());
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }

                            }

                        }
                    });
                }
                if (!isFoundValue) {
                    System.out.println("No Search Value Found");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error-->" + e.getMessage());

        }

    }


}
