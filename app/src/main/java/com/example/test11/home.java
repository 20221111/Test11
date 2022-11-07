package com.example.test11;

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



//외부에서 new Frag1 호출 시
public class home extends Fragment implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth2 = new SimpleDateFormat("yyyy-MM", Locale.KOREA);

    public String searchText;

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
        TextView textView_result = (TextView) v.findViewById(R.id.textView_result2);


        ArrayList<commMain> cm_List;

        long mNow;
        Date mDate;
        SimpleDateFormat mFormat=new SimpleDateFormat("yyyy-MM-dd");
        mNow=System.currentTimeMillis();
        mDate=new Date(mNow);
        searchText=mFormat.format(mDate);
        joinmember jm=new joinmember();
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);

        selectdata read = new selectdata();
        read.execute("http://ec2-13-231-175-154.ap-northeast-1.compute.amazonaws.com:8080/calender/month/"+searchText, "0");

        cm_List = new ArrayList<>();
        RecyclerView recyclerview;



        recyclerview = (RecyclerView) v.findViewById(R.id.listView_result);
        LinearLayoutManager linearLayoutManager;
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        // recyclerview에 adapter 적용
        recyclerview.setAdapter(read.a1); //selectData에서 add해도 성공
        read.a1.notifyDataSetChanged();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Log.d("어뎁터왔나", String.valueOf(read.a1.getItemCount()));//어뎁터에 세팅은 완료

            }
        }, 1000);

        //Log.d("어뎁터왔나", read.showResult().);//어뎁터에 세팅은 완료




        textView_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        textView_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getDate = textView_result.getText().toString();

                String[] getDate2 = getDate.split(":");
                String getDateST = getDate2[0];
                Log.d(TAG, "태그 getDateST : " + getDateST);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog dialog = builder.setTitle("일정을 삭제하겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 확인 클릭 시 실행할 거 작성
                                Date writeDate = null;
                                try {
                                    writeDate = simpleDate.parse(getDateST);
                                    Log.d(TAG, "태그 writeDate: " + writeDate);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Long currentLong = writeDate.getTime();
                                Log.d(TAG, "태그 currentLong : " + currentLong);

                                compactCalendarView.removeEvents(currentLong);
                                //textView_result.setText("");
                                Toast.makeText(getActivity(), "일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 취소 클릭 시 실행할 거 작성
                                Toast.makeText(getActivity(), "일정삭제가 취소되었습니다..", Toast.LENGTH_SHORT).show();
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

                read.to_list.clear();
                read.showResult(clickDate);
                //read.adapter.setCdate(clickDate,true);
                //read.adapter.notifyDataSetChanged();


                // 해당날짜에 이벤트가 있으면
                if (events.size() > 0) {
                    //textView_result.setText(events.get(0).getData().toString());
                }
                // 해당날짜에 이벤트가 없으면
                else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    AlertDialog dialog = builder.setTitle("일정추가하기")
                            .setMessage(clickDate + "에 일정을 추가하시겠습니까?")
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
                                                    String dateClicked_st = simpleDate.format(dateClicked);
                                                    Log.d(TAG, "태그 dateClicked_st : " + dateClicked_st);

                                                    Date currentDay = null;
                                                    try {
                                                        // .parse 함수 : Parses text from a string to produce a Date (문자열에서 텍스트를 분석하여 날짜 생성)
                                                        currentDay = simpleDate.parse(dateClicked_st);
                                                        Log.d(TAG, "태그 currentDay : " + currentDay);

                                                    } catch (ParseException e) {
                                                        e.printStackTrace();
                                                    }
                                                    Long currentLong = currentDay.getTime();
                                                    Log.d(TAG, "태그 currentLong : " + currentLong);

                                                    Event ev1 = new Event(Color.GREEN, currentLong, clickDate + " : " + editText.getText().toString());
                                                    compactCalendarView.addEvent(ev1);
                                                    //textView_result.setText(clickDate + " : " + editText.getText().toString());
                                                    Toast.makeText(getActivity(), "일정이 저장되었습니다.", Toast.LENGTH_SHORT).show();
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
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textView_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);

            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}

