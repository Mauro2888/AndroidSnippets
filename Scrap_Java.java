  //Required Jsoup + Retrofit + OkHttp and a simple interface for the endpoint
  
  OkHttpClient.Builder http = new OkHttpClient.Builder();
	    Retrofit callBasic = new Retrofit.Builder()
                .baseUrl(URL_SITE)
                .addConverterFactory(ScalarsConverterFactory.create())
				.client(http.build())
				.build();

	    UserInterface userInterface = callBasic.create(UserInterface.class);
	    Call<String>async = userInterface.basicCall();
	    async.enqueue(new Callback<String>() {
			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				String data = response.body();
				Document document = Jsoup.parse(data);
				data = document.text();

				Elements element = document.getElementsByClass("sp-head");
				for (Element e:element) {
					System.out.println(e.text());
				}
			}

			@Override
			public void onFailure(Call<String> call, Throwable throwable) {

			}
		});
