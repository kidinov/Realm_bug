package realm_bug_test.kidinov.org.myapplication;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Foo extends RealmObject {
    @PrimaryKey
    private String id;
    private RealmList<Bar> bars;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<Bar> getBars() {
        return bars;
    }

    public void setBars(RealmList<Bar> bars) {
        this.bars = bars;
    }
}
