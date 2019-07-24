package shoping.softices.com.trainapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import shoping.softices.com.trainapp.Adapter.UsersRecyclerAdapter;

public class Userlist extends AppCompatActivity {
        private AppCompatActivity activity = Userlist.this;
        private AppCompatTextView textViewName;
        private RecyclerView recyclerViewUsers;
        private List<User> listUsers;
        private RecycleviewActivity recycleviewActivity;
        private Databasehelper databaseHelper;
        private UsersRecyclerAdapter usersRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();
    }
    private void initViews() {
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUsers = new ArrayList<>();
        recycleviewActivity = new RecycleviewActivity(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);
        databaseHelper = new Databasehelper(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");

    }
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(databaseHelper.getAllUser());

                return null;
            }
        }

