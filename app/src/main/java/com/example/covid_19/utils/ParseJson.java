package com.example.covid_19.utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseJson {
    private static ArrayList<HashMap<String, String>> countryHashList;
    private  static String finalCountryCode;

    //reading the json file from assets
    private static String readJsonFile(Context context){
        String jsonValue = null;
        try {
            InputStream stream = context.getAssets().open("country.json");
            int fileSize = stream.available();
            byte[] buffer = new byte[fileSize];
            stream.read(buffer);
            stream.close();
            jsonValue = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonValue;
    }

    public static String readCountryCode(Context context, String country){
        try {

            JSONObject countryObject = new JSONObject(readJsonFile(context));
            JSONArray countriesArray = countryObject.getJSONArray("countries");
            countryHashList = new ArrayList<>();
            HashMap<String, String> countryHashObject;

            //iterate on json array and catch each in a json obj to be stored in a hashMap
            for(int i =0; i < countriesArray.length(); i++){
                JSONObject insideJsonObj = countriesArray.getJSONObject(i);
                String countryName = insideJsonObj.getString("name");
                String countryCode = insideJsonObj.getString("code");

                //create hashMap to store country name and code as in key and value
                countryHashObject = new HashMap<>();
                countryHashObject.put(countryName, countryCode);
                countryHashList.add(countryHashObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i =0; i<countryHashList.size(); i++){
            if(countryHashList.get(i).containsKey(country)) {
                finalCountryCode = countryHashList.get(i).get(country);
                break;
            }
        }
        return finalCountryCode;
    }
}
