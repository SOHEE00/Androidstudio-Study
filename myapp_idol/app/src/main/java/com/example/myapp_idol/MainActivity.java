package com.example.myapp_idol;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button infoButton, calculateButton;
    private EditText calculatorInput;
    private TextView calculatorResult;
    private ImageView groupImage, memberImage; // ImageView 추가

    // 스키즈 멤버 이름들
    private String[] memberNames = {"방찬", "리노", "창빈", "현진", "한", "필릭스", "승민", "아이엔"};

    // 멤버 이미지 배열 (사진 리소스 추가)
    private int[] memberImages = {
            R.drawable.bangchan, R.drawable.lino, R.drawable.changbin, R.drawable.hyunjin,
            R.drawable.han, R.drawable.felix, R.drawable.seungmin, R.drawable.ian
    };

    // 아이돌 그룹 정보
    private String debutDate = "2018-03-25";
    private String latestSong = "Chk-Chk-Boom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI 요소들 초기화
        infoButton = findViewById(R.id.infoButton);
        calculateButton = findViewById(R.id.calculateButton);
        calculatorInput = findViewById(R.id.calculatorInput);
        calculatorResult = findViewById(R.id.calculatorResult);
        groupImage = findViewById(R.id.groupImage);  // 그룹 이미지
        memberImage = findViewById(R.id.memberImage); // 멤버 이미지

        // 아이돌 정보 보기 버튼 클릭 리스너
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아이돌 그룹 정보 메시지로 표시
                StringBuilder groupInfo = new StringBuilder();
                groupInfo.append("스트레이 키즈 멤버들: \n");
                for (String member : memberNames) {
                    groupInfo.append(member).append("\n");
                }
                groupInfo.append("\n데뷔일: ").append(debutDate)
                        .append("\n최신곡: ").append(latestSong);

                // AlertDialog로 아이돌 정보 출력
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("아이돌 그룹 정보")
                        .setMessage(groupInfo.toString())
                        .setPositiveButton("확인", null)  // 확인 버튼
                        .show();
            }
        });

        // 투표 버튼 클릭 리스너
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력된 멤버 이름을 가져오기
                String inputName = calculatorInput.getText().toString().trim();

                // 해당 멤버 이름이 목록에 있는지 확인
                boolean isValidMember = false;
                int memberIndex = -1; // 멤버의 인덱스를 저장할 변수

                for (int i = 0; i < memberNames.length; i++) {
                    if (memberNames[i].equalsIgnoreCase(inputName)) {
                        isValidMember = true;
                        memberIndex = i;
                        break;
                    }
                }

                // 유효한 멤버일 경우
                if (isValidMember) {
                    // 투표 결과를 AlertDialog로 표시
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("투표 완료")
                            .setMessage(inputName + "에게 투표하셨습니다.")
                            .setPositiveButton("확인", null)  // 확인 버튼
                            .show();

                    // TextView에 투표한 멤버의 이름 표시
                    calculatorResult.setText("당신이 선택한 멤버는 : " + inputName);  // TextView에 동적으로 값 변경

                    // 해당 멤버의 사진을 ImageView에 설정
                    memberImage.setVisibility(View.VISIBLE); // 이미지를 표시
                    memberImage.setImageResource(memberImages[memberIndex]); // 이미지 변경

                } else {
                    // 유효하지 않은 멤버일 경우
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("오류")
                            .setMessage("정확한 멤버 이름을 입력하세요.")
                            .setPositiveButton("확인", null)  // 확인 버튼
                            .show();
                }
            }
        });
    }
}