package com.apagao.service;

import com.apagao.model.Endereco;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class LocalSeguroService {

    public String[] obterCoordenadas(Endereco endereco) {
        String query = endereco.getLogradouro() + ", " + endereco.getLocalidade() + ", " + endereco.getUf();
        String url = "https://nominatim.openstreetmap.org/search?q=" + query.replace(" ", "+") + "&format=json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Mozilla/5.0")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArray = new JSONArray(response.body());

            if (jsonArray.length() > 0) {
                JSONObject obj = jsonArray.getJSONObject(0);
                String lat = obj.getString("lat");
                String lon = obj.getString("lon");
                return new String[]{lat, lon};
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String buscarHospitaisProximos(String lat, String lon) {
        return buscarLocaisPorAmenity(lat, lon, "hospital");
    }

    public String buscarDelegaciasProximas(String lat, String lon) {
        return buscarLocaisPorAmenity(lat, lon, "police");
    }

    public String buscarAbrigosProximos(String lat, String lon) {
        return buscarLocaisPorAmenity(lat, lon, "shelter");
    }

    private String buscarLocaisPorAmenity(String lat, String lon, String tipoAmenity) {
        String query = String.format(
                "[out:json];\n" +
                        "node\n" +
                        "  [amenity=%s]\n" +
                        "  (around:1500,%s,%s);\n" +
                        "out;", tipoAmenity, lat, lon);

        String url = "https://overpass-api.de/api/interpreter";

        Map<String, String> nomeAmenityPT = new HashMap<>();
        nomeAmenityPT.put("hospital", "Hospitais");
        nomeAmenityPT.put("police", "Delegacias");
        nomeAmenityPT.put("shelter", "Abrigos");

        StringBuilder resultado = new StringBuilder();
        String nomeExibicao = nomeAmenityPT.getOrDefault(tipoAmenity, tipoAmenity);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("data=" + query))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());
            JSONArray elementos = json.getJSONArray("elements");

            if (elementos.length() == 0) {
                resultado.append(String.format("Nenhum(a) %s encontrado(a) nas proximidades.\n", nomeExibicao.toLowerCase()));
            } else {
                resultado.append(String.format("%s encontrados(as) nas proximidades:\n", nomeExibicao));
                for (int i = 0; i < elementos.length(); i++) {
                    JSONObject elem = elementos.getJSONObject(i);
                    JSONObject tags = elem.optJSONObject("tags");
                    if (tags != null && tags.has("name")) {
                        resultado.append("- ").append(tags.getString("name")).append("\n");
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            resultado.append("Erro ao buscar ").append(nomeExibicao.toLowerCase()).append(".\n");
            e.printStackTrace();
        }

        return resultado.toString();
    }
}
