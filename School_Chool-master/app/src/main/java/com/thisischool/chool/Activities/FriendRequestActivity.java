package com.thisischool.chool.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.thisischool.chool.Adapters.ClassChatGroupAdapter;
import com.thisischool.chool.Adapters.FriendRequestAdapter;
import com.thisischool.chool.Classes.AppHelper;
import com.thisischool.chool.Models.ClassChatGroupMessage;
import com.thisischool.chool.Models.FriendRequest;
import com.thisischool.chool.OnlineDatabase.MyReferences;
import com.thisischool.chool.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<FriendRequest> friendRequestList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);
inearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        FriendRequestAdapter adapter = new FriendRequestAdapter(mList, this);
        recyclerView.setAdapter(adapter);
        AppHelper.showToast(this,"hello");
    }

    @Override
    protected void onStart() {
        super.onStart();
        setData();
    }

    private void setData() {
        friendRequestList = new ArrayList<>();
        MyReferences.friendsRequestRef().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    friendRequestList.clear();
                    for (DataSnapshot dataSnatabaseError error) {
                AppHelper.showToast(FriendRequestActivity.this,error.getMessage());
            }
        });
    }
}