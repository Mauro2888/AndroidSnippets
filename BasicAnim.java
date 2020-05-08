    private void animateCircularReveal(View view) {
        int finalRadius = (int) Math.hypot(view.getWidth() / 2, view.getHeight() / 2);
        Animator anim = ViewAnimationUtils.createCircularReveal(view,
                (int) view.getWidth() / 2,
                (int) view.getHeight() / 2,
                0, finalRadius);
        anim.start();
    }


Here is a Slide Animation for you.
enter image description here

Let's say you have two activities.

MovieDetailActivity
AllCastActivity
And on click of a Button, this happens.

enter image description here

You can achieve this in 3 simple steps
1) Enable Content Transition

Go to your style.xml and add this line to enable the content transition.

<item name="android:windowContentTransitions">true</item>
2) Write Default Enter and Exit Transition for your AllCastActivity

public void setAnimation()
{
    if(Build.VERSION.SDK_INT>20) {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(400);
        slide.setInterpolator(new AccelerateDecelerateInterpolator());
        getWindow().setExitTransition(slide);
        getWindow().setEnterTransition(slide);
    }
}
3) Start Activity with Intent

Write this method in Your MovieDetailActivity to start AllCastActivity

public void startActivity(){

Intent i = new Intent(FirstActivity.this, SecondActivity.class);
i.putStringArrayListExtra(MOVIE_LIST, movie.getImages());

  if(Build.VERSION.SDK_INT>20)
   {
       ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(BlankActivity.this);
       startActivity(i,options.toBundle());
   }
   else {
       startActivity(i);
   }
}
Most important!
put your setAnimation()method before setContentView() method otherwise the animation will not work.
So your AllCastActivity.javashould look like this

 class AllCastActivity extends AppcompatActivity {

   @Override
   protected void onCreate(Bundle savedInstaceState)
   {
      super.onCreate(savedInstaceState);

      setAnimation();

      setContentView(R.layout.all_cast_activity);

      .......
   }

   private void setAnimation(){

      if(Build.VERSION.SDK_INT>20) {
      Slide slide = new Slide();
      slide.setSlideEdge(Gravity.LEFT);
      ..........
  }
}
