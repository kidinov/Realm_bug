package realm_bug_test.kidinov.org.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        putObjectsOne();
        putObjectsTwo();

        RealmResults realmResults = Realm.getInstance(MainActivity.this).where(Bar.class).findAllSorted("id");

        Log.d("", String.format("result size = %d", realmResults.size()));

    }

    private void putObjectsOne() {
        Realm realm = Realm.getInstance(MainActivity.this);
        realm.beginTransaction();
        Bar bar = new Bar();
        Foo foo = new Foo();
        foo.setId("id");
        bar.setId("id1");
        foo.setBars(new RealmList<Bar>());
        foo.getBars().add(bar);
        realm.copyToRealmOrUpdate(foo);
        realm.commitTransaction();
    }

    private void putObjectsTwo() {
        Realm realm = Realm.getInstance(MainActivity.this);
        Foo foo = realm.where(Foo.class).findFirst();
        realm.beginTransaction();
        Bar bar = foo.getBars().first();
        if (bar != null) {
            realm.copyToRealmOrUpdate(bar);
            realm.copyToRealmOrUpdate(foo);
        }
        realm.commitTransaction();
    }
}
