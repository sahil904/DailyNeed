package com.sahil.dailyneed.common

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.iid.FirebaseInstanceId
import com.sahil.dailyneed.R
import com.sahil.dailyneed.activity.api.Retro
import com.sahil.dailyneed.shop.activity.ShopHomeActivity
import com.sahil.dailyneed.shop.model.RegisterModel
import com.sahil.dailyneed.user.activity.MainActivity
import com.sahil.dailyneed.util.SessionManger
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener,
    View.OnClickListener, Callback<RegisterModel> {
    lateinit var sessionManager: SessionManger
    lateinit var email: String
    lateinit var name: String
    lateinit var callbackManager: CallbackManager
    var image: String = ""
    private var mGoogleApiClient: GoogleApiClient? = null
    private val RC_SIGN_IN = 7
    var check_con: String = "fb"
    lateinit var user_type: String
    lateinit var lat: String
    lateinit var long: String
    lateinit var token_id: String


    lateinit var check_type: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sessionManager = SessionManger(this)
        user_type = intent.getStringExtra("key")
        lat = intent.getStringExtra("lat")
        long = intent.getStringExtra("long")
        token_id = FirebaseInstanceId.getInstance().getToken()!!

        click()
        hashkey()
    }

    private fun hashkey() {
        try {
            val info = packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    private fun click() {
        facebook_layout.setOnClickListener(this)
        gmail_relative.setOnClickListener(this)
        login.setOnClickListener(this)
        forget_password.setOnClickListener(this)
        login_register_text.setOnClickListener(this)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        btn_sign_in.setSize(SignInButton.SIZE_STANDARD)
        btn_sign_in.setScopes(gso.getScopeArray())
        btn_sign_in.performClick()
        btn_sign_in.setOnClickListener(this)

        sessionManager = SessionManger(this)


//        if (sessionManager.isLoggedIn() == false) {
//
//            email_login.setText(sessionCheck.uSerDeatis.get("email"))
//            password_login.setText(sessionCheck.uSerDeatis.get("password"))
//        }
        callbackManager = CallbackManager.Factory.create()
        fb_login_button.setOnClickListener(this)
        val loggedOut = AccessToken.getCurrentAccessToken() == null

        fb_login_button.setReadPermissions(Arrays.asList("email", "public_profile"))
        if (!loggedOut) {
            // Picasso.with(this).load(Profile.getCurrentProfile().getProfilePictureUri(200, 200)).into(imageView);
            Log.d(
                "TAhgjhgjG",
                "Username is: " + Profile.getCurrentProfile().name + Profile.getCurrentProfile().getProfilePictureUri(
                    200,
                    200
                )
            )
            image = Profile.getCurrentProfile().getProfilePictureUri(200, 200)!!.toString()
            //Using Graph API
            getUserProfile(AccessToken.getCurrentAccessToken())

        }

        fb_login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                // App code
                val request = GraphRequest.newMeRequest(
                    loginResult.accessToken
                ) { `object`, response ->
                    Log.d("0", response.toString())


                    // Application code
                    try {
                        email = `object`.getString("email")
                        name = `object`.getString("name") // 01/31/1980 format
                        Log.d("FDfdfdf", email + name)
                        sociallogin()
                        check_con = "fb"
                        //  Toast.makeText(this@LoginActivity, "sucess", Toast.LENGTH_SHORT).show()
                        //   hitapi(email, name)
                        /*   intent = Intent(this@LoginActivity, PhoneNumberActivity::class.java)
                           intent.putExtra("name",name)
                           intent.putExtra("name",email)
                         //  sessionManager.nameimage(name,false,image);

                           startActivity(intent)*/
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email")
                request.parameters = parameters
                request.executeAsync()

            }

            override fun onCancel() {
                // App code
                // Toast.makeText(this@LoginActivity, "cance;", Toast.LENGTH_SHORT).show()

            }

            override fun onError(exception: FacebookException) {
                //    Log.e("errorfacebook", exception.getMessage() + "     " + exception.getCause());
                // App code
                Toast.makeText(this@LoginActivity, "" + exception.message, Toast.LENGTH_SHORT)
                    .show()

            }
        })

    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.facebook_layout -> {
                fb_login_button.performClick()
            }
            R.id.login_register_text -> {
                var intent = Intent(this, SignupActivity::class.java)
                intent.putExtra("user_type", user_type)
                intent.putExtra("lat", lat)
                intent.putExtra("long", long)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                startActivity(intent)
            }
            R.id.forget_password -> {
                var intent = Intent(this, ForgotPassActivity::class.java)
                intent.putExtra("user_type", user_type)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                startActivity(intent)
            }

            R.id.gmail_relative -> {
                signIn()

            }
            R.id.login -> {

                if (email_id_login.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show()
                } else if (password_login.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()

                } else {

                    apihit()


                }


            }
        }
    }

    private fun apihit() {
        progress_login.visibility = View.VISIBLE
        Retro.ApiService().login(
            email_id_login.text.toString(),
            password_login.text.toString(),
            token_id,
            user_type,
            lat,
            long
        ).enqueue(this)
        // Retro.ApiService()
    }

    private fun getUserProfile(currentAccessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(
            currentAccessToken
        ) { `object`, response ->
            Log.d("fdgfgf", `object`.toString() + "fdbgfgf" + response)
            try {
                name = `object`.getString("first_name")
                val last_name = `object`.getString("last_name")
                email = `object`.getString("email")
                // String id = object.getString("id");
                //   String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                Log.d("fgfdgfgfg", name + last_name + email)
                sociallogin()
                //    txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
                //  txtEmail.setText(email);
                //  Picasso.with(MainActivity.this).load(image_url).into(imageView);

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "first_name,last_name,id,email")
        request.parameters = parameters
        request.executeAsync()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    private fun sociallogin() {
//        avi.visibility = View.VISIBLE
//        Retro.ApiService().loginWithFacebook(name, email).enqueue(object : Callback<RegisterModel> {
//            override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
//
//                errorToast(" Enter Email ")
//            }
//
//            override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
//                avi.visibility = View.GONE
//
//                if (check_con.equals("fb")) {
//                    if (response.body()!!.data.image == null) {
//                        sessionManager.createLogin(response.body()!!.data.userId,response.body()!!.data.uniqueId, response.body()!!.data.email, response.body()!!.data.name, response.body()!!.data.phone, Profile.getCurrentProfile().getProfilePictureUri(200, 200)!!.toString(), true);
//
//                    } else
//                        sessionManager.createLogin(response.body()!!.data.userId,response.body()!!.data.uniqueId, response.body()!!.data.email, response.body()!!.data.name, response.body()!!.data.phone, response.body()!!.data.image, true);
//
//
//                } else
//
//                    sessionManager.createLogin(response.body()!!.data.userId,response.body()!!.data.uniqueId, response.body()!!.data.email, response.body()!!.data.name, response.body()!!.data.phone, response.body()!!.data.image, true);
//
//                var intent = Intent(this@LoginActivity, NavigationActivity::class.java)
//                startActivity(intent)
//                val inflater = layoutInflater as LayoutInflater
//                val layout = inflater.inflate(R.layout.toast_custom,
//                    findViewById(R.id.custom_toast_card) as? ViewGroup)
//                var text_toast = layout.findViewById(R.id.toast_text) as TextView
//                text_toast.text = "'Login Successful"
//                val custToast = Toast(this@LoginActivity)
//                custToast.setView(layout)
//                custToast.show()
//                finish()
//
//
//            }
//        })

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);


    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(ContentValues.TAG, "handleSignInResult:" + result.isSuccess)
        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            val acct = result.signInAccount

            Log.e(ContentValues.TAG, "display name: " + acct!!.displayName!!)

            name = acct.displayName!!
            // String personPhotoUrl = acct.getPhotoUrl().toString();
            email = acct.email!!

            Log.e(ContentValues.TAG, "Name: $name, email: $email")
            Log.d("gmailname", "$name    $email")
            sociallogin()
            check_con = "gm"
            //  hitapi(email, name)


            //            txtName.setText(personName);
            //            txtEmail.setText(email);
            // Glide.with(getApplicationContext()).load(personPhotoUrl).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imgProfilePic);
            //  updateUI(true)
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false)
        }
    }

    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
        progress_login.visibility = View.GONE

    }

    override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
        progress_login.visibility = View.GONE

        if (response.isSuccessful) {
            var email = response.body()!!.data.email
            var name = response.body()!!.data.full_name
            var user_id = response.body()!!.data.user_id
            var shop_id = response.body()!!.data.shop_id
            if (user_type.equals("user")) {
                sessionManager.CreateInstallerLogin(false, true, email, name, user_id)
                sessionManager.isUserLogin

                var intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finish()

            } else {
                sessionManager.CreateInstallerLogin(true, false, email, name, user_id)

                var intent = Intent(this, ShopHomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.putExtra("shop_id", shop_id)
                startActivity(intent)
                finish()
            }
        }
    }
}
