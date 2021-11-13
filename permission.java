public void permission(){
    if (ContextCompat.checkSelfPermission(getApplicationContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
        // Permission Granted: Do whatever you want to do after Grant Permission.
        //--
    }else {
        // if not granted
        continuePermissionRequest();
    }
}

private void continuePermissionRequest() {
    ActivityCompat.requestPermissions(MainActivity.this,
            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
}

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == STORAGE_PERMISSION){
        for (int i = 0; i<permissions.length; i++){
            String per = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_DENIED){
                // we will check whether user check never ask again or not
                boolean showRationale = shouldShowRequestPermissionRationale(per);
                if (!showRationale){
                    // User check never ask again
                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Background);
                    builder.setTitle("APP PERMISSION").
                            setMessage("For playing videos first of all i need to collect all music files in your storage. And access your storage I need device permission" +"\n\n\n"
                                    + "Now Follow this steps if you want to allow me" + "\n\n"
                                    + "01. Open Setting by bellow button" + "\n"
                                    + "02. Click on Permissions" + "\n"
                                    + "03. Allow Access for Storage"+ "\n\n"
                                    +"Or hit cancel to goes out")
                            .setPositiveButton("OPEN SETTINGS", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                                }
                            }).create().show();
                }else {
                    // User Denied Permission
                    continuePermissionRequest();
                }

            }else {
                // User Clicked on Allow Button
                //--
            }
        }
    }
}

@Override
protected void onResume() {
    super.onResume();
    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
        // Permission Accepted. Do what you want to do.
    }
}
