package joel.com.aplicacion.githubcliete;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Usuario on 12/10/2016.
 */
public interface UserService {
    @GET("/users/MATIASJOEL")
    void obtenerUsuario(Callback<User> user);
}
