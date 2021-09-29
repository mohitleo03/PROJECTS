package com.example.gymworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

import com.huawei.agconnect.appmessaging.AGConnectAppMessaging;
import com.huawei.agconnect.appmessaging.AGConnectAppMessagingOnClickListener;
import com.huawei.agconnect.appmessaging.model.AppMessage;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.entity.AAIDResult;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;

public class MainActivity extends AppCompatActivity{
    public static String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAAID();
        inAppMessage();
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silentSignInByHwId();
            }
        });
        Button info=findViewById(R.id.info_button);
        info.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "INFO", Toast.LENGTH_SHORT).show();
            Intent Info = new Intent(MainActivity.this,info.class);
            startActivity(Info);
        });

       /* Button login=findViewById(R.id.login_button);
        login.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            Intent home=new Intent(MainActivity.this,Home.class);
            startActivity(home);

        });*/
        Button a=findViewById(R.id.hidden);
        a.setOnLongClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Mohit Malik apk", Toast.LENGTH_SHORT).show();
            Intent b=new Intent(MainActivity.this,owner.class);
            startActivity(b);
            return true;
        });
    }
    // AccountAuthService provides a set of APIs, including silentSignIn, getSignInIntent, and signOut.
    private AccountAuthService mAuthService;

    // Set HUAWEI ID sign-in authorization parameters.
    private AccountAuthParams mAuthParam;

    // Define the request code for signInIntent.
    private static final int REQUEST_CODE_SIGN_IN = 1000;

    // Define the log flag.
    private static final String TAG = "Account";

    /**
     * Silent sign-in: If a user has authorized your app and signed in, no authorization or sign-in screen will appear during subsequent sign-ins, and the user will directly sign in.
     * After a successful silent sign-in, the HUAWEI ID information will be returned in the success event listener.
     * If the user has not authorized your app or signed in, the silent sign-in will fail. In this case, your app will show the authorization or sign-in screen to the user.
     */
    private void silentSignInByHwId() {
        // 1. Use AccountAuthParams to specify the user information to be obtained, including the user ID (OpenID and UnionID), email address, and profile (nickname and picture).
        // 2. By default, DEFAULT_AUTH_REQUEST_PARAM specifies two items to be obtained, that is, the user ID and profile.
        // 3. If your app needs to obtain the user's email address, call setEmail().
        //  4. To support ID token-based HUAWEI ID sign-in, use setIdToken(). User information can be parsed from the ID token.
        mAuthParam = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                .setEmail()
                .setIdToken()
                .createParams();
        // Use AccountAuthParams to build AccountAuthService.
        mAuthService = AccountAuthManager.getService(this, mAuthParam);
        // Sign in with a HUAWEI ID silently.
        Task<AuthAccount> task = mAuthService.silentSignIn();
        task.addOnSuccessListener(new OnSuccessListener<AuthAccount>() {
            @Override
            public void onSuccess(AuthAccount authAccount) {
                // The silent sign-in is successful. Process the returned AuthAccount object to obtain the HUAWEI ID information.
                dealWithResultOfSignIn(authAccount);
                user_name=authAccount.getDisplayName().toString();
                Intent home=new Intent(MainActivity.this,Home.class);
                home.putExtra("name",user_name);
                startActivity(home);
            }
        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                // The silent sign-in fails. Your app will call getSignInIntent() to show the authorization or sign-in screen.
                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    Intent signInIntent = mAuthService.getSignInIntent();
                    startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN);
                }
            }
        });
    }

    /**
     * Process the returned AuthAccount object to obtain the HUAWEI ID information.
     *
     * @param authAccount AuthAccount object, which contains the HUAWEI ID information.
     */
    private void dealWithResultOfSignIn(AuthAccount authAccount) {
        Log.i(TAG, "idToken:" + authAccount.getIdToken());
        // TODO: After obtaining the ID token, your app will send it to your app server if there is one. If you have no app server, your app will verify and parse the ID token locally.
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            Log.i(TAG, "onActivitResult of sigInInIntent, request code: " + REQUEST_CODE_SIGN_IN);
            Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
            if (authAccountTask.isSuccessful()) {
                // The sign-in is successful, and the authAccount object that contains the HUAWEI ID information is obtained.
                AuthAccount authAccount = authAccountTask.getResult();
                dealWithResultOfSignIn(authAccount);
                Log.i(TAG, "onActivitResult of sigInInIntent, request code: " + REQUEST_CODE_SIGN_IN);
                user_name=authAccount.getDisplayName().toString();
                Intent home=new Intent(MainActivity.this,Home.class);
                home.putExtra("name",user_name);
                startActivity(home);
            } else {
                // The sign-in fails. Find the failure cause from the status code. For more information, please refer to the "Error Codes" section in the API Reference.
                Log.e(TAG, "sign in failed : " +((ApiException)authAccountTask.getException()).getStatusCode());
            }
        }
    }
    public void inAppMessage(){
        //AGConnectAppMessaging.getInstance().setFetchMessageEnable(true);
        AGConnectAppMessaging appMessaging = AGConnectAppMessaging.getInstance();
        //
        appMessaging.setFetchMessageEnable(true);
        appMessaging.setDisplayEnable(true);
        appMessaging.setForceFetch();
        ClickListener listener = new ClickListener();
        appMessaging.addOnClickListener(listener);
    }

    public class ClickListener implements AGConnectAppMessagingOnClickListener {
        @Override
        public void onMessageClick(AppMessage appMessage) {
            // Obtain the content of the tapped message.
        }
    }
    public void getAAID(){
        Task<AAIDResult> idResult = HmsInstanceId.getInstance(getApplicationContext()).getAAID();
        idResult.addOnSuccessListener(new OnSuccessListener<AAIDResult>() {
            @Override
            public void onSuccess(AAIDResult aaidResult) {
                // Called when the AAID is obtained.
                String aaid = aaidResult.getId();
                Log.d("TAG", "getAAID successfully, aaid is " + aaid );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception myException) {
                // Called when the AAID fails to be obtained.
                Log.d("TAG", "getAAID failed, catch exceptio : " + myException);
            }
        });
    }

}