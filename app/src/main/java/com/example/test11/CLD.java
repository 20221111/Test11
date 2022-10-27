package com.example.test11;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class CLD extends AppCompatActivity {
    private static final String TAG = "CLD";

    //달력
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth2 = new SimpleDateFormat("yyyy-MM", Locale.KOREA);
    //탭바
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private fragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        adapter=new fragmentAdapter(getSupportFragmentManager(),1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new home());
        adapter.addFragment(new menu());
        adapter.addFragment(new search());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        /*tabLayout.getTabAt(0).setText("홈");
        tabLayout.getTabAt(1).setText("검색");
        tabLayout.getTabAt(2).setText("마이페이지");*/

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.search);
        tabLayout.getTabAt(2).setIcon(R.drawable.user);

    }
}

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);

        //탭바
        fragmentAdapter adapter = new fragmentAdapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText("Tab " + (position + 1));
                    }
                }).attach();

        /*final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);

        TextView textView_month = (TextView) findViewById(R.id.textView_month);
        TextView textView_result = (TextView) findViewById(R.id.textView_result);


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
        });*/

    //}
//}


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
