    private FirebaseFirestore mFireStore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = mFireStore.collection("promozioni");
        
        mFireStore.collection("promozioni").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                
                    String title = document.getString("titolo");
                    String description = document.getString("descrizione");
                    String url = document.getString("url");
                }
            }
        })
