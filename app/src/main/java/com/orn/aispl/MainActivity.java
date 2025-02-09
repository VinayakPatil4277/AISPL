package com.orn.aispl;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.orn.aispl.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private MemberAdapter adapter;
    private List<Member> memberList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Inflate the layout using binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = openOrCreateDatabase("MemberDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Members(" +
                "Name TEXT, " +
                "Mobile TEXT, " +
                "Role TEXT, " +
                "Subscription REAL, " +
                "Loan REAL, " +
                "Deposit REAL, " +
                "Gender TEXT, " +
                "DOB TEXT, " +
                "DOJ TEXT, " +
                "MaritalStatus TEXT, " +
                "DOM TEXT, " +
                "Caste TEXT, " +
                "Religion TEXT, " +
                "Category TEXT, " +
                "Aadhar TEXT)");

        memberList = new ArrayList<>();
        adapter = new MemberAdapter(memberList);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        loadMembers();


        binding.btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddMemberActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMembers();
    }

    private void loadMembers() {
        memberList.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM Members", null);
        if (cursor.moveToFirst()) {
            do {
                memberList.add(new Member(
                        cursor.getString(cursor.getColumnIndexOrThrow("Name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("Mobile")),
                        cursor.getString(cursor.getColumnIndexOrThrow("Role")),
                        String.valueOf(cursor.getDouble(cursor.getColumnIndexOrThrow("Subscription"))),
                        String.valueOf(cursor.getDouble(cursor.getColumnIndexOrThrow("Loan"))),
                        String.valueOf(cursor.getDouble(cursor.getColumnIndexOrThrow("Deposit")))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();

        adapter.notifyDataSetChanged();
        binding.tvMemberCount.setText("Members Count: " + memberList.size());
    }
}
