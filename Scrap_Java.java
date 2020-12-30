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


#############################
package com.company;

import okhttp3.*;
import okio.Utf8;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
                Request request1 = new Request.Builder()
                .url("URL1")
                .build();

        Request request2 = new Request.Builder()
                .url("URL2")
                .build();

        client.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String resp = response.body().string();
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = null;
                    try {
                        db = dbf.newDocumentBuilder();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                    try {
                        XPath xpath =XPathFactory.newInstance().newXPath();
                        org.w3c.dom.Document xmlDoc = db.parse(new InputSource(new ByteArrayInputStream(resp.getBytes("UTF-8"))));
                        XPathExpression name = xpath.compile("XPATH");
                        NodeList nodeList = (NodeList) name.evaluate(xmlDoc, XPathConstants.NODESET);
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            Node node = nodeList.item(i);
                            System.out.println(node.getNodeName() + ": " + node.getTextContent());
                        }
                    } catch (SAXException | XPathExpressionException e) {
                        e.printStackTrace();
                    }

                    client.newCall(request2).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println(response.body().string());
                        }
                    });
                }
            }
        });
    }
}
