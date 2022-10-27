package com.example.test11;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CLD extends AppCompatActivity { // 경향님
    private static final String TAG = "CLD";

    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth2 = new SimpleDateFormat("yyyy-MM", Locale.KOREA);
    ArrayList<commMain> cm_List;


      // adapter 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        cm_List = new ArrayList<>();
        RecyclerView recyclerview;
        Adapter adapter = new Adapter(cm_List);
        Log.d("어뎁터테스트", String.valueOf(adapter.getItemCount()));//어뎁터에 세팅은 완료
        recyclerview = findViewById(R.id.listView_result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        // recyclerview에 adapter 적용
        recyclerview.setAdapter(adapter);

        TextView textView_month = (TextView) findViewById(R.id.textView_month);
        TextView textView_result = (TextView) findViewById(R.id.textView_result2);




        textView_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);




        textView_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getDate = textView_result.getText().toString();

                String[] getDate2 = getDate.split(":");
                String getDateST = getDate2[0];
                Log.d(TAG, "태그 getDateST : " +  getDateST);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

                AlertDialog.Builder builder = new AlertDialog.Builder(CLD.this);
                AlertDialog dialog = builder.setTitle("일정을 삭제하겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 확인 클릭 시 실행할 거 작성
                                Date writeDate = null;
                                try {
                                    writeDate = simpleDate.parse(getDateST);
                                    Log.d(TAG, "태그 writeDate: " +writeDate);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Long currentLong = writeDate.getTime();
                                Log.d(TAG, "태그 currentLong : " + currentLong);

                                compactCalendarView.removeEvents(currentLong);
                                textView_result.setText("");
                                Toast.makeText(CLD.this, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 취소 클릭 시 실행할 거 작성
                                Toast.makeText(CLD.this, "메모 삭제가 취소되었습니다..", Toast.LENGTH_SHORT).show();
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

                // 해당날짜에 이벤트가 있으면
                if (events.size() > 0) {
                    textView_result.setText(events.get(0).getData().toString());
                }
                // 해당날짜에 이벤트가 없으면
                else {
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                    String clickDate = simpleDate.format(dateClicked);


                    AlertDialog.Builder builder = new AlertDialog.Builder(CLD.this);
                    AlertDialog dialog = builder.setTitle("메모하기") //팝업창 제목
                            .setMessage(clickDate + "에 메모를 추가하시겠습니까?") //팝업창 질문
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 확인 클릭 시 실행할 거 작성
                                    Toast.makeText(CLD.this, "확인되었습니다.", Toast.LENGTH_SHORT).show();
                                    EditText editText = new EditText(CLD.this);

                                    // 일정입력할 팝업 띄우기
                                    AlertDialog.Builder builder = new AlertDialog.Builder(CLD.this);
                                    AlertDialog dialogText = builder.setTitle("추가할 메모를 입력해 주세요.")
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

                                                    Event ev1 = new Event(Color.GREEN, currentLong, clickDate + " : "+ editText.getText().toString());
                                                    compactCalendarView.addEvent(ev1);
                                                    textView_result.setText(clickDate + " : "+ editText.getText().toString());
                                                    Toast.makeText(CLD.this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // 취소 클릭 시 실행할 거 작성
                                                    Toast.makeText(CLD.this, "메모 입력이 취소되었습니다.", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(CLD.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
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

    }
}

/*public class MainActivity extends AppCompatActivity {
    final String TAG = "calendar test";
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth2 = new SimpleDateFormat("yyyy-MM", Locale.KOREA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        TextView textView_month = (TextView) findViewById(R.id.textView_month); //month
        TextView textView_result = (TextView) findViewById(R.id.textView_result); //클릭한 날짜 정보
        TextView textView_result2 = (TextView) findViewById(R.id.textView_result2); //가져온 날짜 정보

        textView_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);//SUNDAY부터 나타내기


        Button button_get_event = (Button) findViewById(R.id.button_get_event) ; //1일의 이벤트 가져오기
        button_get_event.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date dateyymm = compactCalendarView.getFirstDayOfCurrentMonth();
                String yyymm = dateFormatForMonth2.format(dateyymm);

                String date = yyymm + "-01"; //"2021-11-01";

                Date trans_date = null;
                try {
                    trans_date = dateFormatForDisplaying.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long time = trans_date.getTime();
                List<Event> events = compactCalendarView.getEvents(time);

                String info = "";
                if (events.size() > 0)
                {
                    info = events.get(0).getData().toString();
                }

                textView_result2.setText("이벤트 이름 : " + info);
            }
        });


        // 이벤트 관련 코드
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                List<Event> events = compactCalendarView.getEvents(dateClicked);

                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = transFormat.format(dateClicked);

                String event_name = "";
                String event_date = "";

                if (events.size() > 0) {
                    event_name = events.get(0).getData().toString();
                    long time1 = events.get(0).getTimeInMillis();
                    event_date = transFormat.format(new Date(time1));
                }

                textView_result.setText("클릭한 날짜 " + date1 + " event 정보 " + event_name + " " + event_date);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textView_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });
    }
}*/
