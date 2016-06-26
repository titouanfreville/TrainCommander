package com.reservetontrain.callapi;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Classe to Search in Map Lists of IA Api part
 * The map list represent Databases Objects (parsed from JSON Api. cf CallApi functions
 * This class only implement methods
 * @author FREVILLE Titouan
 * @author GUILLAS Maximilien
 */

public class Searchers {

    /**
     * Search for id of Database object from name
     * @param ltree list of map to search in
     * @param value name to search
     * @param id_identifier string who refer to the id in the JSON
     * @return -1 if element not found or element id.
     */
    public int get_id_from_name (ArrayList<TreeMap<String,String>> ltree, String value, String id_identifier) {
        int i =0;
        int res = -1;
        while (i < ltree.size() && ! (ltree.get(i).get("name").equals(value))) {
            i++;
        }

        if (i < ltree.size()) {
            res = Integer.parseInt(ltree.get(i).get(id_identifier));
        }
        return res;
    }

    /**
     * Search for id of Database object from name
     * @param ltree list of map to search in
     * @param id id to search
     * @param id_identifier string who refer to the id in the JSON
     * @param search_identifier straing who refer to the id to search
     * @return -1 if element not found or element id.
     */
    public int get_id_from_id (ArrayList<TreeMap<String,String>> ltree, int id, String id_identifier, String search_identifier) {
        int i =0;
        int res = -1;
        while (i < ltree.size() && Integer.parseInt(ltree.get(i).get(search_identifier)) != id) {
            i++;
        }

        if (i < ltree.size()) {
            res = Integer.parseInt(ltree.get(i).get(id_identifier));
        }
        return res;
    }

    /**
     * Search for ids of Database object from a couple of ids (as foreign keys)
     * @param ltree list of map to search in
     * @param d id of the departure station
     * @param a id of the arrival station
     * @param id_identifier string who refer to the id in the JSON
     * @param d_identifier string who refer to the departure id in the JSON
     * @param a_identifier string who refer to the arrival id in the JSON
     * @return null if no match found, list of matching ids else
     */
    public ArrayList<Integer> get_ids_from_ints (ArrayList<TreeMap<String,String>> ltree, int d, int a, String id_identifier, String d_identifier, String a_identifier) {
        int i;
        ArrayList<Integer> res = new ArrayList<>();
        for (i=0; i < ltree.size(); i++) {
            TreeMap<String,String> tmpt = ltree.get(i);
            int tmpd = Integer.parseInt(tmpt.get(d_identifier));
            int tmpa = Integer.parseInt(tmpt.get(a_identifier));
            if (tmpd == d && tmpa == a) {
                res.add(Integer.parseInt(tmpt.get(id_identifier)));
            }
        }
        return res;
    }


    /**
     * Search for DB infos from id
     * @param ltree list of map to search in
     * @param id Id of the object you want to find
     * @param id_identifier string who refer to the id in the JSON
     * @return null if no match found, Data map object else
     */
    public TreeMap<String, String> get_map_from_ints (ArrayList<TreeMap<String,String>> ltree, int id, String id_identifier) {
        int i =0;
        TreeMap<String, String> res = null;
        while (i < ltree.size() && Integer.parseInt(ltree.get(i).get(id_identifier)) != id) {
            i++;
        }

        if (i < ltree.size()) {
            res = ltree.get(i);
        }
        return res;
    }
}
