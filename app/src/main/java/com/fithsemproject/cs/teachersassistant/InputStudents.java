package com.fithsemproject.cs.teachersassistant;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fithsemproject.cs.teachersassistant.dummy.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class InputStudents extends AppCompatActivity {

    private EditText name,roll_no;
    int classId;
    Button btn;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_students);
        myDb=new DatabaseHelper(this);
        name=(EditText)findViewById(R.id.student_name);
        roll_no=(EditText)findViewById(R.id.roll_no);
        btn=(Button)findViewById(R.id.submit);
        //Get Class ID

        Intent intent=getIntent();
        Bundle b = this.getIntent().getExtras();
        classId= b.getInt("class");

        AddData();
    }

    public void AddData(){
        btn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        boolean isInserted=myDb.addStudents(name.getText().toString(),Integer.parseInt(roll_no.getText().toString()),classId);
                        if(isInserted){
                            Toast.makeText(getApplicationContext(),"New Student Added",Toast.LENGTH_LONG).show();
                            Log.i("INSERT",":DataInserted");
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Failed to insert data",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

}