package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SlotDataSource.SlotCallback {

    private Button btnA1, btnA2;
    private SlotDataSource.SlotListener mListener;
    private int flagA1 = 0, flagA2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnA1 = findViewById(R.id.btnA1);
        btnA2 = findViewById(R.id.btnA2);

        mListener = SlotDataSource.addSlotListener(this);

        btnA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Slot slotUser = new Slot();
                slotUser.setSeat("A1");

                switch (flagA1) {
                    case 0:
                        slotUser.setFlag("dangdat");
                        flagA1 = 1;
                        break;
                    case 1:
                        slotUser.setFlag("chuadat");
                        flagA1 = 0;
                        break;
                }


                //lưu tin nhắn vào firebase
                SlotDataSource.saveSlot(slotUser);

            }
        });

        btnA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Slot slotUser = new Slot();
                slotUser.setSeat("A2");

                switch (flagA2) {
                    case 0:
                        slotUser.setFlag("dangdat");
                        flagA2 = 1;
                        break;
                    case 1:
                        slotUser.setFlag("chuadat");
                        flagA2 = 0;
                        break;
                }


                //lưu tin nhắn vào firebase
                SlotDataSource.saveSlot(slotUser);

            }
        });
    }

    @Override
    public void onSlotChange(Slot slot) {
        switch (slot.getFlag()) {
            case "chuadat": //chưa đặt
                if (slot.getSeat().equals("A1")) {
                    btnA1.setText("A1");
                } else {
                    btnA2.setText("A2");
                }

                break;
            case "dangdat": //đang đặt
                if (slot.getSeat().equals("A1")) {
                    btnA1.setText("A1 - Đang đặt");
                } else {
                    btnA2.setText("A2 - Đang đặt");
                }

                break;
            case "dadat": //đã đặt
                if (slot.getSeat().equals("A1")) {
                    btnA1.setText("A1 - Đã đặt");
                } else {
                    btnA2.setText("A2 - Đã đặt");
                }

                break;
        }
    }
}