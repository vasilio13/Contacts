package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditRemoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_remove);

        Toast.makeText(EditRemoveActivity.this, "onCreate", Toast.LENGTH_LONG).show();

        Bundle data = getIntent().getExtras();

        if(data!=null){
            String nName = data.getString("name");
            String nPhoneMail = data.getString("phonemail");
            String nTypeContact = data.getString("typeContact");
            Toast.makeText(EditRemoveActivity.this, nName + " " + nTypeContact + " " + nPhoneMail, Toast.LENGTH_LONG).show();

            EditText nameet = findViewById(R.id.name_et);
            EditText phonemailet = findViewById(R.id.number_mail_et);

            nameet.setText(nName);
            phonemailet.setText(nPhoneMail);

        }

        //setContentView(textView);







/**Intent data = new Intent(EditRemoveActivity.this, MainActivity.class);
        String nName = data.getStringExtra("name");
        String nPhoneMail = data.getStringExtra("phonemail");
        String nTypeContact = data.getStringExtra("typeContact");
        setContentView(R.layout.activity_edit_remove);

        EditText nameet = findViewById(R.id.name_et);
        EditText phonemailet = findViewById(R.id.number_mail_et);

        if (nName!=null) {
            Toast.makeText(EditRemoveActivity.this, nName + " " + nTypeContact + " " + nPhoneMail, Toast.LENGTH_LONG).show();
            nameet.setText(nName);
            phonemailet.setText(nPhoneMail);     }
*/


        Button removeb = findViewById(R.id.remove_button);
        removeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newName = findViewById(R.id.name_et);
                EditText newPhoneMail = findViewById(R.id.number_mail_et);
                Intent data = new Intent();
                data.putExtra(MainActivity.NEW_NAME_KEY,newName.toString());
                data.putExtra(MainActivity.NEW_PHONE_MAIL_KEY,newPhoneMail.toString());
                // if (typeContact == AddActivity.TypeContact.phone) {data.putExtra(MainActivity.NEW_TYPE_CONTACT_KEY,"phone");}
                // else if (typeContact == AddActivity.TypeContact.mail) {data.putExtra(MainActivity.NEW_TYPE_CONTACT_KEY,"mail");}
                setResult(RESULT_OK, data);
                finish();
            }
        });



    }



      //Intent intent = getIntent();
        //EditText nameet = findViewById(R.id.name_et);
        //EditText phonemailet = findViewById(R.id.number_mail_et);
        //Bundle arguments = getIntent().getExtras();
      // Intent intent =getIntent();
     // String name = arguments.get("name").toString();
    // String phonemail = arguments.get("phonemail").toString();

      //  String message = intent.getStringExtra(MainActivity.s);

    //    intent.getExtras("name", ;
    //    intent.getExtras("phonemail", phonemail);


        //nameet.setText(name);
      //  nameet.setText(name);
        //phonemailet.setText(phonemail);
       // phonemailet.setText(phonemail);
      //  setResult(RESULT_OK, arguments);



        /**MainActivity.Items item = new MainActivity.Items();
        item.name=nName;
        item.phoneMail=nPhoneMail;
        item.typeContact=nTypeContact;

        if (!nName.trim().isEmpty() && recyclerView.getAdapter() != null) {
            MainActivity.NameListAdapter adapter1 = (MainActivity.NameListAdapter) recyclerView.getAdapter();
            adapter1.addItem(item);
        }
        //  TextView testTextView=findViewById(R.id.testTextView); // тест
        //testTextView.setText(nName);//test
        // Toast.makeText(MainActivity.this, nName+" "+typeContact+" "+nPhoneMail, Toast.LENGTH_LONG).show(); // test
    }
*/




}
