package com.thisischool.chool.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.thisischool.chool.Adapters.AnswerAdapter;
import com.thisischool.chool.Adapters.NotificationsAdapter;
import com.thisischool.chool.Classes.AppHelper;
import com.thisischool.chool.Classes.Controller;
import com.thisischool.chool.Models.Answers;
import com.thisischool.chool.Models.Notification;
import com.thisischool.chool.Models.Question;
import com.thisischool.chool.OnlineDatabase.MyReferences;
import com.thisischool.chool.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.thisischool.chool.Classes.Constants.QUESTION_ID;

public class AnswersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Answers> list;
    private String id;
    private Question question;
    private EditText ansEdit;
    private ImageView postBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        id = getIntent().getStringExtra(QUESTION_ID);

        getQuestionData();

        postBtn.setOnClickListener(view -> {
            if (ansEdit.getText() != null) {
                postAnswer(ansEdit.getText().toString());
            } else {
                ansEdit.setError("Empty Field");
                ansEdit.requestFocus();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void postAnswer(String ans) {

        Question question1 = new Question(question.getQuestion(),question.getId(),
                question.getAnswersCount() + 1, question.getName());
        DatabaseReference reference = My

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setAdapter(@NotNull List<Answers> mList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        AnswerAdapter adapter = new AnswerAdapter(mList, this);
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
    }

    private void getQuestionData() {
}