package com.example.test11;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class selectdata extends AsyncTask<String, Void, String> {

    //public Collection<String> cs_List;
    //        ProgressDialog progressDialog;
    String errorString = null;
    private ArrayList<String> wishlist_drug;
    private String mJsonString;
    int postParameters;
    String searchText;
    private TextView mTextViewResult;
    ArrayList<bonsche> bonsche_list=new ArrayList<>();
    ArrayList<commKong> ck_list=new ArrayList<>();
    ArrayList<commMain> cm_list=new ArrayList<>();
    ArrayList<commSmall> cs_list=new ArrayList<>();
    ArrayList<totalInfo> ti_list = new ArrayList<>();
    ArrayList<totalDate> td_list = new ArrayList<>();
    ArrayList<totalDate> to_list = new ArrayList<>();

    //RecyclerView recyclerview;
    //Adapter adapter = new Adapter(td_list);
    Adapter1 a1=new Adapter1(to_list);
    SetDotlist dl=new SetDotlist(ti_list);


    //List<Object> mergedList = new ArrayList<>();




    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//            progressDialog = ProgressDialog.show(getActivity(),
//                    "Please Wait", null, true, true);

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

//            progressDialog.dismiss();
        long mNow=System.currentTimeMillis();
        Date mDate=new Date(mNow);
        SimpleDateFormat mFormat=new SimpleDateFormat("yyyy-MM-dd");
        String searchText=mFormat.format(mDate);

        if (result == null) {
            mTextViewResult.setText(errorString);
        } else {

            mJsonString = result;
            Log.d("???????????????-??????", result);
            Log.d("???????????????-??????", String.valueOf(postParameters));
            if(postParameters==0){
                showResult(searchText);
            }




        }
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("??????", "test");
        String serverURL = params[0];
        Log.d("??????", serverURL);
        if (params[1] == "0") { //?????????????????? ????????? ????????? ??? ??? ?????? ????????? ?????? (10.26)
            postParameters=0;
        } else if (params[1] == "1") { //??????????????? ??????
            postParameters=1;
        }


        try {

            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(200000);
            httpURLConnection.setConnectTimeout(100000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.connect();

            //??????????????? ?????? post?????? ?????????????????? ??????
            //OutputStream outputStream = httpURLConnection.getOutputStream();
            if (params[1] == "0") {
                //outputStream.write(postParameters.getBytes("UTF-8"));
                Log.d("??????", "??????");
            }
            else if (params[1] == "1") {

            }

            //outputStream.flush();
            //outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d("??????", "response code - " + responseStatusCode);

            InputStream inputStream;
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);

            }

            bufferedReader.close();

            return sb.toString().trim();


        } catch (Exception e) {

            Log.d("??????", "InsertData : Error ", e);
            errorString = e.toString();

            return null;
        }
    }

    public void showResult(String searchText) {

        String TAG_JSON2 = "commKong";
        String TAG_JSON1 = "bonsche";
        String TAG_JSON3 = "commMain";
        String TAG_JSON4 = "commSmall";
        String TAG_JSON5 = "seminar";
        String TAG_DATE = "meeting_DATE";
        String TAG_TIME = "meeting_TIME";
        String TAG_TITLE = "title";;
        String TAG_CMANE = "committee_NAME";



        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON1);
            JSONArray jsonArray1 = jsonObject.getJSONArray(TAG_JSON2);
            JSONArray jsonArray2 = jsonObject.getJSONArray(TAG_JSON3);
            JSONArray jsonArray3 = jsonObject.getJSONArray(TAG_JSON4);
            JSONArray jsonArray4 = jsonObject.getJSONArray(TAG_JSON5);

            //totalDate td=new totalDate();
            //to_list.clear();
            for (int i = 0; i < jsonArray.length(); i++) { //?????????

                JSONObject item = jsonArray.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String url = item.getString("link_URL");
                String name = item.getString("meetingsession");

                //????????? ????????? ??????
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //????????? ??????

                //bc.meetingsession=session;
                ti.type=TAG_JSON1;
                ti.title=title;
                ti.meeting_DATE=date;
                ti.meeting_TIME=time;

                td.type=TAG_JSON1;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=name;
                td.url=url;

                ti_list.add(ti);
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                    to_list.add(td);
                }
                //to_list.add(td);
                /*adapter.setCm_List(td);
                adapter.notifyDataSetChanged();*/




            }

            for (int i = 0; i < jsonArray1.length(); i++) {//?????????

                JSONObject item = jsonArray1.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String dept=item.getString(TAG_CMANE);
                String url=item.getString("link_URL2");
                //????????? ????????? ??????
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //????????? ??????
                ti.type=TAG_JSON2;
                ti.title=title;
                ti.meeting_DATE=date;
                ti.meeting_TIME=time;

                td.type=TAG_JSON2;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=dept;
                td.url=url;

                ti_list.add(ti);
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                    to_list.add(td);
                }
                /*adapter.setCm_List(td);
                adapter.notifyDataSetChanged();*/
                //?????? ???????????? ???????????? ????????? ????????? ????????? ????????????
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray2.length(); i++) {//???????????????

                JSONObject item = jsonArray2.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String dept=item.getString(TAG_CMANE);
                String url=item.getString("link_URL2");

                //????????? ????????? ??????
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //????????? ??????

                ti.type=TAG_JSON3;
                ti.title=title;
                ti.meeting_DATE=date;
                ti.meeting_TIME=time;

                td.type=TAG_JSON3;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=dept;
                td.url=url;

                ti_list.add(ti);
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                    to_list.add(td);
                }
                //cm_list.add(cm);
                //Log.d("??????????????????", String.valueOf(adapter.getItemCount()));//???????????? ????????? ??????



                //?????? ???????????? ???????????? ????????? ????????? ????????? ????????????
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray3.length(); i++) {//?????????

                JSONObject item = jsonArray3.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String dept=item.getString(TAG_CMANE);
                String url=item.getString("link_URL2");
                //????????? ????????? ??????
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //????????? ??????
                ti.type=TAG_JSON4;
                ti.title=title;
                ti.meeting_DATE=date;
                ti.meeting_TIME=time;

                td.type=TAG_JSON4;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=dept;
                td.url=url;

                ti_list.add(ti);
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                    to_list.add(td);
                }


                /*adapter.setCm_List(td);
                adapter.notifyDataSetChanged();*/


                //?????? ???????????? ???????????? ????????? ????????? ????????? ????????????
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray4.length(); i++) {//?????????

                JSONObject item = jsonArray4.getJSONObject(i);

                String date = item.getString("sdate");
                String time = item.getString("stime");
                String title = item.getString(TAG_TITLE);
                String url = item.getString("link");
                String dept=item.getString("name");
                //????????? ????????? ??????
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //????????? ??????
                ti.type=TAG_JSON5;
                ti.title=title;
                ti.meeting_DATE=date;
                ti.meeting_TIME=time;

                td.type=TAG_JSON5;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=dept;
                td.url=url;

                ti_list.add(ti);
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                    to_list.add(td);
                }


                /*adapter.setCm_List(td);
                adapter.notifyDataSetChanged();*/


                //?????? ???????????? ???????????? ????????? ????????? ????????? ????????????
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }


            a1.setCm_List(to_list);
            a1.notifyDataSetChanged();
            Log.d("?????????a1", String.valueOf(a1.getItemCount()));//???????????? ????????? ??????



        }catch(NullPointerException n){

            Log.d("??????", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("??????", "showResult : ", e);

        }

    }

    public void showResult2(String searchText,List<String> filter) {

        String TAG_JSON2 = "commKong";
        String TAG_JSON1 = "bonsche";
        String TAG_JSON3 = "commMain";
        String TAG_JSON4 = "commSmall";
        String TAG_JSON5 = "seminar";
        String TAG_DATE = "meeting_DATE";
        String TAG_TIME = "meeting_TIME";
        String TAG_TITLE = "title";;
        String TAG_CMANE = "committee_NAME";

        Log.d("????????????", filter.get(0));//???????????? ????????? ??????

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            for(int j=0;j<filter.size();j++){
                if(filter.get(j).equals(TAG_JSON1)){
                    JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON1);
                    for (int i = 0; i < jsonArray.length(); i++) { //?????????

                        JSONObject item = jsonArray.getJSONObject(i);

                        String date = item.getString(TAG_DATE);
                        String time = item.getString(TAG_TIME);
                        String title = item.getString(TAG_TITLE);
                        String url = item.getString("link_URL");
                        String name = item.getString("meetingsession");

                        //????????? ????????? ??????
                        totalInfo ti=new totalInfo();
                        totalDate td=new totalDate();

                        //????????? ??????

                        //bc.meetingsession=session;
                        ti.type=TAG_JSON1;
                        ti.title=title;
                        ti.meeting_DATE=date;
                        ti.meeting_TIME=time;

                        td.type=TAG_JSON1;
                        td.title=title;
                        td.meeting_DATE=date;
                        td.meeting_TIME=time;
                        td.committee_NAME=name;
                        td.url=url;

                        ti_list.add(ti);
                        if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                            to_list.add(td);
                        }

                    }
                }

                if(filter.get(j).equals(TAG_JSON2)){
                    JSONArray jsonArray1 = jsonObject.getJSONArray(TAG_JSON2);
                    for (int i = 0; i < jsonArray1.length(); i++) {//?????????

                        JSONObject item = jsonArray1.getJSONObject(i);

                        String date = item.getString(TAG_DATE);
                        String time = item.getString(TAG_TIME);
                        String title = item.getString(TAG_TITLE);
                        String dept=item.getString(TAG_CMANE);
                        String url=item.getString("link_URL2");
                        //????????? ????????? ??????
                        totalInfo ti=new totalInfo();
                        totalDate td=new totalDate();

                        //????????? ??????
                        ti.type=TAG_JSON2;
                        ti.title=title;
                        ti.meeting_DATE=date;
                        ti.meeting_TIME=time;

                        td.type=TAG_JSON2;
                        td.title=title;
                        td.meeting_DATE=date;
                        td.meeting_TIME=time;
                        td.committee_NAME=dept;
                        td.url=url;

                        ti_list.add(ti);
                        if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                            to_list.add(td);
                        }
                    }

                }
                if(filter.get(j).equals(TAG_JSON3)){
                    JSONArray jsonArray2 = jsonObject.getJSONArray(TAG_JSON3);
                    for (int i = 0; i < jsonArray2.length(); i++) {//???????????????
                        JSONObject item = jsonArray2.getJSONObject(i);

                        String date = item.getString(TAG_DATE);
                        String time = item.getString(TAG_TIME);
                        String title = item.getString(TAG_TITLE);
                        String dept=item.getString(TAG_CMANE);
                        String url=item.getString("link_URL2");

                        //????????? ????????? ??????
                        totalInfo ti=new totalInfo();
                        totalDate td=new totalDate();

                        //????????? ??????

                        ti.type=TAG_JSON3;
                        ti.title=title;
                        ti.meeting_DATE=date;
                        ti.meeting_TIME=time;

                        td.type=TAG_JSON3;
                        td.title=title;
                        td.meeting_DATE=date;
                        td.meeting_TIME=time;
                        td.committee_NAME=dept;
                        td.url=url;

                        ti_list.add(ti);
                        Log.d("????????????", searchText);//???????????? ????????? ??????
                        if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                            to_list.add(td);
                        }
                    }
                }
                if(filter.get(j).equals(TAG_JSON4)){
                    JSONArray jsonArray3 = jsonObject.getJSONArray(TAG_JSON4);
                    for (int i = 0; i < jsonArray3.length(); i++) {//?????????

                        JSONObject item = jsonArray3.getJSONObject(i);

                        String date = item.getString(TAG_DATE);
                        String time = item.getString(TAG_TIME);
                        String title = item.getString(TAG_TITLE);
                        String dept=item.getString(TAG_CMANE);
                        String url=item.getString("link_URL2");
                        //????????? ????????? ??????
                        totalInfo ti=new totalInfo();
                        totalDate td=new totalDate();

                        //????????? ??????
                        ti.type=TAG_JSON4;
                        ti.title=title;
                        ti.meeting_DATE=date;
                        ti.meeting_TIME=time;

                        td.type=TAG_JSON4;
                        td.title=title;
                        td.meeting_DATE=date;
                        td.meeting_TIME=time;
                        td.committee_NAME=dept;
                        td.url=url;

                        ti_list.add(ti);
                        if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                            to_list.add(td);
                        }
                    }
                }
                if(filter.get(j).equals(TAG_JSON5)){
                    JSONArray jsonArray4 = jsonObject.getJSONArray(TAG_JSON5);
                    for (int i = 0; i < jsonArray4.length(); i++) {//?????????

                        JSONObject item = jsonArray4.getJSONObject(i);

                        String date = item.getString("sdate");
                        String time = item.getString("stime");
                        String title = item.getString(TAG_TITLE);
                        String url = item.getString("link");
                        String dept=item.getString("name");
                        //????????? ????????? ??????
                        totalInfo ti=new totalInfo();
                        totalDate td=new totalDate();

                        //????????? ??????
                        ti.type=TAG_JSON5;
                        ti.title=title;
                        ti.meeting_DATE=date;
                        ti.meeting_TIME=time;

                        td.type=TAG_JSON5;
                        td.title=title;
                        td.meeting_DATE=date;
                        td.meeting_TIME=time;
                        td.committee_NAME=dept;
                        td.url=url;

                        ti_list.add(ti);
                        if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//???????????? ?????? ????????? ??????
                            to_list.add(td);
                        }

                    }
                }
            }

            a1.setCm_List(to_list);
            a1.notifyDataSetChanged();
            Log.d("?????????a1", String.valueOf(a1.getItemCount()));//???????????? ????????? ??????



        }catch(NullPointerException n){

            Log.d("??????", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("??????", "showResult : ", e);

        }

    }



}