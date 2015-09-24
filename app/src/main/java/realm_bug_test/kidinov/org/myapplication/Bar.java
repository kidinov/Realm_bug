package realm_bug_test.kidinov.org.myapplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Bar extends RealmObject {
    @PrimaryKey
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
