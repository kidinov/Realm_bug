package realm_bug_test.kidinov.org.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 5; i++) {
            saveAndCreateObject();
        }

        RealmResults realmResults = Realm.getInstance(MainActivity.this).where(Model.class).findAll();

        Log.d("", String.format("result size = %d", realmResults.size()));

    }

    private void saveAndCreateObject() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Realm realm = Realm.getInstance(MainActivity.this);
                realm.beginTransaction();
                Model m = new Model();
                m.setId("id");
                realm.copyToRealmOrUpdate(m);
                realm.commitTransaction();
            }
        }).start();
    }
}
