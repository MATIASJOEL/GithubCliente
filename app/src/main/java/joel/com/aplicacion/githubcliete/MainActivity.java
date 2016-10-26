package joel.com.aplicacion.githubcliete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    ImageView avatarImageView;
    TextView nameTextView;
    TextView loginTextView;
    TextView emailTextView;
    TextView locationTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextView = (TextView) findViewById(R.id.textViewname);
        loginTextView = (TextView) findViewById(R.id.textViewlogin);
        emailTextView = (TextView) findViewById(R.id.textViewemail);
        locationTextView = (TextView) findViewById(R.id.textViewlocation);
        avatarImageView = (ImageView) findViewById(R.id.imageViewavatar);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();

        UserService servicio = restAdapter.create(UserService.class);

        servicio.obtenerUsuario(new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                nameTextView.setText(user.getName());
                loginTextView.setText(user.getLogin());
                emailTextView.setText(user.getEmail());
                locationTextView.setText(user.getLocation());
                //TODO AVATAR

                Picasso.with(getApplicationContext()).load(user.getAvatar_url()).into(avatarImageView);


                Log.i("RESULTADO", user.getName());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("ERROR", error.getMessage());

                Toast.makeText(getApplicationContext(), error.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

}

