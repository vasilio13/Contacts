package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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
            String nName = data.getString(MainActivity.NEW_NAME_KEY);
            String nPhoneMail = data.getString(MainActivity.NEW_PHONE_MAIL_KEY);
            String nTypeContact = data.getString(MainActivity.NEW_TYPE_CONTACT_KEY);
            Toast.makeText(EditRemoveActivity.this, nName + " " + nTypeContact + " " + nPhoneMail, Toast.LENGTH_LONG).show();

            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            EditText nameet = findViewById(R.id.name_et);
            EditText phonemailet = findViewById(R.id.number_mail_et);

            if (nTypeContact.equals("phone")) {radioGroup.check(R.id.radioButtonPhone);} //
            else if (nTypeContact.equals("mail")) {radioGroup.check(R.id.radioButtonMail);}//
            else  {}

            nameet.setText(nName);
            phonemailet.setText(nPhoneMail);

        }

        Button removeb = findViewById(R.id.remove_button);
        removeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameet = findViewById(R.id.name_et);
                EditText numbermailet = findViewById(R.id.number_mail_et);
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int code = radioGroup.getCheckedRadioButtonId();
                String nName = nameet.toString();
                String nPhoneMail = numbermailet.toString();
                String nTypeContact="mail";
                if (code == (R.id.radioButtonPhone)) {nTypeContact="phone";}
                else {nTypeContact="mail";}

                Intent data = new Intent(EditRemoveActivity.this, MainActivity.class);
                data.putExtra(MainActivity.NEW_NAME_KEY,nName);
                data.putExtra(MainActivity.NEW_PHONE_MAIL_KEY,nPhoneMail);
                data.putExtra(MainActivity.NEW_TYPE_CONTACT_KEY, nTypeContact);
                // if (typeContact == AddActivity.TypeContact.phone) {data.putExtra(MainActivity.NEW_TYPE_CONTACT_KEY,"phone");}
                // else if (typeContact == AddActivity.TypeContact.mail) {data.putExtra(MainActivity.NEW_TYPE_CONTACT_KEY,"mail");}
                //setResult(RESULT_OK, data);


                setResult(2,data);
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
