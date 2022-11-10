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
            Log.d("데이터소통-일정", result);
            showResult(searchText);



        }
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("과연", "test");
        String serverURL = params[0];
        Log.d("과연", serverURL);
        if (params[1] == "0") { //파라미터부분 사실상 없어도 될 것 같음 나중에 수정 (10.26)
            postParameters=0;
        } else if (params[1] == "1") { //필터링요청 입력
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

            //주석처리한 것은 post방식 프로토콜에서 쓰임
            //OutputStream outputStream = httpURLConnection.getOutputStream();
            if (params[1] == "0") {
                //outputStream.write(postParameters.getBytes("UTF-8"));
               Log.d("과연", "그냥");
            }
            else if (params[1] == "1") {

            }

            //outputStream.flush();
            //outputStream.close();

            int responseStatusCode = httpURLConnection.getResponseCode();
            Log.d("과연", "response code - " + responseStatusCode);

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

            Log.d("과연", "InsertData : Error ", e);
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
            for (int i = 0; i < jsonArray.length(); i++) { //본회의

                JSONObject item = jsonArray.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String url = item.getString("link_URL");
                String name = item.getString("meetingsession");

                //이부분 생성자 선언
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //생성자 세팅

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
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//디폴트로 오늘 날짜만 출력
                    to_list.add(td);
                }
                //to_list.add(td);
                /*adapter.setCm_List(td);
                adapter.notifyDataSetChanged();*/




            }

            for (int i = 0; i < jsonArray1.length(); i++) {//공청회

                JSONObject item = jsonArray1.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String dept=item.getString(TAG_CMANE);
                String url=item.getString("link_URL2");
                //이부분 생성자 선언
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //생성자 세팅
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
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//디폴트로 오늘 날짜만 출력
                    to_list.add(td);
                }
                /*adapter.setCm_List(td);
                adapter.notifyDataSetChanged();*/
                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray2.length(); i++) {//위원회별본

                JSONObject item = jsonArray2.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String dept=item.getString(TAG_CMANE);
                String url=item.getString("link_URL2");

                //이부분 생성자 선언
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //생성자 세팅

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
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//디폴트로 오늘 날짜만 출력
                    to_list.add(td);
                }
                //cm_list.add(cm);
                //Log.d("어뎁터테스트", String.valueOf(adapter.getItemCount()));//어뎁터에 세팅은 완료



                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray3.length(); i++) {//소회의

                JSONObject item = jsonArray3.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String dept=item.getString(TAG_CMANE);
                String url=item.getString("link_URL2");
                //이부분 생성자 선언
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //생성자 세팅
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
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//디폴트로 오늘 날짜만 출력
                    to_list.add(td);
                }


                /*adapter.setCm_List(td);
                adapter.notifyDataSetChanged();*/


                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray4.length(); i++) {//세미나

                JSONObject item = jsonArray4.getJSONObject(i);

                String date = item.getString("sdate");
                String time = item.getString("stime");
                String title = item.getString(TAG_TITLE);
                String url = item.getString("link");
                String dept=item.getString("name");
                //이부분 생성자 선언
                totalInfo ti=new totalInfo();
                totalDate td=new totalDate();

                //생성자 세팅
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
                if(td.getMeeting_DATE().substring(0,10).equals(searchText)){//디폴트로 오늘 날짜만 출력
                    to_list.add(td);
                }


                /*adapter.setCm_List(td);
                adapter.notifyDataSetChanged();*/


                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }


            a1.setCm_List(to_list);
            a1.notifyDataSetChanged();
            Log.d("어뎁터a1", String.valueOf(a1.getItemCount()));//어뎁터에 세팅은 완료



        }catch(NullPointerException n){

            Log.d("과연", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("과연", "showResult : ", e);

        }

    }



}