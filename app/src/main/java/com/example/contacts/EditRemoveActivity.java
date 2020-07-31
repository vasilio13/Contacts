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
int position;
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
            position = data.getInt(MainActivity.POSITION);
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
                String nName = nameet.getText().toString();
                String nPhoneMail = numbermailet.getText().toString();
                String nTypeContact="mail";
                if (code == (R.id.radioButtonPhone)) {nTypeContact="phone";}
                else {nTypeContact="mail";}

                Intent data = new Intent(EditRemoveActivity.this, MainActivity.class);
                data.putExtra(MainActivity.NEW_NAME_KEY,nName);
                data.putExtra(MainActivity.NEW_PHONE_MAIL_KEY,nPhoneMail);
                data.putExtra(MainActivity.NEW_TYPE_CONTACT_KEY, nTypeContact);
                data.putExtra(MainActivity.POSITION, position);

                setResult(2020,data);
                finish();

            }
        });
    }
}
