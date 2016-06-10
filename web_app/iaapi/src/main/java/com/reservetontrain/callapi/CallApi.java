package com.reservetontrain.callapi;
import javafx.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Class CallApi : manage connection and call to the API.
 * This class only implement methods
 * @author FREVILLE Titouan
 * @author GUILLAS Maximilien
 */

public class CallApi {
    // TODO Make a relevant read on API. (currently, we are only reading 1000 chars from the api, need to loop it to have all the buffer
    public byte[] getApiInfo(String surl)
    {
        HttpURLConnection connection = null;

        System.out.print("Je suis une url de java <==>" + surl+  "\n");
        byte[] reader = new byte[2000];
        //Create connection
        URL url = null;
        try {
            url = new URL(surl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Content-Type",
                "application/json");
        connection.setUseCaches(false);
        connection.setDoOutput(true);

        //Send request
        DataInputStream wr = null;
        try {
            wr = new DataInputStream (
                    connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wr.read(reader, 0, 2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }

    public Pair<String, Integer> byte_to_string (byte[] tb, int s) {
        int i = s;
        String res = "";
        char c = (char)tb[i];
        while (i < tb.length && c != '\"' && c != ',') {
            res = res + c;
            i++; c=(char)(tb[i]);
        }
        return new Pair(res,i);
    }

    private int getOut(int ent, byte[] reader) {
        int i = ent;
        char c = (char)reader[i];
        while (i < reader.length && c != '}') {
            i++; c=(char)(reader[i]);
        }
        return i;
    }

    public void reader_to_trees (byte[] reader, ArrayList<TreeMap<String,String>> ltree) {
        int i = 0;
        char c = (char)reader[i];
        TreeMap<String,String> tmap = null;
        Pair<String,Integer> p = null;
        while (i < reader.length && c != ']') {
            switch (c) {
                case '{':
                    tmap = new TreeMap<String, String>();
                    break;
                case '}':
                    ltree.add(tmap);
                    break;
                case '"':
                    p = byte_to_string(reader, i + 1);
                    String key = p.getKey();
                    if (key.equals("locationMap")) {
                        i = getOut(p.getValue(), reader);
                        break;
                    }
                    int tmp = p.getValue()+1;
                    if ((char)reader[tmp] == ':') {
                        tmp ++;
                    }
                    if ((char)reader[tmp] == '\"') {
                        tmp ++;
                    }
                    String regex1 = "intermediate(.*)";
                    String regex2 = "i(.*)time";
                    if ((key.matches(regex1) || key.matches(regex2)) && ((char)reader[tmp]=='n')) {
                        i = getOut(p.getValue(), reader)-1;
                        break;
                    }
                    if ((char)reader[tmp+1] == '}') {
                        String tc = "";
                        tc=tc+(char)reader[tmp];
                        tmap.put(key, tc);
                        ltree.add(tmap);
                        i=tmp;
                        break;
                    }
                    p = byte_to_string(reader, tmp);
                    String value = p.getKey();
                    tmap.put(key, value);
                    i = p.getValue();
                    if ((char)reader[i] == '\"') {
                        i ++;
                    }
                    break;
                default:
                    break;
            }
            i++;
            c = (char)reader[i];
        }

    }
}