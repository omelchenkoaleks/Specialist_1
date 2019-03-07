package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Это обязательный метод, потому-что в суперклассе происходит инициализация
        // всего на свете, и сами мы это сделать не можем!!!
        super.onCreate(savedInstanceState);
        // Этот метод перегружен и имеет два типа параметров, который может принимать...
        // Какой-нибудь объект или разметку...
        setContentView(R.layout.activity_main);
    }
}
