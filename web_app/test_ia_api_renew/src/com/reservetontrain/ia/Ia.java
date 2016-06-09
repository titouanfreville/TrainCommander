package com.reservetontrain.ia;

import com.reservetontrain.callapi.*;
import java.util.ArrayList;
import java.util.TreeMap;
import org.json.*;

import static java.lang.System.console;
import static java.lang.System.in;

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
        System.out.print("\nIn ILITS --------------------- \n");
        System.out.print(map+" .....");
        System.out.print(map.get("departure")+" .....");
        int tmp = Integer.parseInt(map.get("departure"));
        System.out.print("tmp : "+tmp+ " ....");
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
     * @param departure string naming the Depature Station
     * @param arrival String namin the Arrival Station
     * @return null if no route exist, Tree of the possible route else (ids)
     */
    public TreeMap<Integer,ArrayList<Integer>> getshortesttrips (String departure, String arrival) {
        // Variables ---------------------------------------------------------------------------------------------------
        TreeMap<Integer,ArrayList<Integer>> res =new TreeMap<Integer,ArrayList<Integer>>();
        TreeMap<String,String> tmp;
        ArrayList<Integer> ltmp;
        int i;
        System.out.print("\n" + departure + " ... ");
        int idepart = searchers.get_id_from_name(lstations,departure, "idstation");
        System.out.print(idepart + " ... ");
        int iarival = searchers.get_id_from_name(lstations,arrival, "idstation");
        System.out.print("\n" + arrival + " ... ");
        if (idepart==-1 || iarival==-1 ) {
            return null;
        }
        ArrayList<Integer> idlists = searchers.get_ids_from_ints(ltrips,idepart, iarival, "idtrips", "departure", "arrival");
        System.out.print(idlists + " ... ");
        // -------------------------------------------------------------------------------------------------------------
        // Operations --------------------------------------------------------------------------------------------------
        System.out.print("\n In list ------------------------ ");
        for (i=0; i < idlists.size(); i++) {
            System.out.print("\n" + i + " ... ");
            tmp=searchers.get_map_from_ints(ltrips, idlists.get(i), "idtrips");
            System.out.print(tmp + " ... ");
            ltmp=ilist_from_map(tmp);
            System.out.print(ltmp + " ... ");
            res.put(tmp.size()-1, ltmp);
        }

        return res;
    }

    // TODO create methods to find by prices
}
