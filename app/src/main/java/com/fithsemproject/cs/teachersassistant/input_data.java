package com.fithsemproject.cs.teachersassistant;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
public class input_data extends AppCompatActivity  {

    // UI references.
   //private AutoCompleteTextView mEmailView;
    private EditText title,department,year,semester;
    Button btn;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_data);
        myDb=new DatabaseHelper(this);
        title=(EditText)findViewById(R.id.class_title);
        department=(EditText)findViewById(R.id.department);
        year=(EditText)findViewById(R.id.Year);
        semester=(EditText)findViewById(R.id.Semester);
        btn=(Button)findViewById(R.id.done);
        AddData();
        myDb.close();
    }

    public void AddData(){
        btn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        boolean isInserted=myDb.insertData(title.getText().toString(),department.getText().toString());
                        if(isInserted){
                            Toast.makeText(getApplicationContext(),"DataInserted",Toast.LENGTH_LONG).show();
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

