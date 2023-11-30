package com.teams.vidhividhan.utils.commonUtils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.provider.Settings;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.teams.vidhividhan.baseApplication.BaseApplication;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public final class ViewUtils {
    public static final int RIGHT_LEFT_STYLE = 1;
    public static final int BOTTOM_UP_STYLE = 2;

    public static void showToast(Context context, String message) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showToast(Context context, String message, int toastLength) {
        if (context != null) {
            Toast.makeText(context, message, toastLength).show();
        }
    }

    public static String getCurrentDate() {
        Date d = new Date();
        CharSequence s = DateFormat.format("MM-dd-yyyy", d.getTime());
        return ViewUtils.formateDateFromstring("MM-dd-yyyy", "dd-MM-yyyy", s.toString());
    }


    public static void showSnackbar(View parentView, String message) {

        try {

            if (parentView.isShown()) {
                Snackbar snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG);
                if (snackbar.getView().getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) snackbar.getView().getLayoutParams();
//                    layoutParams.setAnchorId(R.id.bottomNavigation); //Id for your bottomNavBar or TabLayout
                    layoutParams.anchorGravity = Gravity.TOP;
                    layoutParams.gravity = Gravity.TOP;

                    snackbar.getView().setLayoutParams(layoutParams);

                }
                snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                snackbar.setTextColor(Color.parseColor("#FFFFFF"));
                snackbar.show();
            }
        } catch (Exception e) {
            showToast(BaseApplication.Companion.getContext(), message);

        }

    }

    public static String formatAmount2DecimalDoubleToString(Double amount) {
        return String.format(Locale.US, "%.2f", amount);
    }

    public static String formatAmount3DecimalDoubleToString(Double amount) {
        return String.format(Locale.US, "%.3f", amount);
    }


    public static void showSnackbarAtTop(View parentView, String message) {
        try {

            if (parentView.isShown()) {
                Snackbar snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG);


                ViewGroup.LayoutParams params = snackbar.getView().getLayoutParams();
                if (params instanceof CoordinatorLayout.LayoutParams) {
                    ((CoordinatorLayout.LayoutParams) params).gravity = Gravity.TOP;
                } else {
                    ((FrameLayout.LayoutParams) params).gravity = Gravity.TOP;
                }


                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.parseColor("#174F79"));

                snackbar.show();
            }
        } catch (Exception e) {
            showToast(BaseApplication.Companion.getContext(), message);

        }
    }

//
//    public static void showSnackbarWithOption(View parentView, String message,
//                                              View.OnClickListener listener) {
//        if (parentView.isShown()) {
//            Snackbar snackbar;
//            if (message.equals("Item added to cart") || message.contains("added to cart") || message.contains("Quantity changed for")) {
//                snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG).setAction("View cart", listener);
//            } else {
//                snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG).setAction("Retry", listener);
//            }
//            if (snackbar.getView().getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
//                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) snackbar.getView().getLayoutParams();
//                layoutParams.setAnchorId(R.id.main_bottom_navigation); //Id for your bottomNavBar or TabLayout
//                layoutParams.anchorGravity = Gravity.TOP;
//                layoutParams.gravity = Gravity.TOP;
//                snackbar.getView().setLayoutParams(layoutParams);
//            }
//            snackbar.show();
//        }
//    }


    //    public static void showPopup(Activity activity, View parentView, int layoutResource, int viewGroupResource,
//                                 PopupCallback popupCallback, int style, boolean fullLayout) {
//        if (activity != null && !activity.isFinishing() && parentView.isAttachedToWindow()) {
//            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            ViewGroup viewGroup = parentView.findViewById(viewGroupResource);
//            View popupView = inflater.inflate(layoutResource, viewGroup);
//            int width = LinearLayout.LayoutParams.MATCH_PARENT;
//            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//            if (fullLayout) {
//                height = LinearLayout.LayoutParams.MATCH_PARENT;
//            }
////          int width = SCREEN_WIDTH - ((SCREEN_WIDTH/100)*20);
////          int height = SCREEN_HEIGHT - ((SCREEN_HEIGHT/100)*20);
//            PopupWindow pw = new PopupWindow(popupView, width, height, true);
//            pw.setOutsideTouchable(true);
//            if (!fullLayout) {
//                alphaPopupBackground(parentView, activity.getBaseContext());
//                if (style == BOTTOM_UP_STYLE) {
//                    pw.setAnimationStyle(R.style.PopUpAnimation2);
//                } else {
//                    pw.setAnimationStyle(R.style.PopUpAnimation);
//                }
//            } else {
//                if (style == BOTTOM_UP_STYLE) {
//                    pw.setAnimationStyle(R.style.PopUpAnimationFullPage2);
//                } else {
//                    pw.setAnimationStyle(R.style.PopUpAnimationFullPage);
//                }
//            }
//            pw.setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(android.R.color.transparent)));
//            pw.showAtLocation(parentView, Gravity.CENTER, 0, 0);
//            pw.setOnDismissListener(() -> unAlphaPopupBackground(parentView, activity.getBaseContext()));
//            popupCallback.onClick(popupView, pw);
//        }
//
//    }
    public static void showAlert(Activity activity, String title, String message, String positiveMessage,
                                 DialogInterface.OnClickListener listener, boolean isCancellable) {
        if (activity != null) {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            if (!title.equals("")) {
                alert.setTitle(title);
            }
            alert.setMessage(message);
            alert.setPositiveButton(positiveMessage, listener);
            alert.setCancelable(isCancellable);
            alert.show();
        }
    }

    public static void showAlertWithOptions(Activity activity, String title, String message,
                                            String positiveMessage, String negativeMessage,
                                            DialogInterface.OnClickListener positiveListener,
                                            DialogInterface.OnClickListener negativeListener, boolean isCancellable) {
        if (activity != null) {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            if (!title.equals("")) {
                alert.setTitle(title);
            }
            alert.setMessage(message);
            alert.setPositiveButton(positiveMessage, positiveListener);
            alert.setNegativeButton(negativeMessage, negativeListener);
            alert.setCancelable(isCancellable);
            alert.show();
        }
    }

    public static void showToast(@NotNull String s) {

    }


    public interface PopupCallback {
        void onClick(View popupView, PopupWindow popupWindow);
    }

    public static void hideKeyBoard(Activity activity) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    public static void hideKeyBoard(Activity activity, View viewGroup) {
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(viewGroup.getWindowToken(), 0);
            }
        }
    }

    static boolean isKeyboardShowing = false;

    public static boolean isKeyBoardOpen(View view) {

        Rect r = new Rect();
        view.getWindowVisibleDisplayFrame(r);
        int screenHeight = view.getRootView().getHeight();

        // r.bottom is the position above soft keypad or device button.
        // if keypad is shown, the r.bottom is smaller than that before.
        int keypadHeight = screenHeight - r.bottom;


        if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
            // keyboard is opened
            if (!isKeyboardShowing) {
                isKeyboardShowing = true;
            }
        } else {
            // keyboard is closed
            if (isKeyboardShowing) {
                isKeyboardShowing = false;
            }
        }
        return isKeyboardShowing;
    }

    public static void unAlphaPopupBackground(View view, Context context) {
        if (view.isShown()) {
            view.setForeground(ContextCompat.getDrawable(context, android.R.color.transparent));
        }
    }

    public static void blockTouchEvent(Activity activity) {
        if (activity != null) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public static void unBlockTouchEvent(Activity activity) {
        if (activity != null) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public static void callThisNumber(String phoneNumber, Activity activity) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                String no = "tel:" + phoneNumber;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(no));
                activity.startActivity(intent);
            }
        }

    }


    public static void emailIntent(String email, Activity activity) {
        if (!TextUtils.isEmpty(email)) {
            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
            intent.setData(Uri.parse("mailto:" + email)); // or just "mailto:" for blank
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// this will make such that when user returns to your app, your app is displayed, instead of the email app.
            activity.startActivity(intent);
        }

    }


    public static void phoneIntent(String phone, Activity activity) {
        if (!TextUtils.isEmpty(phone)) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone));
            activity.startActivity(intent);
        }

    }


    public static void whatsAppIntent(String phone, Activity activity, boolean isSaudi) {
        if (!TextUtils.isEmpty(phone)) {
            if (isSaudi) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=966" + phone));
                activity.startActivity(intent);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=91" + phone));
                activity.startActivity(intent);
            }

        }

    }

    public static void whatsAppIntent(String phone, Activity activity, String extention) {
        if (!TextUtils.isEmpty(phone)) {
            extention = extention.replace("+", "").trim();
            Log.d("checkDatass", extention + phone);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + extention + phone));
            activity.startActivity(intent);
        }

    }


    public static void optionForWhatsAppAndPhone(String number, Activity activity, boolean isSaudiNumber) {
        if (!TextUtils.isEmpty(number)) {

            ViewUtils.showAlertWithOptions(activity
                    , "", "Select Action", "Call", "Whats App", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ViewUtils.phoneIntent(number, activity);

                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ViewUtils.whatsAppIntent(number, activity, isSaudiNumber);
                        }
                    }, true);


        }

    }


    public static void openMapIntent(Activity activity, String lat, String lon) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + lat + "," + lon));
            intent.setClassName("com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity");
            activity.startActivity(intent);
        } catch (Exception e) {
            ViewUtils.showToast(activity, "Unable to open");
        }


    }

    public static void showKeyboard(Context context) {
        if (context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }

        }

    }

    public static void closeKeyboard(Context context) {
        if (context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }

        }

    }


    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate) {

        Date parsed = null;
        String outputDate = null;

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.ENGLISH);


        if (inputDate == null) {
            return "";
        } else {
            try {
                parsed = df_input.parse(inputDate);
                outputDate = df_output.format(parsed);

            } catch (ParseException e) {
                Log.d("dateerror", e + "");

            }

            return outputDate;
        }


    }


    public static boolean checkPermissionForCameraAndGallery(Activity activity)
    {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkPermissionForBluetooth(Activity activity) {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }


    public static boolean checkPermissionForFile(Activity activity)
    {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }


    public static void openSettings(Activity activity) {
        if (activity != null) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivityForResult(intent, 101);
        }

    }


    public static void addImageAtEndOfText(String text, int imageID, Context context, TextView textView) {

        String mainText = text + "  ";
        SpannableStringBuilder ssb = new SpannableStringBuilder(mainText);
        ssb.setSpan(new ImageSpan(context, imageID), mainText.length() - 1, mainText.length(), 0);
        textView.setText(ssb, TextView.BufferType.SPANNABLE);
    }

    public static Bitmap renderPdfPage(File fileOG) throws IOException {
        Log.d("PATH", fileOG.toString());
        ParcelFileDescriptor pfd = ParcelFileDescriptor.open(fileOG, ParcelFileDescriptor.MODE_READ_ONLY);
        PdfRenderer renderer = new PdfRenderer(pfd);
        Bitmap bitmap = Bitmap.createBitmap(360, 360, Bitmap.Config.ARGB_4444);
        PdfRenderer.Page page = renderer.openPage(0);
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        page.close();
        renderer.close();
        System.out.println("priy bbb " + fileOG.getAbsolutePath() + bitmap.getHeight());
        return bitmap;
    }
//    public static void openLinkOnChromeTabs(String url,Context context){
//
//        try {
//            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//            CustomTabsIntent customTabsIntent = builder.build();
//
////            builder.setDefaultColorSchemeParams(new CustomTabColorSchemeParams(context.getColor(R.color.app_yellow)),ContextCompat.getColor(context,R.color.app_brown),ContextCompat.getColor(context,R.color.black),ContextCompat.getColor(context,R.color.black));
////            builder.setStartAnimations(context, R.anim.enter_from_right, R.anim.exit_to_left);
////            builder.setExitAnimations(context, R.anim.enter_from_left, R.anim.exit_to_right);
//            customTabsIntent.launchUrl(context, Uri.parse(url));
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

//    public static void openLinkOnChromeTabsInApp(String url,Context context){
//
//        try {
//            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//            CustomTabsIntent customTabsIntent = builder.build();
//            builder.setStartAnimations(context, R.anim.enter_from_right, R.anim.exit_to_left);
//            builder.setExitAnimations(context, R.anim.exit_to_right, R.anim.enter_from_left);
//            String packageName = "com.android.chrome";
//            if (packageName != null) {
//                customTabsIntent.intent.setPackage(packageName);
//                customTabsIntent.launchUrl(context, Uri.parse(url));
//            } else {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

    public static String capitalizeFirstLetter(final String line) {
        try {
            return Character.toUpperCase(line.charAt(0)) + line.substring(1).toLowerCase();
        } catch (Exception e) {
            return line;
        }

    }


    public static CharSequence toHTML(final String html) {
        try {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        } catch (Exception e) {
            return html;
        }


    }


    public static void print(Context context, final File file_name_pdf) {

        try {

            PrintManager printManager = null;
            printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);
            String jobName = "Document";
            PrintDocumentAdapter pda = null;

            pda = new PrintDocumentAdapter() {

                @Override
                public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes1, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
                    if (cancellationSignal.isCanceled()) {
                        layoutResultCallback.onLayoutCancelled();
                        return;
                    }

                    PrintDocumentInfo pdi = new PrintDocumentInfo.Builder("EasyInvoice").setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).build();

                    layoutResultCallback.onLayoutFinished(pdi, true);
                }

                @Override
                public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {
                    InputStream input = null;
                    OutputStream output = null;

                    try {
                        input = new FileInputStream(file_name_pdf);
                        output = new FileOutputStream(destination.getFileDescriptor());

                        byte[] buf = new byte[1024];
                        int bytesRead;

                        while ((bytesRead = input.read(buf)) > 0) {
                            output.write(buf, 0, bytesRead);
                        }

                        callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});

                    } catch (Exception e) {
                        e.printStackTrace();
                        //Catch exception
                    } finally {
                        try {
                            if (input != null) {
                                input.close();
                            }
                            if (output != null) {
                                output.close();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            };

            printManager.print(jobName, pda, null);
        } catch (Exception e) {
            e.printStackTrace();


        }

    }

    public static void displayPdf(Activity activity, String pdf) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(pdf);
        browserIntent.setDataAndType(uri, "application/pdf");

        Intent chooser = Intent.createChooser(browserIntent, "Open PDF");
        chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // optional

        activity.startActivity(chooser);
    }


}

