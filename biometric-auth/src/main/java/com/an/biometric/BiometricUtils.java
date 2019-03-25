package com.an.biometric;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;


@SuppressLint({"MissingPermission"})
public class BiometricUtils {


    public static boolean isBiometricPromptEnabled() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P);
    }


    /*
     * Condicion 1: Comprobar si la version del dispositivo es superior a
     * Marshmallow, ya que la autenticacion mediante huella solo se da
     * a partir de Android 6.0.
     * Nota: Si la version minima de SDK es 23,
     * esta comprobacion no es necesaria.
     *
     * */
    public static boolean isSdkVersionSupported() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
    }



    /*
     * Condicion 2: Comprueba que el dispositivo posee sensor de huellas.
     * Nota: Si marcas android.hardware.fingerprint como requisito de
     * tu aplicacion (android:required="true"), no es necesario
     * realizar esta comprobacion.
     *
     * */
    public static boolean isHardwareSupported(Context context) {
        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(context);
        return fingerprintManager.isHardwareDetected();
    }



    /*
     * Condicion 3: La autenticacion de huella se compara con una huella
     * que ya tenga el usuario registrada. Por tanto tenemos que comprobar que
     * exista alguna para poder habilitar la autenticacion.
     *
     * */
    public static boolean isFingerprintAvailable(Context context) {
        FingerprintManagerCompat fingerprintManager = FingerprintManagerCompat.from(context);
        return fingerprintManager.hasEnrolledFingerprints();
    }



    /*
     * Condicion 4: Comprobar si el permiso se ha otorgado a
     * la app. Esta peticion de permiso se hace en cuanto el usuario
     * instala la aplicacion en su dispositivo.
     *
     * */
    public static boolean isPermissionGranted(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) ==
                PackageManager.PERMISSION_GRANTED;
    }
}
