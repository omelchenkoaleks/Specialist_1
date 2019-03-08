package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Это обязательный метод, потому-что в суперклассе происходит инициализация
        // всего на свете, и сами мы это сделать не можем!!!
        super.onCreate(savedInstanceState);
        // Этот метод перегружен и имеет два типа параметров, который может принимать...
        // Какой-нибудь объект или разметку...
        // Мы этой строчкой говорим, что будем показывать...
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        MainActivity.this,
                        getString(R.string.message_btn),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
