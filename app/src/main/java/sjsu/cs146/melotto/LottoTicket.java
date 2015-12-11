package sjsu.cs146.melotto;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LottoTicket {
    private static List<LottoTicket> newTickets = new ArrayList<>();
    private static List<LottoTicket> pastTickets = new ArrayList<>();
    private static List<LottoTicket> allTickets = new ArrayList<>();
    private String nums;
    private ParseFile pic;


    public LottoTicket(){
        getNewTickets();
        getPastTickets();
        getAllTickets();
    }

    public LottoTicket(String nums, ParseFile pic) {
        this.nums = nums;
        this.pic = pic;
    }

    public String getNums(){
        return nums;
    }

    public ParseFile getPic(){
        return pic;
    }

    public static List<LottoTicket> getNewList(){
        return newTickets;
    }

    public static List<LottoTicket> getPastList(){
        return pastTickets;
    }

    public static List<LottoTicket> getAllList() {return allTickets; }

    public static void getNewTickets() {
        List<String> keys = Arrays.asList("B1", "B2", "B3", "B4", "B5", "PB", "DATE", "profilepic");
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int date = year*10000 + month*100 + day;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("test");
        query.selectKeys(keys);
        query.whereGreaterThanOrEqualTo("DATE", date);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> query, ParseException e) {
                if (e == null) {
                    for (ParseObject po : query) {
                        ParseFile thisPic = po.getParseFile("profilepic");
                        Integer ticketDate = po.getInt("DATE");
                        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                        Date aDate = null;
                        try {
                            aDate = originalFormat.parse(ticketDate.toString());
                        } catch (java.text.ParseException e1) {
                            e1.printStackTrace();
                        }
                        SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
                        String formatedDate = newFormat.format(aDate);
                        String thisTicket = (po.getString("B1") + " " + po.getString("B2") + " " + po.getString("B3") +
                                " " + po.getString("B4") + " " + po.getString("B5") + " " + po.getString("PB") +
                                " date " + formatedDate);
                        newTickets.add(new LottoTicket(thisTicket, thisPic));
                    }
                }else{
                    Log.d("B1", "Error: " + e.getMessage());
                    Log.d("B2", "Error: " + e.getMessage());
                    Log.d("B3", "Error: " + e.getMessage());
                    Log.d("B4", "Error: " + e.getMessage());
                    Log.d("B5", "Error: " + e.getMessage());
                    Log.d("PB", "Error: " + e.getMessage());
                    Log.d("DATE", "Error: " + e.getMessage());
                }
                LottoNewListFragment.setNewTickets(newTickets);
            }
        });
    }

    public static void getPastTickets() {
        List<String> keys = Arrays.asList("B1", "B2", "B3", "B4", "B5", "PB", "DATE", "profilepic");
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int date = year * 10000 + month*100 + day;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("test");
        query.selectKeys(keys);
        query.whereLessThanOrEqualTo("DATE", date);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> query, ParseException e) {
                if (e == null) {
                    for (ParseObject po : query) {
                        ParseFile thisPic = po.getParseFile("profilepic");
                        Integer ticketDate = po.getInt("DATE");
                        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                        Date aDate = null;
                        try {
                            aDate = originalFormat.parse(ticketDate.toString());
                        } catch (java.text.ParseException e1) {
                            e1.printStackTrace();
                        }
                        SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
                        String formatedDate = newFormat.format(aDate);
                        String thisTicket = (po.getString("B1") + " " + po.getString("B2") + " " + po.getString("B3") +
                                " " + po.getString("B4") + " " + po.getString("B5") + " " + po.getString("PB") +
                                " date " + formatedDate);
                        pastTickets.add(new LottoTicket(thisTicket, thisPic));
                    }
                } else {
                    Log.d("B1", "Error: " + e.getMessage());
                    Log.d("B2", "Error: " + e.getMessage());
                    Log.d("B3", "Error: " + e.getMessage());
                    Log.d("B4", "Error: " + e.getMessage());
                    Log.d("B5", "Error: " + e.getMessage());
                    Log.d("PB", "Error: " + e.getMessage());
                    Log.d("DATE", "Error: " + e.getMessage());
                }
                LottoPastListFragment.setPastTickets(pastTickets);
            }
        });
    }


    //this may be used for winning tickets or printing the pdf...
    public static void getAllTickets() {
        List<String> keys = Arrays.asList("B1", "B2", "B3", "B4", "B5", "PB", "DATE", "profilepic");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("test");
        query.selectKeys(keys);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> query, ParseException e) {
                if (e == null) {
                    for (ParseObject po : query) {
                        ParseFile thisPic = po.getParseFile("profilepic");
                        Integer ticketDate = po.getInt("DATE");
                        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
                        Date aDate = null;
                        try {
                            aDate = originalFormat.parse(ticketDate.toString());
                        } catch (java.text.ParseException e1) {
                            e1.printStackTrace();
                        }
                        SimpleDateFormat newFormat = new SimpleDateFormat("MM/dd/yyyy");
                        String formatedDate = newFormat.format(aDate);
                        String thisTicket = (po.getString("B1") + " " + po.getString("B2") + " " + po.getString("B3") +
                                " " + po.getString("B4") + " " + po.getString("B5") + " " + po.getString("PB") +
                                " date " + formatedDate );
                        allTickets.add(new LottoTicket(thisTicket, thisPic));
                    }
                } else {
                    Log.d("B1", "Error: " + e.getMessage());
                    Log.d("B2", "Error: " + e.getMessage());
                    Log.d("B3", "Error: " + e.getMessage());
                    Log.d("B4", "Error: " + e.getMessage());
                    Log.d("B5", "Error: " + e.getMessage());
                    Log.d("PB", "Error: " + e.getMessage());
                    Log.d("Date", "Error: " + e.getMessage());
                }
                LottoPrintListFragment.setAllTickets(allTickets);
            }
        });
    }

}
