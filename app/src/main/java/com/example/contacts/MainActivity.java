package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String ACCESS_MESSAGE="ACCESS_MESSAGE";
    static final String NEW_NAME_KEY = "NAME";
    static final String NEW_PHONE_MAIL_KEY ="PHONE_MAIL";
    static final String NEW_TYPE_CONTACT_KEY = "TYPE_CONTACT";

    private static  final int REQUEST_ACCESS_TYPE=1;

    private RecyclerView recyclerView;
    private EditText newItemEditText;

    public static String s;

    public static String nName;
    public static String nPhoneMail;
    public static String nTypeContact;

    class Items {
        String name;
        String phoneMail;
        String typeContact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new NameListAdapter());

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        newItemEditText = findViewById(R.id.newItemEditText);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = newItemEditText.getText().toString();
                s=newName;
/**
              if (!newName.trim().isEmpty() && recyclerView.getAdapter() != null) {
                   NameListAdapter adapter1 = (NameListAdapter) recyclerView.getAdapter();
                   adapter1.addItem(newName);
            } */
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra(NEW_NAME_KEY, nName);
               startActivityForResult(intent, 1010);

            }



        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1010) {

        String nName = data.getStringExtra(NEW_NAME_KEY);
        String nPhoneMail = data.getStringExtra(NEW_PHONE_MAIL_KEY);
        String nTypeContact = data.getStringExtra(NEW_TYPE_CONTACT_KEY);



        Items item = new Items();
        item.name=nName;
        item.phoneMail=nPhoneMail;
        item.typeContact=nTypeContact;

        if (!nName.trim().isEmpty() && recyclerView.getAdapter() != null) {
            Toast.makeText(MainActivity.this, "requestCode="+requestCode, Toast.LENGTH_LONG).show();
            NameListAdapter adapter1 = (NameListAdapter) recyclerView.getAdapter();
            adapter1.addItem(item);
        }
                     }
        else Toast.makeText(MainActivity.this, "resultCode<>1", Toast.LENGTH_LONG).show();

      //  TextView testTextView=findViewById(R.id.testTextView); // тест
       //testTextView.setText(nName);//test
       // Toast.makeText(MainActivity.this, nName+" "+typeContact+" "+nPhoneMail, Toast.LENGTH_LONG).show(); // test
    }

    class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.ItemViewHolder> {

        private ArrayList<Items> items = new ArrayList<>();

        NameListAdapter() {
        }

        void addItem(Items item) {
            items.add(item);
            notifyItemChanged(items.indexOf(item)); // for item
            notifyDataSetChanged(); // for all items
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position)

        {
            holder.bindData(items.get(position).name, items.get(position).phoneMail, items.get(position).typeContact);

            holder.itemView.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView iconPM = v.findViewById(R.id.icon_phone_mail);
                iconPM.setImageResource(R.drawable.ic_search_black_24dp); // Тестово меняю иконку для проверки нажатия

                    Intent data = new Intent(MainActivity.this, EditRemoveActivity.class);
                    nName=items.get(position).name;
                   nPhoneMail=items.get(position).phoneMail;
                   nTypeContact=items.get(position).typeContact;
                    data.putExtra(NEW_NAME_KEY,nName);
                    data.putExtra(NEW_PHONE_MAIL_KEY,nPhoneMail);
                    data.putExtra(NEW_TYPE_CONTACT_KEY, nTypeContact);
                   /** if (nTypeContact == nTypeContact.phone) {data.putExtra(MainActivity.NEW_TYPE_CONTACT_KEY,"phone");}
                    else if (typeContact == TypeContact.mail) {data.putExtra(MainActivity.NEW_TYPE_CONTACT_KEY,"mail");}*/
                  //  setResult(RESULT_OK, data);
                   // Toast.makeText(MainActivity.this, "!!!", Toast.LENGTH_LONG).show();
                    finish();



                 startActivityForResult(data, 2020);
                }
            });
        }

        @Override
        public int getItemCount() {
            return items != null ? items.size() : 0;
        }

         class ItemViewHolder extends RecyclerView.ViewHolder {
            private TextView nameText;
            private TextView numberMailText;
            private ImageView iconPhoneMail;

            ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                nameText = itemView.findViewById(R.id.nameText);
                numberMailText = itemView.findViewById(R.id.number_mail_et);
                iconPhoneMail = itemView.findViewById(R.id.icon_phone_mail);

            }



            void bindData(String nName, String nPhoneMail, String typeContact) {

                nameText.setText(nName);
                numberMailText.setText(nPhoneMail);
                if (typeContact.equals("phone")) {iconPhoneMail.setImageResource(R.drawable.ic_contact_phone_blue_48dp);} //
                else if (typeContact.equals("mail")) {iconPhoneMail.setImageResource(R.drawable.ic_contact_mail_pink_48dp);}//
                else  {iconPhoneMail.setImageResource(R.drawable.ic_search_black_24dp);}//
            }
        }
    }
}
