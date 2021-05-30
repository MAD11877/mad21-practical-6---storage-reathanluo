package sg.edu.np.s10205205;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_NAME + " TEXT,"+ COLUMN_DESCRIPTION + " TEXT," + COLUMN_FOLLOWED + " INTEGER,"+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT"  + ")";
        db.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> uList = new ArrayList<User>();
        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){
            User u = new User();
            u.setName(cursor.getString(0));
            u.setDescription(cursor.getString(1));
            u.setFollowed(Integer.parseInt(cursor.getString(2)) == 1);
            u.setId(Integer.parseInt(cursor.getString(3)));
            uList.add(u);
        }
        cursor.close();
        db.close();
        return uList;
    }

    public void updateUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        SQLiteDatabase db = this.getWritableDatabase();

        db.update(TABLE_USERS, values, COLUMN_ID + " = ?", (new String[] {String.valueOf(user.getId())}));
        db.close();
    }

    public static void deleteDatabase(Context Context) {
        Context.deleteDatabase(DATABASE_NAME);
    }
}
