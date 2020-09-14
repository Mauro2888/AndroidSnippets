Intent i = new Intent(applicationContext, SplashActivity.java);        // Specify any activity here e.g. home or splash or login etc
  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  i.putExtra("EXIT", true);
  startActivity(i);
  finish();
