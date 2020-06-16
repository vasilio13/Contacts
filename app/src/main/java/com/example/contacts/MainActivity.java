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
               startActivityForResult(intent, 1);

            }



        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String nName = data.getStringExtra(NEW_NAME_KEY);
        String nPhoneMail = data.getStringExtra(NEW_PHONE_MAIL_KEY);
        String typeContact = data.getStringExtra(NEW_TYPE_CONTACT_KEY);

        NameListAdapter.Item item = null;
        item.name=nName;
        item.phoneMail=nPhoneMail;
        item.typeContact=typeContact;

        if (!nName.trim().isEmpty() && recyclerView.getAdapter() != null) {
        NameListAdapter adapter1 = (NameListAdapter) recyclerView.getAdapter();
        adapter1.addItem(item);
    }
      //  TextView testTextView=findViewById(R.id.testTextView); // тест
       //testTextView.setText(nName);//test
        Toast.makeText(MainActivity.this, nName+" "+typeContact+" "+nPhoneMail, Toast.LENGTH_LONG).show(); // test
    }

    static class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.ItemViewHolder> {
class Item {
String name;
String phoneMail;
String typeContact;
}

Item item = new Item();
        private ArrayList<Item> items = new ArrayList<>();

        NameListAdapter() {
        }

        void addItem(Item item) {
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
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bindData(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items != null ? items.size() : 0;
        }

        static class ItemViewHolder extends RecyclerView.ViewHolder {
            private TextView nameText;
            private TextView numberMailText;

            ItemViewHolder(@NonNull View itemView) {
                super(itemView);
                nameText = itemView.findViewById(R.id.nameText);
                numberMailText = itemView.findViewById(R.id.nameText);
            }

            void bindData(String nName, String nPhoneMail) {
                nameText.setText(nName);
                numberMailText.setText(nPhoneMail);
            }
        }
    }
}
