package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


public class AddActivity extends AppCompatActivity {

    static String newName;
    static String newPhoneMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        // Получаем сообщение из объекта intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.s);

        // Создаем текстовое поле
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        Toolbar toolbarDone = (Toolbar) findViewById(R.id.toolbar);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final EditText numberMailET = findViewById(R.id.number_mail_et);
        radioGroup.clearCheck();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        numberMailET.setText("");
                        break;
                    case R.id.radioButtonPhone:
                        numberMailET.setHint("+375 29 xx - xx - xx");
                        break;
                    case R.id.radioButtonMail:
                        numberMailET.setHint("name@hosting.zone");
                        break;
                }
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
           /**
            * switch (item.getItemId()) {
            * 		case R.id.menuPurchasesListNewRecord:
            * 			// TODO: обработчик нажатия здесь
            * 		hhhh
            * 			return true;
            *                }
            *
            * 		return super.onOptionsItemSelected(item);

            *
            */



            int id = item.getItemId();

           final EditText numberMailET = findViewById(R.id.number_mail_et);

           final EditText nameET = findViewById(R.id.name_et);
            //noinspection SimplifiableIfStatement
            if (id == R.id.toolbar_button_done) {
                Toast.makeText(AddActivity.this, nameET.getText()+" "+numberMailET.getText()+" Галочка работает !!!", Toast.LENGTH_LONG).show();
                String newName = nameET.getText().toString();
                String newPhoneMail = numberMailET.getText().toString();
//.setDuration(s)
                onPause();
//.show();
             //   Intent intent = new Intent(AddActivity.this, MainActivity.class);
             //   intent.putExtra(newName,newPhoneMail);
             //   startActivity(intent);
                Intent data = new Intent();
                data.putExtra(newName, newPhoneMail);
                setResult(RESULT_OK, data);
                finish();





                return true;
            }

            return super.onOptionsItemSelected(item);
        }
}
