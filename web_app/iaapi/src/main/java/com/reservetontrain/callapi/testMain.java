package com.reservetontrain.callapi;

import com.reservetontrain.ia.Ia;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Test class. TO DELETE AFTER THE PROJECT
 * @author FREVILLE Titouan
 * @author GUILLAS Maximilien
 */
public class testMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CallApi mytestApi = new CallApi();
        byte[] reader = mytestApi.getApiInfo("http://51.255.235.183:3000/api/Stations");
        ArrayList<TreeMap<String,String>> lstations = new ArrayList<TreeMap<String,String>>();
        ArrayList<TreeMap<String,String>> ltrips = new ArrayList<TreeMap<String,String>>();
        ArrayList<TreeMap<String,String>> lshedules = new ArrayList<TreeMap<String,String>>();
        mytestApi.reader_to_trees(reader, lstations);
        reader = mytestApi.getApiInfo("http://51.255.235.183:3000/api/Trips");
        mytestApi.reader_to_trees(reader, ltrips);

        reader = mytestApi.getApiInfo("http://51.255.235.183:3000/api/Schedules");
        mytestApi.reader_to_trees(reader, lshedules);

        Searchers search = new Searchers();

		System.out.print("\n" + lstations + "\n");
		System.out.print("\n" + ltrips + "\n");

        System.out.print("\n id of BC : " + search.get_id_from_name(lstations, "Palombie Forest", "idstation") + "\n");

		System.out.print("\n List of id for BC to Palombia : " + search.get_ids_from_ints(ltrips, 24, 1, "idtrips", "departure", "arrival") + "\n");

		System.out.print("\n Schedule id: " + search.get_id_from_id(lshedules, 3, "idschedule", "trip") + "\n");


		System.out.print("\n Map id: " + search.get_map_from_ints(ltrips, 1, "idtrips") + "\n");

        Ia ia = new Ia("http://51.255.235.183:3000/api/");
        System.out.print("\n Map res shortest : " + ia.getshortesttrips("PalombieForest", "BatCave") +"\n");

        //

        JSONArray jsonr;
        ia = new Ia("http://51.255.235.183:3000/api/");
        TreeMap<Integer, ArrayList<Integer>> test = ia.getshortesttrips("PalombieForest", "LaCiteDesSonges");
        System.out.print("\n Map res shortest test : " + test +"\n");
        jsonr=ia.map_to_json(test);
        System.out.print("JSONR : " + jsonr +"\n");


    }

}