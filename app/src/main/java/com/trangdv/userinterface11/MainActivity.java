package com.trangdv.userinterface11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edt_numberA;
    private EditText edt_numberB;
    private Button btn_add;
    private Button btn_sub;
    private Button btn_mul;
    private Button btn_div;
    private TextView tv_result;

    private String textNumberA;
    private String textNumberB;
    private String math_operations;
    private double value = 0;
    private RecyclerView recyclerView;
    private Expression expression;

    RvAdapter rvAdapter;

    private List<String> results = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        clickButton();


    }

    private void clickButton() {

        setBtn_add(btn_add);
        setBtn_sub(btn_sub);
        setBtn_mul(btn_mul);
        setBtn_div(btn_div);
    }

    private void initViews() {
        edt_numberA = findViewById(R.id.number_a);
        edt_numberB = findViewById(R.id.number_b);
        btn_add = findViewById(R.id.add_btn);
        btn_sub = findViewById(R.id.sub_btn);
        btn_mul = findViewById(R.id.mul_btn);
        btn_div = findViewById(R.id.div_btn);
        tv_result = findViewById(R.id.tv_result);
        recyclerView = findViewById(R.id.rv_result);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void getTextfromEdt(EditText edt_numberA, EditText edt_numberB) {
        textNumberA = edt_numberA.getText().toString();
        textNumberB = edt_numberB.getText().toString();
    }

    //

    public void caculator(String operation) {
        getTextfromEdt(edt_numberA, edt_numberB);
        if (textNumberA.equals("")==false && textNumberB.equals("")==false) {
            tv_result.setText(edt_numberA.getText() +" "+ operation +" "+edt_numberB.getText());
            math_operations = tv_result.getText().toString();
            expression = new ExpressionBuilder(math_operations).build();
            try {
                value = expression.evaluate();
                tv_result.setText(tv_result.getText()+ " = " +Double.toString(value));


            }catch (ArithmeticException e) {
                tv_result.setText(tv_result.getText()+ " = ∞");
            }
            results.add(tv_result.getText().toString());
            rvAdapter = new RvAdapter(results);
            recyclerView.setAdapter(rvAdapter);
        }
    }

    //

    public void setBtn_add(Button btn_add) {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caculator("+");
            }
        });
    }

    public void setBtn_sub(Button btn_sub) {
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caculator("-");
            }
        });
    }

    public void setBtn_mul(Button btn_mul) {
        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caculator("*");
            }
        });
    }

    public void setBtn_div(Button btn_div) {
        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caculator("/");
            }
        });
    }

    //
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
