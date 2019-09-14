/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controllers.ReadFiles;
import java.util.Collection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static Config.AppParams.organizationFilePath;
import static Config.AppParams.ticketsFilePath;
import static Config.AppParams.usersFilePath;
import org.mockito.Mockito;

/**
 *
 * @author Mtchathuranga
 */
public class JUnitTest_ReadFiles {

    public JUnitTest_ReadFiles() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void CheckKey() throws ParseException {
        //This method will return true if KEY Is available in JSON ARRAY
        //WRONG Fileds should be DISPLAY 'No Search Item Field Found'

        String ArrayString = "[\n"
                + "  {\n"
                + "    \"_id\": 24,\n"
                + "    \"url\": \"http://initech.tokoin.io.com/api/v2/users/24.json\",\n"
                + "    \"external_id\": \"c01c2b7a-30cd-41d1-98e7-2cdd42d55d84\",\n"
                + "    \"name\": \"Harris Côpeland\",\n"
                + "    \"alias\": \"Miss Gates\",\n"
                + "    \"created_at\": \"2016-03-02T03:35:41 -11:00\",\n"
                + "    \"active\": false,\n"
                + "    \"verified\": false,\n"
                + "    \"shared\": false,\n"
                + "    \"locale\": \"zh-CN\",\n"
                + "    \"timezone\": \"Cameroon\",\n"
                + "    \"last_login_at\": \"2013-05-11T10:41:04 -10:00\",\n"
                + "    \"email\": \"gatescopeland@flotonic.com\",\n"
                + "    \"phone\": \"9855-882-406\",\n"
                + "    \"signature\": \"Don't Worry Be Happy!\",\n"
                + "    \"organization_id\": 110,\n"
                + "    \"tags\": [\n"
                + "      \"Kieler\",\n"
                + "      \"Swartzville\",\n"
                + "      \"Salvo\",\n"
                + "      \"Guthrie\"\n"
                + "    ],\n"
                + "    \"suspended\": false,\n"
                + "    \"role\": \"agent\"\n"
                + "  }\n"
                + "]";

        JSONParser parser = new JSONParser();
        JSONArray json = (JSONArray) parser.parse(ArrayString);

        ReadFiles rd = new ReadFiles();
        assertEquals(true, rd.CheckKey(json, "phone"));
        assertEquals(false, rd.CheckKey(json, "phone"));
        assertEquals(true, rd.CheckKey(json, "phone1"));
        assertEquals(false, rd.CheckKey(json, "phone1"));
    }
    
    @Test
    public void ReadJSON_FILES(){
        
        ReadFiles rd = new ReadFiles();

        assertNotNull(usersFilePath);
        assertNull(usersFilePath);
    
    }
    
    @Test
    public void showKeys(){
        ReadFiles rd = new ReadFiles();
        
        
         assertNotNull(usersFilePath);
         assertNull(usersFilePath);
         JSONObject mock = Mockito.mock(JSONObject.class);
         
    
    }
}
