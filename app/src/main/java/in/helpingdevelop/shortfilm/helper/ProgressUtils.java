package in.helpingdevelop.shortfilm.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class ProgressUtils {

    private static ProgressDialog progressDialog;

    public static void showProgress(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading..");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }

        try {
            if (!((Activity) context).isFinishing()) {
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
