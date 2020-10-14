import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "note.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME ="notes";
    public static final String ID = "_id";
    public static final String COL_NOTE = "note";

    public static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + " ( "
            +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOTE + " TEXT " +
            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public long insertIntoDatabase(String note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NOTE,note);

        long status = db.insert(TABLE_NAME,null,values);
        db.close();

        return status;
    }

    public int deleteFromDatabase (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int status = db.delete(TABLE_NAME,ID + "= ? ", new String[]{id});
        db.close();

        return status;
    }




}
