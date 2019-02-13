package com.example.sookmyung.databasee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;


public class MainActivity extends AppCompatActivity {

    private EditText filterText;
    private ArrayAdapter<String> listAdapter;

    private final String dbName = "beerdb";
    private final String tableName = "beer";

    private String names[];
    {
        names = new String[]{"hoegaarden", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "Kitkat"};
    }
    private String dosu[];
    {
        dosu = new String[]{"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "Kitkat"};
    }
    private String country[];
    {
        country = new String[]{"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "Kitkat"};
    }

    private String phones[];
    {
        phones = new String[]{"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "Kitkat"};
    }

    ArrayList<HashMap<String, String>> personList;
    ListView list;
    private static final String TAG_NAME = "name";
    private static final String TAG_PHONE ="phone";
    private static final String TAG_DOSU = "dosu";
    private static final String TAG_COUNTRY ="country";

    SQLiteDatabase sampleDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filterText = (EditText)findViewById(R.id.editText);
        ListView itemList = (ListView)findViewById(R.id.listView);

        String [] listViewAdapterContent = names;

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listViewAdapterContent);

        try {


            sampleDB = this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);

            //테이블이 존재하지 않으면 새로 생성합니다.
            sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName
                    + " (name VARCHAR(20), dosu VARCHAR(20), country VARCHAR(20), phone VARCHAR(20));");

            //테이블이 존재하는 경우 기존 데이터를 지우기 위해서 사용합니다.
            sampleDB.execSQL("DELETE FROM " + tableName);

            //새로운 데이터를 테이블에 집어넣습니다..
            for (int i = 0; i < names.length; i++) {
                sampleDB.execSQL("INSERT INTO " + tableName
                        + " (name, dosu, country, phone)  Values ('" + names[i] + "','" + dosu[i] + "', '" + country[i] + "', '" + phones[i] + "');");
            }

            sampleDB.close();

        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(), se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("", se.getMessage());


        }

        itemList.setAdapter(listAdapter);
        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // make Toast when click

                String findstring = (String)parent.getAdapter().getItem(position);


                Toast.makeText(getApplicationContext(), "Position " + findstring , Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Beerclicked.class);

                intent.putExtra("name",findstring);
                /*intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(Database.columns[0]))); //assuming that the first column in your database table is the row_id
                intent.putExtra("date", cursor.getString(cursor.getColumnIndex(Database.columns[1]))); //assuming that the second column in your database table is the text1
                intent.putExtra("name", cursor.getString(cursor.getColumnIndex(Database.columns[2])));*/ //assuming that the third column in your database table is the text2
                startActivity(intent);

            }


        });

        filterText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
