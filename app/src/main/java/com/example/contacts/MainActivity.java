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

        if (!nName.trim().isEmpty() && recyclerView.getAdapter() != null) {
        NameListAdapter adapter1 = (NameListAdapter) recyclerView.getAdapter();
        adapter1.addItem(nName);
    }
      //  TextView testTextView=findViewById(R.id.testTextView); // тест
       //testTextView.setText(nName);//test
       // Toast.makeText(MainActivity.this, nName+" "+nPhoneMail, Toast.LENGTH_LONG).show(); // test
    }

    static class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.ItemViewHolder> {

        private ArrayList<String> items = new ArrayList<>();

        NameListAdapter() {
        }

        void addItem(String name) {
            items.add(name);
            notifyItemChanged(items.indexOf(name)); // for item
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

            ItemViewHolder(@NonNull View itemView) {
                super(itemView);
              itemView.findViewById(R.id.nameText);
            }

            void bindData(String nName) {
                nameText.setText(nName);
            }
        }
    }
}
