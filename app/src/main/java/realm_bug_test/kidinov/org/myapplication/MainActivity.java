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

        Realm realm = Realm.getInstance(this);
        for (int i = 0; i < 5; i++) {
            saveAndCreateObject(realm);
        }

        RealmResults realmResults = Realm.getInstance(MainActivity.this).where(Model.class).findAll();

        Log.d("", String.format("result size = %d", realmResults.size()));

    }

    private void saveAndCreateObject(final Realm realm) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                realm.beginTransaction();
                Model m = new Model();
                m.setId("id");
                realm.copyToRealmOrUpdate(m);
                realm.commitTransaction();
            }
        }).start();
    }
}
