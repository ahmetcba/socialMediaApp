package com.thisischool.chool.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.thisischool.chool.Adapters.ClassChatGroupAdapter;
import com.thisischool.chool.Classes.AppHelper;
import com.thisischool.chool.Classes.Controller;
import com.thisischool.chool.OnlineDatabase.MyReferences;
import com.thisischool.chool.OnlineDatabase.SendMessage;
import com.thisischool.chool.Models.ClassChatGroupMessage;
import com.thisischool.chool.OurWork.NotesActivity;
import com.thisischool.chool.OurWork.SchoolChatActivity;
import com.thisischool.chool.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ClassChatGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ClassChatGroup";
    private RecyclerView recyclerView;
    private List<ClassChatGroupMessage> messageList;
    private boolean isMenuOpened = false;
    private EditText newMessageEdit;
    private LinearLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic(Controller.CurrentUser.getUserClassId(this));

        //FirebaseMessaging.getInstance().subscribeToTopic("all");

        recyclerView = findViewById(R.id.cgc_recyclerview);
        newMessageEdit = findViewById(R.id.msgEdit_cgc);
        menu = findViewById(R.id.opened_menu_cgc);
        menu.setBackground(new ColorDrawable(Color.TRANSPARENT));
        ImageView selectImageBtn, sendBtn, lessons, menuBtn;
        CircleImageView questionsBtn, classInfoBtn, notesBtn,
                mProfileBtn, schoolChatBtn;

        selectImageBtn = findViewById(R.id.select_img_cgc);
        sendBtn = findViewById(R.id.send_msg_cgc);
        lessons = findViewById(R.id.lesson_cgc);
        menuBtn = findViewById(R.id.menu_cgc);
        questionsBtn = findViewById(R.id.questions_menu_cgc);
        classInfoBtn = findViewById(R.id.classInfo_menu_cgc);
        notesBtn = findViewById(R.id.notes_menu_cgc);
        mProfileBtn = findViewById(R.id.profile_menu_cgc);
        schoolChatBtn = findViewById(R.id.schoolChat_menu_cgc);

        selectImageBtn.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
        lessons.setOnClickListener(this);
        menuBtn.setOnClickListener(this);
        questionsBtn.setOnClickListener(this);
        classInfoBtn.setOnClickListener(this);
        notesBtn.setOnClickListener(this);
        mProfileBtn.setOnClickListener(this);
        schoolChatBtn.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void setAdapter(@NotNull List<ClassChatGroupMessage> mList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        ClassChatGroupAdapter adapter = new ClassChatGroupAdapter(mList, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(mList.size() - 1);
    }

    @Override
    public void onClick(@NotNull View view) {
    }

    private void getData() {
        messageList = new ArrayList<>();
        Dialog dialog = AppHelper.getLoadingDialog(this);
        dialog.show();
        DatabaseReference reference = MyReferences.classGroupMessages(this);
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    messageList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ClassChatGroupMessage message = dataSnapshot.getValue(ClassChatGroupMessage.class);
                        if (message != null) {
                            messageList.add(message);
                            dialog.dismiss();
                        }
                        setAdapter(messageList);
                    }
                } else {
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}