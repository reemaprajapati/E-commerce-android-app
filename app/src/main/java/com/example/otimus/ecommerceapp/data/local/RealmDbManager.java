package com.example.otimus.ecommerceapp.data.local;



import io.realm.Realm;

/**
 * Created by brain on 4/17/17.
 */

public class RealmDbManager {

    private static Realm mRealm;

    public static Realm open() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public static void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    static CartDao createCartDao() {
        checkForOpenRealm();
        return new CartDao(mRealm);
    }

    /*  public static void clear() {
          checkForOpenRealm();
          mRealm.executeTransaction(new Realm.Transaction() {
              @Override
              public void execute(Realm realm) {
                  realm.clear(JobsModel.class);
                  //clear rest of your dao classes
              }
          });
      }
  */
    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }


}
