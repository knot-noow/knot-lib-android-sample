package knotlibandroid.sample.com.meshbluandroidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.nkzawa.emitter.Emitter;
import com.octoblu.sanejsonobject.SaneJSONObject;

import org.json.JSONArray;

import knotlibandroid.sample.com.mylibrary.Meshblu;

import static knotlibandroid.sample.com.mylibrary.Meshblu.CLAIM_DEVICE;
import static knotlibandroid.sample.com.mylibrary.Meshblu.DELETE_DEVICE;
import static knotlibandroid.sample.com.mylibrary.Meshblu.GENERATED_TOKEN;
import static knotlibandroid.sample.com.mylibrary.Meshblu.GET_DATA;
import static knotlibandroid.sample.com.mylibrary.Meshblu.GET_DEVICE;
import static knotlibandroid.sample.com.mylibrary.Meshblu.GET_PUBLIC_KEY;
import static knotlibandroid.sample.com.mylibrary.Meshblu.MESSAGE;
import static knotlibandroid.sample.com.mylibrary.Meshblu.REGISTER;
import static knotlibandroid.sample.com.mylibrary.Meshblu.RESET_TOKEN;
import static knotlibandroid.sample.com.mylibrary.Meshblu.SEND_DATA;
import static knotlibandroid.sample.com.mylibrary.Meshblu.UPDATE_DEVICE;
import static knotlibandroid.sample.com.mylibrary.Meshblu.WHOAMI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DJACA";
    private String mUuid;
    private String mToken;
    private String mServerUrl;
    private int mPort;
    private Emitter mEmitter;
    private Meshblu mMeshblu;

    private Button mBtnInsertDevice;
    private Button mBtnUpdateDevice;
    private Button mBtnListDevices;
    private Button mBtnSendData;
    private Button mBtnGetDevices;
    private Button mBtnSendMessage;

    private Button mBtnWhoami;
    private Button mBtnClaim;
    private Button mBtnDeleteDevice;
    private Button mBtnPublicKey;
    private Button mBtnGetData;
    private Button mBtnResetToken;
    private Button mBtnGeneratedToken;

    private int count = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupMeshblu();

        initView();

        registerMeshbluMethod();

        Log.i("DJACA", "Meshblu status: " + mMeshblu.isRegistered());
    }

    /**
     *
     * Configure view of application sample
     */
    private void initView() {
        mBtnInsertDevice = (Button) findViewById(R.id.btn_insert_device);
        mBtnListDevices = (Button) findViewById(R.id.btn_list_device);
        mBtnUpdateDevice = (Button) findViewById(R.id.btn_update_device);
        mBtnSendData = (Button) findViewById(R.id.btn_send_data);
        mBtnGetDevices = (Button) findViewById(R.id.btn_get_device);
        mBtnSendMessage = (Button) findViewById(R.id.btn_send_message);

        mBtnWhoami = (Button) findViewById(R.id.btn_whoami);
        mBtnClaim = (Button) findViewById(R.id.btn_claim_device);
        mBtnDeleteDevice = (Button) findViewById(R.id.btn_delete_device);
        mBtnGetData = (Button) findViewById(R.id.btn_get_data);
        mBtnResetToken = (Button) findViewById(R.id.btn_reset_token);
        mBtnPublicKey = (Button) findViewById(R.id.btn_get_pub_key);
        mBtnGeneratedToken = (Button) findViewById(R.id.btn_generated_token);

        mBtnInsertDevice.setOnClickListener(this);
        mBtnListDevices.setOnClickListener(this);
        mBtnUpdateDevice.setOnClickListener(this);
        mBtnSendData.setOnClickListener(this);
        mBtnGetDevices.setOnClickListener(this);
        mBtnSendMessage.setOnClickListener(this);

        mBtnWhoami.setOnClickListener(this);
        mBtnClaim.setOnClickListener(this);
        mBtnDeleteDevice.setOnClickListener(this);
        mBtnGetData.setOnClickListener(this);
        mBtnResetToken.setOnClickListener(this);
        mBtnPublicKey.setOnClickListener(this);
        mBtnGeneratedToken.setOnClickListener(this);
    }

    /**
     * set uuid, token, url and port of cloud.
     */
    public void setCredentials() {
            SaneJSONObject meshbluConfig = new SaneJSONObject();
            meshbluConfig.putOrIgnore("uuid", mUuid);
            meshbluConfig.putOrIgnore("token", mToken);
            meshbluConfig.putOrIgnore("server", mServerUrl);
            meshbluConfig.putIntOrIgnore("port", mPort);
        if (mMeshblu != null) {
            mMeshblu.setCredentials(meshbluConfig);
        } else {
            mMeshblu = new Meshblu(meshbluConfig, getApplicationContext());
        }
    }

    /**
     * Configure the meshblu cloud attribute
     */
    private void setupMeshblu() {
        mEmitter = new Emitter();
        mUuid = "9b92a874-a07c-46a1-a9e1-cf89835792e7-0000";
        mToken = "df6695d8b94a666449128208a2d4f996662ce5bf";
        mServerUrl = "10.0.3.2";
        mPort = 3000;
        setCredentials();
    }

    /**
     * This method register all methods available of Meshblu.
     */
    private void registerMeshbluMethod() {
        setCredentials();
        mMeshblu.on(REGISTER, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "REGISTER Gateblu Device is registered");
                mEmitter.emit(REGISTER, args);
            }
        });
        mMeshblu.on(WHOAMI, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "WHOAMI Gateblu Device");
                mEmitter.emit(WHOAMI, args);
            }
        });
        mMeshblu.on(MESSAGE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "MESSAGE TO token for Gateblu Device");
                mEmitter.emit(MESSAGE, args);
            }
        });
        mMeshblu.on(CLAIM_DEVICE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "CLAIM Device");
                mEmitter.emit(CLAIM_DEVICE, args);
            }
        });
        mMeshblu.on(UPDATE_DEVICE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "Updated Gateblu Device");
                mEmitter.emit(UPDATE_DEVICE, args);
            }
        });
        mMeshblu.on(SEND_DATA, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "SEND_DATA Gateblu Device");
                mEmitter.emit(SEND_DATA, args);
            }
        });
        mMeshblu.on(DELETE_DEVICE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "DELETE_DEVICEGateblu Device");
                mEmitter.emit(DELETE_DEVICE, args);
            }
        });
        mMeshblu.on(GET_DEVICE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "GET_DEVICE Gateblu Device");
                mEmitter.emit(GET_DEVICE, args);
            }
        });
        mMeshblu.on(GET_DATA, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "GET_DATA Gateblu Device");
                mEmitter.emit(GET_DATA, args);
            }
        });
        mMeshblu.on(RESET_TOKEN, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "RESET_TOKEN Gateblu Device");
                mEmitter.emit(RESET_TOKEN, args);
            }
        });
        mMeshblu.on(GET_PUBLIC_KEY, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "GET_PUBLIC_KEY Gateblu Device");
                mEmitter.emit(GET_PUBLIC_KEY, args);
            }
        });
        mMeshblu.on(GENERATED_TOKEN, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d(TAG, "GENERATED_TOKEN for Gateblu Device");
                mEmitter.emit(GENERATED_TOKEN, args);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert_device:
                SaneJSONObject jsonInsert = new SaneJSONObject();
                jsonInsert.putOrIgnore("type", "my_devices");
                jsonInsert.putOrIgnore("name", "device_example_yy"+ count);
                jsonInsert.putOrIgnore("owner", mUuid);
                jsonInsert.putBooleanOrIgnore("online", true);
                mMeshblu.register(jsonInsert);
                count++;
                break;
            case R.id.btn_send_data:
                SaneJSONObject sendDataJson = new SaneJSONObject();
                sendDataJson.putOrIgnore("sensor", "hello");
                mMeshblu.sendData("f97f3191-a61b-48dc-a3de-cc8e3d0bf14d-0000", sendDataJson);
                break;
            case R.id.btn_whoami:
                mMeshblu.whoami();
                break;
            case R.id.btn_list_device:
                SaneJSONObject queryList = new SaneJSONObject();
                queryList.putOrIgnore("uuid", mUuid);
                queryList.putOrIgnore("token", mToken);

                mMeshblu.devices(queryList);
                break;
            case R.id.btn_send_message:

                SaneJSONObject message = new SaneJSONObject();
                JSONArray jsonArray = new JSONArray();
                //Send message to specific device
                jsonArray.put("b26b18e6-597c-4b22-ae6d-b3cb62fcf115-0000");
                jsonArray.put("54262e14-44c7-408d-8d44-c6b9bda45621-0000");
                jsonArray.put("67ba901f-3d62-41b5-aeb8-b4fecfaa7314-0000");
                jsonArray.put("b26b18e6-597c-4b22-ae6d-b3cb62fcf115-0000");
                jsonArray.put("f97f3191-a61b-48dc-a3de-cc8e3d0bf14d-0000");

                message.putArrayOrIgnore("devices", jsonArray);
                message.putOrIgnore("topic", "test_message");
                SaneJSONObject payloadJSON = new SaneJSONObject();
                payloadJSON.putOrIgnore("change_data", "Receba");
                message.putJSONOrIgnore("payload", payloadJSON);
                mMeshblu.message(message);
                break;

            case R.id.btn_claim_device:
                mMeshblu.claimDevice("c11ea2de-a765-449c-9d0b-49cffc467481-0000");
                break;

            case R.id.btn_get_device:
                SaneJSONObject query = new SaneJSONObject();
                query.putOrIgnore("owner", mUuid);
                mMeshblu.devices(query);
                break;

            case R.id.btn_update_device:
                SaneJSONObject jsonObject = new SaneJSONObject();
                jsonObject.putBooleanOrIgnore("online", true);
                jsonObject.putOrIgnore("owner", mUuid);

                mMeshblu.updateDevice("db54bcf6-3b74-465e-b914-625c56728b9a-0000", jsonObject);
                break;
            case R.id.btn_delete_device:
                mMeshblu.deleteDevice("67ba901f-3d62-41b5-aeb8-b4fecfaa7314-0000");
                break;

            case R.id.btn_get_data:
                SaneJSONObject jsonGetData = new SaneJSONObject();
                mMeshblu.getData("f97f3191-a61b-48dc-a3de-cc8e3d0bf14d-0000", jsonGetData);
                break;

            case R.id.btn_reset_token:
                mMeshblu.resetToken("b26b18e6-597c-4b22-ae6d-b3cb62fcf115-0000");
                break;

            case R.id.btn_get_pub_key:
                mMeshblu.getPublicKey("f97f3191-a61b-48dc-a3de-cc8e3d0bf14d-0000");
                break;

            case R.id.btn_generated_token:
                mMeshblu.generateToken("b26b18e6-597c-4b22-ae6d-b3cb62fcf115-0000");
                break;

        }
    }
}
