package com.example.getcurrlatlangjava;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener {

        final String TAG = "GPS";
        private final static int ALL_PERMISSIONS_RESULT = 101;
        private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
        private static final long MIN_TIME_BW_UPDATES = 1000 * 60;

        TextView tvLatitude, tvLongitude, tvTime;
        LocationManager locationManager;
        Location loc;
        ArrayList<String> permissions = new ArrayList<>();
        ArrayList<String> permissionsToRequest;
        ArrayList<String> permissionsRejected = new ArrayList<>();
        boolean isGPS = false;
        boolean isNetwork = false;
        boolean canGetLocation = true;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            tvLatitude = (TextView) findViewById(R.id.tvLatitude);
            tvLongitude = (TextView) findViewById(R.id.tvLongitude);
            tvTime = (TextView) findViewById(R.id.tvTime);

            locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);
            isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissionsToRequest = findUnAskedPermissions(permissions);

            if (!isGPS && !isNetwork) {
                Log.e(TAG, "Connection off");
                showSettingsAlert();
                getLastLocation();
            } else {
                Log.e(TAG, "Connection on");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (permissionsToRequest.size() > 0) {
                        requestPermissions((String[]) permissionsToRequest.toArray(new String[0]),
                                ALL_PERMISSIONS_RESULT);
                        Log.e(TAG, "Permission requests");
                        canGetLocation = false;
                    }
                }
                getLocation();
            }
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged");
            updateUI(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {
            getLocation();
        }

        @Override
        public void onProviderDisabled(String s) {
            if (locationManager != null) {
                locationManager.removeUpdates(this);
            }
        }

        private void getLocation() {
            try {
                if (canGetLocation) {
                    Log.e(TAG, "Can get location");
                    if (isGPS) {
                        // from GPS
                        Log.e(TAG, "GPS on");
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if (locationManager != null) {
                            loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (loc != null)
                                updateUI(loc);
                        }
                    } else if (isNetwork) {
                        // from Network Provider
                        Log.e(TAG, "NETWORK_PROVIDER on");
                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        if (locationManager != null) {
                            loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                            if (loc != null)
                                updateUI(loc);
                        }
                    } else {
                        loc.setLatitude(0);
                        loc.setLongitude(0);
                        updateUI(loc);
                    }
                } else {
                    Log.e(TAG, "Can't get location");
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
}

        private void getLastLocation() {
            try {
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, false);
                Location location = locationManager.getLastKnownLocation(provider);
                Log.e(TAG, provider);
                Log.e(TAG, location == null ? "NO LastLocation" : location.toString());
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
            ArrayList<String> result = new ArrayList<>();

            for (String perm : wanted) {
                if (hasPermission(perm)) {
                    result.add(perm);
                }
            }

            return result;
        }

        private boolean hasPermission(String permission) {
            if (canAskPermission()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED);
                }
            }
            return false;
        }

        private boolean canAskPermission() {
            return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
        }

        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case ALL_PERMISSIONS_RESULT:
                    Log.d(TAG, "onRequestPermissionsResult");
                    for (Object perms : permissionsToRequest) {
                        if (hasPermission((String) perms)) {
                            permissionsRejected.add((String) perms);
                        }
                    }
                    if (permissionsRejected.size() > 0) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                                showMessageOKCancel(
                                        new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestPermissions(permissionsRejected.toArray(
                                                new String[0]), ALL_PERMISSIONS_RESULT);
                                    }
                                });
                                return;
                            }
                        }
                    } else {
                        Log.d(TAG, "No rejected permissions.");
                        canGetLocation = true;
                        getLocation();
                    }
                    break;
            }
        }

        public void showSettingsAlert() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("GPS is not Enabled!");
            alertDialog.setMessage("Do you want to turn on GPS?");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });

            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            alertDialog.show();
        }

        private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("These permissions are mandatory for the application Please allow access")
                    .setPositiveButton("OK", okListener)
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        }

        private void updateUI(Location loc) {
            Log.d(TAG, "updateUI");
            tvLatitude.setText(Double.toString(loc.getLatitude()));
            tvLongitude.setText(Double.toString(loc.getLongitude()));
            tvTime.setText(DateFormat.getTimeInstance().format(loc.getTime()));
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (locationManager != null) {
                locationManager.removeUpdates(this);
            }
        }
}

