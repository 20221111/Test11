package com.example.test11;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class insertData extends AsyncTask<String, Void, String> {
    //        ProgressDialog progressDialog;
    String errorString = null;
    private ArrayList<String> wishlist_drug;
    private String Result;
    String[] join={"email","name","password","security"};
    String[] forgot_Id={"email","name"};
    String[] forgot_pw={"id","security"};
    String[] login={"id","password"};
    String[] subs={"date","id","title","type"};
    String searchText;
    private TextView mTextViewResult;
    String loginresult;
    int postParameters=0;
    ArrayList<subscribe> ss_list=new ArrayList<>();
    ArrayList<memodata> mo_list=new ArrayList<>();
    ArrayList<totalDate> to_list=new ArrayList<>();
    AdapterUser au=new AdapterUser(ss_list);
    AdapterMemo am=new AdapterMemo(mo_list);
    String findpw;
    String findid;
    String memoresult="false";

    Adaptersearch as=new Adaptersearch(to_list);



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

//            progressDialog.dismiss();

        if (result == null) {
            mTextViewResult.setText(errorString);
        }

        else {

            Result = result;
            Log.d("데이터소통", result);

            switch(postParameters){
                case 0:
                    if(Result.contains("id")){//아이디찾기
                        //showResult2();
                    }

                    else if(Result.contains("password")){//비번찾기
                        //showResult3();
                    }

                    else if(Result.contains("err")){//에러의 경우
                        //읽을 필요 없음
                    }

                    else{
                        showResult();
                    }

                    break;
                case 1:
                    //Log.d("데이터소통-구독", Result);
                    showResult3();
                    break;
                case 2://아이디찾기
                    Log.d("아이디찾기", Result);
                    showResult2();
                    break;
                case 3://비밀번호 찾기
                    Log.d("비밀번호찾기", Result);
                    showResult4();
                    break;

                case 4:
                    //Log.d("메모입력", Result);
                    showResult5();
                    break;
                case 5:
                    //Log.d("데이터소통-메모", Result);
                    showResult6();
                    break;
                case 6:
                    showResult7();
                    break;




            }



        }

    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("과연", "test");
        String serverURL = params[0];
        Log.d("과연", serverURL);
        joinmember jm=new joinmember();
        String id=jm.getId();
        String pw=jm.getPassword();
        String em=jm.getEmail();
        String name=jm.getName();
        String sec=jm.getSecurity();

        if (params[1] == "0") {//회원가입
            Log.d("아이디", "비밀번호 : "+jm.id+jm.password);
            join[0]="email="+em;
            join[1]="&name="+name;
            join[2]="&password="+pw;
            join[3]="&security="+sec;
        }

        else if (params[1] == "1") { //아이디찾기
            postParameters=2;

        }
        else if(params[1]=="2"){//비밀번호 찾기
            postParameters=3;

        }
        else if(params[1]=="3"){
            //login[0]="id="+id;
            login[1]="&password="+pw;
        }

        else if(params[1]=="4"){//일정구독
            subs[0]="date="+em;
            subs[1]="&id="+name;
            subs[1]="&title="+name;
        }
        else if(params[1]=="5"){//일정구독불러오기
            postParameters=1;
        }
        else if(params[1]=="6"){//메모입력
            postParameters=4;
        }
        else if(params[1]=="7"){//메모불러오기
            postParameters=5;
        }
        else if(params[1]=="8"){//검색
            postParameters=6;
        }


        try {

            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(200000);
            httpURLConnection.setConnectTimeout(100000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.connect();

            //주석처리한 것은 post방식 프로토콜에서 쓰임
            OutputStream outputStream = httpURLConnection.getOutputStream();
            if (params[1] == "0") {
                for (int i = 0; i < 4; i++) {
                    outputStream.write(join[i].getBytes("UTF-8"));
                    Log.d("과연", join[i]);
                }

            }

            else{
                //outputStream.write();
            }

            outputStream.flush();
            outputStream.close();

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

    private void showResult() {//회원가입시 호출

        if(Result.contains("0")){//이부분 기능별로 분리될 필요가 있는데 ..흠 url으로 구분해야겠다
            Log.d("데이터소통", "성공");
            loginresult="true";
        }
        else if(Result.contains("1")){
            Log.d("데이터소통", "에러");
            loginresult="false";
        }
        else if(Result.contains("2")){
            Log.d("데이터소통", "에러 틀림");
            loginresult="false";
        }
        else {
            Log.d("데이터소통", "비밀번호 초기화");
        }

    }

    private void showResult2() {

        String TAG_JSON = "id";

        try {
            JSONObject jsonObject = new JSONObject(Result);
            //JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            findid=jsonObject.getString(TAG_JSON);
            Log.d("아이디찾기: ", findid);


        }catch(NullPointerException n){

            Log.d("과연", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("과연", "showResult : ", e);

        }
    }
    private void showResult4() {//비밀번호찾기

        String TAG_JSON = "id";

        try {
            JSONObject jsonObject = new JSONObject(Result);
            //JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            findpw=jsonObject.getString(TAG_JSON);
            Log.d("비밀번호찾기: ", findpw);


        }catch(NullPointerException n){

            Log.d("과연", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("과연", "showResult : ", e);

        }
    }

    private void showResult5() {
        //Log.d("메모결과", String.valueOf(Result.charAt(3)));
        Log.d("메모결과", String.valueOf(Result.charAt(2)));
        if(String.valueOf(Result.charAt(2)).equals("0")){
            memoresult="true";
            Log.d("메모결과", memoresult);
        }

    }

    private void showResult3() {

        String TAG_DATE = "date";
        String TAG_ID = "id";
        String TAG_NUM = "num";
        String TAG_TITLE = "title";
        String TAG_TYPE = "type";



        try {
            JSONArray jsonArray = new JSONArray(Result);
            for (int i = 0; i < jsonArray.length(); i++) { //일정구독

                JSONObject item = jsonArray.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String id = item.getString(TAG_ID);
                String number = item.getString(TAG_NUM);
                String title = item.getString(TAG_TITLE);
                String type = item.getString(TAG_TYPE);

                //이부분 생성자 선언
                subscribe ss=new subscribe();

                //생성자 세팅

                //bc.meetingsession=session;
                ss.num=number;
                ss.id=id;
                ss.date=date;
                ss.type=type;
                ss.title=title;


                ss_list.add(ss);
                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                //*adapter.setArrayData(drugData);
                //adapter.notifyDataSetChanged();*//*


            }

            au.setCm_List(ss_list);
            au.notifyDataSetChanged();
            Log.d("어뎁터au", String.valueOf(au.getItemCount()));//어뎁터에 세팅은 완료



        }catch(NullPointerException n){

            Log.d("과연", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("과연", "showResult : ", e);

        }

    }

    private void showResult6() {

        String TAG_DATE = "date";
        String TAG_ID = "id";
        String TAG_NUM = "num";
        String TAG_CONTENTS = "contents";

        try {
            JSONObject object =new JSONObject(Result);
            JSONArray jsonArray = object.getJSONArray("MEMO");
            for (int i = 0; i < jsonArray.length(); i++) { //일정구독

                JSONObject item = jsonArray.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String id = item.getString(TAG_ID);
                String number = item.getString(TAG_NUM);
                String content = item.getString(TAG_CONTENTS);

                //이부분 생성자 선언
                memodata mo=new memodata();

                //생성자 세팅
                mo.num=number;
                mo.id=id;
                mo.date=date;
                mo.contents=content;

                mo_list.add(mo);
                //아래 예시처럼 생성자에 세팅된 데이터 배열에 넣어주기
                //*adapter.setArrayData(drugData);
                //adapter.notifyDataSetChanged();*//*


            }

            am.setCm_List(mo_list);
            am.notifyDataSetChanged();
            Log.d("어뎁터am", String.valueOf(am.getItemCount()));//어뎁터에 세팅은 완료



        }catch(NullPointerException n){

            Log.d("과연", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("과연", "showResult : ", e);

        }

    }

    public void showResult7() {

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
            JSONObject jsonObject = new JSONObject(Result);
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

                totalDate td=new totalDate();

                td.type=TAG_JSON1;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=name;
                td.url=url;

                to_list.add(td);

            }

            for (int i = 0; i < jsonArray1.length(); i++) {//공청회

                JSONObject item = jsonArray1.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String dept=item.getString(TAG_CMANE);
                String url=item.getString("link_URL2");
                //이부분 생성자 선언
                totalDate td=new totalDate();

                td.type=TAG_JSON2;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=dept;
                td.url=url;

                to_list.add(td);


            }

            for (int i = 0; i < jsonArray2.length(); i++) {//위원회별본

                JSONObject item = jsonArray2.getJSONObject(i);

                String date = item.getString(TAG_DATE);
                String time = item.getString(TAG_TIME);
                String title = item.getString(TAG_TITLE);
                String dept=item.getString(TAG_CMANE);
                String url=item.getString("link_URL2");


                totalDate td=new totalDate();


                td.type=TAG_JSON3;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=dept;
                td.url=url;

                to_list.add(td);

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

                td.type=TAG_JSON4;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=dept;
                td.url=url;
                to_list.add(td);

            }

            for (int i = 0; i < jsonArray4.length(); i++) {//세미나

                JSONObject item = jsonArray4.getJSONObject(i);

                String date = item.getString("sdate");
                String time = item.getString("stime");
                String title = item.getString(TAG_TITLE);
                String url = item.getString("link");
                String dept=item.getString("name");
                //이부분 생성자 선언

                totalDate td=new totalDate();

                td.type=TAG_JSON5;
                td.title=title;
                td.meeting_DATE=date;
                td.meeting_TIME=time;
                td.committee_NAME=dept;
                td.url=url;

                to_list.add(td);

            }


            as.setCm_List(to_list);
            as.notifyDataSetChanged();
            Log.d("어뎁터a1", String.valueOf(as.getItemCount()));//어뎁터에 세팅은 완료



        }catch(NullPointerException n){

            Log.d("과연", "showResult : ",n);


        } catch (JSONException e) {

            Log.d("과연", "showResult : ", e);

        }

    }


}