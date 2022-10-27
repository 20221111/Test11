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
    String searchText;
    private TextView mTextViewResult;



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
        } else {

            Result = result;
            Log.d("데이터소통", result);
            showResult();

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
            forgot_Id[0]="name="+name;
            forgot_Id[1]="&email="+em;

        }
        else if(params[1]=="2"){//비밀번호 찾기
            forgot_pw[0]="id="+id;
            forgot_pw[1]="&security="+sec;

        }
        else if(params[1]=="3"){//로그인//이거 호출하고 나서 요청정보 jm에 넣으면 됨
            login[0]="id="+id;
            login[1]="&password="+pw;
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
            else if (params[1] == "1") {
                for (int i = 0; i < 2; i++) {
                    outputStream.write(forgot_Id[i].getBytes("UTF-8"));
                    Log.d("과연", forgot_Id[i]);
                }

            }
            else if (params[1] == "2") {
                for (int i = 0; i < 2; i++) {
                    outputStream.write(forgot_pw[i].getBytes("UTF-8"));
                    Log.d("과연", forgot_pw[i]);
                }

            }
            else if (params[1] == "3") {
                for (int i = 0; i < 2; i++) {
                    outputStream.write(login[i].getBytes("UTF-8"));
                    Log.d("과연", login[i]);
                }

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
        }
        else if(Result.contains("1")){
            Log.d("데이터소통", "에러");
        }
        else if(Result.contains("2")){
            Log.d("데이터소통", "잘못된 입력");
        }
        else {
            Log.d("데이터소통", "비밀번호 초기화");
        }

    }

    private void showResult2() throws JSONException {



    }
    private void showResult3() throws JSONException {

    }

}