    private void animateCircularReveal(View view) {
        int finalRadius = (int) Math.hypot(view.getWidth() / 2, view.getHeight() / 2);
        Animator anim = ViewAnimationUtils.createCircularReveal(view,
                (int) view.getWidth() / 2,
                (int) view.getHeight() / 2,
                0, finalRadius);
        anim.start();
    }
