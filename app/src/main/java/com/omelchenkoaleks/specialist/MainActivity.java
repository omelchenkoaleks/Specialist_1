package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Это обязательный метод, потому-что в суперклассе происходит инициализация
        // всего на свете, и сами мы это сделать не можем!!!
        super.onCreate(savedInstanceState);
        // Этот метод перегружен и имеет два типа параметров, который может принимать...
        // Какой-нибудь объект или разметку...
        // Мы этой строчкой говорим, что будем показывать...
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Toast.makeText(this, getString(R.string.message_btn), Toast.LENGTH_SHORT).show();
    }
}
