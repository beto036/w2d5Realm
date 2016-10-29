package com.example.admin.w2d5realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityMainTAG";
    private RecyclerView mRecyclerView;

    private ArrayList<Book> mArrayList;
    //    private SimpleAdapter mSimpleAdapter;
    private BookAdapter bookAdapter;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm.init(this);
        realm = Realm.getDefaultInstance();
        mArrayList = new ArrayList<Book>();
        bookAdapter = new BookAdapter(mArrayList);
        mRecyclerView = (RecyclerView) findViewById(R.id.a_main_recycler);
        mRecyclerView.setAdapter(bookAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        mArrayList.addAll(realm.where(Book.class).findAll());

        Intent add = getIntent();
        Log.d(TAG, "onCreate: " + add.getAction());
        if(add.getStringExtra(AddActivity.TITLE) != null) {
            Book book = new Book(add.getStringExtra(AddActivity.TITLE), add.getStringExtra(AddActivity.AUTHOR));
            realm.beginTransaction();
            realm.insert(book);
            realm.commitTransaction();
            mArrayList.add(book);
            add.removeExtra(add.getStringExtra(AddActivity.TITLE));
            add.removeExtra(add.getStringExtra(AddActivity.AUTHOR));
        }

        bookAdapter.notifyDataSetChanged();

    }

    public void add(View view) {
        Intent addAct = new Intent(this, AddActivity.class);
        startActivity(addAct);
    }

    public void deleteAll(View view) {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        mArrayList.clear();
        mArrayList.addAll(realm.where(Book.class).findAll());
        bookAdapter.notifyDataSetChanged();
    }
}
