package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;


import static java.util.Locale.filter;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    static final String ACCESS_MESSAGE = "ACCESS_MESSAGE";
    static final String NEW_NAME_KEY = "NAME";
    static final String NEW_PHONE_MAIL_KEY = "PHONE_MAIL";
    static final String NEW_TYPE_CONTACT_KEY = "TYPE_CONTACT";
    static final String POSITION = "POSITION";

    int position;

    private static final int REQUEST_ACCESS_TYPE = 1;

    private RecyclerView recyclerView;
    private EditText newItemEditText;

    public static String s;

    public static String nName;
    public static String nPhoneMail;
    public static String nTypeContact;

    ArrayList<Items> allItems = new ArrayList<>();

    DBHelper dbHelper;

  //  NameListAdapter sortAdapter = (NameListAdapter) recyclerView.getAdapter();

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final ArrayList<Items> filteredModelList = new ArrayList<>();
        for (Items sch : allItems) {

        if (sch.name.startsWith(query))
            filteredModelList.add(sch);
        }

        NameListAdapter sortAdapter = (NameListAdapter) recyclerView.getAdapter();
        sortAdapter.setItems(filteredModelList);
        return true;
    }


    class Items {
        String name;
        String phoneMail;
        String typeContact;

        @Override
        public boolean equals(@Nullable Object item) {
            if ( item instanceof Items) {
                return
                        ((name.equals(((Items) item).name)) &&
                                (phoneMail.equals(((Items) item).phoneMail)) &&
                                (typeContact.equals(((Items) item).typeContact)));
            }
            else return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new NameListAdapter());
NameListAdapter adapter = new NameListAdapter(dbHelper.getDataFromDatabase().this);
recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        dbHelper = new DBHelper(this);



        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = "";//newItemEditText.getText().toString();
                s = newName;

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
            item.name = nName;
            item.phoneMail = nPhoneMail;
            item.typeContact = nTypeContact;

            if (!nName.trim().isEmpty() && recyclerView.getAdapter() != null) {
                allItems.add(item);
                Toast.makeText(MainActivity.this, "requestCode=" + requestCode, Toast.LENGTH_LONG).show();
                NameListAdapter adapter1 = (NameListAdapter) recyclerView.getAdapter();
                adapter1.setItems(allItems);
            }
        } else if (requestCode == 2020) {
            String nName = data.getStringExtra(NEW_NAME_KEY);
            String nPhoneMail = data.getStringExtra(NEW_PHONE_MAIL_KEY);
            String nTypeContact = data.getStringExtra(NEW_TYPE_CONTACT_KEY);
            position = data.getIntExtra(POSITION,1);

            Toast.makeText(MainActivity.this, "requestCode=" + requestCode + " " + nName + " " + nPhoneMail + " " + nTypeContact, Toast.LENGTH_LONG).show();

            Items item = new Items();
            item.name = nName;
            item.phoneMail = nPhoneMail;
            item.typeContact = nTypeContact;

            if (!nName.trim().isEmpty() && recyclerView.getAdapter() != null) {
                Toast.makeText(MainActivity.this, "!requestCode=" + requestCode, Toast.LENGTH_LONG).show();
               // int index = allItems.indexOf(item);
                allItems.set(position,item);
                NameListAdapter changeAdapter = (NameListAdapter) recyclerView.getAdapter();
                changeAdapter.setItems(allItems);
            }

        }

    }

    class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {



        private ArrayList<Items> items = new ArrayList<>();

        NameListAdapter() {
        }

        void setItems(ArrayList<Items> newItems){
            items = newItems;
            notifyDataSetChanged();
        }

        void addItem(Items item) {
            items.add(item);
            notifyItemChanged(items.indexOf(item)); // for item
           // notifyDataSetChanged(); // for all items
        }

        void changeItem(Items item) {
            items.set(position,item);
          notifyItemChanged(position); // for item
          //  notifyDataSetChanged(); // for all items
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
            holder.bindData(items.get(position).name, items.get(position).phoneMail, items.get(position).typeContact);
//holder.itemView.findViewById(R.id.edit_button);
 Button editButton;
            editButton = (Button) holder.itemView.findViewById(R.id.edit_button);

            //Button editButton = findViewById(R.id.edit_button);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent data = new Intent(MainActivity.this, EditRemoveActivity.class);
                    nName = items.get(position).name;
                    nPhoneMail = items.get(position).phoneMail;
                    nTypeContact = items.get(position).typeContact;
                    data.putExtra(POSITION, position);
                    data.putExtra(NEW_NAME_KEY, nName);
                    data.putExtra(NEW_PHONE_MAIL_KEY, nPhoneMail);
                    data.putExtra(NEW_TYPE_CONTACT_KEY, nTypeContact);
                    startActivityForResult(data, 2020);

                }


            });

            final ImageView iconIV;
            iconIV = (ImageView) holder.itemView.findViewById(R.id.icon_phone_mail);

            //Button editButton = findViewById(R.id.edit_button);
            iconIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"icon press "+position, Toast.LENGTH_LONG).show();
                    if (iconIV.getDrawable().getConstantState().equals(getDrawable(R.drawable.ic_help_black_24dp).getConstantState())) {
                        iconIV.setImageResource(R.drawable.ic_help_outline_black_24dp);
                    } //
                    else if (iconIV.getDrawable().getConstantState().equals(getDrawable(R.drawable.ic_help_outline_black_24dp).getConstantState())) {
                        iconIV.setImageResource(R.drawable.ic_help_black_24dp);
                    }


                }


            });









            holder.itemView.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"press "+position, Toast.LENGTH_LONG).show();
TextView englishWord = v.findViewById(R.id.nameText);

if (englishWord.getVisibility()==v.VISIBLE)

englishWord.setVisibility(v.GONE);
else englishWord.setVisibility(v.VISIBLE);

                    iconIV.setImageResource(R.drawable.ic_help_black_24dp);
/**



                    ImageView iconPM = v.findViewById(R.id.icon_phone_mail);
                       iconPM.setImageResource(R.drawable.ic_search_black_24dp); // Тестово меняю иконку для проверки нажатия

                    Intent data = new Intent(MainActivity.this, EditRemoveActivity.class);
                    nName = items.get(position).name;
                    nPhoneMail = items.get(position).phoneMail;
                    nTypeContact = items.get(position).typeContact;
                    data.putExtra(POSITION, position);
                    data.putExtra(NEW_NAME_KEY, nName);
                    data.putExtra(NEW_PHONE_MAIL_KEY, nPhoneMail);
                    data.putExtra(NEW_TYPE_CONTACT_KEY, nTypeContact);
                    startActivityForResult(data, 2020);

 **/
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
                if (typeContact.equals("phone")) {
                    iconPhoneMail.setImageResource(R.drawable.ic_contact_phone_blue_48dp);
                } //
                else if (typeContact.equals("mail")) {
                    iconPhoneMail.setImageResource(R.drawable.ic_contact_mail_pink_48dp);
                }//
                else {
                    iconPhoneMail.setImageResource(R.drawable.ic_search_black_24dp);
                }//
            }
        }

        @Override
        public void onItemDismiss(int position) {
            items.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public void onItemMove(int fromPosition, int toPosition) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(items, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(items, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
            //return true;
        }

    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);



        return true;
    }

}
