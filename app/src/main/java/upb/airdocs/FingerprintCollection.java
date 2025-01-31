package upb.airdocs;

import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FingerprintCollection{
    private static final String LOG_TAG = "FingerprintCollection";

    private String devId="-";
    private String devName=android.os.Build.MODEL;
    private String androidVersion= Build.VERSION.RELEASE;
    private String comment="-";
    private String map="-";
    private float x=-1;
    private float y=-1;
    private float z=-1;
    private float x_p=-1;
    private float y_p=-1;
    public List<Fingerprint> itemList = new ArrayList<Fingerprint>();

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void setX_P(float x_p) {
        this.x_p = x_p;
    }

    public void setY_P(float y_p) {
        this.y_p = y_p;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void addFingerprintToCollection(Fingerprint fingerprint){
        itemList.add(fingerprint);
    }

    public JSONObject toJSON(boolean ble, boolean cellular, boolean gps, boolean magnetic){
        JSONObject collectionJSON = new JSONObject();

        try {

            collectionJSON.put("devId", devId);
            collectionJSON.put("devName", devName);
            collectionJSON.put("AndroidVersion", androidVersion);
            collectionJSON.put("comment", comment);
            collectionJSON.put("map", map);
            collectionJSON.put("x_p", x_p);
            collectionJSON.put("y_p", y_p);
            collectionJSON.put("x", x);
            collectionJSON.put("y", y);
            collectionJSON.put("z", z);

            JSONArray collectionFingerprintJSON = new JSONArray();

            for (int i = 0; i < itemList.size(); i++) {
                Fingerprint fingerprint = itemList.get(i);
                collectionFingerprintJSON.put(fingerprint.toJSON(ble, cellular, gps, magnetic));
            }

            collectionJSON.put("fingerprints", collectionFingerprintJSON);
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        return collectionJSON;
    }

    public void printToLogFingerprint() {
        Log.d(LOG_TAG, "Fingerprint Collection: ");
        Log.d(LOG_TAG, "devID: " + devId);
        Log.d(LOG_TAG, "devName: " + devName);
        Log.d(LOG_TAG, "AndroidVersion: " + androidVersion);
        Log.d(LOG_TAG, "comment: " + comment);
        Log.d(LOG_TAG, "map: " + map);
        Log.d(LOG_TAG, "x_p: " + x_p);
        Log.d(LOG_TAG, "y_p: " + y_p);
        Log.d(LOG_TAG, "x: " + x);
        Log.d(LOG_TAG, "y: " + y);
        Log.d(LOG_TAG, "z: " + z);
        Log.d(LOG_TAG, "fingerprints: ");
        for (int i = 0; i < itemList.size(); i++) {
            Fingerprint fingerprint = itemList.get(i);
            fingerprint.printToLogFingerprint();
        }
    }
}
