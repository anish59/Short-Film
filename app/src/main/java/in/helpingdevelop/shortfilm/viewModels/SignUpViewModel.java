package in.helpingdevelop.shortfilm.viewModels;

import android.content.Context;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import in.helpingdevelop.shortfilm.NavigationActivity;
import in.helpingdevelop.shortfilm.client.SignUpApiClient;
import in.helpingdevelop.shortfilm.helper.Functions;
import in.helpingdevelop.shortfilm.helper.IntentUtils;

public class SignUpViewModel {
    private Context context;
    private SignUpViewModelCallBack callBack;

    public SignUpViewModel(Context context, SignUpViewModelCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public void doSignUp(EditText edtFullName, EditText edtEmail, EditText edtPassword) {
        String name = Functions.toString(edtFullName);
        String email = Functions.toString(edtEmail);
        String pwd = Functions.toString(edtPassword);

        if (name.isEmpty()) {
            Toast.makeText(context, "Please enter full name.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(context, "Please enter email id.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.isEmpty()) {
            Toast.makeText(context, "Please enter password.", Toast.LENGTH_SHORT).show();
            return;
        }


        new SignUpApiClient(context).doRegister(email, pwd, name, getLocalIpAddress(), new SignUpApiClient.SignUpClientCallBack() {
            @Override
            public void onSuccess(String msg) {
                IntentUtils.fireIntent(context, NavigationActivity.class, true);
                callBack.onSuccess(msg);
            }

            @Override
            public void onError(String err) {
                callBack.onError(err);
            }
        });
    }

    public interface SignUpViewModelCallBack {
        void onSuccess(String msg);

        void onError(String err);
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i("IP_TAG", "***** IP=" + ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("IP_EXCEPTION", ex.toString());
        }
        return null;
    }
}
