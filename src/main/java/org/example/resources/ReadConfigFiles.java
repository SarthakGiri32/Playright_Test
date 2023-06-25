package org.example.resources;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadConfigFiles {
    protected static JSONObject createJsonArray(String testName) throws IOException, ParseException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        String configFilePath = "src/main/resources/Config.json";
        FileReader reader = new FileReader(configFilePath);
        JSONObject testData = (JSONObject) jsonParser.parse(reader);
        if(testData.containsKey(testName)){
            return (JSONObject) testData.get(testName);
        }
        return null;
    }

}
