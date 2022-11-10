package com.example.test11;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//외부에서 new Frag1 호출 시
public class home extends Fragment implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth2 = new SimpleDateFormat("yyyy-MM", Locale.KOREA);
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

    public String searchText;
    public String clickD;
    selectdata read1;
    boolean scroll=false;
    Event ev1;
    Date currentDay = null;
    Date currentDay1 = null;
    Long currentLong;
    Long currentLong1;
    Event ev2;
    Event ev3;
    Event ev4;
    Event ev5;
    Event ev6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        //필터링 팝업
        Button NewAccount = v.findViewById(R.id.test);
        NewAccount.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              final View popupView = getLayoutInflater().inflate(R.layout.popup, null);
                                              final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                              builder.setView(popupView);

                                              final AlertDialog alertDialog = builder.create();
                                              alertDialog.show();

                                              //확인버튼
                                              Button btnInsert = popupView.findViewById(R.id.ok);
                                              btnInsert.setOnClickListener(new Button.OnClickListener(){
                                                  public void onClick(View v){
                                                      /*
                                                      필터링 목록 저장하여 화면에 반환하는 코드 생성
                                                       */
                                                  }
                                              });

                                              //취소버튼
                                              Button btnCancel = popupView.findViewById(R.id.cancel);
                                              btnCancel.setOnClickListener(new Button.OnClickListener(){
                                                  public void onClick(View v){
                                                      alertDialog.dismiss();
                                                  }
                                              });
                                          }
                                      });

            //ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        final CompactCalendarView compactCalendarView = (CompactCalendarView) v.findViewById(R.id.compactcalendar_view);

        TextView textView_month = (TextView) v.findViewById(R.id.textView_month);
        Button textView_result = (Button) v.findViewById(R.id.textView_result2);



        ArrayList<commMain> cm_List;
        MainActivity ma=new MainActivity();

        long mNow;
        Date mDate;
        SimpleDateFormat mFormat=new SimpleDateFormat("yyyy-MM-dd");
        mNow=System.currentTimeMillis();
        mDate=new Date(mNow);
        searchText=mFormat.format(mDate);
        read1=new selectdata();
        textView_result.setText(searchText);
        clickD=searchText;
        joinmember jm=new joinmember();
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        selectdata read = new selectdata();

        read.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/calender/month/"+searchText, "0");

        insertData read2 = new insertData();
        read2.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Memo/Show/"+ma.memberid, "7");

        RecyclerView recyclerview;

        recyclerview = (RecyclerView) v.findViewById(R.id.listView_result);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        // recyclerview에 adapter 적용
        recyclerview.setAdapter(read.a1); //selectData에서 add해도 성공
        read.a1.notifyDataSetChanged();
        //색깔


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Log.d("어뎁터왔나", String.valueOf(read.a1.getItemCount()));//어뎁터에 세팅은 완료
                selectdata st =new selectdata();

                //Date currentDay = null;
                String data;
                //Log.d("날짜확인", String.valueOf(read.ti_list.size()));
                try {
                    // .parse 함수 : Parses text from a string to produce a Date (문자열에서 텍스트를 분석하여 날짜 생성)
                    for(int i=0;i<read.ti_list.size();i++){
                        currentDay = simpleDate.parse(read.ti_list.get(i).getMeeting_DATE());
                        //Log.d("날짜확인", read.ti_list.get(i).getMeeting_DATE());
                        currentLong = currentDay.getTime();
                        currentLong1=currentDay.getTime();
                        if(read.ti_list.get(i).getType().equals("bonsche")){
                            ev2 = new Event(Color.RED, currentLong,read.ti_list.get(i).getTitle());
                            compactCalendarView.addEvent(ev2);
                        }
                        else if(read.ti_list.get(i).getType().equals("commKong")){
                            ev3 = new Event(Color.LTGRAY, currentLong,read.ti_list.get(i).getTitle());
                            compactCalendarView.addEvent(ev3);
                        }
                        else if(read.ti_list.get(i).getType().equals("commMain")){
                            ev4 = new Event(Color.BLACK, currentLong,read.ti_list.get(i).getTitle());
                            compactCalendarView.addEvent(ev4);
                        }
                        else if(read.ti_list.get(i).getType().equals("commSmall")){
                            ev5 = new Event(Color.MAGENTA, currentLong,read.ti_list.get(i).getTitle());
                            compactCalendarView.addEvent(ev5);
                        }

                        else if(read.ti_list.get(i).getType().equals("seiminar")){
                            ev6 = new Event(Color.BLUE, currentLong,read.ti_list.get(i).getTitle());
                            compactCalendarView.addEvent(ev6);
                        }

                    }

                    for(int i=0;i<read2.mo_list.size();i++){
                        currentDay = simpleDate.parse(read2.mo_list.get(i).getDate());
                        Long currentLong = currentDay.getTime();
                        ev1 = new Event(Color.GREEN, currentLong, read2.mo_list.get(i).getContents());
                        //Log.d("메모 점찍기 확인 ", read2.mo_list.get(i).getContents());
                        compactCalendarView.addEvent(ev1);
                    }

                    Log.d(TAG, "태그 currentDay : " + currentDay);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }, 1000);

        //Log.d("어뎁터왔나", read.showResult().);//어뎁터에 세팅은 완료

        //이벤트 점 표시



        //리사이클러뷰위에 날짜 누르면 일정추가됨
        textView_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        textView_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getDate = textView_result.getText().toString();

                String[] getDate2 = getDate.split(":");
                String getDateST = getDate2[0];
                Log.d("메모", "태그 getDateST : " + getDateST);


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog dialog = builder.setTitle("일정추가하기")
                        .setMessage(clickD + "에 일정을 추가하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 확인 클릭 시 실행할 거 작성
                                Toast.makeText(getActivity(), "확인되었습니다.", Toast.LENGTH_SHORT).show();
                                EditText editText = new EditText(getActivity());

                                // 일정입력할 팝업 띄우기
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                AlertDialog dialogText = builder.setTitle("추가할 일정을 입력해 주세요.")
                                        // .setMessage("메시지 입력")
                                        .setView(editText)
                                        .setPositiveButton("저장하기", new DialogInterface.OnClickListener() {
                                            @SuppressLint("SetTextI18n")
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //메모 api불러오기
                                                //http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Memo/push/rr?Contents=ff&date=2022-11-09

                                                insertData insert = new insertData(); //로그인 가동
                                                MainActivity am=new MainActivity();
                                                String memo= String.valueOf(editText.getText());
                                                Log.d("메모입력", memo+":"+clickD);

                                                insert.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Memo/push/"+am.memberid+"?Contents="+memo+"&date="+clickD, "6");

                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        switch (insert.memoresult){
                                                            case "true":
                                                                Date currentDay = null;
                                                                try {
                                                                    // .parse 함수 : Parses text from a string to produce a Date (문자열에서 텍스트를 분석하여 날짜 생성)
                                                                    currentDay = simpleDate.parse(clickD);
                                                                    //Log.d(TAG, "태그 currentDay : " + currentDay);

                                                                } catch (ParseException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                Long currentLong = currentDay.getTime();
                                                                Log.d(TAG, "태그 currentLong : " + currentLong);

                                                                Event ev7 = new Event(Color.GREEN, currentLong, currentDay + " : " + editText.getText().toString());
                                                                compactCalendarView.addEvent(ev7);
                                                                //textView_result.setText(clickDate + " : " + editText.getText().toString());
                                                                Toast.makeText(getActivity(), "일정이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                                                                break;

                                                            case "false":
                                                                Toast.makeText(getActivity(), "일정이 저장실패", Toast.LENGTH_SHORT).show();
                                                                break;

                                                        }
                                                    }
                                                }, 1000);


                                            }
                                        })
                                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // 취소 클릭 시 실행할 거 작성
                                                Toast.makeText(getActivity(), "일정입력 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .create();
                                dialogText.show();


                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 취소 클릭 시 실행할 거 작성
                                Toast.makeText(getActivity(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                dialog.show();

            }
        });

        // 날짜 클릭 이벤트 관련 코드
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Log.d(TAG, "태그 dateClicked : " + dateClicked);
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                String clickDate = simpleDate.format(dateClicked);
                clickD=clickDate;
                textView_result.setText(clickDate);

                Log.d("과연", String.valueOf(scroll));

                switch (String.valueOf(scroll)){
                    case "true":
                        read1.to_list.clear();
                        read1.showResult(clickDate);
                        break;
                    case "false":
                        read.to_list.clear();
                        read.showResult(clickDate);

                }





                //read.adapter.setCdate(clickDate,true);
                //read.adapter.notifyDataSetChanged();




            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                scroll=true;
                textView_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                Log.d(TAG, "달력 이전으로 넘어가는거임?: " + firstDayOfNewMonth);
                //selectdata read1 = new selectdata();
                searchText=mFormat.format(firstDayOfNewMonth);
                //read.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/calender/month/"+searchText, "0");


                ExecutorService THREAD_POOL= Executors.newFixedThreadPool(12);
                read1=new selectdata();
                read1.executeOnExecutor(THREAD_POOL,"http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/calender/month/"+searchText, "0");

                insertData read2 = new insertData();
                read2.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/Memo/Show/"+ma.memberid, "7");

                recyclerview.setAdapter(read1.a1); //selectData에서 add해도 성ㄷ공
                read1.a1.notifyDataSetChanged();



                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {//버그있음 리스트 안 비워짐 -> 비워야함
                        //Log.d("어뎁터왔나", String.valueOf(read1.a1.getItemCount()));//어뎁터에 세팅은 완료
                        //selectdata st =new selectdata();

                        //Date currentDay1 = null;

                        compactCalendarView.removeAllEvents();


                        String data;
                        Log.d("날짜확인", String.valueOf(read1.ti_list.size()));
                        try {
                            // .parse 함수 : Parses text from a string to produce a Date (문자열에서 텍스트를 분석하여 날짜 생성)
                            for(int i=0;i<read1.ti_list.size();i++){
                                currentDay1 = simpleDate.parse(read1.ti_list.get(i).getMeeting_DATE());
                                currentLong1 = currentDay1.getTime();
                                //Log.d("날짜확인", read1.ti_list.get(i).getMeeting_DATE());
                                //compactCalendarView.removeEvents(currentLong1);

                                if(read1.ti_list.get(i).getType().equals("bonsche")){

                                    Event ev8 = new Event(Color.RED, currentLong1,read1.ti_list.get(i).getTitle());
                                    compactCalendarView.addEvent(ev8);
                                    //compactCalendarView.addEvent(ev2);
                                }
                                else if(read1.ti_list.get(i).getType().equals("commKong")){
                                    Event ev9 = new Event(Color.LTGRAY, currentLong1,read1.ti_list.get(i).getTitle());
                                    compactCalendarView.addEvent(ev9);
                                    //compactCalendarView.addEvent(ev3);
                                }
                                else if(read1.ti_list.get(i).getType().equals("commMain")){
                                    Event ev10 = new Event(Color.BLACK, currentLong1,read1.ti_list.get(i).getTitle());
                                    compactCalendarView.addEvent(ev10);
                                    //compactCalendarView.addEvent(ev4);
                                }
                                else if(read1.ti_list.get(i).getType().equals("commSmall")){
                                    Event ev11 = new Event(Color.MAGENTA, currentLong1,read1.ti_list.get(i).getTitle());
                                    compactCalendarView.addEvent(ev11);
                                    //compactCalendarView.addEvent(ev5);
                                }

                                else if(read1.ti_list.get(i).getType().equals("seiminar")){
                                    Event ev12 = new Event(Color.BLUE, currentLong1,read1.ti_list.get(i).getTitle());
                                    compactCalendarView.addEvent(ev12);
                                    //compactCalendarView.addEvent(ev12);
                                }

                            }

                            for(int i=0;i<read2.mo_list.size();i++){
                                currentDay = simpleDate.parse(read2.mo_list.get(i).getDate());
                                Long currentLong = currentDay.getTime();
                                ev1 = new Event(Color.GREEN, currentLong, read2.mo_list.get(i).getContents());
                                //Log.d("메모 점찍기 확인 ", read2.mo_list.get(i).getContents());
                                compactCalendarView.addEvent(ev1);
                            }

                            Log.d(TAG, "태그 currentDay : " + currentDay);

                            Log.d(TAG, "태그 currentDay : " + currentDay1);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, 5000);

            }

        });

        return v;


    }

    @Override
    public void onClick(View v) {

    }
}

