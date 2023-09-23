package com.vndevpro.android53_day4_recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IItemSwipeListener {

    private ArrayList<User> mListUsers;
    private UserAdapter mUserAdapter;
    private RecyclerView rvDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        rvDemo = findViewById(R.id.rvDemo);

        mUserAdapter = new UserAdapter(mListUsers);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);
//        rvDemo.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDemo.setLayoutManager(linearLayoutManager);
        SwipeItemCallBack swipeItemCallBack = new SwipeItemCallBack(this, mUserAdapter);
        swipeItemCallBack.setItemSwipeListener(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeItemCallBack);

        itemTouchHelper.attachToRecyclerView(rvDemo);
        rvDemo.setAdapter(mUserAdapter);

    }

    private void initData() {
        mListUsers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setUserName("User name " + i);
            user.setAge(i + 10);
            user.setEmail("email" + i + "@gmail.com");
            user.setAvatar("https://img6.thuthuatphanmem.vn/uploads/2022/11/18/anh-avatar-don-gian-ma-dep_081757969.jpg");
            user.setAddress("HN");

            mListUsers.add(user);
        }
    }

    @Override
    public void onItemSwiped(int pos) {
        mUserAdapter.deleteItem(pos);
    }
}