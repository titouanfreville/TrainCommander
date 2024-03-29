package com.reservetontrain.ia;

import com.reservetontrain.callapi.CallApi;
import com.reservetontrain.callapi.Searchers;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Classe IA
 * Provides methods to search the tree network for a trip.
 * @author FREVILLE Titouan
 * @author GUILLAS Maximilien
 */
public class Ia {
    private CallApi callers = new CallApi();
    private Searchers searchers = new Searchers();
    private String base_url;
    /**
     * ArrayLists : store the full maps
     */
    private ArrayList<TreeMap<String, String>> lstations;
    private ArrayList<TreeMap<String, String>> ltrips;
    private ArrayList<TreeMap<String, String>> lschedules;

    /**
     * Private method in : Search if element is in list
     * @param e int to search
     * @param l list of integer
     * @return true if e in list, false else
     */
    private boolean in(Integer e, ArrayList<Integer> l) {
        for (int i=0; i<l.size();i++) {
            if (l.get(i) == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Private cleaner : Remove duplicates element from list
     * @param l list to clean
     * @return list without duplicates
     */
    private ArrayList<Integer> remove_duplicates(ArrayList<Integer> l) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i=0; i< l.size(); i++) {
            if (!(in(l.get(i), res))) {
                res.add(l.get(i));
            }
        }
        return res;
    }

    /**
     *Constructor : init the ArrayLists
     * @param ibase_url String containing the base url to use
     */
    public Ia(String ibase_url) {
        base_url=ibase_url;
        byte[] reader = callers.getApiInfo(base_url+"/Stations");
        lstations = new ArrayList<TreeMap<String, String>>();
        callers.reader_to_trees(reader,lstations);
        reader = callers.getApiInfo(base_url+"/Trips");
        ltrips = new ArrayList<TreeMap<String, String>>();
        callers.reader_to_trees(reader,ltrips);
        reader = callers.getApiInfo(base_url+"/Schedules");
        lschedules = new ArrayList<TreeMap<String, String>>();
        callers.reader_to_trees(reader,lschedules);
    }

    /**
     * Create treemap of shortest trips possibles (Weight, list of Stations)
     * @param map tmap of String,String
     * @return List of station's id in the trip
     */
    private ArrayList<Integer> ilist_from_map(TreeMap<String,String> map) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int tmp = Integer.parseInt(map.get("departure"));
        res.add(tmp);
        tmp = Integer.parseInt(map.get("arrival"));
        res.add(tmp);
        if (map.get("intermediatea")!=null) {
            tmp = Integer.parseInt(map.get("intermediatea"));
            res.add(tmp);
        }
        if (map.get("intermediateb")!=null) {
            tmp = Integer.parseInt(map.get("intermediateb"));
            res.add(tmp);
        }
        if (map.get("intermediatec")!=null) {
            tmp = Integer.parseInt(map.get("intermediatec"));
            res.add(tmp);
        }
        if (map.get("intermediated")!=null) {
            tmp = Integer.parseInt(map.get("intermediated"));
            res.add(tmp);
        }

        return res;
    }

    /**
     * Create treemap of shortest trips possibles (Weight, list of Stations) => It will not give Schedule for this, just the trips possible.
     * @param map Map to parse in json
     * @return JSONObject corresponding to Map
     * @throws JSONException if problems occurs ^^
     */
    public JSONArray map_to_json(TreeMap<Integer,ArrayList<Integer>> map) throws JSONException {
        JSONArray res = new JSONArray();
        JSONObject object;
        ArrayList<Integer> tmp;
        for (int i=0; i<8; i++)  {
            if (map.get(i) != null) {
                object=new JSONObject();
                tmp=map.get(i);
                System.out.print("tmp : " + tmp + " --------------------- \n");
                object.put("weight",Integer.toString(i));
                object.put("list",remove_duplicates(tmp));
                res.put(object);
            }
        }
        return res;
    }

    /**
     * Create treemap of shortest trips possibles (Weight, list of Stations) => It will not give Schedule for this, just the trips possible.
     * @param departure string naming the Depature Station
     * @param arrival String namin the Arrival Station
     * @return null if no route exist, Tree of the possible route else (ids)
     */
    public TreeMap<Integer,ArrayList<Integer>> getshortesttrips (String departure, String arrival) {
        // Variables ---------------------------------------------------------------------------------------------------
        TreeMap<Integer,ArrayList<Integer>> res =new TreeMap<Integer,ArrayList<Integer>>();
        TreeMap<String,String> tmp = null;
        ArrayList<Integer> intlist = new ArrayList<Integer>();
        int pds=-1;
        int i;
        int idepart = searchers.get_id_from_name(lstations,departure, "idstation");
        int iarival = searchers.get_id_from_name(lstations,arrival, "idstation");
        if (idepart==-1 || iarival==-1 ) {
            return null;
        }
        ArrayList<Integer> idlists = searchers.get_ids_from_ints(ltrips,idepart, iarival, "idtrips", "departure", "arrival");
        System.out.print("\n ------ ID LISTS "+idlists+" -------------------- \n");
        // -------------------------------------------------------------------------------------------------------------
        // Operations --------------------------------------------------------------------------------------------------
        if (idlists.size()==1) {
            tmp=searchers.get_map_from_ints(ltrips, idlists.get(0), "idtrips");
            res.put(tmp.size()-3, idlists);
            return res;
        }
        for (i=0; i < idlists.size(); i++) {
            tmp=searchers.get_map_from_ints(ltrips, idlists.get(i), "idtrips");
            if (pds != tmp.size()- 3) {
                res.put(pds, intlist);
                pds=tmp.size()-3;
                intlist = new ArrayList<Integer>();
            }
            intlist.add(idlists.get(i));
        }
        if (tmp != null) {
            pds = tmp.size()-3;
            res.put(pds, intlist);
            intlist = new ArrayList<Integer>();
            intlist.add(idlists.get(i-1));
        }
        return res;
    }

    // TODO create methods to find by prices
}