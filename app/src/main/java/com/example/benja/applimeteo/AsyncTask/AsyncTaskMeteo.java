package com.example.benja.applimeteo.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.benja.applimeteo.Modele.City;
import com.example.benja.applimeteo.Modele.MeteoData;
import com.example.benja.applimeteo.Modele.PartOfDay;
import com.example.benja.applimeteo.Modele.PeriodeID;
import com.example.benja.applimeteo.Modele.Prevision;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by benja on 26/03/2017.
 */

public class AsyncTaskMeteo extends AsyncTask<String, Void, City> {
    final String baseURL = "http://api.meteorologic.net/rssid?id=";

    @Override
    protected City doInBackground(String... params) {
        String nomVille = params[0];
        String id = params[1];
        City ville = new City(nomVille, id);
        try {
            URL url = new URL(baseURL + id);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(urlConnection.getInputStream(), "UTF-8");
                while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType() == XmlPullParser.START_TAG) {
                        if (parser.getName().equals("item")) {
                            ville.addPrevision(readPrevision(parser));
                        }
                    }
                    parser.next();
                }
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("PARSING ERROR", Log.getStackTraceString(e));
        }
        return ville;
    }

    @Override
    protected void onPostExecute(City ville) {
        super.onPostExecute(ville);
        MeteoData.getInstance().addVille(ville);
    }

    /**
     * Méthode utilisée pour lire une prévision depuis le parser Xml
     * @param parser : Envoyer le parser situé au tag "item"
     * @return {@link Prevision}
     * @throws XmlPullParserException : au cas ou le parser ne rencontre pas les conditions START TAG et "item"
     * @throws IOException : au cas ou le parser ne rencontre pas les conditions START TAG et "item"
     */
    private Prevision readPrevision(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "item");
        String description = "";
        String jour = "";
        ArrayList<PartOfDay> periodes = new ArrayList<>();

        PartOfDay matin = new PartOfDay(); matin.setPeriodeId(PeriodeID.MATIN);
        PartOfDay midi = new PartOfDay(); midi.setPeriodeId(PeriodeID.MIDI);
        PartOfDay apmidi = new PartOfDay(); apmidi.setPeriodeId(PeriodeID.APRES_MIDI);
        PartOfDay soir = new PartOfDay(); soir.setPeriodeId(PeriodeID.SOIR);

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("description")) {
                description = readPrevisionDescription(parser);
            } else if (name.equals("jour")) {
                jour = readPrevisionJour(parser);
            } else {
                if (name.endsWith("matin")) {
                    preciserPeriode(parser, matin);
                } else if (name.endsWith("apmidi")) {
                    preciserPeriode(parser, apmidi);
                } else if (name.endsWith("midi")) {
                    preciserPeriode(parser, midi);
                } else if (name.endsWith("soir")) {
                    preciserPeriode(parser, soir);
                } else {
                    skip(parser);
                }
            }
        }

        periodes.add(matin);
        periodes.add(midi);
        periodes.add(apmidi);
        periodes.add(soir);

        return new Prevision(jour, description, periodes);
    }

    /**
     * Méthode utilisée pour lire le tag description d'une {@link Prevision}
     * @param parser
     * @return un string contenant la description
     * @throws XmlPullParserException : au cas ou le parser ne rencontre pas les conditions START TAG et "description"
     * @throws IOException : : au cas ou le parser ne rencontre pas les conditions START TAG et "description"
     */
    private String readPrevisionDescription(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "description");
        String description = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "description");
        return description;
    }

    /**
     * Méthode utilisée pour lire le tag jour d'une {@link Prevision}
     * @param parser
     * @return un string contenant le jour de la prevision
     * @throws XmlPullParserException : au cas ou le parser ne rencontre pas les conditions START TAG et "jour"
     * @throws IOException : au cas ou le parser ne rencontre pas les conditions START TAG et "jour"
     */
    private String readPrevisionJour(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "jour");
        String jour = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "jour");
        return jour;
    }

    /**
     * Lit le texte d'un tag. Méthode fournie par la documentation Android sur le parsage Xml
     * @param parser
     * @return le texte du tag.
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    /**
     * Passe un tag inutile. Méthode fournie par la documentation Android sur le parsage Xml
     * @param parser
     * @throws IOException
     * @throws XmlPullParserException
     */
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    /**
     * Précise la période en lui rajoutant une information
     * @param parser positionné sur le tag correspondant à une des variables de la période (picto, tempe, precipitations, vent, seeing)
     * @param periode : la période à préciser
     * @throws XmlPullParserException
     * @throws IOException
     */
    private void preciserPeriode(XmlPullParser parser, PartOfDay periode) throws XmlPullParserException, IOException {
        if (parser.getName().startsWith("pictos")) {
            periode.setPictoId(readText(parser));
        } else if (parser.getName().startsWith("tempe")) {
            periode.setTemp(readText(parser));
        } else if (parser.getName().startsWith("precipitations")) {
            periode.setPrecipitation(readText(parser));
        } else if (parser.getName().startsWith("vent")) {
            periode.setVent(readText(parser));
        } else if (parser.getName().startsWith("seeing")) {
            periode.setSeeing(readText(parser));
        } else {
            skip(parser);
        }
    }
}
