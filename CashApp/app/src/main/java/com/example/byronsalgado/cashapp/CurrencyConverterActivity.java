package com.example.byronsalgado.cashapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CurrencyConverterActivity extends AppCompatActivity {

    private TextView usd, hnl, eur;
    private Button Convertbtn;
    private EditText Currencytxt;
    private Spinner Currencyspn;
    private int index = 0;
    private double usdValue, eurValue, hnlValue, dato;
    private String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);
        Currencytxt = (EditText)findViewById(R.id.Currencytxt);
        usd = (TextView)findViewById(R.id.usd);
        hnl = (TextView)findViewById(R.id.hnl);
        eur = (TextView)findViewById(R.id.eur);
        Convertbtn = (Button)findViewById(R.id.Convertbtn);
        Currencyspn = (Spinner)findViewById(R.id.Currencyspn);

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currency, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Currencyspn.setAdapter(adapter);

        Currencyspn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Convertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(index == 0){
                    valor = Currencytxt.getText().toString();
                    dato = Double.parseDouble(valor);
                    usdValue = dato * 1;
                    eurValue = dato * 0.811196;
                    hnlValue = dato * 23.673154;

                    usd.setText(""+usdValue);
                    hnl.setText(""+hnlValue);
                    eur.setText(""+eurValue);
                    }
                    else
                        if(index==1){
                            valor = Currencytxt.getText().toString();
                            dato = Double.parseDouble(valor);
                            eurValue = dato * 1;
                            hnlValue = dato * 29.1905;
                            usdValue = dato * 1.2330;

                            usd.setText(""+usdValue);
                            hnl.setText(""+hnlValue);
                            eur.setText(""+eurValue);

                                    }
                                    else
                                        if(index==2){

                                            valor = Currencytxt.getText().toString();
                                            dato = Double.parseDouble(valor);
                                            hnlValue = dato * 1;
                                            eurValue = dato * 0.03425;
                                            usdValue = dato * 0.0422;

                                            usd.setText(""+usdValue);
                                            hnl.setText(""+hnlValue);
                                            eur.setText(""+eurValue);
                                        }



            }
        });
    }



  /*  public class calculate extends AsyncTask<String, String, String[]>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(String... strings) {
            if (index == 0) {
                String uRl;
                try {
                    uRl = getJson("https://openexchangerates.org/api/latest.json?app_id=28530d297b9441f4a921767a4f9d388a&base=USD&symbols=HNL%2CEUR");
                    JSONObject USDtojObj;
                    USDtojObj = new JSONObject(uRl);

                    JSONArray rateArray = USDtojObj.getJSONObject("rates").getJSONArray("rates");
                    resultado[0] = rateArray.getJSONObject(0).getString("rates");
                    resultado[1] = rateArray.getJSONObject(1).getString("rates");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (index == 1) {
                String uRl;
                try {
                    uRl = getJson("https://openexchangerates.org/api/latest.json?app_id=28530d297b9441f4a921767a4f9d388a&base=EUR&symbols=USD%2CHNL");
                    JSONObject EurotojObj;
                    EurotojObj = new JSONObject(uRl);

                    JSONArray rateArray = EurotojObj.getJSONObject("rates").getJSONArray("rates");
                    resultado[0] = rateArray.getJSONObject(0).getString("rates");
                    resultado[1] = rateArray.getJSONObject(1).getString("rates");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (index == 2) {
                String uRl;
                try {
                    uRl = getJson("https://openexchangerates.org/api/latest.json?app_id=28530d297b9441f4a921767a4f9d388a&base=HNL&symbols=USD%2CEUR");
                    JSONObject HNLtojObj;
                    HNLtojObj = new JSONObject(uRl);

                    JSONArray rateArray = HNLtojObj.getJSONObject("rates").getJSONArray("rates");
                    resultado[0] = rateArray.getJSONObject(0).getString("rates");
                    resultado[1] = rateArray.getJSONObject(1).getString("rates");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return resultado;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            if(index == 0){
                double usdtoeuroval, usdtoinrval, usdtoeuroinp, usdtoinrinp, usdtousdinp;
                usdtousdinp = inputValue * 1;
                usd.setText(""+usdtousdinp);

                usdtoeuroval = Double.parseDouble(resultado[0]);
                usdtoeuroinp = inputValue * usdtoeuroval;
                eur.setText(""+usdtoeuroinp);

                usdtoinrval = Double.parseDouble(resultado[1]);
                usdtoinrinp = inputValue * usdtoinrval;
                hnl.setText(""+usdtoinrinp);
            }else if(index == 1){
                double eurotousdval, eurotoinrval, eurotousdinp, eurotoinrinp, eurotoeuroinp;
                eurotoeuroinp = inputValue * 1;
                eur.setText(""+eurotoeuroinp);

                eurotousdval = Double.parseDouble(resultado[0]);
                eurotousdinp = inputValue * eurotousdval;
                usd.setText(""+eurotousdinp);

                eurotoinrval = Double.parseDouble(resultado[1]);
                eurotoinrinp = inputValue * eurotoinrval;
                hnl.setText(""+eurotoinrinp);
            }else if(index == 2){
                double inrtousdval, inrtoeuroval, inrtousdinp, inrtoeuroinp, inrtoinrinp;

                inrtoinrinp = inputValue * 1;
                hnl.setText(""+inrtoinrinp);

                inrtousdval = Double.parseDouble(resultado[0]);
                inrtousdinp = inputValue * inrtousdval;
                usd.setText(""+inrtousdinp);

                inrtoeuroval = Double.parseDouble(resultado[1]);
                inrtoeuroinp = inputValue * inrtoeuroval;
                eur.setText(""+inrtoeuroinp);
            }
        }

        public String getJson(String url) throws ClientProtocolException, IOException {

            StringBuilder build = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String con;
            while ((con = reader.readLine()) != null) {
                build.append(con);
            }
            return build.toString();
        }

    }
    */
}
