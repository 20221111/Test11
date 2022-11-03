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
import java.util.ArrayList;

public class selectdata extends AsyncTask<String, Void, String> {
    //        ProgressDialog progressDialog;
    String errorString = null;
    private ArrayList<String> wishlist_drug;
    private String mJsonString;
    private String postParameters;
    String searchText;
    private TextView mTextViewResult;
    //ArrayList<bonsche> bonsche_list;
    //ArrayList<commKong> ck_list;
    //ArrayList<commMain> cm_list;
    //ArrayList<commSmall> cs_list;
        // adapter 생성
    ArrayList<commMain> cm_list = new ArrayList<>();
    RecyclerView recyclerview;
    Adapter adapter = new Adapter(cm_list);


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

        if (result == null) {
            mTextViewResult.setText(errorString);
        } else {

            mJsonString = result;
            Log.d("데이터소통", result);
            showResult(0);


        }
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("과연", "test");
        String serverURL = params[0];
        Log.d("과연", serverURL);
        if (params[1] == "0") { //파라미터부분 사실상 없어도 될 것 같음 나중에 수정 (10.26)

            //Log.d("과연", searchText);
            postParameters = "month=" + searchText;
        } else if (params[1] == "1") { //필터링요청 입력

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

    private void showResult(int num) {

        String TAG_JSON2 = "commKong";
        String TAG_JSON1 = "bonsche";
        String TAG_JSON3 = "commMain";
        String TAG_JSON4 = "commSmall";
        String TAG_CHA = "cha";
        String TAG_URL = "link_URL";
        String TAG_DATE = "meeting_DATE";
        String TAG_TIME = "meeting_TIME";
        String TAG_SESSION = "meetingsession";
        String TAG_TITLE = "title";
        String TAG_CD = "unit_CD";
        String TAG_NM = "unit_NM";
        String TAG_SESS = "sess";
        String TAG_CMANE = "committee_NAME";
        String TAG_URL2 = "link_URL2";
        String TAG_DEPT = "hr_DEPT_CD";



        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON1);
            JSONArray jsonArray1 = jsonObject.getJSONArray(TAG_JSON2);
            JSONArray jsonArray2 = jsonObject.getJSONArray(TAG_JSON3);
            JSONArray jsonArray3 = jsonObject.getJSONArray(TAG_JSON4);
            for (int i = 0; i < jsonArray.length(); i++) { //본회의

                JSONObject item = jsonArray.getJSONObject(i);

                String cha = item.getString(TAG_CHA);
                String url = item.getString(TAG_URL);
                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String session = item.getString(TAG_SESSION);
                String title = item.getString(TAG_TITLE);
                String cd = item.getString(TAG_CD);
                String nm = item.getString(TAG_NM);

                //이부분 생성자 선언
                bonsche bc=new bonsche();

                //생성자 세팅
                //예)drugData.setDrugName(name);
                bc.setCha(cha);
                bc.setLink_URL(url);
                bc.setMeeting_DATE(date);
                bc.setMeeting_TIME(time);
                bc.setTitle(title);
                bc.setMeetingsessioness(session);
                bc.setUnit_CD(cd);
                bc.setUnit_NM(nm);


                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray1.length(); i++) {//공청회

                JSONObject item = jsonArray1.getJSONObject(i);

                String url2 = item.getString(TAG_URL2);
                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String session = item.getString(TAG_SESS);
                String title = item.getString(TAG_TITLE);
                String cd = item.getString(TAG_CD);
                String nm = item.getString(TAG_NM);
                String dept=item.getString(TAG_DEPT);
                //이부분 생성자 선언
                commKong ck=new commKong();

                //생성자 세팅
                ck.setLink_URL2(url2);
                ck.setMeeting_DATE(date);
                ck.setMeeting_TIME(time);
                ck.setTitle(title);
                ck.setSess(session);
                ck.setUnit_CD(cd);
                ck.setUnit_NM(nm);
                ck.setHr_DEPT_CD(dept);


                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray2.length(); i++) {//위원회별본

                JSONObject item = jsonArray2.getJSONObject(i);

                String url2 = item.getString(TAG_URL2);
                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String session = item.getString(TAG_SESS);
                String title = item.getString(TAG_TITLE);
                String cd = item.getString(TAG_CD);
                String nm = item.getString(TAG_NM);
                String dept=item.getString(TAG_DEPT);
                //이부분 생성자 선언
                commMain cm=new commMain();

                //생성자 세팅
                /*cm.setLink_URL2(url2);
                cm.setMeeting_DATE(date);
                cm.setMeeting_TIME(time);
                cm.setTitle(title);
                cm.setSess(session);
                cm.setUnit_CD(cd);
                cm.setUnit_NM(nm);
                cm.setHr_DEPT_CD(dept);*/

                cm.title=title;
                cm.sess=session;
                cm.meeting_TIME=time;
                cm.unit_CD=cd;
                cm.unit_NM=nm;
                cm.meeting_DATE=date;
                cm.hr_DEPT_CD=dept;
                cm.link_URL2=url2;

                adapter.setCm_List(cm);
                adapter.notifyDataSetChanged();
                Log.d("어뎁터테스트", String.valueOf(adapter.getItemCount()));//어뎁터에 세팅은 완료



                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }

            for (int i = 0; i < jsonArray3.length(); i++) {//소회의

                JSONObject item = jsonArray3.getJSONObject(i);

                String url2 = item.getString(TAG_URL2);
                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String session = item.getString(TAG_SESS);
                String title = item.getString(TAG_TITLE);
                String cd = item.getString(TAG_CD);
                String nm = item.getString(TAG_NM);
                String dept=item.getString(TAG_DEPT);
                //이부분 생성자 선언
                commSmall cs=new commSmall();

                //생성자 세팅
                cs.setLink_URL2(url2);
                cs.setMeeting_DATE(date);
                cs.setMeeting_TIME(time);
                cs.setTitle(title);
                cs.setSess(session);
                cs.setUnit_CD(cd);
                cs.setUnit_NM(nm);
                cs.setHr_DEPT_CD(dept);


                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                /*adapter.setArrayData(drugData);
                adapter.notifyDataSetChanged();*/


            }



        }catch(NullPointerException n){

            Log.d("과연", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("과연", "showResult : ", e);

        }

    }

    private void showResult2() throws JSONException {



    }
    private void showResult3() throws JSONException {

    }


}