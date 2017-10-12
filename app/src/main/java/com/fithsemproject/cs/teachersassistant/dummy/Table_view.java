package com.fithsemproject.cs.teachersassistant.dummy;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.fithsemproject.cs.teachersassistant.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Table_view extends AppCompatActivity {

    TextView nameholder;
    TextView a;TextView f;TextView c;TextView d;TextView e;TextView ff,aaa,bbb,ccc,ddd,eee,fff;
    int noStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
        Intent intent = getIntent();

        Bundle b = this.getIntent().getExtras();
        int classId = b.getInt("classId");
        HashMap<Integer, ArrayList<Integer>> map = (HashMap<Integer, ArrayList<Integer>>) b.getSerializable("map");

        //get student_ids and names from class_id

        DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());
        int x = 1;
        Cursor res = myDb.getStudentName(classId);
        String Name = "";
        int r, s, t, u, v, w,xx,yy,zz,tt,uu,vv;
        noStudents = res.getCount();
        while (res.moveToNext()) {
            r = getResources().getIdentifier("Student" + x, "id", getPackageName());
            s = getResources().getIdentifier("a" + x, "id", getPackageName());
            t = getResources().getIdentifier("b" + x, "id", getPackageName());
            u = getResources().getIdentifier("c" + x, "id", getPackageName());
            v = getResources().getIdentifier("d" + x, "id", getPackageName());
            w = getResources().getIdentifier("e" + x, "id", getPackageName());
            xx = getResources().getIdentifier("f" + x, "id", getPackageName());
            yy = getResources().getIdentifier("g" + x, "id", getPackageName());
            zz = getResources().getIdentifier("h" + x, "id", getPackageName());
            tt = getResources().getIdentifier("i" + x, "id", getPackageName());
            uu = getResources().getIdentifier("j" + x, "id", getPackageName());
            vv = getResources().getIdentifier("k" + x, "id", getPackageName());
            if (r != 0) {
                nameholder = (TextView) findViewById(r);
                a = (TextView) findViewById(r);
                f = (TextView) findViewById(s);
                c = (TextView) findViewById(t);
                d = (TextView) findViewById(u);
                e = (TextView) findViewById(v);
                ff= (TextView) findViewById(w);
                aaa= (TextView) findViewById(xx);
                bbb= (TextView) findViewById(yy);
                ccc= (TextView) findViewById(zz);
                ddd= (TextView) findViewById(tt);
                eee= (TextView) findViewById(uu);
                fff= (TextView) findViewById(vv);
                a.setTag(res.getString(1));
                f.setTag(res.getString(1));
                e.setTag(res.getString(1));
                c.setTag(res.getString(1));
                d.setTag(res.getString(1));
                ff.setTag(res.getString(1));
                aaa.setTag(res.getString(1));
                bbb.setTag(res.getString(1));
                ccc.setTag(res.getString(1));
                ddd.setTag(res.getString(1));
                eee.setTag(res.getString(1));
                fff.setTag(res.getString(1));

                Log.i("Gettag", "" + c.getTag());
                Log.i("Gettag", "" + a.getTag());

            } else {
                Log.i("Gotnull", "yeah" + r);
            }
            x++;

            nameholder.setText(res.getString(0));
        }

        //Display Present or absent


        int i;
        int y=1;
        TextView intermediate;
        Iterator it = map.entrySet().iterator();
        String att = "";
        ArrayList<Integer> arr;
        i=97;
        while (it.hasNext()) {
            Log.i("LOOP","ONCE");
            Map.Entry pair = (Map.Entry) it.next();
            att = att + pair.getKey().toString() + ',';
            //iterate through rows in table loop a,b,c...
            arr = (ArrayList<Integer>) pair.getValue();


                Log.i("ASCII", Character.toString((char) i));

                //check if current tag is in the array
                //if so then present

                for (y = 1; y <= noStudents; y++) {
                    intermediate = (TextView) findViewById(getResources().getIdentifier(Character.toString((char) i) + y, "id", getPackageName()));
                    int interVariable =getResources().getIdentifier(Character.toString((char) i) + y, "id", getPackageName());
                    if (intermediate.getTag() != null && interVariable !=0) {
                        Log.i("valuezz", "" + arr);
                        Log.i("with", "" + Integer.parseInt(intermediate.getTag().toString()));
                        Log.i("TRAVERSING", Character.toString((char) i) + y);

                        if (arr.contains(Integer.parseInt(intermediate.getTag().toString()))) {
                            intermediate.setText("P");

                        } else {
                            intermediate.setText("N/A");
                        }
                    }
                }

                i++;
        }

    }
    }

